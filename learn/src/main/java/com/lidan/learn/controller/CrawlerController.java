package com.lidan.learn.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lidan.learn.common.CommonResult;
import com.lidan.learn.entity.ResultModel;
import com.lidan.learn.entity.RichTextDocumentEntity;

/**
 * 爬虫控制层
 * @author lidanmax
 * 2017/03/10
 * */
@Controller
@RequestMapping("/crawler")
public class CrawlerController {


	@RequestMapping(value="/search", method=RequestMethod.GET)
	@ResponseBody
	public String search(String searchInput){
		/*CommonResult<List<RichTextDocumentEntity>> response = richTextService
				.queryRichText(searchInput);
		List<RichTextDocumentEntity> resultList = response.getData();
		return new Gson().toJson(resultList);*/
		return null;
	}
}
