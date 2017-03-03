package com.lidan.learn.dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.dao.RichTextDAO;
import com.lidan.learn.util.SolrUtil;

/**
 * RichTextDAO实现类
 * @author lidanmax
 * 2017/03/02
 * */
public class RichTextDAOImpl implements RichTextDAO {
	
	private static HttpSolrClient solr = new HttpSolrClient(SolrUtil
			.getPropertyValueByKey("serverUrl"));  // 该类公用的Solr客户端对象
	
	/**
	 * 给出文件路径，本方法将该路径下所有富文本文件上传至Solr服务器并建立索引
	 * @author lidanmax
	 * @param path 存放富文本文件的路径
	 * @return CommonResult对象，其中的Integer是上传文件的数量
	 * 2017/03/03通过测试
	 * */
	@Override
	public CommonResult<Integer> updateRichTextDocument(String path){
		CommonResult<Integer> result = new CommonResult<>();  // 用来装结果的CommonResult
		// 首先递归地遍历该路径下的所有文件路径字符串
		List<String> pathList = new ArrayList<>();  // 用来装path目录下所有文件路径的List
		pathList = getAllPaths(pathList, path);
		// 将遍历到的每一个文件上传并建立索引
		String serverUrl = SolrUtil.getPropertyValueByKey("serverUrl");  // Solr服务器地址
		ContentStreamUpdateRequest up 
	    	= new ContentStreamUpdateRequest("/update/extract");  // 获取上传和提取文本的对象
		try{
			for(String filePath : pathList){
				String solrId = filePath.substring(filePath.lastIndexOf("\\") + 1);  // 获取文件名作为该文件的唯一标识
				up.addFile(new File(filePath), serverUrl);
				up.setParam("literal.id", solrId);
				//up.setParam("uprefix", "attr_");  // 将文本抽取产生的未在schema中定义的域加上前缀attr_
				up.setParam("fmap.content", "text");  // 将文本抽取产生的content域命名为schema中已定义的text
				up.setParam("fmap.id", "title");
				up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
				solr.request(up);  // 提交到服务器
			}
			solr.close();
			// 设置返回结果
			result.setSuccess(true);
			result.setData(pathList.size());
			result.setMessage("Documents updated successfully.");
		}catch(IOException ioe){
			result.setSuccess(false);
			result.setData(0);
			result.setMessage("Documents update fail. The stack trace: " + ioe.getMessage());
		}catch(SolrServerException sse){
			result.setSuccess(false);
			result.setData(0);
			result.setMessage("Documents update fail. The stack trace: " + sse.getMessage());
		}
		return result;
	}
	
	/**
	 * 根据用户传来的待查询字段名和字段值全文检索富文本文档
	 * @author lidanmax
	 * @param fieldName 待检索字段名
	 * @param fieldValue 待检索字段值
	 * @return 
	 * 2017/03/03 测试通过
	 * */
	@Override
	public CommonResult<SolrDocumentList> queryRichTextDocument(String fieldName, String fieldValue) {
		CommonResult<SolrDocumentList> result = new CommonResult<>();  // 用来装结果的CommonResult对象
		SolrQuery request = null;
		if(fieldName == null && fieldValue == null){  // 如果用户什么都不输入，则查询所有
			request = new SolrQuery("*:*");  // *:*即为查询所有
		}else{
			request = new SolrQuery(fieldName + ":" + fieldValue);
		}
		
		try {
			QueryResponse response = solr.query(request);
			SolrDocumentList responseResults = response.getResults();
			// 设置返回结果
			result.setSuccess(true);
			result.setData(responseResults);
			result.setMessage("Get query result successfully.");
		} catch (SolrServerException e) {
			result.setSuccess(false);
			result.setMessage("Get query result fail. The stack trace: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMessage("Get query result fail. The stack trace: " + e.getMessage());
		}
		return result;
	}
	
	/**
	 * 给出路径，返回该路径下的所有路径，至文件为止
	 * @author lidanmax
	 * @param pathList 用来装所有文件路径字符串的列表
	 * @param path 待递归搜索的文件路径
	 * @return 所有已穷尽路径的字符串列表
	 * 2017/.3/.2 16:34通过测试
	 * */
	private static List<String> getAllPaths(List<String> pathList, String path){
		File[] files = new File(path).listFiles();
		for(File file : files){
			File[] innerFiles = file.listFiles();
			if(innerFiles == null){  // 说明这个路径已经是一个文件，则将其装到pathList中
				pathList.add(file.getAbsolutePath());
			}else{
				getAllPaths(pathList, file.getAbsolutePath());
			}
		}
		return pathList;
	}
	
	public static void main(String[] args) {
		
		CommonResult<Integer> result = new RichTextDAOImpl().updateRichTextDocument("C:\\Users\\lidanmax\\Desktop\\test2");
		System.out.println(result.getMessage());
		
	}
}
