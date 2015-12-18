package com.ming.service;

import com.ming.entity.Food;

public interface IFoodService {
	// 增加菜品
	public void save(Food food);

	// 删除菜品
	public void delete(int id);

	// 更新菜品
	public void update(Food food);

	// 根据id查询菜品
	public Food findById(int id);
}
