package com.lidan.learn.controller;

import java.lang.reflect.InvocationTargetException;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lidan.learn.entity.ResultModel;
import com.lidan.learn.service.KidInfoService;

/**
 * KidInfoEntity控制层
 * @author lidanmax
 * 2014/02/24
 * */
@Controller
@RequestMapping("/kid")
public class KidInfoController {
	
	@Resource
	private KidInfoService kidInfoService;  // 注入KidInfoService对象 
	
	/**
	 * 控制跳转
	 * @author lidanmax
	 * */
	@RequestMapping(value="/toSearch", method=RequestMethod.GET)
	public String toSearch(){
		return "/search";
	}
	
	/**
	 * 返回根据页面输入的关键字查询到的数据
	 * @author lidanmax
	 * @param queryString 页面输入的查询关键词拼成的语句，符合Solr查询语法
	 * @param mode Model对象
	 * @return ResultModel对象
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public ResultModel kidList(String queryString) throws NoSuchMethodException, 
		SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{  // 这里是拼接后的查询语句，形如neighborhood_committee_cn:居委会 AND name:孙
		ResultModel result = kidInfoService.search(queryString);
		return result;
	}
}
