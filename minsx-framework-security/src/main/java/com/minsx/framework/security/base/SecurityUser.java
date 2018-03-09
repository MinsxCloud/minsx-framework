package com.minsx.framework.security.base;

import java.io.Serializable;
import java.util.List;

/**
 * 用户身份
 */
public interface SecurityUser extends Serializable {

    String getUsername();

    String getPassword();

    Boolean isLocked();

    Boolean isEnabled();

    List<? extends Authorised> getAuthorities();

}
