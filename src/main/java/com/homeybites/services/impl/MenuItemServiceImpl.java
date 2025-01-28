package com.homeybites.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeybites.entities.Category;
import com.homeybites.entities.MenuItem;
import com.homeybites.exceptions.ResourceNotFoundException;
import com.homeybites.payloads.MenuItemDto;
import com.homeybites.repositories.CategoryRepository;
import com.homeybites.repositories.MenuItemRepository;
import com.homeybites.services.MenuItemService;

@Service
public class MenuItemServiceImpl implements MenuItemService {

	@Autowired
	private MenuItemRepository menuItemRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public MenuItemDto addMenuItem(MenuItemDto menuItemDto, Integer categoryId) {

		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", categoryId));

		MenuItem menuItem = this.modelMapper.map(menuItemDto, MenuItem.class);
		menuItem.setCategory(category);
		MenuItem savedMenu = this.menuItemRepository.save(menuItem);

		return this.modelMapper.map(savedMenu, MenuItemDto.class);
	}

	@Override
	public MenuItemDto getMenuItem(Integer menuId) {
		MenuItem menuItem = this.menuItemRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu item", "id", menuId));
		return this.modelMapper.map(menuItem, MenuItemDto.class);
	}

	@Override
	public List<MenuItemDto> getMenuItemByCategory(Integer cId) {
		Category category = this.categoryRepository.findById(cId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "id", cId));

		List<MenuItem> findByCategory = this.menuItemRepository.findByCategory(category);
		List<MenuItemDto> allMenuItems = findByCategory.stream()
				.map(menuItem -> this.modelMapper.map(menuItem, MenuItemDto.class)).collect(Collectors.toList());
		
		return allMenuItems;
	}

	@Override
	public List<MenuItemDto> getAllMenuItem() {
		List<MenuItem> allMenus = this.menuItemRepository.findAll();

		List<MenuItemDto> allMenuItems = allMenus.stream()
				.map(menuItem -> this.modelMapper.map(menuItem, MenuItemDto.class)).collect(Collectors.toList());
		return allMenuItems;
	}

	@Override
	public MenuItemDto updateMenuItem(MenuItemDto menuItemDto, Integer menuId) {
		MenuItem menuItem = this.menuItemRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu item", "id", menuId));

		menuItem.setMenuName(menuItemDto.getMenuName());
		menuItem.setPrice(menuItemDto.getPrice());
		menuItem.setDescription(menuItemDto.getDescription());
		menuItem.setActive(menuItemDto.isActive());
		menuItem.setMenuName(menuItemDto.getMenuName());

		MenuItem updatedMenu = this.menuItemRepository.save(menuItem);

		return this.modelMapper.map(updatedMenu, MenuItemDto.class);
	}

	@Override
	public void deleteMenuItem(Integer menuId) {
		MenuItem menuItem = this.menuItemRepository.findById(menuId)
				.orElseThrow(() -> new ResourceNotFoundException("Menu item", "id", menuId));

		this.menuItemRepository.delete(menuItem);

	}
}
