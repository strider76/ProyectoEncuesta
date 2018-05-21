package com.atsistemas.EncuestaProj.controller;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private final static Logger LOG = LoggerFactory.getLogger(UserService.class);
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public User addUser(@RequestParam(name="username",required=true) String userName,
						@RequestParam(name="email",required=true) String email,
						@RequestParam(name="password", defaultValue="") String password
						) {
		LOG.info("RestController UserController AddUser (/user/add) PARAMS--  userName: " + userName + " , email: " + email + " , password: " + password);
		User user = new User();
		user.setUserName(userName);
		user.setEmail(email);
		user.setPassword(password);
		user=userService.addUser(user);
		LOG.info("RestController UserController AddUser (/user/add) Añadido -- " + user);
		return user;
		
	}
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	public List<User> findAllUsers() {
		return userService.findAllUser();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> controlClaves(Exception ex,WebRequest request) {
		return new ResponseEntity<Object>("User ya creado con mismo email o user name: " + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
}
