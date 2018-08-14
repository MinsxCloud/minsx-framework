/*
 *
 *  * Copyright 2017 https://www.minsx.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.framework.common.shell.core;

import com.minsx.framework.common.shell.builder.*;
import com.minsx.framework.common.shell.process.ProcessInfo;
import com.minsx.framework.common.shell.process.ProcessInfoManager;
import com.minsx.framework.common.shell.process.ProcessManager;

import java.io.*;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DefaultShell implements Shell {

    private String command;
    private String inPath;
    private String[] environments;
    private String charset = "UTF-8";
    private int bufferSize = 20480;
    private boolean logged = false;
    private boolean sync = false;
    private Process process;
    private Operator operator;
    private static Builder builder;
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private WhetherSuccess whetherSuccess;
    private boolean result = false;
    private List<Callback> outCallback = new LinkedList<>();
    private List<Callback> errCallback = new LinkedList<>();
    private List<ExceptionCallback> exceptionCallback = new LinkedList<>();
    private List<Consumer<Operator>> preHandler = new LinkedList<>();
    private List<PostCallback> postHandler = new LinkedList<>();

    static {
        switch (OSType.current) {
            case WINDOWS:
                builder = new WindowsBuilder();
                break;
            case MAC:
                builder = new MacBuilder();
                break;
            case LINUX:
                builder = new LinuxBuilder();
                break;
            case UNIX:
            default:
                builder = new UnixBuilder();
                break;
        }
    }

    public static Shell build(String command) {
        return new DefaultShell(command);
    }

    public static Shell buildText(String shellText) {
        return builder.build(shellText);
    }

    public static Shell buildText(String shellText, String shellPath, boolean autoDelete) {
        return builder.build(shellText, shellPath, autoDelete);
    }

    public DefaultShell(String command) {
        this.command = command;
        this.charset = OSType.current.charset;
        operator = new Operator() {
            @Override
            public void stop() {
                process.destroyForcibly();
            }

            @Override
            public void send(String msg) {
                try {
                    process.getOutputStream().write(msg.getBytes());
                    process.getOutputStream().flush();
                } catch (IOException e) {
                    handleException(e);
                }
            }
        };
    }

    @Override
    public void run() {
        try {
            process = Runtime.getRuntime().exec(command, environments, inPath == null ? null : new File(inPath));
            preHandler.forEach(consumer -> consumer.accept(operator));
            execute(process);
            if (sync) {
                process.waitFor();
                handleAfterFinish(process);
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public boolean isRunning() {
        return process != null && process.isAlive();
    }

    @Override
    public int exitCode() {
        check("run", "complete");
        return process.exitValue();
    }

    @Override
    public boolean isSuccess() {
        check("run", "complete");
        return result;
    }

    @Override
    public void stop() {
        check("run");
        ProcessManager.stopProcessTree(process);
        process.destroyForcibly();
        executorService.shutdownNow();
    }

    @Override
    public Integer getProcessId() {
        check("run");
        return ProcessInfoManager.getProcessId(process);
    }

    @Override
    public ProcessInfo getProcessInfoTree() {
        check("run");
        return ProcessInfoManager.getProcessInfoTree(process);
    }

    private void execute(Process process) {
        executorService.execute(() -> {
            InputStream is = process.getInputStream();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(is, Charset.forName(charset)), bufferSize);
                String str;
                while ((str = in.readLine()) != null) {
                    final String line = str;
                    outCallback.forEach(consumer -> consumer.accept(line, operator));
                    if (logged) System.out.println(str);
                }
                in.close();
                if (!sync) {
                    handleAfterFinish(process);
                }
            } catch (Exception e) {
                handleException(e);
            }
        });
        executorService.execute(() -> {
            InputStream es = process.getErrorStream();
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(es, Charset.forName(charset)), bufferSize);
                String str;
                while ((str = in.readLine()) != null) {
                    final String line = str;
                    errCallback.forEach(consumer -> consumer.accept(line, operator));
                    if (logged) System.err.println(str);
                }
                in.close();
            } catch (Exception e) {
                handleException(e);
            }
        });
    }

    private void handleAfterFinish(Process process) {
        postHandler.forEach(consumer -> consumer.accept(process.exitValue()));
        this.result = whetherSuccess == null ? (process.exitValue() == 0) : whetherSuccess.accept(process.exitValue());
    }

    private void handleException(Exception e) {
        if (exceptionCallback != null && exceptionCallback.size() > 0) {
            exceptionCallback.forEach(consumer -> consumer.accept(e));
        } else {
            throw new ExecuteException(e);
        }
    }

    public void check(String... items) {
        for (String item : items) {
            if (item.equals("run")) {
                if (process == null) handleException(new ExecuteException("the process has not been run yet"));
            } else if (item.equals("complete")) {
                if (isRunning()) handleException(new ExecuteException("the process has not been completed yet"));
            }
        }
    }

    @Override
    public Shell command(String command) {
        this.command = command;
        return this;
    }

    @Override
    public Shell inPath(String inPath) {
        this.inPath = inPath;
        return this;
    }

    @Override
    public Shell environments(String[] environments) {
        this.environments = environments;
        return this;
    }

    @Override
    public Shell charset(String charset) {
        this.charset = charset;
        return this;
    }

    @Override
    public Shell bufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    @Override
    public Shell logged(boolean logged) {
        this.logged = logged;
        return this;
    }

    @Override
    public Shell sync(boolean sync) {
        this.sync = sync;
        return this;
    }

    @Override
    public Shell onOut(Callback outCallback) {
        this.outCallback.add(outCallback);
        return this;
    }

    @Override
    public Shell onErr(Callback errCallback) {
        this.errCallback.add(errCallback);
        return this;
    }

    @Override
    public Shell onException(ExceptionCallback exceptionCallback) {
        this.exceptionCallback.add(exceptionCallback);
        return this;
    }

    @Override
    public Shell preHandler(Consumer<Operator> preHandler) {
        this.preHandler.add(preHandler);
        return this;
    }

    @Override
    public Shell postHandler(PostCallback postCallback) {
        this.postHandler.add(postCallback);
        return this;
    }

    @Override
    public Shell whetherSuccess(WhetherSuccess whetherSuccess) {
        this.whetherSuccess = whetherSuccess;
        return this;
    }

}
