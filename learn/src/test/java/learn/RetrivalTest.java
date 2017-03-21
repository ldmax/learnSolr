package learn;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.junit.Test;

import com.lidan.learn.pipeline.MyPipeline;
import com.lidan.learn.util.SolrUtil;
import com.lidan.learn.webMagic.RetrivalProcessor;

import us.codecraft.webmagic.Spider;

public class RetrivalTest {
	
	private HttpSolrClient solr = new HttpSolrClient(SolrUtil
			.getPropertyValueByKey("crawlerUrl"));
	
	@Test
	public void Test(){
		// 首先将待检索的网页保存到指定的目录
		Spider.create(new RetrivalProcessor())  
        .addUrl("http://localhost:8080/boco-health-ehrBrowser/accessIndex/login?idCard=510617198809183761&cardType=身份证")
        //.addPipeline(new MyPipeline("D:\\webmagic_data\\"))  
        .thread(5)  // 开启5个线程抓取  
        .run();  // 启动爬虫 
	}
}
