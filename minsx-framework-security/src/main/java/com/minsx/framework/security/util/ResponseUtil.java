package com.minsx.framework.security.util;

import com.alibaba.fastjson.JSON;
import org.springframework.http.ResponseEntity;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static <T> void responseJson(HttpServletResponse httpServletResponse, ResponseEntity<T> responseEntity) throws IOException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        httpServletResponse.setStatus(responseEntity.getStatusCodeValue());
        httpServletResponse.getWriter().write(JSON.toJSONString(responseEntity.getBody()));
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
