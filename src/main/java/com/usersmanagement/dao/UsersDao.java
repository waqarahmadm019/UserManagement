package com.usersmanagement.dao;

//import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.usersmanagement.model.UserEntity;

@Repository
//@Transactional
public class UsersDao extends GenericDaoImplementation<UserEntity>{
	
	public UsersDao(){
		super(UserEntity.class);
		
	}
	
	public String sayHello(){
		UserEntity user = new UserEntity();
		user.setEmail("waqar1@gmail.com");
		user.setManagerId(0);
		user.setPassword("password1");
		//user.se
		this.save(user);
		return "Users Dao";
	}
	
	

}
