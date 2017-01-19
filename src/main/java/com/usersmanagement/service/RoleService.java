package com.usersmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersmanagement.dao.RolesDao;
import com.usersmanagement.model.RoleEntity;

@Service
public class RoleService {
	
	
	@Autowired
	private RolesDao rolesDao;
	
	
	public void save(RoleEntity user){
		rolesDao.save(user);
	}
	
	public RoleEntity findOne( int id ){
	      return rolesDao.findOne(id);   
	   }
	
	   public List< RoleEntity > findAll(){
	      return rolesDao.findAll();
	    		  
	   }
	 
	   public void update( RoleEntity entity ){
		   rolesDao.update(entity);    
	   }
	 
	   public void delete( RoleEntity entity ){
		   rolesDao.delete(entity);   
	   }
	   public void deleteById( int entityId ){
	      rolesDao.deleteById(entityId);
	   }


}
