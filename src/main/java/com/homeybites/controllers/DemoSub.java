package com.homeybites.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.homeybites.entities.Subscription;
import com.homeybites.entities.TiffinPlan;
import com.homeybites.repositories.SubscriptionRepository;
import com.homeybites.repositories.TiffinplanRepository;

@RestController
@RequestMapping("/api/")
public class DemoSub {
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private TiffinplanRepository tiffinplanRepository;

	@PostMapping("/")
	public String addSub(@RequestBody Subscription subscription, @RequestParam Integer tiffinId) {
		TiffinPlan tiffinPlan = this.tiffinplanRepository.findById(tiffinId).get();
		subscription.getTiffinPlan().add(tiffinPlan);
		Subscription save = this.subscriptionRepository.save(subscription);
		System.out.println(save.getTotalPrice());
		return "success";
	}
}
