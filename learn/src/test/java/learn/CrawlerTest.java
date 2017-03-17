package learn;

import java.net.MalformedURLException;

import org.junit.Test;

import com.lidan.learn.crawler.CommitConsumer;
import com.lidan.learn.crawler.WomiCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.authentication.AuthInfo;
import edu.uci.ics.crawler4j.crawler.authentication.FormAuthInfo;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;


public class CrawlerTest {
	@Test
	public void test(){
		String crawlStorageFolder = "E:\\crawl";  // 设置数据存放路径
	    int numberOfCrawlers = 5;  
	    CrawlConfig config = new CrawlConfig();  
	    
	    // 文明请求web：确保我们不发送超过1每秒请求数（1000毫秒之间的请求）。  
	    config.setPolitenessDelay(1000);      
	    // 深度，即从入口URL开始算，URL是第几层。如入口A是1，从A中找到了B，B中又有C，则B是2，C是3   
	    config.setMaxDepthOfCrawling(5);  
	      
	    // 设置最大的抓取页面数。默认值为1，页面的数量不限  
	    config.setMaxPagesToFetch(50);  
 
	    config.setResumableCrawling(false);  
	      
	    config.setCrawlStorageFolder(crawlStorageFolder);
	    // 增加自动登录；这里将登录写死为：使用身份证登录，且登录身份证号为510617198809183761
	    AuthInfo authInfo = null;
	    try {
			authInfo = new FormAuthInfo("身份证", "510617198809183761", 
					"http://localhost:8080/boco-health-ehrBrowser/accessIndex/login", "cardType", "idCard");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	    config.addAuthInfo(authInfo);
	    
	    PageFetcher pageFetcher = new PageFetcher(config); 
	   
	    
	    RobotstxtConfig robotstxtConfig = new RobotstxtConfig();  
	    RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);  
	    CrawlController controller = null;
		try {
			controller = new CrawlController(config, pageFetcher, robotstxtServer);
		} catch (Exception e) {
			e.printStackTrace();
		}  
	    controller.addSeed("http://localhost:80/boco-health-ehrBrowser/hypertension/getHypertensionInfo?healthFileCode=659004002001000003");
	    controller.addSeed("http://localhost:80/boco-health-ehrBrowser/hospitalization/homeCase?jzlsh=1");
	    CommitConsumer consumer=new CommitConsumer();  
	    new Thread(consumer).start();     
	    controller.start(WomiCrawler.class, numberOfCrawlers);
	}
}
