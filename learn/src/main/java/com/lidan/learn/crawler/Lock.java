package com.lidan.learn.crawler;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.solr.common.SolrInputDocument;

public class Lock {
	private static Lock lock ;  
    public static Lock getInstance(){  
        if(lock==null){  
            synchronized (Lock.class) {  
                if(lock==null){  
                    lock=new Lock();  
                }  
            }  
        }  
        return lock;  
    }  
    private Lock(){}  
    //爬取page数量  
    public int num = 0;  
    //提交次数  
    public int commitNum = 0;  
    //索引数据集-消费者模式  
    public LinkedBlockingQueue<SolrInputDocument> lstDocument = new LinkedBlockingQueue<SolrInputDocument>();  
}
