package com.atsistemas.EncuestaProj.mapper;

import java.util.HashSet;
import java.util.Set;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
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
	public Set<User> userGetDtoToDao(Set<UserDTO> users) {
		Set<User> result = new HashSet<>();
		for (UserDTO userDTO : users) 
			result.add(mapper.map(userDTO, User.class));
		return result;
	}

	@Override
	public Set<UserDTOPost> userGetDaoToDto(Set<User> users) {
		Set<UserDTOPost> result = new HashSet<>();
		for (User userDAO : users) 
			result.add(mapper.map(userDAO, UserDTOPost.class));
		return result;
	}

}
