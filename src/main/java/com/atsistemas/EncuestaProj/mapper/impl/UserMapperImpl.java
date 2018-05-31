package com.atsistemas.EncuestaProj.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.mapper.UserMapper;
import com.atsistemas.EncuestaProj.model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Autowired
	DozerBeanMapper mapper;
	
	
	@Override
	public User userDtoToDao(UserDTO user) {
		return mapper.map(user, User.class);
	}

	@Override
	public UserDTOPost userDaoToDto(User user) {
		return mapper.map(user, UserDTOPost.class);
	}

	@Override
	public List<User> userGetDtoToDao(List<UserDTO> users) {
		List<User> result = new ArrayList<>();
		for (UserDTO userDTO : users) 
			result.add(mapper.map(userDTO, User.class));
		return result;
	}

	@Override
	public List<UserDTOPost> userGetDaoToDto(List<User> users) {
		List<UserDTOPost> result = new ArrayList<>();
		for (User userDAO : users) 
			result.add(mapper.map(userDAO, UserDTOPost.class));
		return result;
	}

}
