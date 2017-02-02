package com.usersmanagement.dao;

import org.springframework.stereotype.Repository;

import com.usersmanagement.model.PermissionEntity;

@Repository
public class PermissionsDao extends GenericDaoImplementation<PermissionEntity>{
	
	public PermissionsDao(){
		super(PermissionEntity.class);
	}

}
