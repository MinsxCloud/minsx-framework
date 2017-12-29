package com.minsx.core.user.controller;

import com.minsx.core.user.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping(value = "/menus")
	public ResponseEntity<?> getMenus() {
		return menuService.getMenus();
	}


}
