package com.ming.dao;

import java.util.List;
import com.ming.entity.DinnerTable;



public interface IDinnerTableDao {
	// 添加
	public void save(DinnerTable dinnerTable);

	// 更新
	public void update(DinnerTable dinnerTable);

	// 删除
	void delete(int id);

	// 根据主键查询
	DinnerTable findById(int id);

	// 查询全部
	List<DinnerTable> getAll();

	// 根据餐桌名查询
	List<DinnerTable> getAll(String tableName);
	
//	根据餐桌预定状态查询
	List<DinnerTable> getStaAll(int tableStatus);
}
