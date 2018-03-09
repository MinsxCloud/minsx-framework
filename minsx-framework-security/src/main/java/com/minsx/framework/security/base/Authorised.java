package com.minsx.framework.security.base;

import java.io.Serializable;

public interface Authorised extends Serializable {

    String getType();

    String getValue();

}
