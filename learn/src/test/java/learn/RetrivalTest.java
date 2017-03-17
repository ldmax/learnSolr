package learn;

import org.junit.Test;

import com.lidan.learn.webMagic.RetrivalProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class RetrivalTest {
	@Test
	public void Test(){
		Spider.create(new RetrivalProcessor())  
        // 从"http://www.imooc.com/user/setprofile"开始抓  
        .addUrl("http://localhost:8080/boco-health-ehrBrowser/hypertension/getHypertensionInfo?healthFileCode=659004002001000003").addPipeline(new ConsolePipeline())  
        // 开启5个线程抓取  
        .thread(1)  
        // 启动爬虫  
        .run(); 
	}
}
