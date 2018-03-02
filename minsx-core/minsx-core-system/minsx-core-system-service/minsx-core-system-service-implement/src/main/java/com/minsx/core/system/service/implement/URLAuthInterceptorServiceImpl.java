package com.minsx.core.system.service.implement;

import com.minsx.common.util.UserUtil;
import com.minsx.core.system.service.api.URLAuthInterceptorService;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 对所有URL进行拦截并验证用户是否具有该接口权限
 */
@Service
public class URLAuthInterceptorServiceImpl implements URLAuthInterceptorService {

    private final static AntPathMatcher APM = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Boolean passed = false;
        List<String> auths = UserUtil.getCurrentUserAuths();
        for (String auth : auths) {
            String authType = auth.substring(0, auth.indexOf(":"));
            if ("URL".equals(authType)) {
                String method = auth.substring(auth.indexOf(":")+1, auth.lastIndexOf(":"));
                if (httpServletRequest.getMethod().equalsIgnoreCase(method)||"ALL".equalsIgnoreCase(method)) {
                    String regex = auth.substring(auth.lastIndexOf(":")+1, auth.length());
                    Boolean isMatched = APM.match(regex, httpServletRequest.getRequestURI());
                    if (isMatched) {
                        passed = true;
                        break;
                    }
                }
            }
        }
        if (!passed) {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setStatus(403);
            httpServletResponse.getWriter().write("权限不足");
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
        }
        return passed;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
