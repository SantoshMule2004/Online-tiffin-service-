package com.homeybites.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.homeybites.entities.Category;
import com.homeybites.entities.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
	
	List<MenuItem> findByCategory(Category category);
}
