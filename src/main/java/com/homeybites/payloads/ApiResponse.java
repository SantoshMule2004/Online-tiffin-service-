package com.homeybites.payloads;

public class ApiResponse {
	private String message;
	private boolean success;
	private Object classObj;
	
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApiResponse(String message, boolean success, Object classObj) {
		super();
		this.message = message;
		this.success = success;
		this.classObj = classObj;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getClassObj() {
		return classObj;
	}
	public void setClassObj(Object classObj) {
		this.classObj = classObj;
	}
}
