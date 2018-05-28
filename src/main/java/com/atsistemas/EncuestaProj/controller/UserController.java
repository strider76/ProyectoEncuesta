 package com.atsistemas.EncuestaProj.controller;

import java.util.Optional;
import java.util.Set;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.UserService;


@RestController
@RequestMapping(value="/user")
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
	
	
	@RequestMapping(value="/{idUser}", method= RequestMethod.GET)
	public UserDTOPost findById(@PathVariable Integer idUser) throws UserNotFoundException {
		if (userService.findById(idUser).isPresent())
			return userMapper.userDaoToDto(userService.findById(idUser).get());
		else
			throw new UserNotFoundException("No se encuentra el usuario con id " + idUser);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public UserDTOPost create(@RequestBody UserDTO user) throws NotFoundException{
		LOG.info("UserController create --Param user=" + user);
		return userMapper.userDaoToDto(userService.create(userMapper.userDtoToDao(user)));
		
	}

	@RequestMapping(value="/{idUser}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer idUser, @RequestBody UserDTO userDTO) throws NotFoundException {
		LOG.info("UserController update --Param idUser="+ idUser +" user=" + userDTO);
		Optional<User> userSearch = userService.findById(idUser);
		if (userSearch.isPresent()){
			userService.update(userSearch.get(), userDTO);
		} else {
			throw new UserNotFoundException("No se encuentra el usuario con id " + idUser);
		}
	
	}

	@RequestMapping(value="/{idUser}", method=RequestMethod.DELETE)
	public void delete (@PathVariable Integer idUser) throws UserNotFoundException {
		if (userService.findById(idUser).isPresent())
			userService.delete(userService.findById(idUser).get());
		else
			throw new UserNotFoundException("No se encuentra el usuario con id " + idUser);
	}
	

}
