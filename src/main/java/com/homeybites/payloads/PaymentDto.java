package com.homeybites.payloads;

import java.util.Date;

public class PaymentDto {
	
	private Integer paymentId;
	private String paymentMethod;
	private Date paymentDate;
	private String paymentStatus;
	private String amount;
	private UserDto user;
	private OrderInfoDto order;
	
	public PaymentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public OrderInfoDto getOrder() {
		return order;
	}
	public void setOrder(OrderInfoDto order) {
		this.order = order;
	}
}
