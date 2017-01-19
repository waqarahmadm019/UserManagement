package com.usersmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usersmanagement.dao.PermissionsDao;
import com.usersmanagement.model.PermissionEntity;

@Service
public class PermissionService {
	
	@Autowired
	private PermissionsDao permissionsDao;
	
	
	public void save(PermissionEntity permission){
		permissionsDao.save(permission);
	}
	
	public PermissionEntity findOne( int id ){
	      return permissionsDao.findOne(id);   
	   }
	
	   public List< PermissionEntity > findAll(){
	      return permissionsDao.findAll();
	    		  
	   }
	 
	   public void update( PermissionEntity entity ){
		   permissionsDao.update(entity);    
	   }
	 
	   public void delete( PermissionEntity entity ){
		   permissionsDao.delete(entity);   
	   }
	   public void deleteById( int entityId ){
	      permissionsDao.deleteById(entityId);
	   }


}
