package com.lidan.learn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.dao.CrawlerDAO;
import com.lidan.learn.entity.WebPageEntity;
import com.lidan.learn.service.CrawlerService;
import com.lidan.learn.util.EntityUtil;

@Service
public class CrawlerServiceImpl implements CrawlerService {

	@Resource
	private CrawlerDAO crawlerDAO;  // 注入DAO对象
	
	/**
	 * 根据用户的输入获得检索到的SolrDocumentList，然后包装成WebPageEntity对象
	 * @author lidanmax
	 * @param searchInput 用户输入的文本
	 * @return
	 * */
	@Override
	public CommonResult<List<WebPageEntity>> queryWebPage(String searchInput) {
		CommonResult<SolrDocumentList> response = crawlerDAO.queryWebPage(searchInput);
		CommonResult<List<WebPageEntity>> result = new CommonResult<List<WebPageEntity>>();
		List<WebPageEntity> list = new ArrayList<>();
		for(SolrDocument doc : response.getData()){
			WebPageEntity entity = new WebPageEntity();
			String content = (String)doc.getFieldValue("content");
			// 在此截取包含关键词的一段文本返回出去，而不返回全部文本，以便在页面显示搜索结果
			EntityUtil.setFieldsFromDocumentForRichText(entity, doc);
			list.add(entity);
		}
		result.setData(list);
		result.setMessage("This is CrawlerServiceImpl. Query succeeded.");
		result.setSuccess(true);
		return result;
	}

}
