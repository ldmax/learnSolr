package com.lidan.learn.service;

import org.apache.solr.common.SolrDocumentList;

import com.lidan.learn.common.CommonResult;

public interface RichTextService {
	/**
	 * 给出路径，将该路径下所有的富文本文件上传至Solr并建立索引
	 * @author lidanmax
	 * @param path 放置待检索富文本文档的路径
	 * @return void
	 * */
	CommonResult<Integer> updateRichTextDocument(String path);
	/**
	 * 根据用户传来的待查询字段名和字段值全文检索富文本文档
	 * @author lidanmax
	 * @param fieldName 待检索字段名
	 * @param fieldValue 待检索字段值
	 * @return 
	 * */
	CommonResult<SolrDocumentList> queryRichTextByField(String fieldName, String fieldValue);
}
