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
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.UserNotFoundException;
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
	public User findById(Integer id) throws NotFoundException {
		Optional<User> userSearch = userDAO.findById(id);
		LOG.info("UserServiceImpl - findById(" + id +"): "+ (userSearch.isPresent()?"Encontrado " + userSearch.get():"No Encontrado"));
		if (userSearch.isPresent())
			return userSearch.get();
		else
			throw new UserNotFoundException("User no encontrado idUser('"+ id +"')");
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
	public void update(Integer idUser,UserDTO dto) throws UserNotFoundException {
		LOG.info("UserServiceImpl - update --PARAMS idUser="+ idUser +" dto=" + dto);
		Optional<User> userSearch = userDAO.findById(idUser);
		if (userSearch.isPresent()) {
			User user = userSearch.get();
			user.setUserName(dto.getUserName());
			user.setPassword(dto.getPassword());
			user.setEmail(dto.getEmail());
			userDAO.save(user);
			LOG.info("UserServiceImpl - update --realizado model=" + user );
		} else
			throw new UserNotFoundException("User no encontrado idUser('"+ idUser +"')");
	}

	@Override
	public void delete(Integer idUser) throws NotFoundException {
		Optional<User> userSearch = userDAO.findById(idUser);
		if (userSearch.isPresent()) 
			userDAO.delete(userSearch.get());
		else
			throw new UserNotFoundException("User no encontrado idUser('"+ idUser +"')");
	}

	@Override
	public boolean userAccess(String userName, String password) {
		Optional<User> user = userDAO.findOneByUserNameOrderByUserName(userName);
		return (user.isPresent()?user.get().getPassword().equals(password): false);
	}

}
