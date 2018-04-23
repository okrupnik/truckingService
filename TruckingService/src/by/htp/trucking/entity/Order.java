package by.htp.trucking.entity;

import java.time.LocalDate;

public class Order {
	
	private LocalDate dateOfCreating;
	private User user;
	private OrderInfo orderInfo;

	public Order() {
		
	}

	public Order(LocalDate dateOfCreating, User user, OrderInfo orderInfo) {
		this.dateOfCreating = dateOfCreating;
		this.user = user;
		this.orderInfo = orderInfo;
	}

	public LocalDate getDateOfCreating() {
		return dateOfCreating;
	}

	public void setDateOfCreating(LocalDate dateOfCreating) {
		this.dateOfCreating = dateOfCreating;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderInfo getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}
}
