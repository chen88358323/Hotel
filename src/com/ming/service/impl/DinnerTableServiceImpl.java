package com.ming.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ming.dao.IDinnerTableDao;
import com.ming.entity.DinnerTable;
import com.ming.entity.Orders;
import com.ming.factory.BeanFactory;
import com.ming.service.IDinnerTableService;
import com.ming.service.IOrdersService;

public class DinnerTableServiceImpl implements IDinnerTableService {

	// 利用工厂得到DinnerTableDao的实例化对象
	private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTable", IDinnerTableDao.class);
	private IOrdersService ordersService = BeanFactory.getInstance("ordersService",IOrdersService.class);

	@Override
	public void save(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		dinnerTableDao.save(dinnerTable);

	}

	@Override
	public void update(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		if (dinnerTable.getTableStatus()==0) {
			try {
				// 设置订餐桌的时间
				DateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				Date orderTable;
				orderTable = dt.parse(dt.format(new Date()));
				dinnerTable.setOrderDate(orderTable);
				// 设置订餐状态
				dinnerTable.setTableStatus(1);
				//			更新餐桌信息
				dinnerTableDao.update(dinnerTable);
				
//				添加订单
				Orders orders=new Orders();
				orders.setTable_id(dinnerTable.getId());
				orders.setOrderDate(orderTable);
				orders.setOrderStatus(0);
				orders.setTotalPrice(0.0);
				ordersService.save(orders);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}else if (dinnerTable.getTableStatus()==1) {
//			将订餐时间置空
			dinnerTable.setOrderDate(null);
			
//			将订餐状态置0
			dinnerTable.setTableStatus(0);
			
//			更新餐桌信息
			dinnerTableDao.update(dinnerTable);
			
			
		}
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
//		根据id得到餐桌
		DinnerTable dinnerTable =findById(id);
		
//		餐桌空闲才能删除
		if(dinnerTable.getTableStatus()==0){
			dinnerTableDao.delete(id);
		}
	}

	@Override
	public DinnerTable findById(int id) {
		// TODO Auto-generated method stub
		return dinnerTableDao.findById(id);
	}

	@Override
	public List<DinnerTable> getAll() {
		// TODO Auto-generated method stub
		return dinnerTableDao.getAll();
	}

	@Override
	public List<DinnerTable> getAll(String tableName) {
		// TODO Auto-generated method stub
		return dinnerTableDao.getAll(tableName);
	}

	@Override
	public List<DinnerTable> getStaAll(int tableStatus) {
		// TODO Auto-generated method stub
		return dinnerTableDao.getStaAll(tableStatus);
	}

}
