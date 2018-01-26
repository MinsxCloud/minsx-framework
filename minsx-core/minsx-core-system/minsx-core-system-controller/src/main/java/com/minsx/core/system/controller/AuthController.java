package com.minsx.core.system.controller;

import com.minsx.core.common.entity.ordinary.Auth;
import com.minsx.core.system.service.api.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @GetMapping(value = "/auths")
	public ResponseEntity<?> getAuths() {
		return authService.getAuths();
	}

	@PutMapping(value = "/auths")
	public ResponseEntity<?> saveAuths(@RequestBody Auth auth) {
		return authService.saveAuths(auth);
	}


}
