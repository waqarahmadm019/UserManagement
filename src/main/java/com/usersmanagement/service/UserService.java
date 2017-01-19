package com.usersmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersmanagement.dao.UsersDao;
import com.usersmanagement.model.UserEntity;

@Service
public class UserService {
	
	@Autowired
	private UsersDao usersDao;
	
	
	public void save(UserEntity user){
		usersDao.save(user);
	}
	
	public UserEntity findOne( int id ){
	      return usersDao.findOne(id);   
	   }
	
	   public List< UserEntity > findAll(){
	      return usersDao.findAll();
	    		  
	   }
	 
	   public void update( UserEntity entity ){
		   usersDao.update(entity);    
	   }
	 
	   public void delete( UserEntity entity ){
		   usersDao.delete(entity);   
	   }
	   public void deleteById( int entityId ){
		   usersDao.deleteById(entityId);
	   }

}
