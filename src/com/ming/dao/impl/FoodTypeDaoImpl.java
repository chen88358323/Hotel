package com.ming.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ming.dao.IFoodTypeDao;
import com.ming.entity.FoodType;
import com.ming.utils.JdbcUtils;

public class FoodTypeDaoImpl implements IFoodTypeDao {

	@Override
	public void save(FoodType foodType) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO foodtype(typeName) VALUES(?);";
		try {
			JdbcUtils.getQuneryRunner().update(sql,foodType.getTypeName());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(FoodType foodType) {
		// TODO Auto-generated method stub
		String sql="update foodType set typeName=? where id=?";
		try {
			JdbcUtils.getQuneryRunner().update(sql,foodType.getTypeName(),foodType.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from foodType where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql,id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public FoodType findById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from foodType where id=?";
		try {
			return JdbcUtils.getQuneryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from foodType;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<FoodType> getAll(String typeName) {
		// TODO Auto-generated method stub
		String sql="select * from foodType where typeName like ?";
		try {
			return JdbcUtils.getQuneryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),"%"+typeName+"%");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}