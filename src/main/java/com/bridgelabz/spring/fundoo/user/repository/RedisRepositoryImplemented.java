package com.bridgelabz.spring.fundoo.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.bridgelabz.spring.fundoo.notes.nexception.custom.IdNotFoundException;
import com.bridgelabz.spring.fundoo.notes.nexception.custom.InvalidUserException;
import com.bridgelabz.spring.fundoo.notes.nutitlity.NoteUtility;
import com.bridgelabz.spring.fundoo.user.model.RedisModel;
import com.bridgelabz.spring.fundoo.user.model.User;

@Repository
public class RedisRepositoryImplemented implements IRedisRepository 
{	
	
	
	
	@SuppressWarnings("rawtypes")
	private HashOperations hashoperations;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate<String , Object> redistemplate;

	public RedisRepositoryImplemented() {
		super();
	}
	
	@Autowired
	public RedisRepositoryImplemented( RedisTemplate<String , Object> redistemplate) {
		super();
		
		this.redistemplate = redistemplate;
		this.hashoperations = this.redistemplate.opsForHash();
	}
	
	@Override
    @SuppressWarnings("unchecked")
	public void save( RedisModel redisModel ){
		System.out.println( redisModel.toString() );
    	hashoperations.put(redisModel.getToken(), "USER",redisModel);
    	
    }
    
	@Override
	 @SuppressWarnings("unchecked")
	public void delete(String token){
		 hashoperations.delete( token ,"USER");
		
		
    }

	@Override
	public RedisModel findUser(String token) 
	{
		return (RedisModel) hashoperations.get( token ,"USER");
	}



	

}
