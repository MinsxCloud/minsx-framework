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
package com.minsx.framework.common.basic;

import java.util.HashMap;
import java.util.Map;

public final class ThreadLocalUtil {

    private static final ThreadLocal<Map<String, Object>> data = ThreadLocal.withInitial(HashMap::new);

    public static Map<String, Object> getAll() {
        return data.get();
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        return (T) data.get().get(key);
    }

    public static <T> T put(String key, T value) {
        data.get().put(key, value);
        return value;
    }

    public static void remove(String key) {
        data.get().remove(key);
    }

    public static void clear() {
        data.remove();
    }

}
