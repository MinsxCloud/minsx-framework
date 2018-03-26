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
package com.minsx.framework.common.basic.executor;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Executor {

    private String script;
    private String inPath;
    private String[] environments;
    private String charset = "UTF-8";
    private int bufferSize = 20480;
    private boolean output = false;
    private boolean sync = false;
    private ReadLineHandler normalLineHandler;
    private ReadLineHandler errorLineHandler;
    private Process process;
    private Thread normalThread;
    private Thread errorThread;

    public Executor() {
    }

    public Executor(String script) {
        this.script = script;
    }

    public Executor(String script, String[] environments, String inPath) {
        this.script = script;
        this.inPath = inPath;
        this.environments = environments;
    }

    public Executor(String script, String inPath, String charset, int bufferSize, boolean output, boolean sync, ReadLineHandler normalLineHandler, ReadLineHandler errorLineHandler) {
        this.script = script;
        this.inPath = inPath;
        this.charset = charset;
        this.bufferSize = bufferSize;
        this.output = output;
        this.sync = sync;
        this.normalLineHandler = normalLineHandler;
        this.errorLineHandler = errorLineHandler;
    }

    public Process execute() {
        try {
            process = Runtime.getRuntime().exec(script, environments, new File(inPath));
            final InputStream normal = process.getInputStream();
            final InputStream error = process.getErrorStream();
            errorThread = new Thread(() -> {
                read(error, errorLineHandler);
            });
            errorThread.start();
            if (sync) {
                read(normal, normalLineHandler);
            } else {
                normalThread = new Thread(() -> {
                    read(normal, normalLineHandler);
                });
                normalThread.start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return process;
    }

    @SuppressWarnings("unchecked")
    public Process stop() {
        if (normalThread != null && normalThread.isAlive()) {
            normalThread.stop();
        }
        if (errorThread != null && errorThread.isAlive()) {
            errorThread.stop();
        }
        if (process != null) {
            process.destroyForcibly();
        }
        return process;
    }

    private void read(InputStream inputStream, ReadLineHandler handler) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, Charset.forName(charset)), bufferSize);
            String str;
            while ((str = in.readLine()) != null) {
                if (handler != null) {
                    handler.handle(str);
                }
                if (output) {
                    System.out.println(str);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Executor script(String script) {
        this.script = script;
        return this;
    }

    public Executor inPath(String inPath) {
        this.inPath = inPath;
        return this;
    }

    public Executor environments(String[] environments) {
        this.environments = environments;
        return this;
    }

    public Executor charset(String charset) {
        this.charset = charset;
        return this;
    }

    public Executor bufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        return this;
    }

    public Executor output(boolean output) {
        this.output = output;
        return this;
    }

    public Executor sync(boolean sync) {
        this.sync = sync;
        return this;
    }

    public Executor normalLineHandler(ReadLineHandler normalLineHandler) {
        this.normalLineHandler = normalLineHandler;
        return this;
    }

    public Executor errorLineHandler(ReadLineHandler errorLineHandler) {
        this.errorLineHandler = errorLineHandler;
        return this;
    }

    public String getScript() {
        return script;
    }

    public String getInPath() {
        return inPath;
    }

    public String[] getEnvironments() {
        return environments;
    }

    public String getCharset() {
        return charset;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public boolean isOutput() {
        return output;
    }

    public boolean isSync() {
        return sync;
    }

    public ReadLineHandler getNormalLineHandler() {
        return normalLineHandler;
    }

    public ReadLineHandler getErrorLineHandler() {
        return errorLineHandler;
    }

    public Process getProcess() {
        return process;
    }

    public Thread getNormalThread() {
        return normalThread;
    }

    public Thread getErrorThread() {
        return errorThread;
    }
}
