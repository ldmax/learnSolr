package com.lidan.learn.service.impl;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocumentList;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.dao.RichTextDAO;
import com.lidan.learn.service.RichTextService;

public class RichTextServiceImpl implements RichTextService {

	@Resource
	private RichTextDAO richTextDAO;
	
	/**
	 * 根据用户传过来的待查询字段名和该字段值全文检索富文本文件
	 * @author lidanmax
	 * @param fieldName 待检索字段名
	 * @param fieldValue 待检索字段值
	 * @return 
	 * 2017/03/02
	 * */
	@Override
	public CommonResult<SolrDocumentList> queryRichTextByField(String fieldName, String fieldValue) {
		return richTextDAO.queryRichTextDocument(fieldName, fieldValue);
	}

	
	/**
	 * 给出路径，将该路径下所有的富文本文件上传至Solr并建立索引
	 * @author lidanmax
	 * @param path 放置待检索富文本文档的路径
	 * @return 
	 * */
	@Override
	public CommonResult<Integer> updateRichTextDocument(String path) {
		return richTextDAO.updateRichTextDocument(path);
	}

}
