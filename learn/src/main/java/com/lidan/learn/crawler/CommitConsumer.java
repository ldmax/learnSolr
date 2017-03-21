package com.lidan.learn.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

import com.lidan.learn.util.SolrUtil;

public class CommitConsumer implements Runnable{
	private HttpSolrClient solr = new HttpSolrClient(SolrUtil
			.getPropertyValueByKey("crawlerUrl")); 
    private List<SolrInputDocument> list=new LinkedList<SolrInputDocument>();  
      
    private int commit=0;  
    public void run() {  
        try {  
            SolrInputDocument doc=null;  
            while((doc=Lock.getInstance().lstDocument.take())!=null){  
                list.add(doc);  
                if(list.size()==1){  
                    commit++;     
                    solr.add(list);  
                    solr.commit();  
                    list.clear();  
                    System.out.println("提交次数："+commit);   
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (SolrServerException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
    }  
}
