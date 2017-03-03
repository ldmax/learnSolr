package com.lidan.learn.service.impl;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrQuery;

import com.lidan.learn.dao.KidInfoDAO;
import com.lidan.learn.entity.ResultModel;
import com.lidan.learn.service.KidInfoService;
import org.springframework.stereotype.Service;

/**
 * KidInfoEntity查询的service实现
 * @author lidanmax
 * 2017/02/24
 * */

@Service
public class KidInfoServiceImpl implements KidInfoService{
	
	@Resource
	private KidInfoDAO kidInfoDAO;  // 注入KidInfoDAOImpl对象

	/**
	 * service层的search方法
	 * @author lidanmax
	 * @param queryString 查询字符串
	 * @return ResultModel对象，其中有查询到的数据的的包装好的对象及其它信息
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * */
	@Override
	public ResultModel search(String queryString) throws NoSuchMethodException, SecurityException, InvocationTargetException, IllegalAccessException, IllegalArgumentException {
		// 将查询语句包装为SolrQuery对象
		SolrQuery solrQuery = new SolrQuery();
		// 如果查询语句为空，则设置q的参数为*:*，否则将q设为查询语句
		if(queryString != null && (!"".equals(queryString.trim()))){
			solrQuery.setQuery(queryString);
		}else{
			solrQuery.setQuery("*:*");  // 此时将查询设置为查全部
		}
		ResultModel result = kidInfoDAO.search(solrQuery);
		return result;
	}
}
