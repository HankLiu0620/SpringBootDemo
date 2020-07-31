package com.example.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.controller.UserController;
import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserMapper userMapper;

	public List<User> getAllUser(){
		
		List<User> users = userMapper.getAllUser();
		
		users.stream().forEach(user -> LOGGER.info("userId : {} , userName : {} , userAge : {}",user.getUserId(),user.getUserName(),user.getUserAge()));
		
		return users;
	}
	
	public User getUserById(int userId){
		
		User user = userMapper.getUserById(userId);
		
		return user;
	}
	
	public User addUser(String userName){
		
		User userPo = new User();
		
		userPo.setUserName(userName);
		userMapper.insertUser(userPo);
		
		return userPo;
	}
	
	public User updateUserById(int userId,int userAge){
		
		User user = this.getUserById(userId);
		
		LOGGER.info("Updare {} age to {} ",user.getUserName(),userAge);
		
		user.setUserAge(userAge);
		
		userMapper.updateUserById(user);
		
		return user;
	}
	
	public String deleteUser(int userId){
		
		String userName = this.getUserById(userId).getUserName();
		userMapper.deleteUser(userId);
		
		return userName;
	}
}
