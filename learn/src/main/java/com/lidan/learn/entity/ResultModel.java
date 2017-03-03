package com.lidan.learn.entity;

import java.util.List;

/**
 * 用来返回结果，带分页参数
 * @author lidanmax
 * 2017/02/23
 * */
public class ResultModel {
	private List<KidInfoEntity> kidList;
	private Long recordCount;
	private Long pageCount;
	private Long currentPage;
	public List<KidInfoEntity> getKidList() {
		return kidList;
	}
	public void setKidList(List<KidInfoEntity> kidList) {
		this.kidList = kidList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public Long getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Long currentPage) {
		this.currentPage = currentPage;
	}
}
