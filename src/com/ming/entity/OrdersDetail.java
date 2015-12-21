package com.ming.entity;

public class OrdersDetail {
	private int	id;		// 主键
	private int	orderId;		//外键：引入的是订单表的主键
	private int	food_id;		//外键：引用的是菜信息表的主键
	private int	foodCount;		//外键：菜的数量
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getFood_id() {
		return food_id;
	}
	public void setFood_id(int food_id) {
		this.food_id = food_id;
	}
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	
	
}
