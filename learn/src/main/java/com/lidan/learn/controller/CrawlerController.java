package com.lidan.learn.controller;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.entity.WebPageEntity;
import com.lidan.learn.service.CrawlerService;


/**
 * 爬虫控制层
 * @author lidanmax
 * 2017/03/10
 * */
@Controller
@RequestMapping("/crawler")
public class CrawlerController {
	
	@Resource
	private CrawlerService crawlerService;  // 注入Service对象

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
	 * 向Solr服务器发出请求，获取已经检索的网站页面数据
	 * @param searchInput 搜索关键词字符串
	 * @return
	 * */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	@ResponseBody
	public CommonResult<List<WebPageEntity>> search(String searchInput, Model model){
		CommonResult<List<WebPageEntity>> response = crawlerService.queryWebPage(searchInput);
		/*List<WebPageEntity> resultList = response.getData();
		model.addAttribute("resultList", resultList);*/
		return response;
	}
}
