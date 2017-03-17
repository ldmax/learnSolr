package com.lidan.learn.dao.impl;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.stereotype.Repository;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.dao.CrawlerDAO;
import com.lidan.learn.util.SolrUtil;

/**
 * CrawlerDAO实现类
 * @author lidanmax
 * 2017/03/13
 * */
@Repository
public class CrawlerDAOImpl implements CrawlerDAO {

	private static HttpSolrClient solr = new HttpSolrClient(SolrUtil
			.getPropertyValueByKey("crawlerUrl"));  // 该类公用的Solr客户端对象
	
	/**
	 * 根据用户传来的待查询字段名和字段值全文检索爬取的网页内容
	 * @author lidanmax
	 * @param searchInput 搜索框输入的文本
	 * @return 
	 * 2017/03/13 测试通过
	 * */
	@Override
	public CommonResult<SolrDocumentList> queryWebPage(String searchInput) {
		SolrParams queryParams = getSolrParams(searchInput);  // 通过输入字符串过得包装好的搜索参数
		CommonResult<SolrDocumentList> result = new CommonResult<>();
		try {
			QueryResponse response = solr.query(queryParams);
			SolrDocumentList data = response.getResults();
			result.setData(data);
			result.setMessage("This is CrawlerDAOImpl. Query succeeded.");
			result.setSuccess(true);
		} catch (SolrServerException | IOException e) {
			result.setMessage("This is CrawlerDAOImpl. Query failed. "
					+ "The stack trace is:" + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 输入搜索框内的字符串，返回包装好的ModifiableSolrParams对象
	 * @author lidanmax
	 * @param params 包装好的ModifiableSolrParams对象
	 * @return
	 * 2017/03/13测试通过
	 * */
	private static SolrParams getSolrParams(String searchInput){
		ModifiableSolrParams params = new ModifiableSolrParams();  // 这是最后要返回的结果
		// 开始处理搜索字符串
		String [] processedInput = null;  // 用来装trim且split by space后结果的字符串数组
		StringBuilder queryStringBuilder = new StringBuilder();  // 用来拼接q字符串
		if(searchInput == null || "".equals(searchInput)){  // 当输入为空或空字符串时认为搜索所有
			params.add("q", "*:*");
		}else{  
			processedInput = searchInput.trim().split(" ");  // 头尾去空后用空格来分割
			// 由于是搜索网页，因此只针对content属性进行搜索
			for(String s : processedInput){
				if(" ".equals(s) || "".equals(s)){
					continue;
				}
				queryStringBuilder.append("content:" + s + " AND ");
			}
		}
		String queryString = queryStringBuilder.toString().substring(0, 
				queryStringBuilder.toString().lastIndexOf("AND"));  // 去掉字符串末尾的AND
		System.out.println(queryString);
		params.add("q", queryString);
		return params;
	}
}
