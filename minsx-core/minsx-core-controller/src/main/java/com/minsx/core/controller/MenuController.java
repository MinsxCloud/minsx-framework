package com.minsx.core.controller;

import com.minsx.core.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class MenuController {

	@Autowired
	MenuService menuService;

	@GetMapping(value = "/menus")
	public ResponseEntity<?> getMenus() {
		return menuService.getMenus();
	}


}
