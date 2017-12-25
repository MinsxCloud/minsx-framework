package com.minsx.core.user.service.api;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface SystemService {

    public Map<String,Object> getToken(HttpServletRequest request);

}
