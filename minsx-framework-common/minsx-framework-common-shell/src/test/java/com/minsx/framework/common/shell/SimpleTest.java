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
package com.minsx.framework.common.shell;

import com.minsx.framework.common.shell.core.Shell;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void RunShell() {
        Shell shell = Shell.build("E:/Temp/ServerRunner/MsAuthServer/echo.bat")
                .inPath("E:/Temp/ServerRunner/MsAuthServer")
                .charset("UTF-8")
                .logged(true)
                .sync(true)
                .onOut((line, operator) -> {
                    if (line.contains("hi")) {
                        operator.send("Joker");
                    }
                });
        shell.run();
        System.out.println(shell.exitCode());
    }

    @Test
    public void RunTextShell() throws InterruptedException {
        StringBuffer text = new StringBuffer("@echo off");
        text.append("\n").append("echo please input your name:");
        text.append("\n").append("set /p name=");
        text.append("\n").append("echo Your name is %name%");

        Shell.buildText(text.toString())
                .charset("UTF-8")
                .logged(true)
                .sync(true)
                .preHandler(c -> {
                    System.out.println("begin");
                })
                .onOut((line, operator) -> {
                    if (line.contains("please input your name")) {
                        //operator.stop();
                        operator.send("Joker");
                    }
                }).onErr((line, operator) ->{
                    System.err.println(line);
                }).postHandler(code -> {
                    System.out.println("end");
                }).whetherSuccess(code -> {
                    return code == 0;
                }).onException(e->{
                    System.out.println(e.getMessage());
                }).run();
    }


}
