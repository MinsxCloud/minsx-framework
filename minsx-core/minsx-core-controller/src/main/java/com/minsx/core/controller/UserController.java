package com.minsx.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.minsx.core.respository.UserRepository;

/**
 * IndexController
 * Created by Joker on 2017/8/29.
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;

    @ResponseBody
    @PreAuthorize("hasRole('user')")
    @RequestMapping(value = "/getUserName",method = RequestMethod.POST)
    public String getUserName(){
        return "userName = "+userRepository.findOne(1).getUserName();
    }
    
    @ResponseBody
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public String PostUserInfo(){
        return "userInfo = "+JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication());
    }
    
    @ResponseBody
    @PreAuthorize("hasAuthority('URL:/user')")
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public String getUserInfo(){
    	return "userInfo = "+JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication());
    }
    
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/getUserAge",method = RequestMethod.POST)
    public String getUserAge(){
        return "userAge = 12";
    }

}
