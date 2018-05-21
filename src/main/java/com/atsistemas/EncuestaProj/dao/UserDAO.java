package com.atsistemas.EncuestaProj.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.atsistemas.EncuestaProj.model.User;

@Repository
public interface UserDAO extends PagingAndSortingRepository<User, Integer> {

	Optional<User> findOneByEmail(String email);
	Optional<User> findOneByUserName(String userName);
	
}
