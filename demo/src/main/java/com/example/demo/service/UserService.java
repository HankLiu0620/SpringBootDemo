package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserMapper;
import com.example.demo.model.User;

@Service
public class UserService {
	
	@Autowired
	UserMapper userMapper;

	public List<User> getAllUser(){
		
		List<User> users = userMapper.getAllUser();
		
		return users;
	}
	
	public User addUser(String name){
		
		User userPo = new User();
		
		userPo.setUserName(name);
		userMapper.insertUser(userPo);
		
		return userPo;
	}
}
