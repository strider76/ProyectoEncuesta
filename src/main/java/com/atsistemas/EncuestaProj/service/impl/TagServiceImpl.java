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

import com.atsistemas.EncuestaProj.dao.TagDAO;
import com.atsistemas.EncuestaProj.model.Cuestionario;
import com.atsistemas.EncuestaProj.model.Pregunta;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.TagService;

@Service
public class TagServiceImpl implements TagService, InitializingBean {

	@Autowired
	private TagDAO tagDAO;
	
	private final static Logger LOG = LoggerFactory.getLogger(TagServiceImpl.class);
	
	@Override
	public void afterPropertiesSet() throws Exception {
		test();

	}

	@Override
	@Transactional
	public void test() {
		Tag tag1 = new Tag();
		Tag tag2 = new Tag();
		Tag tag3 = new Tag();
		
		tag1.setName("tag1");
		tag2.setName("tag2");
		tag3.setName("tag3");
		
		LOG.info("DicicultadServiceImpl - test() - add Tag 1 -" + addTag(tag1));
		LOG.info("DicicultadServiceImpl - test() - add Tag 2 -" + addTag(tag2));
		LOG.info("DicicultadServiceImpl - test() - add Tag 3 -" + addTag(tag3));
		
	}	
	
	@Override
	public Optional<Tag> findByIdTag(Integer idTag) {
		LOG.info("TagServiceImpl - findByIdTag , PARAMS -- idTag: " + idTag);
		Optional<Tag> tag = tagDAO.findById(idTag);
		if (tag.isPresent())
			LOG.info("TagServiceImpl - findByIdTag , Encontrada Tag(" + idTag + ") -- " + tag.get());
		else
			LOG.info("TagServiceImpl - findByIdTag , No Encontrada Tag(" + idTag + ") ");
		return tag;
	}

	@Override
	public Optional<Tag> findByName(String name) {
		LOG.info("TagServiceImpl - findByName , PARAMS -- name: " + name);
		Optional<Tag> tag = tagDAO.findOneByName(name);
		if (tag.isPresent())
			LOG.info("TagServiceImpl - findByName , Encontrada Tag(" + name + ") -- " + tag.get());
		else
			LOG.info("TagServiceImpl - findByName , No Encontrada Tag(" + name + ") ");
		return tag;
	}

	@Override
	public List<Pregunta> findPreguntaByIdTag(Integer idTag) {
		LOG.info("TagServiceImpl - findPreguntaByIdTag , PARAMS -- idTag: " + idTag);
		List<Pregunta> listPregunta = new ArrayList<Pregunta>();
		Optional<Tag> tag = tagDAO.findById(idTag);
		if (tag.isPresent()) {
			listPregunta = tag.get().getPreguntas();
		}
		LOG.info("TagServiceImpl - findPreguntaByIdTag , totalList: " + listPregunta.size());
		return listPregunta;
	}

	@Override
	public List<Cuestionario> findCuestionarioByIdTag(Integer idTag) {
		LOG.info("TagServiceImpl - findCuestionarioByIdTag , PARAMS -- idTag: " + idTag);
		List<Cuestionario> listCuestionario = new ArrayList<Cuestionario>();
		Optional<Tag> tag = tagDAO.findById(idTag);
		if (tag.isPresent()) {
			listCuestionario = tag.get().getCuestionarios();
		}
		LOG.info("TagServiceImpl - findCuestionarioByIdTag , totalList: " + listCuestionario.size());
		return listCuestionario;
	}

	@Override
	public List<Tag> getAll() {
		LOG.info("TagServiceImpl - getAll()");
		List<Tag> listTag = new ArrayList<>();
		for (Tag tag : tagDAO.findAll()) {
			listTag.add(tag);
		}
		LOG.info("TagServiceImpl - getAll() - size: " + listTag.size());
		return listTag;
	}

	@Override
	@Transactional
	public Tag addTag(Tag tag) {
		LOG.info("TagServiceImpl - addTag() --PARAMS tag: " + tag);
		tag = tagDAO.save(tag);
		LOG.info("TagServiceImpl - addTag() - tag added: " + tag);
		return tag;
	}

	@Override
	@Transactional
	public Optional<Tag> updateTag(Integer idTag, Tag tag) {
		LOG.info("TagServiceImpl - updateTag , PARAMS -- idTag: " + idTag + ", tag: " + tag);
		Optional<Tag> tagSearch = tagDAO.findById(idTag);
		if (tagSearch.isPresent()) {
			Tag tagOrigin = tagSearch.get();
			tagOrigin.setName(tag.getName());
			tagOrigin.setCuestionarios(tag.getCuestionarios());
			tagOrigin.setPreguntas(tag.getPreguntas());
			tagOrigin = tagDAO.save(tagOrigin);
			LOG.info("TagServiceImpl - updateTag , Tag actualizada --Tag: " + tagOrigin);
			return Optional.of(tagOrigin);
			
		} else {
			LOG.info("TagServiceImpl - updateTag , Tag no encontrado --idTag: " + idTag);
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public Boolean removeTag(Integer idTag) {
		LOG.info("TagServiceImpl - removeTag , PARAMS -- idTag: " + idTag);
		Optional<Tag> tag = tagDAO.findById(idTag);
		if (tag.isPresent()) {
			tagDAO.delete(tag.get());
			LOG.info("TagServiceImpl - removeTag , Tag Eliminado -- idTag: " + idTag);
			return true;
		} else {
			LOG.info("TagServiceImpl - removeTag , Cuestionario No encontrado -- idTag: " + idTag);
			return false;
		}
	}



}
