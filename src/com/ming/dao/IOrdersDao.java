package com.ming.dao;

import java.util.List;

import com.ming.entity.Orders;

public interface IOrdersDao {
//	增加一个订单
	public void save(Orders orders);
	
//	根据id删除订单
	public void delete(int id);
	
//	更新订单
	public void update(Orders orders);
	
//	查询所有订单
	public List<Orders> getAll();
}
