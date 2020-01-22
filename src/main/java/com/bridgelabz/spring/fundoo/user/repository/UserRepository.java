
package com.bridgelabz.spring.fundoo.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.spring.fundoo.user.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Object /*String*/> 
{
	/* Java 8 - Optional Class. Optional is a container object which may or may not contain a non-null value.
	 * You must import java. util package to use this class.
	 * If a value is present, isPresent() will return true and get() will return the value.*/
	
	User findByEmail(String email);

	/*Adavantages of using JPA =========>
	 * you just need to define a method on your repository interface with a name that starts with find…By. 
	 * Spring then parses the method name and creates a query for it.
	 * 
	 * Internally, Spring generates a JPQL(The Java Persistence Query Language) query based on the method name, 
	 * sets the provided method parameters as bind parameter values, executes the query and returns the result.
	 * 
	 * That means that you no longer need to implement basic read or write operations. 
	 * 
	 * writing the standard CRUD operations for each entity creates a lot of repetitive code.
	 *  Spring Data JPA provides you a set of repository interfaces which you only need to 
	 *  extend to define a specific repository for one of your entities.*/
	
}
