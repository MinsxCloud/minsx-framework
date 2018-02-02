package com.minsx.core.system.service.implement;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.minsx.common.constant.Constant;
import com.minsx.common.util.DateUtil;
import com.minsx.common.util.SystemUtil;
import com.minsx.core.common.entity.ordinary.User;
import com.minsx.core.system.service.api.SystemService;
import org.hyperic.sigar.CpuInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class SystemServiceImpl implements SystemService {

    private final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final DecimalFormat DF = new DecimalFormat("######0.0");

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
        systemInfo.put("Minsx Cloud 系统版本", Constant.SYSTEM_VERSION);
        systemInfo.put("服务器系统", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        CpuInfo cpuInfo = SystemUtil.getCpuInfo();
        systemInfo.put("处理器核心数", String.valueOf(cpuInfo.getTotalCores()) + " Core");
        systemInfo.put("处理器频率", String.valueOf(cpuInfo.getMhz()) + " Mhz");
        systemInfo.put("服务器用户", System.getProperty("user.name"));
        systemInfo.put("服务器时间", SDF.format(new Date()));
        systemInfo.put("系统运行目录", Constant.SYSTEM_PATH);
        systemInfo.put("系统运行时间", DateUtil.formatMilliSecond(new Date().getTime() - Constant.SYSTEM_START_TIME.getTime()));
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