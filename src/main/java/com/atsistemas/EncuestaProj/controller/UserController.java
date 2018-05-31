 package com.atsistemas.EncuestaProj.controller;


import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
import com.atsistemas.EncuestaProj.service.UserService;


@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	private final static Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping
	public List<UserDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
									@RequestParam(defaultValue="10", required=false) Integer size) {
		LOG.info("UserController findAll --Param page=" + page +" , size=" + size);
		return userMapper.userGetDaoToDto(userService.findAll(PageRequest.of(page, size)));
	}
	
	
	@GetMapping("/{idUser}")
	public UserDTOPost findById(@PathVariable Integer idUser) throws NotFoundException {
		return userMapper.userDaoToDto(userService.findById(idUser));
	}
	
	
	@PostMapping
	public UserDTOPost create(@RequestBody UserDTO user) throws NotFoundException{
		LOG.info("UserController create --Param user=" + user);
		return userMapper.userDaoToDto(userService.create(userMapper.userDtoToDao(user)));
		
	}

	@PutMapping("/{idUser}")
	public void update(@PathVariable Integer idUser, @RequestBody UserDTO userDTO) throws NotFoundException {
		userService.update(idUser, userDTO);
	}

	@DeleteMapping("/{idUser}")
	public void delete (@PathVariable Integer idUser) throws NotFoundException {
		userService.delete(idUser);
	}
	

}
