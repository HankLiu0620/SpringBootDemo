package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/demo")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/getAllUsers")
	public String SearchUsers(Model model){
				
		List<User> users = userService.getAllUser();
		
		LOGGER.info("Searching user ...");
		
		users.stream().forEach(user -> LOGGER.info("userId : {} , userName : {}",user.getUserId(),user.getUserName()));
		
		model.addAttribute("title", "User List");
		model.addAttribute("userList", users);
		
		return "getAllUser";
	}
	
	@GetMapping(value = "/addUser/{name}")
	public String AddUser(Model model ,@PathVariable("name") String name){
				
		User newUser = userService.addUser(name);
		
		model.addAttribute("newUserName",name);
		
		LOGGER.info("Inserting {} to Db ...",newUser.getUserName());
				
		return "addUser";
	}
}
