package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/demo")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/getAllUser")
	public String SearchUsers(){
				
		List<User> users = userService.getAllUser();
		
		LOGGER.info("Searching user ...");
		
		users.stream().forEach(user -> LOGGER.info("userId : {} , userName : {}",user.getUserId(),user.getUserName()));
		
		return "helloWorld";
	}
}
