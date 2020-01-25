package com.bridgelabz.spring.fundoo.config;

import java.net.UnknownHostException;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ElasticsearchConfig 
{

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() throws UnknownHostException 
    {
    	
    	RestHighLevelClient client = null;
    	
        client = new RestHighLevelClient(
		    RestClient.builder(new HttpHost("localhost" ,9200 ,"http") ) );

        return client;

    }
    
    
   


}