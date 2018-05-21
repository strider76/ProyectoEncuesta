package com.atsistemas.EncuestaProj.service;

import java.util.List;
import java.util.Optional;

import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Tag;

public interface TagService {

	public Optional<Tag> findByIdTag(Integer idTag);
	public Optional<Tag> findByName(String name);
	public List<Pregunta> findPreguntaByIdTag(Integer idTag);
	public List<Cuestionario> findCuestionarioByIdTag(Integer idTag);
	public List<Tag> getAll();
	public Tag addTag(Tag tag);
	public Optional<Tag> updateTag(Integer idTag, Tag tag);
	public Boolean removeTag(Integer idTag);
	public void test();
	
}
