package com.homeybites.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TiffinPlanDto {
	
	private Integer tiffinPlanId;
	private String planName;
	private String planType;
	private double price;
	private String addOns;
	private boolean isActive;
	private Date createdAt;
	
	private OrderInfoDto order;
	
	private UserDto user;
	
	private List<MenuItemDto> menuItems = new ArrayList<>();
	
	private List<SubscriptionDto> subscriptions = new ArrayList<>();
	
	public TiffinPlanDto() {
		super();
	}
	
	public Integer getTiffinPlanId() {
		return tiffinPlanId;
	}
	public void setTiffinPlanId(Integer tiffinPlanId) {
		this.tiffinPlanId = tiffinPlanId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getPlanType() {
		return planType;
	}
	public void setPlanType(String planType) {
		this.planType = planType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAddOns() {
		return addOns;
	}
	public void setAddOns(String addOns) {
		this.addOns = addOns;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public OrderInfoDto getOrder() {
		return order;
	}
	public void setOrder(OrderInfoDto order) {
		this.order = order;
	}
	public UserDto getUserDto() {
		return user;
	}
	public void setUserDto(UserDto user) {
		this.user = user;
	}
	public List<MenuItemDto> getMenuItems() {
		return menuItems;
	}
	public void setMenuItems(List<MenuItemDto> menuItems) {
		this.menuItems = menuItems;
	}
	public List<SubscriptionDto> getSubscriptions() {
		return subscriptions;
	}
	public void setSubscriptions(List<SubscriptionDto> subscriptions) {
		this.subscriptions = subscriptions;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
