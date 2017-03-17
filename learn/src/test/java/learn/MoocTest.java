package learn;

import org.junit.Test;

import com.lidan.learn.webMagic.MoocProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

public class MoocTest {
	@Test
	public void Test(){
		Spider.create(new MoocProcessor())  
        // 从"http://www.imooc.com/user/setprofile"开始抓  
        .addUrl("http://www.imooc.com/user/setprofile").addPipeline(new ConsolePipeline())  
        // 开启5个线程抓取  
        .thread(1)  
        // 启动爬虫  
        .run(); 
	}
}
