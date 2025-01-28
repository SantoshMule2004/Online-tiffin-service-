package com.homeybites.payloads;

import java.util.ArrayList;
import java.util.List;

public class TiffinPlanDto {
	
	private Integer tiffinPlanId;
	private String planName;
	private String planType;
	private String price;
	private String addOns;
	private boolean isActive;
	
	private OrderInfoDto order;
	
	private UserDto user;
	
	private List<MenuItemDto> menuItems = new ArrayList<>();
	
	private List<SubscriptionDto> subscriptions = new ArrayList<>();
	
	public TiffinPlanDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
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
}
