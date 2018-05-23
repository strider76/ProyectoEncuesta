package com.atsistemas.EncuestaProj.service;

import com.atsistemas.EncuestaProj.model.User;

public interface UserService extends AbstractService<User, Integer> {

	boolean userAccess(String userName, String password);
	
}
