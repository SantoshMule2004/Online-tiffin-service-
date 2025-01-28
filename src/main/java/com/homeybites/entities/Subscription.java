package com.homeybites.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Subscription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planId;
	private Date startDate;
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(mappedBy = "subscription", cascade = CascadeType.ALL)
	private OrderInfo order;

	@ManyToMany
	@JoinTable(name = "subscription_tiffin", joinColumns = @JoinColumn(name = "sub_id"), inverseJoinColumns = @JoinColumn(name = "tiffin_id"))
	private List<TiffinPlan> tiffinPlans = new ArrayList<>();

	public Subscription() {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public List<TiffinPlan> getTiffinPlans() {
		return tiffinPlans;
	}

	public void setTiffinPlans(List<TiffinPlan> tiffinPlans) {
		this.tiffinPlans = tiffinPlans;
	}
}
