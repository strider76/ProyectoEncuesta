package com.atsistemas.EncuestaProj.service.impl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atsistemas.EncuestaProj.dao.PreguntaDAO;
import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Respuesta;
import com.atsistemas.EncuestaProj.service.PreguntaService;

@Service
public class PreguntaServiceImpl implements PreguntaService, InitializingBean {

	@Autowired
	private PreguntaDAO preguntaDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(PreguntaServiceImpl.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();

	}

	@Override
	@Transactional
	public void test() {
		Pregunta pregunta1 = new Pregunta();
		Pregunta pregunta2 = new Pregunta();
		pregunta1.setPregunta("Pregunta1");
		pregunta2.setPregunta("Pregunta2");
		LOG.info("PreguntaServiceImpl - test() - add Pregunta 1 -" +preguntaDAO.save(pregunta1));
		LOG.info("PreguntaServiceImpl - test() - add Pregunta 2 -" +preguntaDAO.save(pregunta2));

		Iterator<Pregunta> it = preguntaDAO.findAll().iterator();
		while (it.hasNext()) {
			Pregunta pregunta = it.next();
			LOG.info("PreguntaServiceImpl - test() - List " + pregunta);
		}

	}	
	
	@Override
	public Optional<Pregunta> findOneByIdPregunta(Integer idPregunta) {
		LOG.info("PreguntaServiceImpl - findByIdPregunta , PARAMS -- id: " + idPregunta);
		Optional<Pregunta> optPregunta = preguntaDAO.findById(idPregunta);
		if (optPregunta.isPresent())
			LOG.info("PreguntaServiceImpl - findByIdPregunta , Encontrado -- id: " + idPregunta + " Objeto : " + optPregunta.get());
		else
			LOG.info("PreguntaServiceImpl - findByIdPregunta , No Encontrado -- id: ");
		return optPregunta;
	}

	@Override
	public List<Pregunta> finddAllByCuestionario(Cuestionario cuestionario) {
		LOG.info("PreguntaServiceImpl - findByAllByCuestionario, PARAMS -- Cuestionario: " + cuestionario);
		List<Pregunta> allPregunta = cuestionario.getPreguntas();;
		LOG.info("PreguntaServiceImpl - findByAllByCuestionario, Resultado -- Cuestionario: " + cuestionario + ", --Preguntas: " + allPregunta );
		return allPregunta;
	}

	@Override
	public List<Respuesta> findAllRespuesta(Integer idPregunta) {
		LOG.info("PreguntaServiceImpl - findByAllRespueta , PARAMS -- idPregunta: " + idPregunta);
		List<Respuesta> allRespuesta = new ArrayList<Respuesta>();
		Optional<Pregunta> preguntaSearch = findOneByIdPregunta(idPregunta);
		if (preguntaSearch.isPresent()) {
			allRespuesta = preguntaSearch.get().getRespuestas();
			LOG.info("PreguntaServiceImpl - findByAllRespuesta , Encontrada Pregunta id("+ idPregunta +") -- GetRespuestas(): " + allRespuesta);
		} else {
			LOG.info("PreguntaServiceImpl - findByAllRespuesta , No Encontrado Pregunta id(" + idPregunta + ")");
		}
		
		return allRespuesta;
	}

	@Override
	@Transactional
	public Pregunta addPregunta(Pregunta pregunta) {
		LOG.info("PreguntaServiceImpl - addPregunta , PARAMS -- Pregunta: " + pregunta);
		pregunta = preguntaDAO.save(pregunta);
		LOG.info("PreguntaServiceImpl - addPregunta , Pregunta añadida: " + pregunta);
		return pregunta;
	}

	@Override
	@Transactional
	public Optional<Pregunta> updatePregunta(Integer idPregunta, Pregunta pregunta) {
		LOG.info("PreguntaServiceImpl - updatePregunta , PARAMS -- idCuestionario: " + idPregunta + ", Pregunta: " + pregunta);
		Optional<Pregunta> preguntaSearch = preguntaDAO.findById(idPregunta);
		if (preguntaSearch.isPresent()) {
			Pregunta preguntaOrigin = preguntaSearch.get();
			preguntaOrigin.setPregunta(pregunta.getPregunta());
			preguntaOrigin.setCuestionarios(pregunta.getCuestionarios());
			preguntaOrigin.setDificultad(pregunta.getDificultad());
			preguntaOrigin.setRespuestas(pregunta.getRespuestas());
			preguntaOrigin.setTag(pregunta.getTag());
			preguntaOrigin = preguntaDAO.save(preguntaOrigin);
			LOG.info("PreguntaServiceImpl - updatePregunta , Pregutna Actualizada --pregunta: " + preguntaOrigin);
			return Optional.of(preguntaOrigin);
			
		} else {
			LOG.info("PreguntaServiceImpl - updatePregunta , Pregunta no encontrada --idPregunta: " + idPregunta);
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Boolean removePregunta(Integer idPregunta) {
		LOG.info("PreguntaServiceImpl - removePregunta , PARAMS -- idPregunta: " + idPregunta);
		Optional<Pregunta> pregunta = preguntaDAO.findById(idPregunta);
		if (pregunta.isPresent()) {
			preguntaDAO.delete(pregunta.get());
			LOG.info("PreguntaServiceImpl - removePregunta , Pregunta Eliminada -- idPregunta: " + idPregunta);
			return true;
		} else {
			LOG.info("PreguntaServiceImpl - removePregunta , Pregunta No encontrado -- idPregunta: " + idPregunta);
			return false;
		}
	}



}
