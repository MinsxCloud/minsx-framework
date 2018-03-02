package com.minsx.core.system.controller;

import com.minsx.core.common.entity.ordinary.Role;
import com.minsx.core.system.service.api.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
	RoleService roleService;

    @GetMapping(value = "/roles")
	public ResponseEntity<?> getRoles(@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable) {
		return roleService.getRoles(pageable);
	}

	@PutMapping(value = "/roles")
	public ResponseEntity<?> saveRoles(@RequestBody Role role) {
		return roleService.saveRoles(role);
	}

	@DeleteMapping(value = "/roles/{id}")
	public ResponseEntity<?> deleteRoles(@PathVariable Integer id) {
		return roleService.deleteRole(id);
	}

}
