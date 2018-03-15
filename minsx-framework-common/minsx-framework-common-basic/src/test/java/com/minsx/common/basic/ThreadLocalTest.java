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

import com.alibaba.fastjson.JSON;
import com.minsx.framework.common.basic.ThreadLocalUtil;
import org.junit.Test;

public class ThreadLocalTest {

    public class Student {
        private String name;

        public Student(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void testThreadLocal() {
        ThreadLocalUtil.put("1", new Student("小张"));
        Student student = ThreadLocalUtil.get("1");
        System.out.println(JSON.toJSONString(student));
    }

    @Test
    public void testAll() {

        new Thread(() -> {
            ThreadLocalUtil.put("1", new Student("小张"));
        }).start();

        new Thread(() -> {
            ThreadLocalUtil.put("2", new Student("小红"));
        }).start();

        new Thread(() -> {
            Student student = ThreadLocalUtil.get("2");
            System.out.println(JSON.toJSONString(student));
        }).start();

    }



}
