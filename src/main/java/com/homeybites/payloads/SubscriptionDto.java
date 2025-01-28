package com.homeybites.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubscriptionDto {
	
	private Integer planId;
	private Date startDate;
	private Date endDate;
	
	private UserDto user;
	private OrderInfoDto order;
	private List<TiffinPlanDto> tiffinPlans = new ArrayList<>();
	
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

	public List<TiffinPlanDto> getTiffinPlans() {
		return tiffinPlans;
	}

	public void setTiffinPlans(List<TiffinPlanDto> tiffinPlans) {
		this.tiffinPlans = tiffinPlans;
	}
}
