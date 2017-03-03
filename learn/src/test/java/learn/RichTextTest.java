package learn;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.Test;

public class RichTextTest {
	@Test
	public void test() throws SolrServerException, IOException{
		// 放置待检索的pdf文件的路径
		String fileName = "C:\\Users\\lidanmax\\Desktop\\rich_text_test\\idea使用手册.docx";
		String solrId = "idea使用手册.docx";
		indexFilesSolrCell(fileName, solrId);
	}
	
	/**
	 * Method to index all types of files into Solr. 
	 * @param fileName
	 * @param solrId
	 * @throws IOException
	 * @throws SolrServerException
	 */
	public static void indexFilesSolrCell(String fileName, String solrId) 
	  throws IOException, SolrServerException {  
	  
 	  String urlString = "http://localhost:8983/solr/rich_text"; 
	  HttpSolrClient solr = new HttpSolrClient(urlString);
	  
	  ContentStreamUpdateRequest up 
	    = new ContentStreamUpdateRequest("/update/extract");
	  up.addFile(new File(fileName), urlString);
	  
	  up.setParam("literal.id", solrId);
	  up.setParam("uprefix", "attr_");
	  up.setParam("fmap.content", "attr_content");
	  
	  up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);
	  
	  solr.request(up);
	  
	  QueryResponse rsp = solr.query(new SolrQuery("*:*"));
	  
	  System.out.println(rsp);
	  
	  solr.close();
	}
}
