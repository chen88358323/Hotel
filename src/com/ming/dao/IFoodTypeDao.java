package com.ming.dao;

import java.util.List;

import com.ming.entity.FoodType;

public interface IFoodTypeDao {
//	添加
	public void save(FoodType foodType);
//	更新
	public void update(FoodType foodType);
//	删除
	void delete(int id);
//	根据主键查询
	FoodType findById(int id);
//	查询全部
	List<FoodType> getAll();
//	根据菜系查询
	List<FoodType> getAll(String typeName);
	
}
