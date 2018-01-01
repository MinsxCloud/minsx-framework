package com.minsx.core.system.service.implement;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.minsx.core.common.entity.system.User;
import com.minsx.core.system.service.api.SystemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class SystemServiceImpl implements SystemService {


    /**
     * 该方法用于后台取Token
     */
    public Map<String, Object> getToken(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("isSuccess", false);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String accessId = request.getParameter("accessId");
        String accessSecret = request.getParameter("accessSecret");
        if (username == null || password == null) {
            username = accessId;
            password = accessSecret;
        }
        if (username == null || password == null) {
            result.put("reason", "Parameter Error");
            return result;
        }
        HttpResponse<String> response;
        try {
            String url = "http://localhost:8080/loginServer/oauth/token?grant_type=password&username=%s&password=%s";
            response = Unirest.post(String.format(url, username, password))
                    .header("authorization", "Basic Z29vZHNhdmU6Z29vZHNhdmU=")
                    .header("cache-control", "no-cache")
                    .asString();
            if (response.getStatus() >= 200 && response.getStatus() < 400) {
                result.put("isSuccess", true);
                result.put("access_token", JSON.parseObject(response.getBody()).get("access_token"));
                result.put("token_type", JSON.parseObject(response.getBody()).get("token_type"));
                result.put("refresh_token", JSON.parseObject(response.getBody()).get("refresh_token"));
                result.put("expires_in", JSON.parseObject(response.getBody()).get("expires_in"));
                result.put("scope", JSON.parseObject(response.getBody()).get("scope"));
            } else {
                result.put("reason", "username or password failed");
            }
        } catch (UnirestException e) {
            e.printStackTrace();
            result.put("isSuccess", false);
            result.put("reason", "inner error");
        }
        return result;
    }

    @Override
    public ResponseEntity<?> getSystemInfo() {
        List<Map<String, String>> systemInfos = new ArrayList<>();
        Map<String, String> systemInfo = new LinkedHashMap<>();
        systemInfo.put("Minsx Cloud 系统版本", "v1.0.0");
        systemInfo.put("服务器系统", "Windows 10 Enterprise");
        systemInfo.put("服务器软件", "Apache");
        systemInfo.put("服务器数据库版本", "Mysql 5.6");
        systemInfo.put("当前数据库尺寸", "7.82 MB");
        systemInfo.forEach((key, value) -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("key", key);
            item.put("value", value);
            systemInfos.add(item);
        });
        return new ResponseEntity<>(systemInfos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getDeveloperTeams() {
        List<Map<String, String>> developerTeams = new ArrayList<>();
        Map<String, String> developerTeam = new LinkedHashMap<>();
        developerTeam.put("版权所有", "米斯云平台");
        developerTeam.put("产品设计与研发团队", "Minsx Cloud Team");
        developerTeam.put("界面与用户体验团队", "Minsx Cloud Team");
        developerTeam.put("相关链接", "https://www.minsx.com/\thttps://github.com/MinsxCloud");
        developerTeam.forEach((key, value) -> {
            Map<String, String> item = new LinkedHashMap<>();
            item.put("key", key);
            item.put("value", value);
            developerTeams.add(item);
        });
        return new ResponseEntity<>(developerTeams, HttpStatus.OK);
    }
}