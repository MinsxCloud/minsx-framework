/*
 *
 *  * Copyright 2018 https://www.minsx.com
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

import com.minsx.framework.common.shell.process.ProcessInfo;

import java.util.function.Consumer;

/**
 * Global Shell API
 * Used to manage shell's life cycle
 */
public interface Shell {

    /**
     * Create a Shell with the specified command
     *
     * @param command The command that needs to run can be a script,
     *                which can be the absolute path of a running file,
     *                and can bring operation parameters.
     * @return A number of instance of Shell
     */
    static Shell build(String command) {
        return DefaultShell.build(command);
    }

    /**
     * Create a Shell composed of multiple command lines
     *
     * @param shellText Command text content composed of multiple commands
     * @return A number of instance of Shell
     */
    static Shell buildText(String shellText) {
        return DefaultShell.buildText(shellText);
    }

    /**
     * Create a Shell composed of multiple command lines with custom shell path
     *
     * @param shellText  Command text content composed of multiple commands
     * @param shellPath  The path to save the Shell script file
     * @param autoDelete Whether the script file is automatically deleted after running
     * @return A number of instance of Shell
     */
    static Shell buildText(String shellText, String shellPath, boolean autoDelete) {
        return DefaultShell.buildText(shellText, shellPath, autoDelete);
    }

    /**
     * Start running shell in a process
     */
    void run();

    /**
     * Stop the current process and all the child processes
     */
    void stop();

    /**
     * Is the current shell executed and executed successfully
     * You can use [whetherSuccess] to implement custom whether successfully
     *
     * @return Whether shell is successful or not
     */
    boolean isSuccess();

    /**
     * See if the current shell is running
     *
     * @return Whether shell is running
     */
    boolean isRunning();

    /**
     * Getting the exit status of the current process
     * If the current process is not running or not running, ExecuteException will be thrown.
     *
     * @return Exit status code for the current process
     */
    int exitCode();

    /**
     * Get the ID of the current process
     * If the current process is not running, ExecuteException will be thrown.
     *
     * @return ID of the current process
     */
    Integer getProcessId();

    /**
     * Get the current process tree information
     *
     * @return current process tree information
     */
    ProcessInfo getProcessInfoTree();

    /**
     * The command to run at the current Shell
     *
     * @param command The command to run at the current Shell
     * @return A number of instance of Shell
     */
    Shell command(String command);

    /**
     * The path of the current Shell
     *
     * @param inPath The path for current Shell
     * @return A number of instance of Shell
     */
    Shell inPath(String inPath);

    /**
     * Environment for current Shell
     *
     * @param environments Environment for current Shell
     * @return A number of instance of Shell
     */
    Shell environments(String[] environments);

    /**
     * Environment for current Shell
     * Without a given case, windows is GBK by default, Linux and Unix are UTF8 by default.
     *
     * @param charset Environment for current Shell
     * @return A number of instance of Shell
     */
    Shell charset(String charset);

    /**
     * Buffer Size  for current Shell
     * Used to cache the output stream of current Shell
     *
     * @param bufferSize Buffer Size  for current Shell
     * @return A number of instance of Shell
     */
    Shell bufferSize(int bufferSize);

    /**
     * Whether output content of Shell at runtime
     *
     * @param logged Whether output content of Shell at runtime
     * @return A number of instance of Shell
     */
    Shell logged(boolean logged);

    /**
     * Whether run Shell in a synchronous way
     *
     * @param sync Whether run Shell in a synchronous way
     * @return A number of instance of Shell
     */
    Shell sync(boolean sync);

    /**
     * A custom callback for the standard output stream of Shell
     *
     * @param outCallback A custom callback
     * @return A number of instance of Shell
     */
    Shell onOut(Callback outCallback);

    /**
     * A custom callback for the error output stream of Shell
     *
     * @param errCallback A custom callback
     * @return A number of instance of Shell
     */
    Shell onErr(Callback errCallback);

    /**
     * A custom callback for the exception of Shell
     *
     * @param exceptionCallback A custom callback
     * @return A number of instance of Shell
     */
    Shell onException(ExceptionCallback exceptionCallback);

    /**
     * The callback before running the script
     *
     * @param preHandler A custom callback
     * @return A number of instance of Shell
     */
    Shell preHandler(Consumer<Operator> preHandler);

    /**
     * The callback after running the script
     *
     * @param postCallback A custom callback
     * @return A number of instance of Shell
     */
    Shell postHandler(PostCallback postCallback);

    /**
     * A custom callback is used to determine whether Shell runs and returns successfully
     *
     * @param whetherSuccess A custom callback
     * @return A number of instance of Shell
     */
    Shell whetherSuccess(WhetherSuccess whetherSuccess);

}
