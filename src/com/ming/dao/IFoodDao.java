package com.ming.dao;

import java.util.List;

import com.ming.entity.Food;

public interface IFoodDao {
//	增加菜品
	public void save(Food food);
	
//	删除菜品
	public void delete(int id);
	
//	更新菜品
	public void update(Food food);
	
//	根据id查询菜品
	public Food findById(int id);
	
	//查询所有菜品
	public List<Food> getAll();
}
