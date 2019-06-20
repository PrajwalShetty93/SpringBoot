package com.boot.todo.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException()
	{
		return new ModelAndView("error");
	}
}
