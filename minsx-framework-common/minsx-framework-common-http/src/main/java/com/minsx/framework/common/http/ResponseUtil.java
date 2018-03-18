package com.minsx.framework.common.http;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static <T> void responseJson(HttpServletResponse httpServletResponse, Integer status, Object body) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setStatus(status);
        httpServletResponse.getWriter().write(JSON.toJSONString(body));
        httpServletResponse.getWriter().flush();
        httpServletResponse.getWriter().close();
    }

    public static void responseJson(ServletResponse response, Object data) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(data));
        response.getWriter().flush();
        response.getWriter().close();
    }


}
