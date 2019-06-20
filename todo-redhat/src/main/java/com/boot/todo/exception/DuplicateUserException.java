package com.boot.todo.exception;

public class DuplicateUserException extends Exception {

	public DuplicateUserException(String message)
	{
		super(message);
	}
}
