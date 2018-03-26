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

import com.minsx.framework.common.basic.StringUtil;
import com.minsx.framework.common.basic.executor.Executor;
import org.junit.Test;

import java.util.Arrays;

public class SimpleTest {

    @Test
    public void testSimple() {
        String value = StringUtil.getParamFromUrl("https://blog.csdn.net/article?name=goodsave&age=25&sex=man", "age");
        System.out.println(value);
        StringUtil.parseUrlParams("https://blog.csdn.net/article?name=goodsave&age=25&sex=man").forEach((k, v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void testExecutor() throws InterruptedException {
        Executor executor = new Executor();
        executor.script("java -jar minsx-authorization-starter-1.0.0.jar -Duser.timezone=GMT+08")
                .inPath("E:/Temp/ServerRunner/MsAuthServer")
                .charset("UTF-8")
                .bufferSize(20480)
                .sync(false)
                .output(false)
                .errorLineHandler(line -> {
                    System.out.println("ERROR: " + line);
                }).normalLineHandler(line -> {
                    System.out.println("NORMAL: " + line);
                }).execute();

        Thread.sleep(25000);
        executor.stop();
    }

}
