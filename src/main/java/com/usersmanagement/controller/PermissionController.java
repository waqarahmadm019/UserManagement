package com.usersmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usersmanagement.model.PermissionEntity;
import com.usersmanagement.service.PermissionService;

@RestController
@RequestMapping("rest/permissions")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
    public String savePermission(PermissionEntity permission) {
		permissionService.save(permission);
        return "hello world "; 
    }
	
	@RequestMapping(value= "/update", method = RequestMethod.PUT)
    public String updatePermission(PermissionEntity entity) {
		permissionService.update(entity);
        return "hello world ";
    }
	
	@RequestMapping(value= "/delete", method = RequestMethod.DELETE)
    public String deletePermission(PermissionEntity entity) {
		permissionService.delete(entity);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public PermissionEntity findById(@PathVariable int id) {
		
        return permissionService.findOne(id);
    }
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable int id) {
		permissionService.deleteById(id);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/all", method = RequestMethod.GET)
    public List<PermissionEntity> getPermissions() {
		
        return permissionService.findAll();
    }
}
