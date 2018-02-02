package com.minsx.core.system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @PostMapping(value = "/query")

	public ResponseEntity<?> test(@RequestBody String queryNode) {

		return new ResponseEntity<>(HttpStatus.OK);
	}


}
