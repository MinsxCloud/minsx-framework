package com.minsx.framework.common.base;

import java.io.IOException;
import java.io.InputStream;

public class StringUtil {

    public static String inputStreamToString(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }


}
