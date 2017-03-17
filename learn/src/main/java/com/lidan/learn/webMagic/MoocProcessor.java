package com.lidan.learn.webMagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class MoocProcessor implements PageProcessor{
	private Site site = new Site().setRetryTimes(3).setSleepTime(100)  
            //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
            .setDomain("www.imooc.com")  
            //添加抓包获取的cookie信息  
            .addCookie("Hm_lpvt_f0cfcccd7b1393990c78efdeebff3968", "1489740560")  
            .addCookie("Hm_lvt_f0cfcccd7b1393990c78efdeebff3968", "1488606309,1489740073")  
            .addCookie("PHPSESSID", "8ogqrnlrhc4k8id4ejdv0qdhp3")  
            .addCookie("apsid",  
                    "E2MWU1NWE0OTMwMmYxYWQ3MDlhN2EyOTVhN2ZjY2MAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANTA0NzgzMwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABsaWRhbm1heEAxNjMuY29tAAAAAAAAAAAAAAAAAAAAADc4YTVhOTZkZDc5OWFkZDVjZjc3OGJiYjg4OGZjMDUy1KLLWNSiy1g%3DND")  
            .addCookie("cvde", "58cba1270d297-34").addCookie("imooc_isnew", "2")  
            .addCookie("imooc_isnew_ct", "1488606312").addCookie("imooc_uuid", "ba2277f0-892c-47f6-b926-01646330e994")  
            .addCookie("last_login_username", "lidanmax@163.com").addCookie("loginstate", "1")  
            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
            .addHeader("User-Agent",  
                    "ozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.516.400 QQBrowser/9.4.8188.400")  
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")  
            .addHeader("Accept-Encoding", "gzip, deflate, sdch").addHeader("Accept-Language", "zh-CN,zh;q=0.8")  
            .addHeader("Connection", "keep-alive").addHeader("Referer", "http://www.imooc.com/");  
  
    @Override  
    public void process(Page page) {  
        page.putField("aboutme", page.getHtml().xpath("//textarea[@id='aboutme']/text()").toString());  
    }  
  
    @Override  
    public Site getSite() {  
        return site;  
    } 
}
