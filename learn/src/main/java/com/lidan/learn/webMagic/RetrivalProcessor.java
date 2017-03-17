package com.lidan.learn.webMagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class RetrivalProcessor implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100)  
            //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
            .setDomain("http://localhost:8080/boco-health-ehrBrowser/")  
            //添加抓包获取的cookie信息  
            .addCookie("JSESSIONID", "5A0B6ACA82B8D3F5EA9EF7E986989C32")  
            .addCookie("_ga", "GA1.1.696640647.1484477969")  
            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
            .addHeader("User-Agent",  
                    "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")  
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")  
            .addHeader("Accept-Encoding", "gzip, deflate, sdch, br").addHeader("Accept-Language", "zh-CN,zh;q=0.8")  
            .addHeader("Connection", "keep-alive").addHeader("Referer", "http://localhost:8080/boco-health-ehrBrowser/accessIndex/login");  
  
    @Override  
    public void process(Page page) {  
        page.putField("content", page.getHtml().smartContent());  
    }  
  
    @Override  
    public Site getSite() {  
        return site;  
    } 
}
