package com.homeybites.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.homeybites.payloads.ApiResponse;
import com.homeybites.payloads.MenuItemDto;
import com.homeybites.services.MenuItemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class MenuController {

	@Autowired
	private MenuItemService menuItemService;

	// add menu item controller
	@PostMapping("/category/{cId}/menuitem/")
	public ResponseEntity<ApiResponse> addMenuItem(@Valid @RequestBody MenuItemDto menuItemDto,
			@PathVariable Integer cId) {
		MenuItemDto menuItem = this.menuItemService.addMenuItem(menuItemDto, cId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("MenuItem added successfully..!", true, menuItem),
				HttpStatus.CREATED);
	}

	// get menu item by id
	@GetMapping("/menuitem/{menuId}")
	public ResponseEntity<MenuItemDto> getMenuItem(@PathVariable Integer menuId) {
		MenuItemDto menuItem = this.menuItemService.getMenuItem(menuId);
		return new ResponseEntity<MenuItemDto>(menuItem, HttpStatus.FOUND);
	}

	// get all menu items
	@GetMapping("/menuitem")
	public ResponseEntity<List<MenuItemDto>> getAllMenuItems() {
		List<MenuItemDto> allMenuItem = this.menuItemService.getAllMenuItem();
		return new ResponseEntity<List<MenuItemDto>>(allMenuItem, HttpStatus.FOUND);
	}

	// get all menu items of a category
	@GetMapping("/category/{cId}/menuitems")
	public ResponseEntity<List<MenuItemDto>> getMenuItemByCategory(@PathVariable Integer cId) {
		List<MenuItemDto> menuItems = this.menuItemService.getMenuItemByCategory(cId);
		return new ResponseEntity<List<MenuItemDto>>(menuItems, HttpStatus.OK);
	}

	// Update menu item
	@PutMapping("/menuitem/{menuId}")
	public ResponseEntity<MenuItemDto> updateMenuItem(@Valid @RequestBody MenuItemDto menuItemDto,
			@PathVariable Integer menuId) {
		MenuItemDto updatedMenuItem = this.menuItemService.updateMenuItem(menuItemDto, menuId);
		return new ResponseEntity<MenuItemDto>(updatedMenuItem, HttpStatus.OK);
	}

	// delete menu item
	@DeleteMapping("/menuitem/{menuId}")
	public ResponseEntity<ApiResponse> deleteMenuItem(@PathVariable Integer menuId) {
		this.menuItemService.deleteMenuItem(menuId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("menuitem deleted successfully..!", true, null),
				HttpStatus.OK);
	}

}
