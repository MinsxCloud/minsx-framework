package com.minsx.core.system.controller;

import com.minsx.core.system.service.api.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {

	private final SystemService systemService;

	@Autowired
	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}

	@GetMapping(value = "/systemInfo")
	public ResponseEntity<?> getSystemInfo() {
		return systemService.getSystemInfo();
	}

	@GetMapping(value = "/developerTeams")
	public ResponseEntity<?> getDeveloperTeams() {
		return systemService.getDeveloperTeams();
	}


}
