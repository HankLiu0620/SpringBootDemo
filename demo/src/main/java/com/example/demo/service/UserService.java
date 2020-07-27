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
	
	public void updateUserById(int userId,String userName){
		
		User user = this.getUserById(userId);
		
		LOGGER.info("Updare {} name to {} ",user.getUserName(),userName);
		
		user.setUserName(userName);
		
		userMapper.updateUserById(user);
	}
	
	public void deleteUser(int id){
		
		userMapper.deleteUser(id);

	}
}
