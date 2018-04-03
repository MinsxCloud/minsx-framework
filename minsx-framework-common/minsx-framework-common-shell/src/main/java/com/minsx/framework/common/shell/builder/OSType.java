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
package com.minsx.framework.common.shell.builder;

public enum OSType {

    WINDOWS("gbk", ".bat"), LINUX("utf-8", ".sh"), MAC("utf-8", ".sh"), UNIX("utf-8", "");

    public final String charset;
    public final String extensionName;
    public static OSType current;

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        OSType current = null;
        if (osName.contains("win")) {
            current = OSType.WINDOWS;
        } else if (osName.contains("linux")) {
            current = OSType.LINUX;
        } else if (osName.contains("mac")) {
            current = OSType.MAC;
        } else if (osName.contains("unix")) {
            current = OSType.UNIX;
        }
        OSType.current = current;
    }

    OSType(String charset, String extensionName) {
        this.charset = charset;
        this.extensionName = extensionName;
    }
}
