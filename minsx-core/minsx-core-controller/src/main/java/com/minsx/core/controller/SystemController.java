package com.minsx.core.controller;

import com.minsx.core.service.api.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SystemController {

	@Autowired
	SystemService systemService;

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index page";
	}

	@ResponseBody
	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	public Map<String,Object> getToken(HttpServletRequest request) {
		return systemService.getToken(request);
	}

	@ResponseBody
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome() {
		return "Home page";
	}
	
	@ResponseBody
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String unauthorized() {
		return "unauthorized";
	}
	
	@ResponseBody
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo() {
		return "userInfo page";
	}


}
