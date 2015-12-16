package com.ming.utils;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
//	初始化连接池
	private static DataSource dataSource;
	static{
		dataSource=new ComboPooledDataSource();
	}
	
	public static DataSource getDataSource() {
		return dataSource;
	}
	
//	创建工具类
	public static QueryRunner getQuneryRunner() {
		return new QueryRunner(dataSource);
	}
}
