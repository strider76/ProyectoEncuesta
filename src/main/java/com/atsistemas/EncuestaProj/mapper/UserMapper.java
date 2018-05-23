package com.atsistemas.EncuestaProj.mapper;


import java.util.Set;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.model.User;

public interface UserMapper {

	User userDtoToDao(UserDTO user);
	UserDTOPost userDaoToDto(User user);
	
	Set<User> userGetDtoToDao(Set<UserDTO> users);
	Set<UserDTOPost> userGetDaoToDto(Set<User> users);
	
}
