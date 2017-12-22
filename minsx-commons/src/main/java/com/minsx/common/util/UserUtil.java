package com.minsx.common.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
