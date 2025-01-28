package com.homeybites.payloads;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;

public class MenuItemDto {
	
	private Integer menuId;
	
	@NotBlank(message = "menu cannot be empty..!")
	private String menuName;
	
	@NotBlank(message = "Price cannot be empty..!")
	private String price;
	
	@NotBlank(message = "Description cannot be empty..!")
	private String description;
	
	private boolean isActive;
	
	@JsonIgnore
	private CategoryDto category;
	
	private TiffinPlanDto tiffinPlan;
	
	public MenuItemDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public TiffinPlanDto getTiffinPlan() {
		return tiffinPlan;
	}
	public void setTiffinPlan(TiffinPlanDto tiffinPlan) {
		this.tiffinPlan = tiffinPlan;
	}
}
