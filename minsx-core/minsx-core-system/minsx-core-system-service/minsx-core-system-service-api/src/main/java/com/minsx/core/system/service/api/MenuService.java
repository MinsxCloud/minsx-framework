package com.minsx.core.system.service.api;

import com.minsx.core.common.entity.system.Menu;
import org.springframework.http.ResponseEntity;

public interface MenuService {

    ResponseEntity<?> getLeftMenus(Integer parentMenuId);

    ResponseEntity<?> getTopMenus();

    ResponseEntity<?> getMenus(String name);

    ResponseEntity<?> getMenu(Integer menuId);

    ResponseEntity<?> saveMenu(Menu menu);

    ResponseEntity<?> deleteMenu(Integer id);
}
