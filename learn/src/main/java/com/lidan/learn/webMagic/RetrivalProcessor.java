package com.lidan.learn.webMagic;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import com.lidan.learn.util.SolrUtil;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class RetrivalProcessor implements PageProcessor{
	
	private HttpSolrClient solr = new HttpSolrClient(SolrUtil
			.getPropertyValueByKey("crawlerUrl"));
	
	private Site site = new Site().setRetryTimes(3).setSleepTime(100) 
            //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
            .setDomain("http://localhost:8080/boco-health-ehrBrowser/");
            
            //添加抓包获取的cookie信息  
            //.addCookie("JSESSIONID", "938D12F74EADCED13B9A5225143D2C84")  
            //.addCookie("_ga", "GA1.1.696640647.1484477969")  
            //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
            //.addHeader("User-Agent",  
            //        "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36")  
            //.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")  
            //.addHeader("Accept-Encoding", "gzip, deflate, sdch, br").addHeader("Accept-Language", "zh-CN,zh;q=0.8")  
            //.addHeader("Connection", "keep-alive").addHeader("Referer", "http://localhost:8080/boco-health-ehrBrowser/accessIndex/login");  
  
    @Override  
    public void process(Page page) { 
    	// 处理页面
    	List<String> wtf = page.getHtml().xpath("//a").links().all();  // 爬虫将抓取页面上出现的a标签的那些页面
    	page.addTargetRequests(wtf);  // 将需要抓取的链接加入到目标请求列表
        page.putField("text", page.getHtml().xpath("//tbody").toString());  
        page.putField("title", page.getHtml().xpath("//div[@class=\"table_wrap clearfix\"]/h2"));
        String content = page.getHtml().xpath("//tbody").toString();
        String title = page.getHtml().xpath("//div[@class=\"table_wrap clearfix\"]/h2/text()").toString();
        String url = page.getUrl().toString();
        // 打包到SolrDocument对象中，上传到Solr服务器
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("url", url);
        doc.addField("title", title);
        doc.addField("content", content);
        try {
			solr.add(doc);
			solr.commit();
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		}
    }  
  
    @Override  
    public Site getSite() {  
        return site;  
    } 
}
