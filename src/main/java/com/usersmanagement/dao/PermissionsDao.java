package com.usersmanagement.dao;

import com.usersmanagement.model.PermissionEntity;

public class PermissionsDao extends GenericDaoImplementation<PermissionEntity>{
	
	public PermissionsDao(){
		super(PermissionEntity.class);
	}

}
