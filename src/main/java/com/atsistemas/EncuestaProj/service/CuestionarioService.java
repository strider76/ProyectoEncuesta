package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Result;
import com.atsistemas.EncuestaProj.model.Tag;

public interface CuestionarioService {

	public Optional<Cuestionario> findByIdCuestionario(Integer id);
	public Optional<Cuestionario> findByIdentificador(String identificador);
	public List<Result> findAllResult(Integer idCuestionario);
	public List<Tag> findAllTag(Integer idCuestionario);
	public List<Pregunta> findAllPregunta(Integer idCuestionario);
	public Cuestionario addCuestionario(Cuestionario cuestionario);
	public Boolean removeCuestionario(Integer idCuestionario);
	public Optional<Cuestionario> updateCuestionario(Integer idCuestionario, Cuestionario cuestionario);
	public void test();
	
	
}
