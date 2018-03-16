package com.minsx.framework.security.core;

import java.io.Serializable;
import java.util.List;

/**
 * 用户
 */
public interface SecurityUser extends Serializable {

    String getUsername();

    String getPassword();

    Boolean isLocked();

    Boolean isEnabled();

    List<Role> getRoles();

    List<CustomAuthority> getCustomAuthorities();

    List<RequestAuthority> getRequestAuthorities();


}
