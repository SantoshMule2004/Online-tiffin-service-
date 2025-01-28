package com.homeybites.services;

import java.util.List;

import com.homeybites.payloads.MenuItemDto;

public interface MenuItemService {
	
	//add new menu item
	MenuItemDto addMenuItem(MenuItemDto menuItemDto, Integer categoryId);
	
	//get menu item
	MenuItemDto getMenuItem(Integer menuId);
	
	//get menu item by category
	List<MenuItemDto> getMenuItemByCategory(Integer cId);
	
	//get all menu items
	List<MenuItemDto> getAllMenuItem();
	
	//update menu item
	MenuItemDto updateMenuItem(MenuItemDto menuItemDto, Integer menuId);
	
	//delete menu item
	void deleteMenuItem(Integer menuId);
}
