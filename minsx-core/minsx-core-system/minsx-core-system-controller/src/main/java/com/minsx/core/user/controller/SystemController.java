package com.minsx.core.user.controller;

import com.minsx.core.user.service.api.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class SystemController {

	private final SystemService systemService;

	@Autowired
	public SystemController(SystemService systemService) {
		this.systemService = systemService;
	}

	@GetMapping(value = "/systemInfo")
	public ResponseEntity<?> getIndex() {
		return systemService.getSystemInfo();
	}


}
