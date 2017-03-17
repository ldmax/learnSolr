package com.lidan.learn.dao;

import org.apache.solr.common.SolrDocumentList;

import com.lidan.learn.common.CommonResult;

public interface CrawlerDAO {
	/**
	 * 通过输入的关键词搜索已索引页面
	 * @author lidanmax
	 * @param searchInput 输入关键词
	 * @return
	 * */
	CommonResult<SolrDocumentList> queryWebPage(String searchInput);
}
