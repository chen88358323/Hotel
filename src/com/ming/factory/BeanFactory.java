package com.ming.factory;

import java.util.ResourceBundle;

import com.ming.service.IFoodTypeService;

public class BeanFactory {
	
//	加载配置文件
	private static ResourceBundle bundle;
	static{
		bundle=ResourceBundle.getBundle("instance");
	}
	
//	根据指定的key生成对象	
	public static <T>T getInstance(String key,Class<T> clazz) {
		String className = bundle.getString(key);
		try {
			return (T) Class.forName(className).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
