package com.example.demo.aop.pointcut;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PointcutDefinition {

	@Pointcut("within(com.example.demo.controller..*)")
	public void webLayer() {
	}

	@Pointcut("within(com.example.demo.service..*)")
	public void serviceLayer() {
	}

	@Pointcut("within(com.example.demo.controller..*)")
	public void controllerLayer() {
	}
}
