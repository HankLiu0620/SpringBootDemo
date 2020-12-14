package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
	
	public void createExcel() throws IOException {
		//1、新建工作簿
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2、建立工作表
		HSSFSheet sheet=workbook.createSheet("工作表1");
		//3、建立行
		HSSFRow row=sheet.createRow(0);
		//4、建立單元格
		HSSFCell cell=row.createCell(2);
		//5、單元格寫入內容
		cell.setCellValue("你好JAVA");
		
		//6、儲存工作簿
		File file=new File("d:\\hello.xls");
		workbook.write(file);
		System.out.println("建立Excel成功");
	}
}
