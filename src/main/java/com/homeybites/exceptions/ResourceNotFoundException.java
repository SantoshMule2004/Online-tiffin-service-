package com.homeybites.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	private String resourceName;
	private String fieldName;
	private long fieldValue;
	private String fieldVal;
	
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName, String fieldName, String fieldVal) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, fieldVal));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldVal = fieldVal;
	}
	
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public long getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(long fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getFieldVal() {
		return fieldVal;
	}
	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}
}

