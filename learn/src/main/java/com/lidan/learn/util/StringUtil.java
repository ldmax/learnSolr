package com.lidan.learn.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来处理跟字符串有关的操作的工具类
 * @author lidanmax
 * 2017/03/14
 * */
public class StringUtil {
	/**
	 * 输入一个字符串，返回这个字符串用空格分割的结果
	 * @author lidanmax
	 * @param inputString 输入的字符串
	 * @return
	 * */
	public static List<String> devideBySpace(String inputString){
		List<String> result = new ArrayList<>();  // 结果
		if(inputString != null){
			String[] temp = inputString.split(" ");
		}
		
		return null;
	}
}
