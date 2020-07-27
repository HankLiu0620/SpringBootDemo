package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
	
	int insertUser(User user);
	int deleteUser(int userId);
	User getUserById(int userId);
	int updateUserById(User user);
	List<User> getAllUser();
	
}
