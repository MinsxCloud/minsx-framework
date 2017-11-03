package com.minsx.common.util;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;


public class HttpServletRequestUtil {
	
	public static void responseJson(HttpServletResponse response,Map<String, Object> keyAndValues) throws IOException {
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(JSON.toJSONString(keyAndValues));
		response.getWriter().flush();
		response.getWriter().close();
	}

}
