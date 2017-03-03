package learn;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;

public class DataTableTest {
	@Test
    public void test() throws SolrServerException, IOException {
        SolrClient solrClient = new HttpSolrClient("http://localhost:8983/solr/solr_test");
        QueryResponse response = solrClient.query(new SolrQuery("region_detail:新疆"));
        SolrDocumentList results = response.getResults();
        for(SolrDocument document: results){
        	System.out.println(document.toString());
        }
        solrClient.close();
    }
}
