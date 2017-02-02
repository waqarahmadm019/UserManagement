package com.usersmanagement.dao;

import org.springframework.stereotype.Repository;

import com.usersmanagement.model.RoleEntity;

@Repository
public class RolesDao extends GenericDaoImplementation<RoleEntity>{
	
	public RolesDao(){
		super(RoleEntity.class);
	}

}
