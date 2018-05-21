package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.EncuestaProj.dao.DificultadDAO;
import com.atsistemas.EncuestaProj.model.Dificultad;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.service.DificultadService;

@Service
public class DificultadServiceImpl implements DificultadService, InitializingBean {

	@Autowired
	private DificultadDAO dificultadDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(DificultadServiceImpl.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();

	}

	@Override
	@Transactional
	public void test() {
		Dificultad dificultad1 = new Dificultad();
		Dificultad dificultad2 = new Dificultad();
		Dificultad dificultad3 = new Dificultad();
		
		dificultad1.setDificultad("facil");
		dificultad2.setDificultad("intermedio");
		dificultad3.setDificultad("dificil");
		
		LOG.info("DicicultadServiceImpl - test() - add Dificultad 1 -" + dificultadDAO.save(dificultad1));
		LOG.info("DicicultadServiceImpl - test() - add Dificultad 2 -" + dificultadDAO.save(dificultad2));
		LOG.info("DicicultadServiceImpl - test() - add Dificultad 3 -" + dificultadDAO.save(dificultad3));
	}	
	
	@Override
	public Optional<Dificultad> findByIdDificultad(Integer idDificultad) {
		
		LOG.info("DificultadServiceImpl - findByIdDificultad , PARAMS -- idDificultad: " + idDificultad);
		Optional<Dificultad> dificultad = dificultadDAO.findById(idDificultad);
		if (dificultad.isPresent())
			LOG.info("DificultadServiceImpl - findByIdDificultad , Encontrada Dificultad(" + idDificultad + ") -- " + dificultad.get());
		else
			LOG.info("DificultadServiceImpl - findByIdDificultad , No Encontrada Dificultad(" + idDificultad + ") ");
		return dificultad;

	}

	@Override
	public Optional<Dificultad> findByDificultad(String dificultadBuscada) {
		
		LOG.info("DificultadServiceImpl - findByDificultad , PARAMS -- dificultad: " + dificultadBuscada);
		Optional<Dificultad> dificultad = dificultadDAO.findOneByDificultad(dificultadBuscada);
		if (dificultad.isPresent())
			LOG.info("DificultadServiceImpl - findByDificultad , Encontrada Dificultad(" + dificultadBuscada + ") -- " + dificultad.get());
		else
			LOG.info("DificultadServiceImpl - findByDificultad , No Encontrada Dificultad(" + dificultadBuscada + ") ");
		return dificultad;
	}

	@Override
	public List<Dificultad> getAllDificultad() {

		LOG.info("DificultadServiceImpl - getAllDificultad()");
		List<Dificultad> allDificultad = new ArrayList<>();
		for (Dificultad dificultad : dificultadDAO.findAll()) {
			LOG.info("DificultadServiceImpl - getAllDificultad() -- " + dificultad);
			allDificultad.add(dificultad);
		}
	
		return allDificultad;
		
	}

	@Override
	public List<Pregunta> getAllPreguntasByIdDificultad(Integer idDificultad) {
		
		List<Pregunta> allDificultad = new ArrayList<>();
		LOG.info("DificultadServiceImpl - getAllPreguntasByIdDificultad() --PARAMS idDificultad: " + idDificultad);
		Optional<Dificultad> dificultad = dificultadDAO.findById(idDificultad);
		if (dificultad.isPresent()) {
			allDificultad = dificultad.get().getPreguntas();
		}
		return allDificultad;

	}

	@Override
	@Transactional
	public Dificultad addDificultad(Dificultad dificultad) {
		LOG.info("DificultadServiceImpl - addDificultad() --PARAMS dificultad: " + dificultad);
		dificultad = dificultadDAO.save(dificultad);
		LOG.info("DificultadServiceImpl - addDificultad() - dificultad added: " + dificultad);
		return dificultad;
	}

	@Override
	@Transactional
	public Optional<Dificultad> updateDificultad(Integer idDificultad, Dificultad dificultad) {
		LOG.info("DificultadServiceImpl - updateDificultad , PARAMS -- idDificultad: " + idDificultad + ", dificultad: " + dificultad);
		Optional<Dificultad> dificultadSearch = dificultadDAO.findById(idDificultad);
		if (dificultadSearch.isPresent()) {
			Dificultad dificultadOrigin = dificultadSearch.get();
			dificultadOrigin.setDificultad(dificultad.getDificultad());
			dificultadOrigin.setPreguntas(dificultad.getPreguntas());
			dificultadOrigin = dificultadDAO.save(dificultadOrigin);
			LOG.info("DificultadServiceImpl - updateDificultad , Dificultad actualizado --dificultad: " + dificultadOrigin);
			return Optional.of(dificultadOrigin);
			
		} else {
			LOG.info("DificultadServiceImpl - updateDificultad , Dificultad no encontrado --idDificultad: " + idDificultad);
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Boolean removeDificultad(Integer idDificultad) {
		LOG.info("DificultadServiceImpl - removeDificultad , PARAMS -- idDificultad: " + idDificultad);
		Optional<Dificultad> dificultad = dificultadDAO.findById(idDificultad);
		if (dificultad.isPresent()) {
			dificultadDAO.delete(dificultad.get());
			LOG.info("DificultadServiceImpl - removeDificultad , Cuestionario Eliminado -- idCuestionario: " + idDificultad);
			return true;
		} else {
			LOG.info("DificultadServiceImpl - removeDificultad , Cuestionario No encontrado -- idCuestionario: " + idDificultad);
			return false;
		}
	}



}
