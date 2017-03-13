package com.lidan.learn.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.solr.common.SolrDocument;

import com.lidan.learn.common.CommonResult;
import com.lidan.learn.entity.Entity;

/**
 * 操作Entity子类的工具方法类
 * @author lidanmax
 * 2017/
 * */
public class EntityUtil {
	/**
	 * 给定一个Entity的子类，返回其对象的所有field名List
	 * @author lidanmax
	 * @param entity Entity子类对象
	 * @return Entity对象的所有field名List
	 * */
	public static List<String> getFieldNameList(Entity entity){
		List<String> result = new ArrayList<>();
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			result.add(field.getName());
		}
		return result;
	}
	
	/**
	 * 为方便从document中获取各属性值set到数据库表Entity对象中写的方法
	 * @author lidanmax
	 * @param entity 用来接属性值的Entity对象
	 * @param document SolrDocument对象
	 * @return  
	 * */
	public static CommonResult setFieldsFromDocumentForDataBase(Entity entity, SolrDocument document){
		CommonResult result = new CommonResult();
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// 1. 从这个Entity对象的各Field名获得数据库中的列名
		List<String> columnNameList = transferCamelToColumn(entity);
		// 2. 获得该Entity对象的各Field的类型名简写
		List<Class> fieldTypeList = getFieldTypeList(entity);
		// 3. 获得该Entity对象的各set方法名
		List<String> setterNameList = getSetterNameList(entity);
		try{
			for(int i=0; i<fields.length; i++){
				// 4. 然后kid.setId((Integer)document.get("id"));
				Method method = clazz.getDeclaredMethod(setterNameList.get(i), fieldTypeList.get(i));
				if(document.get(columnNameList.get(i)) != null){
					Class type = fieldTypeList.get(i);
					if("String".equals(type.getSimpleName())){
						method.invoke(entity, String.valueOf(((ArrayList<String>)document
								.get(columnNameList.get(i))).get(0))); 
					}else if("Integer".equals(type.getSimpleName())){
						method.invoke(entity, Integer.valueOf((String)document.get(columnNameList.get(i))));
					}else if("List".equals(type.getSimpleName())){
						method.invoke(entity, (List<String>)document.get(columnNameList.get(i)));
					}
				}
			}
			result.setMessage("Set fields from documents succeeded.");
			result.setSuccess(true);
		}catch(IllegalAccessException e){
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (IllegalArgumentException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (InvocationTargetException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (NoSuchMethodException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (SecurityException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 为方便从document中获取各属性值set到富文本文件Entity对象中写的方法
	 * @author lidanmax
	 * @param entity 用来接属性值的Entity对象
	 * @param document SolrDocument对象
	 * @return  
	 * */
	public static CommonResult setFieldsFromDocumentForRichText(Entity entity, SolrDocument document){
		CommonResult result = new CommonResult();
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		// 1. 从这个Entity对象的各Field名获得数据库中的列名
		List<String> columnNameList = getFieldNameList(entity);
		// 2. 获得该Entity对象的各Field的类型名简写
		List<Class> fieldTypeList = getFieldTypeList(entity);
		// 3. 获得该Entity对象的各set方法名
		List<String> setterNameList = getSetterNameList(entity);
		try{
			for(int i=0; i<fields.length; i++){
				// 4. 然后kid.setId((Integer)document.get("id"));
				Method method = clazz.getDeclaredMethod(setterNameList.get(i), fieldTypeList.get(i));
				if(document.get(columnNameList.get(i)) != null){
					Class type = fieldTypeList.get(i);
					if("String".equals(type.getSimpleName())){
						method.invoke(entity, String.valueOf(document
								.get(columnNameList.get(i)))); 
					}else if("List".equals(type.getSimpleName())){
						method.invoke(entity, (List<String>)document.get(columnNameList.get(i)));
					}
				}
			}
			result.setMessage("Set fields from documents succeeded.");
			result.setSuccess(true);
		}catch(IllegalAccessException e){
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (IllegalArgumentException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (InvocationTargetException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (NoSuchMethodException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		} catch (SecurityException e) {
			result.setMessage("Set fields from documents failed. The stack trace is: " + e.getMessage());
			result.setSuccess(false);
		}
		return result;
	}
	
	/**
	 * 将驼峰转为下划线的形式，如：healthFileCode转为health_file_code
	 * @author lidanmax
	 * @param entity Entity对象
	 * @return 已转为下划线的Entity属性名字符串List
	 * 2017/02/23 17:40测试通过
	 * */
	public static List<String> transferCamelToColumn(Entity entity){
		// 用来装结果的List对象
		List<String> resultList = new ArrayList<>();
		// 获得entity的各属性字符串并从驼峰转为数据库命名
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			String fieldName = field.getName();
			char[] charArray = fieldName.toCharArray();
			StringBuilder stringBuilder = new StringBuilder();
			for(int i=0; i<charArray.length; i++){
				if(charArray[i] >= 'A' && charArray[i] <= 'Z'){  // 那么此时c是大写字母，需要转为小写
					charArray[i] = (char)(charArray[i] + 32);  // 将大写字母转为小写字母
					stringBuilder.append("_");  // 在转为小写字母的前面加上连字符号
					stringBuilder.append(charArray[i]);
					i++;
				}
				stringBuilder.append(charArray[i]);
			}
			resultList.add(stringBuilder.toString());
		}
		return resultList;
	}
	
	/**
	 * 获得Entity对象的各Field类型名简写
	 * @author lidanmax
	 * @param entity Entity对象
	 * @return 类型名简写List
	 * 2017/02/23 17:57测试通过
	 * */
	public static List<Class> getFieldTypeList(Entity entity){
		// 用来装结果的列表
		List<Class> resultList = new ArrayList<>();
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			resultList.add(field.getType());
		}
		return resultList;
	}
	
	/**
	 * 获得Entity对象各属性的set方法名
	 * @author lidanmax
	 * @param entity Entity对象
	 * @return Entity对象各属性set方法名
	 * 201/02/24 16:19测试通过
	 * */
	public static List<String> getSetterNameList(Entity entity){
		// 装方法名的List
		List<String> resultList = new ArrayList<>();
		Class<? extends Entity> clazz = entity.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			String methodName = "set" + field.getName().substring(0, 1)
					.toUpperCase() + field.getName().substring(1);
			resultList.add(methodName);
		}
		return resultList;
	}
}
