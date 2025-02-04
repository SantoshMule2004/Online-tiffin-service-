package com.homeybites.payloads;

import java.util.Date;

public class SubscriptionDto {
	
	private Integer planId;
	private Date startDate;
	private Date endDate;
	private double totalPrice;
	private String status;
	private Date createdAt;

	private UserDto user;

	private OrderInfoDto order;

	private TiffinPlanDto tiffinPlan;

	public SubscriptionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public TiffinPlanDto getTiffinPlan() {
		return tiffinPlan;
	}

	public void setTiffinPlan(TiffinPlanDto tiffinPlan) {
		this.tiffinPlan = tiffinPlan;
	}
}
