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
package com.minsx.common.basic;

import com.minsx.framework.common.basic.executor.Executor;
import org.junit.Test;

import java.io.IOException;

public class ExecutorTest {

    @Test
    public void testExecutor() throws InterruptedException, IOException {
        Executor executor = new Executor();
        executor.script("E:/Temp/ServerRunner/MsAuthServer/echo.bat")
                .inPath("E:/Temp/ServerRunner/MsAuthServer")
                .charset("UTF-8")
                .bufferSize(20480)
                .sync(true)
                .output(false)
                .errorLineListener(line -> {
                    System.out.println("ERROR: " + line);
                }).normalLineListener(line -> {
                    System.out.println("NORMAL: " + line);
        }).execute();

    }

}
