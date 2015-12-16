package com.ming.service.impl;

import java.util.List;

import com.ming.dao.IFoodTypeDao;
import com.ming.entity.FoodType;
import com.ming.factory.BeanFactory;
import com.ming.service.IFoodTypeService;

public class FoodTypeServiceImpl implements IFoodTypeService {
//	private IFoodTypeDao foodTypeDao=new FoodTypeDaoImpl();		//对象写死不可取
	
//	采用工厂创建对象
	private IFoodTypeDao foodTypeDao=BeanFactory.getInstance("foodType",IFoodTypeDao.class);

	@Override
	public void save(FoodType foodType) {
		try {
			// TODO Auto-generated method stub			
			foodTypeDao.save(foodType);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(FoodType foodType) {
		try {
			// TODO Auto-generated method stub
			foodTypeDao.update(foodType);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			// TODO Auto-generated method stub
			foodTypeDao.delete(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public FoodType findById(int id) {
		try {
			// TODO Auto-generated method stub
			return foodTypeDao.findById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAll() {
		try {
			// TODO Auto-generated method stub
			return foodTypeDao.getAll();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAll(String typeName) {
		try {
			// TODO Auto-generated method stub
			return foodTypeDao.getAll(typeName);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
