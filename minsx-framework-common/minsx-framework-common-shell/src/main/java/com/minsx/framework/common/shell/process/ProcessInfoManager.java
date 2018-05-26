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

import com.minsx.framework.common.shell.core.ProcessPIDGetter;
import com.minsx.framework.common.shell.core.Shell;
import com.sun.jna.Native;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ProcessInfoManager {

    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss.SSSSSS");

    public static Integer getProcessId(Process process) {
        Integer processId = null;
        try {
            Field field = process.getClass().getDeclaredField("handle");
            field.setAccessible(true);
            long fieldId = (Long) field.get(process);
            ProcessPIDGetter pidGetter = Native.loadLibrary("kernel32", ProcessPIDGetter.class);
            processId = (int) pidGetter.GetProcessId(fieldId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new GetProcessInfoException("", e);
        }
        return processId;
    }

    public static ProcessInfo getSingleProcessInfo(Process process) {
        Integer processId = getProcessId(process);
        return getProcessInfo(processId);
    }

    public static ProcessInfo getSingleProcessInfo(Integer processId) {
        return getProcessInfo(processId);
    }

    public static ProcessInfo getProcessInfoTree(Process process) {
        ProcessInfo main = getSingleProcessInfo(process);
        completeProcessInfoTree(main);
        return main;
    }

    public static ProcessInfo getProcessInfoTree(Integer processId) {
        ProcessInfo main = getSingleProcessInfo(processId);
        completeProcessInfoTree(main);
        return main;
    }

    public static List<ProcessInfo> getSubProcessInfos(Process parentProcess) {
        Integer processId = getProcessId(parentProcess);
        return getSubProcessInfos(processId);
    }

    public static List<ProcessInfo> getSubProcessInfos(ProcessInfo parentProcessInfo) {
        return getSubProcessInfos(parentProcessInfo.getProcessId());
    }

    public static List<ProcessInfo> getSubProcessInfos(Integer parentProcessId) {
        return getProcessIds(true, parentProcessId);
    }

    private static void completeProcessInfoTree(ProcessInfo parentProcessInfo) {
        if (parentProcessInfo == null) return;
        List<ProcessInfo> subProcessInfos = getSubProcessInfos(parentProcessInfo);
        subProcessInfos.forEach(ProcessInfoManager::completeProcessInfoTree);
        parentProcessInfo.setChild(subProcessInfos);
    }

    private static ProcessInfo getProcessInfo(Integer processId) {
        List<ProcessInfo> processInfos = getProcessIds(false, processId);
        return processInfos.size() > 0 ? processInfos.get(0) : null;
    }

    private static List<ProcessInfo> getProcessIds(Boolean isParentProcessId, Integer processId) {
        List<ProcessInfo> processInfos = new ArrayList<>();
        StringBuffer content = new StringBuffer();
        StringBuffer lastLine = new StringBuffer();
        Shell.build("wmic process where (" + (isParentProcessId ? "ParentProcessId" : "ProcessId") + "=" + processId + ") get ProcessId,ParentProcessId,Name,CommandLine,ExecutablePath,Description,CreationDate /value")
                .sync(true).onOut((line, operator) -> {
            if (!line.isEmpty() && !line.contains("没有可用实例")) {
                if (lastLine.toString().contains("ProcessId=") && !lastLine.toString().contains("ParentProcessId")) {
                    content.append("#MINSX_SPLIT_PROCESS#");
                }
                content.append(line).append("#MINSX_SPLIT_FIELD#");
                lastLine.delete(0, lastLine.length());
                lastLine.append(line);
            }
        }).run();

        if (content.toString().isEmpty()) return processInfos;
        String[] processInfosStr = content.toString().split("#MINSX_SPLIT_PROCESS#");
        for (String processInfoStr : processInfosStr) {
            String[] processFields = processInfoStr.split("#MINSX_SPLIT_FIELD#");
            ProcessInfo process = new ProcessInfo();
            for (String fieldStr : processFields) {
                setProcessInfoField(process, fieldStr);
            }
            processInfos.add(process);
        }
        return processInfos;
    }

    private static void setProcessInfoField(ProcessInfo processInfo, String fieldStr) {
        if (fieldStr.startsWith("CommandLine")) {
            processInfo.setCommandLine(getFieldValue(fieldStr));
        } else if (fieldStr.startsWith("CreationDate")) {
            String fieldValues = getFieldValue(fieldStr);
            fieldValues = fieldValues.substring(0, fieldValues.indexOf("+"));
            Date date = null;
            try {
                date = SDF.parse(fieldValues);
            } catch (ParseException e) {
                throw new GetProcessInfoException("Parse CreationDate str to date error", e);
            }
            processInfo.setCreationDate(date);
        } else if (fieldStr.startsWith("Description")) {
            processInfo.setDescription(getFieldValue(fieldStr));
        } else if (fieldStr.startsWith("ExecutablePath")) {
            processInfo.setExecutablePath(getFieldValue(fieldStr));
        } else if (fieldStr.startsWith("Name")) {
            processInfo.setName(getFieldValue(fieldStr));
        } else if (fieldStr.startsWith("ParentProcessId")) {
            processInfo.setParentProcessId(Integer.valueOf(getFieldValue(fieldStr)));
        } else if (fieldStr.startsWith("ProcessId")) {
            processInfo.setProcessId(Integer.valueOf(getFieldValue(fieldStr)));
        }
    }

    private static String getFieldValue(String fieldStr) {
        return fieldStr.substring(fieldStr.indexOf("=") + 1, fieldStr.length());
    }


}
