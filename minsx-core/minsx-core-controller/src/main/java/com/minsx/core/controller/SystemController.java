package com.minsx.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemController {

	@ResponseBody
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "index page";
	}
	
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLogin() {
		return "login page";
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
