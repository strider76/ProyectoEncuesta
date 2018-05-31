package com.atsistemas.EncuestaProj.mapper;


import java.util.List;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.dto.UserDTOPost;
import com.atsistemas.EncuestaProj.model.User;

public interface UserMapper {

	User userDtoToDao(UserDTO user);
	UserDTOPost userDaoToDto(User user);
	
	List<User> userGetDtoToDao(List<UserDTO> users);
	List<UserDTOPost> userGetDaoToDto(List<User> users);
	
}
