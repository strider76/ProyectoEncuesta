package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.DificultyDAO;
import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;
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
	public void update(Integer idDificulty, DificultyDTO dto) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyDAO.findById(idDificulty);
		if (dificultySearch.isPresent()){
			Dificulty dificulty = dificultySearch.get();
			dificulty.setName(dto.getName());
			dificultyDAO.save(dificulty);
		} else {
			throw new DificultyNotFoundException("Dificulty no encontrada idDificulty('"+ idDificulty +"')");
		}
	}

	@Override
	public void delete(Integer idDificulty) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyDAO.findById(idDificulty);
		if (dificultySearch.isPresent())
			dificultyDAO.delete(dificultySearch.get());
		else
			throw new DificultyNotFoundException("Dificulty no encontrada idDificulty('"+ idDificulty +"')");
		
	}	
	
	@Override
	public Dificulty findById(Integer idDificult) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyDAO.findById(idDificult);
		if (dificultySearch.isPresent())
			return dificultySearch.get();
		else
			throw new DificultyNotFoundException("Dificulty no encontrada idDificulty('"+ idDificult +"')");
	}

	@Override
	public List<Dificulty> findAll(Pageable pagina) {
		return dificultyDAO.findAll(PageRequest.of(pagina.getPageNumber(), pagina.getPageSize())).stream().collect(Collectors.toList());
	}

	@Override
	public Dificulty findByName(String name) throws DificultyNotFoundException {
		Optional<Dificulty> dificultSearch = dificultyDAO.findOneByName(name);
		if (dificultSearch.isPresent())
			return dificultSearch.get();
		else
			throw new DificultyNotFoundException("Dificulty no encontrada name('"+ name +"')");
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

	@Override
	public List<Question> findQuestionsByDificulty(Pageable page,Integer idDificulty) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyDAO.findById(idDificulty);
		if (dificultySearch.isPresent())
			return subSet(page,dificultySearch.get().getQuestions());
		else
			throw new DificultyNotFoundException("Dificulty no encontrada idDificulty('"+ idDificulty +"')");
	}

	private List<Question> subSet (Pageable page, List<Question> inicial) {
		List<Question> list = new ArrayList<>(inicial);
		Integer posicionInicial = page.getPageSize()*page.getPageNumber();
		Integer posicionFinal = (list.size() > (posicionInicial+page.getPageSize()))?posicionInicial+page.getPageSize()-1:list.size()-1;
		return list.subList(posicionInicial, posicionFinal);
		
	}


}
