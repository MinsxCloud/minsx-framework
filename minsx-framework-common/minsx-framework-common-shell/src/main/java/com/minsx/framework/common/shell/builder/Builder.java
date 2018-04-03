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

import com.minsx.framework.common.shell.core.Shell;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public interface Builder {

    default Shell build(String shellText) throws BuilderException {
        Long timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        String shellPath = String.format("%s/%s%s", System.getProperty("user.dir"), timestamp.toString(), OSType.current.extensionName);
        return build(shellText, shellPath,true);
    }

    default Shell builderWithDelete(String command, String shellPath, boolean autoDelete) throws BuilderException {
        return Shell.build(command).postHandler(consumer -> {
            File shell = new File(shellPath);
            if (autoDelete && shell.exists()) shell.delete();
        });
    }

    Shell build(String shellText, String shellPath,boolean autoDelete) throws BuilderException;

}
