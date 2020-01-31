package com.bridgelabz.spring.fundoo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;


@Configuration
public class RedishCacheConfig 
{		
	
		/*To define the connection settings between the application client and the Redis server instance,
		 *  we need to use a Redis client.There is a number of Redis client implementations available for Java.
 		In this tutorial, we'll use Jedis – a simple and powerful Redis client implementation. 
		it gives good and full support to the XML and java based configuration. */
	
		//First, using the Jedis client, we're defining a connectionFactory.
		@Bean
	    JedisConnectionFactory jedisConnectionFactory()
		{
			
	      JedisConnectionFactory jedisConnectionFactory=new JedisConnectionFactory();
	      
	      //this is deprecated methods. even if you dont use them its ohk.
	      jedisConnectionFactory.setHostName("localhost");
	      jedisConnectionFactory.setPort(6379);
	      
	      return new JedisConnectionFactory();
	     
	    }
		
		//RedisTemplate using the jedisConnectionFactory. This can be used for querying data with a custom repository. 
		@Bean
		public RedisTemplate<String, Object> redisTemplate() 
		{
			final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
			template.setConnectionFactory(jedisConnectionFactory());
			template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
			return template;
		}
		
		
}