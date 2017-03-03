package com.lidan.learn.entity;

import java.util.Date;

/**
 * 富文本文件搜索返回结果的包装类
 * @author lidanmax
 * 2017/03/03
 * 注：由于不存入数据库，因此该类仅用于包装，不设id字段
 * */
public class RichTextDocumentEntity extends Entity {
	private String solrId;  // 用来唯一标识该文档的参数，通常使用文档标题
	private String title;  // 文档标题
	private String author;  // 作者
	private String documentFormat;  // 文档类型，如：application/pdf
	private Date createDate;  // 创建时间
	private String creator;  // 创建人
	private Date lastModifyDate;  // 最近一次修改时间
	private String modifier;  // 修改人
	private Integer pageNumber;  // 页数
	
	public String getSolrId() {
		return solrId;
	}
	public void setSolrId(String solrId) {
		this.solrId = solrId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDocumentFormat() {
		return documentFormat;
	}
	public void setDocumentFormat(String documentFormat) {
		this.documentFormat = documentFormat;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	@Override
	public String toString() {
		return "RichTextDocument [solrId=" + solrId + ", title=" + title + ", author=" + author + ", documentFormat="
				+ documentFormat + ", createDate=" + createDate + ", creator=" + creator + ", lastModifyDate="
				+ lastModifyDate + ", modifier=" + modifier + ", pageNumber=" + pageNumber + "]";
	}
}
