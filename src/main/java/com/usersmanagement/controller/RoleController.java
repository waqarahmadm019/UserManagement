package com.usersmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usersmanagement.model.RoleEntity;
import com.usersmanagement.service.RoleService;

@RestController
@RequestMapping("rest/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value= "/save", method = RequestMethod.POST)
    public String saveRole(RoleEntity role) {
		roleService.save(role);
        return "hello world "; 
    }
	
	@RequestMapping(value= "/update", method = RequestMethod.PUT)
    public String updateRole(RoleEntity entity) {
		roleService.update(entity);
        return "hello world ";
    }
	
	@RequestMapping(value= "/delete", method = RequestMethod.DELETE)
    public String deleteRole(RoleEntity entity) {
		roleService.delete(entity);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public RoleEntity findById(@PathVariable int id) {
		
        return roleService.findOne(id);
    }
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable int id) {
		roleService.deleteById(id);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/all", method = RequestMethod.GET)
    public List<RoleEntity> getRoles() {
		
        return roleService.findAll();
    }
}
