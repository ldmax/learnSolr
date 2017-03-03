package com.lidan.learn.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Solr工具类
 * @author lidanmax
 * 注：将properties文件放在了util包下；放到别的路径会报错
 * 2017/03/02
 * */

public class SolrUtil {
	/**
	 * 获取properties文件某key对应的value
	 * @author lidanmax
	 * @param key key名
	 * @return key对应的value字符串
	 * */
	public static String getPropertyValueByKey(String key){
		Properties props = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("solr.properties");
		String value = null;
		try {
			props.load(in);
			value = props.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}
