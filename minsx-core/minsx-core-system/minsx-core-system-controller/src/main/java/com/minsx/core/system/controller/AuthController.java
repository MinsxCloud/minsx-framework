package com.minsx.core.system.controller;

import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.system.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping(value = "/auths")
	public ResponseEntity<?> getAuths(@PageableDefault(value = 20, sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable) {
		return authService.getAuths(pageable);
	}

	@PutMapping(value = "/auths")
	public ResponseEntity<?> saveAuths(@RequestBody Auth auth) {
		return authService.saveAuths(auth);
	}

	@DeleteMapping(value = "/auths/{id}")
	public ResponseEntity<?> deleteAuths(@PathVariable Integer id) {
		return authService.deleteAuth(id);
	}

}
