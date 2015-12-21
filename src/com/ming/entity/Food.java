package com.ming.entity;


public class Food {
	private int id; // 主键
	private String foodName; // 菜名称
	private int foodType_id; // 外键：所属菜系
	private double price; // 价格
	private double mprice; // 会员价格
	private String remark; // 简介
	private String img; // 图片

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getFoodType_id() {
		return foodType_id;
	}

	public void setFoodType_id(int foodType_id) {
		this.foodType_id = foodType_id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getMprice() {
		return mprice;
	}

	public void setMprice(double mprice) {
		this.mprice = mprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "id:"+id+",foodName:"+foodName+",foodType_id:"+foodType_id+",price:"+price+",mprice:"+mprice+",remark:"+remark+",img:"+img;
	}
}
