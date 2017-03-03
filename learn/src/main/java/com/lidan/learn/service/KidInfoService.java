package com.lidan.learn.service;

import java.lang.reflect.InvocationTargetException;

import com.lidan.learn.entity.ResultModel;

/**
 * KidInfoEntity查询接口
 * @author lidanmax
 * 2017/02/24
 * */
public interface KidInfoService {
	ResultModel search(String queryString) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
