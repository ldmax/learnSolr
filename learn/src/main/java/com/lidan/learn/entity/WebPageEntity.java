package com.lidan.learn.entity;

/**
 * 爬取的网页包装类
 * @author lidanmax
 * 2017/03/10
 * */
public class WebPageEntity extends Entity{
	private String id;  // 唯一标定一个页面的指标
	private String url;  // 页面url
	private String host;  // 页面host
	private String title;  // 页面标题
	private String author;  // 页面作者
	private String content;  // 页面内容（文本）
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "WebPageEntity [id=" + id + ", url=" + url + ", host=" + host + ", title=" + title + ", author=" + author
				+ ", content=" + content + "]";
	}
}
