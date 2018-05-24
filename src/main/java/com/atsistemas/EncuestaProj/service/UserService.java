package com.atsistemas.EncuestaProj.service;

import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.model.User;

public interface UserService extends AbstractService<User,UserDTO, Integer> {

	boolean userAccess(String userName, String password);
	
}
