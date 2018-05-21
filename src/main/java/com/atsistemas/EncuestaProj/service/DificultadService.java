package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Dificultad;
import com.atsistemas.EncuestaProj.model.Pregunta;

public interface DificultadService {

	public Optional<Dificultad> findByIdDificultad(Integer idDificultad);
	public Optional<Dificultad> findByDificultad(String dificultad);
	public List<Dificultad> getAllDificultad();
	public List<Pregunta> getAllPreguntasByIdDificultad(Integer idDificultad);
	public Dificultad addDificultad(Dificultad dificultad);
	public Optional<Dificultad> updateDificultad(Integer idDificultad, Dificultad dificultad);
	public Boolean removeDificultad(Integer idDificultad);
	public void test();
	
}
