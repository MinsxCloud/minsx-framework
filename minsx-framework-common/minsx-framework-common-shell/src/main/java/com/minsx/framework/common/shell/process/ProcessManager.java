/*
 *
 *  * Copyright © 2017-2018 minsx.com All rights reserved
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
package com.minsx.framework.common.shell.process;

import com.minsx.framework.common.shell.core.Shell;

public final class ProcessManager {

    public static void stopProcessTree(Process process) {
        ProcessInfo processInfoTree = ProcessInfoManager.getProcessInfoTree(process);
        stopSubProcess(processInfoTree);
        stopProcess(processInfoTree.getProcessId());
    }

    public static void stopProcessTree(Integer processId) {
        ProcessInfo processInfoTree = ProcessInfoManager.getProcessInfoTree(processId);
        stopSubProcess(processInfoTree);
        stopProcess(processInfoTree.getProcessId());
    }


    public static void stopProcessTree(ProcessInfo processInfo) {
        stopSubProcess(processInfo);
        stopProcess(processInfo.getProcessId());
    }


    public static void stopSubProcess(ProcessInfo processInfo) {
        if (processInfo == null) return;
        processInfo.getChild().forEach(c -> {
            stopSubProcess(c);
            stopProcess(c.getProcessId());
        });
    }

    public static void stopProcess(Integer processId) {
        Shell.build(String.format("cmd /c taskkill /f /pid %s", processId)).sync(true).run();
    }


}
