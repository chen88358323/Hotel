package com.ming.dao.impl;

import com.ming.dao.IOrdersDetailDao;
import com.ming.entity.OrdersDetail;
import com.ming.utils.JdbcUtils;

public class OrdersDetailDaoImpl implements IOrdersDetailDao{

	@Override
	public void update(OrdersDetail ordersDetail) {
		// TODO Auto-generated method stub
		String sql="update orderDetail set foodCount=? where id=?";
		try {
			JdbcUtils.getQuneryRunner().update(sql,ordersDetail.getFoodCount(),ordersDetail.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException();
		}
	}

}
