package com.bridgelabz.spring.fundoo.user.repository;
import com.bridgelabz.spring.fundoo.user.model.RedisModel;
public interface IRedisRepository
{
	
	public void save(RedisModel redisModel);
	
	public void  delete(String token);
	
	public RedisModel findUser(String token);

}
