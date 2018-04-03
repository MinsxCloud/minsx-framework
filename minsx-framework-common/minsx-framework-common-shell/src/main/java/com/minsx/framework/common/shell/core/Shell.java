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

import java.util.function.Consumer;

/**
 * Global shell master
 * Used to manage shell's life cycle
 */
public interface Shell {

    static Shell build(String command) {
        return DefaultShell.build(command);
    }

    static Shell buildText(String shellText) {
        return DefaultShell.buildText(shellText);
    }

    static Shell buildText(String shellText, String shellPath, boolean autoDelete) {
        return DefaultShell.buildText(shellText,shellPath,autoDelete);
    }

    /**
     * start running shell with process
     */
    void run();

    /**
     * Returns the exit value for the process
     * @return the exit value of the process represented by this
     *         {@code Process} object.  By convention, the value
     *         {@code 0} indicates normal termination.
     */
    int exitCode();

    /**
     * Tests whether the process is finished with success
     * @return {@code true} if the process is finished with success
     *         {@code Process} object has not yet terminated.
     */
    boolean isSuccess();

    /**
     * Tests whether the process represented by this {@code Process} is running.
     * @return {@code true} if the process represented by this
     *         {@code Process} object has not yet terminated.
     * @since 1.8
     */
    boolean isRunning();

    void stop();

    Shell command(String command);

    Shell inPath(String inPath);

    Shell environments(String[] environments);

    Shell charset(String charset);

    Shell bufferSize(int bufferSize);

    Shell logged(boolean logged);

    Shell sync(boolean sync);

    Shell onOut(Callback outCallback);

    Shell onErr(Callback errCallback);

    Shell onException(ExceptionCallback exceptionCallback);

    Shell preHandler(Consumer<Operator> preHandler);

    Shell postHandler(PostCallback postCallback);

    Shell whetherSuccess(WhetherSuccess whetherSuccess);

}
