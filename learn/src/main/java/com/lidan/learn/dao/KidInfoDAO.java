package com.lidan.learn.dao;

import java.lang.reflect.InvocationTargetException;

import org.apache.solr.client.solrj.SolrQuery;
import com.lidan.learn.entity.ResultModel;

/**
 * kid_info表的DAO接口
 * @author lidanmax
 * 2017/02/23
 * */
public interface KidInfoDAO {
	ResultModel search(SolrQuery solrQuery) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
