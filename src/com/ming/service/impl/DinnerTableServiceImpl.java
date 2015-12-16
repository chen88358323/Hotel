package com.ming.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ming.dao.IDinnerTableDao;
import com.ming.entity.DinnerTable;
import com.ming.factory.BeanFactory;
import com.ming.service.IDinnerTableService;

public class DinnerTableServiceImpl implements IDinnerTableService {

	// 利用工厂得到DinnerTableDao的实例化对象
	private IDinnerTableDao dinnerTableDao = BeanFactory.getInstance("dinnerTable", IDinnerTableDao.class);

	@Override
	public void save(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		dinnerTableDao.save(dinnerTable);

	}

	@Override
	public void update(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		dinnerTableDao.update(dinnerTable);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		dinnerTableDao.delete(id);
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

}
