package com.lidan.learn.dao.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Repository;

import com.lidan.learn.dao.KidInfoDAO;
import com.lidan.learn.entity.KidInfoEntity;
import com.lidan.learn.entity.ResultModel;
import com.lidan.learn.util.EntityUtil;

/**
 * 接收service层传过来的参数，实施solr搜索，返回搜索结果
 * @author lidanmax
 * 2017/02/23
 * */

@Repository
public class KidInfoDAOImpl implements KidInfoDAO{

	private SolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/solr_test");
	
	@Override
	public ResultModel search(SolrQuery solrQuery) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 用来装结果的ResultModel
		ResultModel resultModel = new ResultModel();
		// 用来接收结果的List
		List<KidInfoEntity> kidInfoList = new ArrayList<>();
		try {
			// 查询获得响应
			QueryResponse response = solrClient.query(solrQuery);
			// 从响应中获取结果集
			SolrDocumentList results = response.getResults();
			// 防止results为空
			if(results != null){
				for(SolrDocument document : results){  // 将document中的数据取出装到KidInfoEntity中
					KidInfoEntity kid = new KidInfoEntity();
					EntityUtil.setFieldsFromDocument(kid, document);
					kidInfoList.add(kid);
				}
			}
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
		resultModel.setKidList(kidInfoList);
		return resultModel;
	}
}
