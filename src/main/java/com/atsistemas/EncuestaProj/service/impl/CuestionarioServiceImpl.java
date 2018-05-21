package com.atsistemas.EncuestaProj.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.CuestionarioDAO;
import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Result;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.CuestionarioService;

@Service
public class CuestionarioServiceImpl implements CuestionarioService, InitializingBean {

	@Autowired
	private CuestionarioDAO cuestionarioDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(CuestionarioServiceImpl.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();

	}

	@Override
	@Transactional
	public void test() {
		Cuestionario cuestionario1 = new Cuestionario();
		Cuestionario cuestionario2 = new Cuestionario();
		cuestionario1.setIdentificador("Cuestionario 1");
		cuestionario2.setIdentificador("Cuestionario 2");
		LOG.info("CuestionarioServiceImpl - test() - add cuestionario 1 -" +cuestionarioDAO.save(cuestionario1));
		LOG.info("CuestionarioServiceImpl - test() - add cuestionario 2 - " +cuestionarioDAO.save(cuestionario2));
		Iterator<Cuestionario> it = cuestionarioDAO.findAll().iterator();
		while (it.hasNext()) {
			Cuestionario cuestionario= it.next();
			LOG.info("CuestionarioServiceImpl - test() - List " + cuestionario);
		}
			
		

	}	
	
	@Override
	public Optional<Cuestionario> findByIdCuestionario(Integer id) {
		LOG.info("CuestionarioServiceImpl - findByIDCuestionario , PARAMS -- id: " + id);
		return cuestionarioDAO.findById(id);
	}

	@Override
	public Optional<Cuestionario> findByIdentificador(String identificador) {
		LOG.info("CuestionarioServiceImpl - findByIdentificador , PARAMS -- identificador: " + identificador);
		return cuestionarioDAO.findOneByIdentificador(identificador);
	}

	@Override
	public List<Result> findAllResult(Integer idCuestionario) {
		LOG.info("CuestionarioServiceImpl - findByAllResult , PARAMS -- idCuestionario: " + idCuestionario);
		List<Result> allResult = new ArrayList<Result>();
		Optional<Cuestionario> cuestionarioSearch = findByIdCuestionario(idCuestionario);
		if (cuestionarioSearch.isPresent()) {
			allResult = cuestionarioSearch.get().getResults();
			LOG.info("CuestionarioServiceImpl - findByAllResult , Encontrado Cuestionario id("+idCuestionario+") -- GetResult(): " + allResult);
		} else {
			LOG.info("CuestionarioServiceImpl - findByAllResult , No Encontrado Cuestionario id(" +idCuestionario + ")");
		}
		
		return allResult;
	}

	@Override
	public List<Tag> findAllTag(Integer idCuestionario) {
		LOG.info("CuestionarioServiceImpl - findByAllTag , PARAMS -- idCuestionario: " + idCuestionario);
		List<Tag> allTag = new ArrayList<Tag>();
		Optional<Cuestionario> cuestionarioSearch = findByIdCuestionario(idCuestionario);
		if (cuestionarioSearch.isPresent()) {
			allTag = cuestionarioSearch.get().getTags();
			LOG.info("CuestionarioServiceImpl - findByAllTag , Encontrado Cuestionario id("+idCuestionario+") -- GetTags(): " + allTag);
		} else {
			LOG.info("CuestionarioServiceImpl - findByAllTag , No Encontrado Cuestionario id("+idCuestionario+")");
		}
		return allTag;
	}

	@Override
	@Transactional
	public Cuestionario addCuestionario(Cuestionario cuestionario) {
		LOG.info("CuestionarioServiceImpl - addCuestionario , PARAMS -- cuestionario: " + cuestionario);
		cuestionario = cuestionarioDAO.save(cuestionario);
		LOG.info("CuestionarioServiceImpl - addCuestionario , cuestionario añadido: " + cuestionario);
		return cuestionario;
	}

	@Override
	@Transactional
	public Boolean removeCuestionario(Integer idCuestionario) {
		LOG.info("CuestionarioServiceImpl - removeCuestionario , PARAMS -- idCuestionario: " + idCuestionario);
		Optional<Cuestionario> cuestionario = cuestionarioDAO.findById(idCuestionario);
		if (cuestionario.isPresent()) {
			cuestionarioDAO.delete(cuestionario.get());
			LOG.info("CuestionarioServiceImpl - removeCuestionario , Cuestionario Eliminado -- idCuestionario: " + idCuestionario);
			return true;
		} else {
			LOG.info("CuestionarioServiceImpl - removeCuestionario , Cuestionario No encontrado -- idCuestionario: " + idCuestionario);
			return false;
		}
	}

	@Override
	@Transactional
	public Optional<Cuestionario> updateCuestionario(Integer idCuestionario, Cuestionario cuestionario) {
		LOG.info("CuestionarioServiceImpl - updateCuestionario , PARAMS -- idCuestionario: " + idCuestionario + ", cuestionario: " + cuestionario);
		Optional<Cuestionario> cuestionarioSearch = cuestionarioDAO.findById(idCuestionario);
		if (cuestionarioSearch.isPresent()) {
			Cuestionario cuestionarioOrigin = cuestionarioSearch.get();
			cuestionarioOrigin.setCourse(cuestionario.getCourse());
			cuestionarioOrigin.setIdentificador(cuestionario.getIdentificador());;
			cuestionarioOrigin.setPreguntas(cuestionario.getPreguntas());
			cuestionarioOrigin.setResults(cuestionario.getResults());
			cuestionarioOrigin.setTags(cuestionario.getTags());
			cuestionarioOrigin = cuestionarioDAO.save(cuestionarioOrigin);
			LOG.info("CuestionarioServiceImpl - updateCuestionario , Cuestionario Actualizado --cuestionario: " + cuestionarioOrigin);
			return Optional.of(cuestionarioOrigin);
			
		} else {
			LOG.info("CuestionarioServiceImpl - updateCuestionario , Cuestionario no encontrado --idCuestioanrio: " + idCuestionario);
			return Optional.empty();
		}
	}

	@Override
	public List<Pregunta> findAllPregunta(Integer idCuestionario) {
		LOG.info("CuestionarioServiceImpl - findByAllPregunta , PARAMS -- idCuestionario: " + idCuestionario);
		List<Pregunta> allPregunta = new ArrayList<Pregunta>();
		Optional<Cuestionario> cuestionarioSearch = findByIdCuestionario(idCuestionario);
		if (cuestionarioSearch.isPresent()) {
			allPregunta = cuestionarioSearch.get().getPreguntas();
			LOG.info("CuestionarioServiceImpl - findByAllResult , Encontrado Cuestionario id("+idCuestionario+") -- GetPreguntas(): " + allPregunta);
		} else {
			LOG.info("CuestionarioServiceImpl - findByAllResult , No Encontrado Cuestionario id(" +idCuestionario + ")");
		}
		
		return allPregunta;
	}



}
