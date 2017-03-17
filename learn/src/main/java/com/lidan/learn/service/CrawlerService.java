package com.lidan.learn.service;

import java.util.List;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.entity.WebPageEntity;

/**
 * 搜索网页的Serivce层
 * @author lidanmax
 * 2017/03/13
 * */
public interface CrawlerService {
	CommonResult<List<WebPageEntity>> queryWebPage(String searchInput);
}
