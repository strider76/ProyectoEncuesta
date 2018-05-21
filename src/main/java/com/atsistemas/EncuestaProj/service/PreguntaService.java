package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Respuesta;

public interface PreguntaService {

	public Optional<Pregunta> findOneByIdPregunta(Integer idPregunta);
	public List<Pregunta> finddAllByCuestionario(Cuestionario cuestionario);
	public List<Respuesta> findAllRespuesta (Integer idPregunta);
	public Pregunta addPregunta(Pregunta pregunta);
	public Optional<Pregunta> updatePregunta(Integer idPregunta, Pregunta pregunta);
	public Boolean removePregunta(Integer idPregunta);
	public void test();
	
}
