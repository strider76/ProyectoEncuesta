package com.atsistemas.EncuestaProj.controller;

import java.util.Set;

import javax.websocket.server.PathParam;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
import com.atsistemas.EncuestaProj.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	private final static Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(method= RequestMethod.GET)
	public Set<UserDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
									@RequestParam(defaultValue="10", required=false) Integer size) {
		LOG.info("UserController findAll --Param page=" + page +" , size=" + size);
		return userMapper.userGetDaoToDto(userService.findAll(PageRequest.of(page, size)));
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public UserDTOPost create(@RequestBody UserDTO user){
		LOG.info("UserController create --Param user=" + user);
		return userMapper.userDaoToDto(userService.create(userMapper.userDtoToDao(user)));
		
	}

	@RequestMapping(value="/{idUser}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer idUser, @RequestBody UserDTOPost user) {
		LOG.info("UserController update --Param user=" + user);
		userService.update(userMapper.userDtoToDao(user));
	
	}

	@RequestMapping(value="/{idUser}", method=RequestMethod.DELETE)
	public void delete (@PathVariable Integer idUser) {
		if (userService.findById(idUser).isPresent())
			userService.delete(userService.findById(idUser).get());
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> controlClaves(Exception ex,WebRequest request) {
		return new ResponseEntity<Object>("User ya creado con mismo email o user name: " + ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
	}
}
