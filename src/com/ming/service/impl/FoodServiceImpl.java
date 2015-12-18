package com.ming.service.impl;


import com.ming.dao.IFoodDao;
import com.ming.entity.Food;
import com.ming.factory.BeanFactory;
import com.ming.service.IFoodService;

public class FoodServiceImpl implements IFoodService {

	// 利用工厂得到DinnerTableDao的实例化对象
	private IFoodDao foodDao = BeanFactory.getInstance("food", IFoodDao.class);

	@Override
	public void save(Food food) {
		// TODO Auto-generated method stub
		foodDao.save(food);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		foodDao.delete(id);
	}

	@Override
	public void update(Food food) {
		// TODO Auto-generated method stub
		foodDao.update(food);
	}

	@Override
	public Food findById(int id) {
		// TODO Auto-generated method stub
		return foodDao.findById(id);
	}

}
