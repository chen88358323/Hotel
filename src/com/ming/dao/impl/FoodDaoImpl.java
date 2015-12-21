package com.ming.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ming.dao.IFoodDao;
import com.ming.entity.Food;
import com.ming.utils.JdbcUtils;

public class FoodDaoImpl implements IFoodDao {

	@Override
	public void save(Food food) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO food(foodName,foodType_id,price,mprice,remark,img) VALUES(?,?,?,?,?,?);";
		try {
			JdbcUtils.getQuneryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from food where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql,id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(Food food) {
		// TODO Auto-generated method stub
		String sql = "update food set foodName=?,foodType_id=?,price=?,mprice=?,remark=?,img=? where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql,food.getFoodName(),food.getFoodType_id(),food.getPrice(),food.getMprice(),food.getRemark(),food.getImg(),food.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public Food findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from food where id=?;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanHandler<Food>(Food.class), id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Food> getAll() {
		// TODO Auto-generated method stub
		String sql = "select * from food;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanListHandler<Food>(Food.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
