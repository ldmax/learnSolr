package com.lidan.learn.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Service;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.dao.RichTextDAO;
import com.lidan.learn.entity.RichTextDocumentEntity;
import com.lidan.learn.service.RichTextService;
import com.lidan.learn.util.EntityUtil;

@Service
public class RichTextServiceImpl implements RichTextService {

	@Resource
	private RichTextDAO richTextDAO;
	
	/**
	 * 根据用户传过来的待查询字段名和该字段值全文检索富文本文件
	 * @author lidanmax
	 * @param searchInput 用户在搜索框输入的文本
	 * @return 
	 * 2017/03/02
	 * */
	@Override
	public CommonResult<List<RichTextDocumentEntity>> queryRichText(String searchInput) {
		CommonResult<SolrDocumentList> response = richTextDAO
				.queryRichTextDocument(searchInput);  // 根据用户输入的文本查富文本文件
		List<RichTextDocumentEntity> richTextDocumentList = new ArrayList<>();
		CommonResult<List<RichTextDocumentEntity>> result = new CommonResult<>();  // 用来装结果的CommonResult对象
		try{
			for(SolrDocument doc : response.getData()){
				RichTextDocumentEntity entity = new RichTextDocumentEntity();
				EntityUtil.setFieldsFromDocument(entity, doc);
				richTextDocumentList.add(entity);
			}
			result.setData(richTextDocumentList);
			result.setMessage("This is RichTextService. Query succeeded.");
			result.setSuccess(true);
		}catch(InvocationTargetException ite){
			result.setMessage("This is RichTextService. "
					+ "Query failed. The stack trace is: " + ite.getMessage());
			result.setSuccess(false);
		} catch (NoSuchMethodException e) {
			result.setMessage("This is RichTextService. "
					+ "Query failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (SecurityException e) {
			result.setMessage("This is RichTextService. "
					+ "Query failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (IllegalAccessException e) {
			result.setMessage("This is RichTextService. "
					+ "Query failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (IllegalArgumentException e) {
			result.setMessage("This is RichTextService. "
					+ "Query failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		}
		
		return result;
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
