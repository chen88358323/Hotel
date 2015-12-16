package com.ming.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.ming.dao.IDinnerTableDao;
import com.ming.entity.DinnerTable;
import com.ming.utils.JdbcUtils;

public class DinnerTableDaoImpl implements IDinnerTableDao {

	@Override
	public void save(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO dinnerTable(tableName,tableStatus,orderDate) VALUES(?,?,?);";
		try {
			JdbcUtils.getQuneryRunner().update(sql,dinnerTable.getTableName(),dinnerTable.getTableStatus(),dinnerTable.getOrderDate());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public void update(DinnerTable dinnerTable) {
		// TODO Auto-generated method stub
		String sql="update dinnerTable set tableStatus=? where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql,dinnerTable.getId());
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from dinnerTable where id=?;";
		try {
			JdbcUtils.getQuneryRunner().update(sql,id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	@Override
	public DinnerTable findById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from dinnerTable where id=?;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> getAll() {
		// TODO Auto-generated method stub
		String sql="select * from dinnerTable;";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<DinnerTable> getAll(String tableName) {
		// TODO Auto-generated method stub
		String sql="select * from dinnerTable where tableName=?";
		try {
			return JdbcUtils.getQuneryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),"%"+tableName+"%");
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

}
