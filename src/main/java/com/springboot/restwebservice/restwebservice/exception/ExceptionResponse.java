package com.springboot.restwebservice.restwebservice.exception;

import java.util.Date;

public class ExceptionResponse {
	//Timestamp
	private Date timestamp;
	//message
	private String message;
	//details
	private String details;
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	
	
}
