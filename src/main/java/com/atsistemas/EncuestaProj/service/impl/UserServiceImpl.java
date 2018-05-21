package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.UserDAO;
import com.atsistemas.EncuestaProj.model.Course;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.UserService;

@Service
public class UserServiceImpl implements UserService, InitializingBean {

	@Autowired
	UserDAO userDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();
		
	}

	@Override
	public void test() {
		User user = new User();
		user.setEmail("manuel@betangible.com");
		user.setUserName("strider");
		user.setPassword("1234");
		
		userDAO.save(user);
		Optional<User> userBusqueda = userDAO.findOneByEmail("manuel@betangible.com");
		LOG.info(userBusqueda.isPresent()? user.toString() : "Objeto user no encontrado");
		
	}
	
	@Override
	@Transactional
	public User addUser(User user) {
		User userSave = userDAO.save(user);
		LOG.info("UserServiceImpl - addUser: Añadido nuevo usuario: " + userSave.toString());
		return userSave;
	}

	@Override
	public Boolean removeUser(Integer idUser) {
		Optional<User> usuarioBuscar = userDAO.findById(idUser);
		if (usuarioBuscar.isPresent()){
			userDAO.delete(usuarioBuscar.get());
			LOG.info("UserServiceImpl - removeUser: Borrado Usuario con ID " + idUser);
			return true;
		} else {
			LOG.info("UserServiceImpl - removeUser: Error no existe Usuario con ID " + idUser);
			return false;
		}
			
		
	}

	@Override
	@Transactional
	public Optional<User> updateUser(Integer idUser, User user) {
		Optional<User> usuarioBuscar = userDAO.findById(idUser);
		if (usuarioBuscar.isPresent()){
			User userEncontrado =usuarioBuscar.get();
			userEncontrado.setUserName(user.getUserName());
			userEncontrado.setPassword(user.getPassword());
			userEncontrado.setEmail(user.getEmail());
			userEncontrado.setCourses(user.getCourses());
			userEncontrado.setResults(user.getResults());
			userEncontrado = userDAO.save(userEncontrado);
			LOG.info("UserServiceImpl - updateUser: Modificado Usuario " + userEncontrado);
			return Optional.of(userDAO.save(userEncontrado));
		} else {
			LOG.info("UserServiceImpl - updateUser: Error no encontrado Usuario con ID" + idUser);
			return Optional.empty();
		}
	}

	@Override
	public List<User> findAllUser() {
		List<User> allUsers = new ArrayList<>();
		Iterator<User> iterador = userDAO.findAll().iterator();
		while (iterador.hasNext())
			allUsers.add(iterador.next());
		return allUsers;
	}

	@Override
	public Optional<User> findUserById(Integer idUser) {
		return userDAO.findById(idUser);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return userDAO.findOneByEmail(email);
	}

	@Override
	public boolean userAccess(String userName, String passowrd) {
		Optional<User> user = userDAO.findOneByUserName(userName);
		if (user.isPresent()) 
			return user.get().getPassword().equals(passowrd);
		else
			return false;
	}

	@Override
	public List<Course> findAllCourse(Integer idUser) {
		List<Course> userCourses = new ArrayList<>();
		Optional<User> userSearch = userDAO.findById(idUser);
		if (userSearch.isPresent()) {
			userCourses = userSearch.get().getCourses();
		}
		return userCourses;
	}

}
