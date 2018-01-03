package com.minsx.core.system.service.api;

import org.springframework.http.ResponseEntity;

public interface MenuService {

    ResponseEntity<?> getLeftMenus(Integer parentMenuId);

    ResponseEntity<?> getTopMenus();
}
