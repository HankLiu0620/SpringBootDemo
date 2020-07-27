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
	
	@GetMapping(value = "/addUser/{userName}")
	public String addUser(Model model ,@PathVariable("userName") String userName){
				
		User newUser = userService.addUser(userName);
		
		model.addAttribute("newUserName",userName);
		
		LOGGER.info("Inserting {} to Db ...",newUser.getUserName());
				
		return "addUser";
	}
	
	@GetMapping(value = "/getUserById/{userId}")
	public String getUserById(Model model ,@PathVariable("userId") int userId){
			
		String userName = userService.getUserById(userId).getUserName();
		
		model.addAttribute("userName",userName);
		
		LOGGER.info("Get {} from Db ...",userName);
				
		return "getUserById";
	}
	
	@GetMapping(value = "/updateUserById/{userId}/{userName}")
	public String updateUserById(Model model ,@PathVariable("userId") int userId ,@PathVariable("userName") String userName){
		
		userService.updateUserById(userId,userName);
		
		model.addAttribute("userId",userId);
		
		LOGGER.info("Update userId = {} from Db ...",userId);
				
		return "updateUserById";
	}
	
	@GetMapping(value = "/deleteUser/{userId}")
	public String dleteUser(Model model ,@PathVariable("userId") int userId){
			
		String userName = userService.getUserById(userId).getUserName();
		userService.deleteUser(userId);
		
		model.addAttribute("deleteUser",userName);
		
		LOGGER.info("Delete {} from Db ...",userName);
				
		return "deleteUser";
	}
}
