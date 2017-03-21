package com.lidan.learn.crawler;


import java.util.regex.Pattern;

import org.apache.solr.common.SolrInputDocument;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class WomiCrawler extends WebCrawler{
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|bmp|gif|jpe?g" + "|png|tiff?|mid|mp2|mp3|mp4"  
            + "|wav|avi|mov|mpeg|ram|m4v|pdf" + "|rm|smil|wmv|swf|wma|zip|rar|gz))$");  
    //页面前缀  
    private final static String URL_PREFIX = "http://www.xnjz.com/";  // 这里是待爬网站    
  
    /** 
     * shouldVisit是判断当前的URL是否已经应该被爬取（访问） 
     */  
    @Override  
    public boolean shouldVisit(Page referencePage, WebURL url) {  
        String href = url.getURL().toLowerCase();  
        return !FILTERS.matcher(href).matches() && href.startsWith(URL_PREFIX);  
    }  
  
    /** 
     * visit则是爬取该URL所指向的页面的数据，其传入的参数即是对该web页面全部数据的封装对象Page。 
     * @param page 页面
     * @return
     */  
    @Override  
    public  void  visit(Page page) {  
        try {  

        	
            SolrInputDocument doc=new SolrInputDocument();  
            int docid = page.getWebURL().getDocid();  
            String url = page.getWebURL().getURL();  
            String parentUrl = page.getWebURL().getParentUrl();  
            String anchor = page.getWebURL().getAnchor();
            
            doc.addField("id", docid+"");  
            doc.addField("url", url+"");  
            doc.addField("host", url+"");  
            doc.addField("title", anchor+"");  
            doc.addField("author", anchor+"");  
            System.out.println("Docid: " + docid);  
            System.out.println("URL: " + url);  
            System.out.println("Parent page: " + parentUrl);  
            System.out.println("anchor: " + anchor);  
            if (page.getParseData() instanceof HtmlParseData) {  
                HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();  
                String text = htmlParseData.getText();
                System.out.println("text: " + text);
                doc.addField("content", text); 
            }             
            Lock lock = Lock.getInstance();               
            lock.lstDocument.add(doc);  
            lock.num++;  
            System.out.println("爬虫次数: num ==" + lock.num);  
          
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}
