package com.lidan.learn.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lidan.learn.common.CommonResult;
import com.lidan.learn.entity.RichTextDocumentEntity;
import com.lidan.learn.service.RichTextService;

/**
 * @author lidanmax
 * 2017/03/03
 * */
@Controller
@RequestMapping("/richText")
public class RichTextController {
	
	@Resource
	private RichTextService richTextService;  // 注入Service对象
	
	
	/**
	 * 控制用户请求到搜索框页面的跳转
	 * @author lidanmax
	 * 2017/03/04
	 * */
	@RequestMapping(value="/toSearch", method=RequestMethod.GET)
	public String toSearchBox(){
		return "/searchBox";
	}
	
	/**
	 * 控制用户请求到搜索结果展示页面的跳转
	 * @author lidanmax
	 * 2017/03/07
	 * */
	@RequestMapping(value="/toRichTextPage", method=RequestMethod.GET)
	public String toRichTextPage(String data, Model model){
		model.addAttribute("data", data);
		return "/richTextPage";
	}
	
	/**
	 * 用户在输入框输入输入查询关键字，在这里自动匹配常用的fieldName，并给予权重
	 * 这里我们采用常见的搜索逻辑，即：当搜索框内输入一个词时，默认这个词可能是针对schema已定义
	 * 的所有field进行搜索，对搜索结果取并集（要去重吗？！）；当搜索框内输入多个词时
	 * 默认是各词的搜索结果取交集。
	 * @author lidanmax
	 * @param searchInput 搜索框输入
	 * @return
	 * */
	@RequestMapping(value="/search", method=RequestMethod.GET)
	@ResponseBody
	public String queryRichTextDocument(String searchInput){
		CommonResult<List<RichTextDocumentEntity>> response = richTextService
				.queryRichText(searchInput);
		List<RichTextDocumentEntity> resultList = response.getData();
		return new Gson().toJson(resultList);
	}
}
