package com.atsistemas.EncuestaProj.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.DificultyDAO;
import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.service.DificultyService;

@Service
public class DificultyServiceImpl implements DificultyService, InitializingBean {

	@Autowired
	private DificultyDAO dificultyDAO;
	
	
	@Override
	public Dificulty create(Dificulty model) {
		return dificultyDAO.save(model);
	}

	@Override
	public Optional<Dificulty> findById(Integer idDificult) {
		return dificultyDAO.findById(idDificult);
	}

	@Override
	public Set<Dificulty> findAll(Pageable pagina) {
		return dificultyDAO.findAll(PageRequest.of(pagina.getPageNumber(), pagina.getPageSize())).stream().collect(Collectors.toSet());
	}

	@Override
	public void update(Dificulty model, DificultyDTO dto) {
		model.setName(dto.getName());
		dificultyDAO.save(model);
		
	}

	@Override
	public void delete(Dificulty model) {
		dificultyDAO.delete(model);
		
	}

	@Override
	public Optional<Dificulty> findByName(String name) {
		return dificultyDAO.findOneByName(name);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		iniciarDatos();
		
	}

	private void iniciarDatos() {
		if (!dificultyDAO.findOneByName("facil").isPresent()) { dificultyDAO.save(new Dificulty("facil")); }
		if (!dificultyDAO.findOneByName("medio").isPresent()) { dificultyDAO.save(new Dificulty("medio")); }
		if (!dificultyDAO.findOneByName("dificil").isPresent()) { dificultyDAO.save(new Dificulty("dificil")); }		
	}



}
