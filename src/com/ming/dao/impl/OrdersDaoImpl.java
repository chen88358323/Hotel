package com.ming.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ming.dao.IOrdersDao;
import com.ming.entity.Orders;
import com.ming.utils.JdbcUtils;

public class OrdersDaoImpl implements IOrdersDao{

	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO orders(table_id,orderDate,totalPrice,orderStatus) VALUES(?,?,?,?);";
		try {
			JdbcUtils.getQuneryRunner().update(sql, orders.getTable_id(),orders.getOrderDate(),orders.getTotalPrice(),orders.getOrderStatus());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from orders where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql, id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
	}

	@Override
	public void update(Orders orders) {
		// TODO Auto-generated method stub
		String sql="update orders set orderStatus=?, where id=?";
		try {
			JdbcUtils.getQuneryRunner().update(sql,orders.getOrderStatus(),orders.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
		
	}

	@Override
	public List<Orders> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from orders;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanListHandler<Orders>(Orders.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}

}