package com.usersmanagement.controller;

import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.usersmanagement.model.UserEntity;
import com.usersmanagement.service.UserService;

@RestController
@RequestMapping("rest/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//static Logger logger = Logger.getLogger(UserController.class);

	@RequestMapping(value= "/save", method = RequestMethod.POST)
    public String saveUser(UserEntity user) {
		userService.save(user);
        return "hello world "; 
    }
	
	@RequestMapping(value= "/update", method = RequestMethod.PUT)
    public String updateUser(UserEntity entity) {
		userService.update(entity);
        return "hello world ";
    }
	
	@RequestMapping(value= "/hello", method = RequestMethod.GET)
    public String users() {
		//userService.update(entity);
		//logger.info("logging information for hello");
        return "hello world ";
    }
	
	
	@RequestMapping(value= "/delete", method = RequestMethod.DELETE)
    public String deleteUser(UserEntity entity) {
		userService.delete(entity);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public UserEntity findById(@PathVariable int id) {
		
        return userService.findOne(id);
    }
	
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteById(@PathVariable int id) {
		userService.deleteById(id);
        return "hello world " ;
    }
	
	@RequestMapping(value= "/all", method = RequestMethod.GET)
    public List<UserEntity> getUsers() {
		
        return userService.findAll();
    }
}
