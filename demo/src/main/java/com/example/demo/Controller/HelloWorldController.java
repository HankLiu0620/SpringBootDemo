package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class HelloWorldController {
	
	@GetMapping("/hello")
	public String hello(){
		return "Hey, Spring Boot 的 Hello World ! ";
	}
	
	@GetMapping("/")
	public String Login(){
		return "index";
	}
}
