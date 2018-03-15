package com.minsx.framework.common.basic;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public final class StringUtil {

    public static String inputStreamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 获取URL中参数值
     *
     * @param urlParams URL参数
     * @param key       参数名
     * @return 参数值
     */
    public static String getParamFromUrl(String urlParams, String key) {
        String value = null;
        if (urlParams != null && key != null) {
            if (urlParams.contains("http") && urlParams.contains("?")) {
                urlParams = urlParams.substring(urlParams.indexOf("?") + 1, urlParams.length());
            }
            String[] kvs = urlParams.split("&");
            for (String kv : kvs) {
                String k = kv.substring(0, kv.indexOf("="));
                if (key.equalsIgnoreCase(k)) {
                    value = kv.substring(kv.indexOf("=") + 1, kv.length());
                    break;
                }
            }

        }
        return value;
    }

    /**
     * URL参数到MAP
     *
     * @param urlParams URL参数
     * @return MAP
     */
    public static Map<String, String> parseUrlParams(String urlParams) {
        if (urlParams == null) {
            return null;
        }
        if (urlParams.contains("http") && urlParams.contains("?")) {
            urlParams = urlParams.substring(urlParams.indexOf("?") + 1, urlParams.length());
        }
        Map<String, String> result = new LinkedHashMap<>();
        String[] kvs = urlParams.split("&");
        for (String kv : kvs) {
            String k = kv.substring(0, kv.indexOf("="));
            String v = kv.substring(kv.indexOf("=") + 1, kv.length());
            result.put(k, v);
        }
        return result;
    }


}
