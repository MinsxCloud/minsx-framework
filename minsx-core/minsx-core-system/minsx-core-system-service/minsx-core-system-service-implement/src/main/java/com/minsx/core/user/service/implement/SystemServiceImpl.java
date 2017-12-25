package com.minsx.core.user.service.implement;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.minsx.core.user.service.api.SystemService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemServiceImpl implements SystemService{

    @Override
    public Map<String,Object> getToken(HttpServletRequest request) {
    	Map<String,Object> result = new HashMap<String,Object>();
    	result.put("isSuccess",false);
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String accessId = request.getParameter("accessId");
    	String accessSecret = request.getParameter("accessSecret");
    	if (username==null||password==null) {
    	    username=accessId;
    	    password=accessSecret;
    	}
    	if (username==null||password==null) {
    	    result.put("reason","Parameter Error");
    	    return result;
    	}
        HttpResponse<String> response ;
		try {
			String url="http://localhost:8080/loginServer/oauth/token?grant_type=password&username=%s&password=%s";
			response = Unirest.post(String.format(url,username,password))
			  .header("authorization", "Basic Z29vZHNhdmU6Z29vZHNhdmU=")
			  .header("cache-control", "no-cache")
			  .asString();
			if (response.getStatus() >= 200 && response.getStatus() < 400) {
				result.put("isSuccess",true);
				result.put("access_token",JSON.parseObject(response.getBody()).get("access_token"));
				result.put("token_type",JSON.parseObject(response.getBody()).get("token_type"));
				result.put("refresh_token",JSON.parseObject(response.getBody()).get("refresh_token"));
				result.put("expires_in",JSON.parseObject(response.getBody()).get("expires_in"));
				result.put("scope",JSON.parseObject(response.getBody()).get("scope"));
			}else{
				result.put("reason","username or password failed");
			}
		} catch (UnirestException e) {
			e.printStackTrace();
			result.put("isSuccess",false);
			result.put("reason","inner error");
		}
        return result;
    }
}
