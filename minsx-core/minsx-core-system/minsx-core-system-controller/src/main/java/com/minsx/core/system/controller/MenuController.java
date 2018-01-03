package com.minsx.core.system.controller;

import com.minsx.core.system.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping(value = "/topMenus")
	public ResponseEntity<?> getTopMenus() {
		return menuService.getTopMenus();
	}

	@GetMapping(value = "/leftMenus/{parentMenuId}")
	public ResponseEntity<?> getLeftMenus(@PathVariable(required = false) Integer parentMenuId) {
		return menuService.getLeftMenus(parentMenuId);
	}








}
