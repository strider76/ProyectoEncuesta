package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;

public interface UserService {

	public void test();
	public User addUser(User user);
	public Boolean removeUser(Integer idUser);
	public Optional<User> updateUser(Integer idUser, User user);
	public List<User> findAllUser();
	public Optional<User> findUserById(Integer idUser);
	public Optional<User> findUserByEmail(String email);
	public boolean userAccess(String userName, String passowrd);
	public List<Course> findAllCourse(Integer idUser);
	
}
