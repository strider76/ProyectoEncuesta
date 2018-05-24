package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.UserDAO;
import com.atsistemas.EncuestaProj.dto.UserDTO;
import com.atsistemas.EncuestaProj.model.User;
import com.atsistemas.EncuestaProj.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User create(User model) {
		User result = userDAO.save(model);
		LOG.info("UserServiceImpl - create: " + result);
		return result;
	}

	@Override
	public Optional<User> findById(Integer id) {
		Optional<User> user = userDAO.findById(id);
		LOG.info("UserServiceImpl - findById(" + id +"): "+ (user.isPresent()?"Encontrado " + user.get():"No Encontrado"));
		return user;
	}

	@Override
	public Set<User> findAll(Pageable pagina) {
		int numPagina = pagina.getPageNumber();
		int sizePagina = pagina.getPageSize();
		Set<User> listUser = userDAO.findAll(PageRequest.of(numPagina, sizePagina)).stream().collect(Collectors.toSet());
		LOG.info("UserServiceImpl - findByAll(page=" + numPagina +",sizePagina=" + sizePagina +"): Total" + listUser.size());
		return listUser;
	}

	@Override
	public void update(User model,UserDTO dto) {
		LOG.info("UserServiceImpl - update --PARAMS model=" + model);
		model.setUserName(dto.getUserName());
		model.setPassword(dto.getPassword());
		model.setEmail(dto.getEmail());
		userDAO.save(model);
		LOG.info("UserServiceImpl - update --realizado model=" + model );
	}

	@Override
	public void delete(User model) {
		userDAO.delete(model);
		LOG.info("UserServiceImpl - delete(" + model +"): ");

	}

	@Override
	public boolean userAccess(String userName, String password) {
		Optional<User> user = userDAO.findOneByUserNameOrderByUserName(userName);
		return (user.isPresent()?user.get().getPassword().equals(password): false);
	}

}
