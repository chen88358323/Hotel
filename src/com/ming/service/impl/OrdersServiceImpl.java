package com.ming.service.impl;

import java.util.List;

import com.ming.dao.IOrdersDao;
import com.ming.entity.Orders;
import com.ming.factory.BeanFactory;
import com.ming.service.IOrdersService;

public class OrdersServiceImpl implements IOrdersService {
	IOrdersDao ordersDao=BeanFactory.getInstance("orders",IOrdersDao.class);
	@Override
	public void save(Orders orders) {
		// TODO Auto-generated method stub
		ordersDao.save(orders);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		ordersDao.delete(id);
	}

	@Override
	public void update(Orders orders) {
		// TODO Auto-generated method stub
		if(orders.getOrderStatus()==0){
			orders.setOrderStatus(1);
		}
		ordersDao.update(orders);
	}

	@Override
	public List<Orders> getAll() {
		// TODO Auto-generated method stub
		return ordersDao.getAll();
	}

	@Override
	public Orders findById(int id) {
		// TODO Auto-generated method stub
		return ordersDao.findById(id);
	}

}
