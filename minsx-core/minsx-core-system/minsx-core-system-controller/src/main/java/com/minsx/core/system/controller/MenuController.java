package com.minsx.core.system.controller;

import com.minsx.core.common.entity.system.Menu;
import com.minsx.core.system.service.api.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}

	@GetMapping(value = "/menus")
	public ResponseEntity<?> getMenus(@RequestParam(required = false) String name) {
		return menuService.getMenus(name);
	}

	@GetMapping(value = "/menus/{menuId}")
	public ResponseEntity<?> getMenu(@PathVariable(required = true) Integer menuId) {
		return menuService.getMenu(menuId);
	}

	@GetMapping(value = "/topMenus")
	public ResponseEntity<?> getTopMenus() {
		return menuService.getTopMenus();
	}

	@GetMapping(value = "/leftMenus/{parentMenuId}")
	public ResponseEntity<?> getLeftMenus(@PathVariable(required = false) Integer parentMenuId) {
		return menuService.getLeftMenus(parentMenuId);
	}

	@PutMapping(value = "/menus")
	public ResponseEntity<?> saveMenu(@RequestBody Menu menu) {
		return menuService.saveMenu(menu);
	}

	@DeleteMapping(value = "/menus/{id}")
	public ResponseEntity<?> deleteMenu(@PathVariable(required = true) Integer id) {
		return menuService.deleteMenu(id);
	}






}
