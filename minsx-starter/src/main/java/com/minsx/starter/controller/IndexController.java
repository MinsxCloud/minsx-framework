package com.minsx.starter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * IndexController
 * Created by Joker on 2017/8/29.
 */
@Controller
@RequestMapping("/user")
public class IndexController {

    @ResponseBody
    @RequestMapping(value = "/getUserName",method = RequestMethod.POST)
    public String getUserName(){
        return "goodsave";
    }

}
