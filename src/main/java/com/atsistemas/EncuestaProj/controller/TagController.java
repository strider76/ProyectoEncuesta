package com.atsistemas.EncuestaProj.controller;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.EncuestaProj.dto.TagDTO;
import com.atsistemas.EncuestaProj.dto.TagDTOPost;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.SurveyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.mapper.TagMapper;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.TagService;

@RestController
@RequestMapping(value="/tag")
public class TagController {
	
	@Autowired
	TagService tagService;
	
	@Autowired
	TagMapper tagMapper;
	
	@GetMapping
	@ResponseStatus(code=HttpStatus.OK)
	public Set<TagDTOPost> findAll(@RequestParam(defaultValue="0", required=false) Integer page,
									@RequestParam(defaultValue="10", required=false) Integer size) {
		
		return tagMapper.tagsGetDaoToDto(tagService.findAll(PageRequest.of(page, size)));
		
	}
	
	
	@GetMapping("/{idTag}")
	@ResponseStatus(code=HttpStatus.OK)
	public TagDTOPost findById(@PathVariable Integer idTag) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			return tagMapper.tagDaoToDto(tagSearch.get());
		else
			throw new TagNotFoundException("Tag no encontrado con id('"+ idTag +"')");
	}
	
	@GetMapping("/search")
	@ResponseStatus(code=HttpStatus.OK)
	public TagDTOPost findByName(@RequestParam(defaultValue="",required=false) String name) throws TagNotFoundException{
		Optional<Tag> tagSearch = tagService.findByName(name);				
		if (tagSearch.isPresent())
			return tagMapper.tagDaoToDto(tagSearch.get());
		else
			throw new TagNotFoundException("Tag no encontrado con name('"+ name +"')");
	}
	
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public TagDTOPost create(@RequestBody TagDTO tagDTO) throws NotFoundException {
		return tagMapper.tagDaoToDto(tagService.create(tagMapper.tagDtoToDao(tagDTO)));
	}
	
	
	@DeleteMapping("/{idTag}")
	@ResponseStatus(code=HttpStatus.OK)
	public void delete(@PathVariable Integer idTag) throws TagNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			tagService.delete(tagSearch.get());
		else
			throw new TagNotFoundException("Tag no encontrado con id('"+ idTag +"')");
	}
	
	@PutMapping("/{idTag}")
	@ResponseStatus(code=HttpStatus.OK)
	public void update(@PathVariable Integer idTag, @RequestBody TagDTO tagDTO) throws NotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			tagService.update(tagSearch.get(), tagDTO);
		else
			throw new TagNotFoundException("Tag no encontrado con id('"+ idTag +"')");
	}
	

	@PostMapping("/{idTag}/survey/{idSurvey}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public void AssingTagToSurvey(@PathVariable Integer idTag, @PathVariable Integer idSurvey) throws TagNotFoundException, SurveyNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			tagService.asignSurvey(tagSearch.get(), idSurvey);
		else
			throw new TagNotFoundException("Tag no encontrado con id('"+ idTag +"')");
	}
	
	@DeleteMapping("/{idTag}/survey/{idSurvey}")
	@ResponseStatus(code=HttpStatus.CREATED)
	public void removeTagToSurvey(@PathVariable Integer idTag, @PathVariable Integer idSurvey) throws TagNotFoundException, SurveyNotFoundException {
		Optional<Tag> tagSearch = tagService.findById(idTag);
		if (tagSearch.isPresent())
			tagService.removeSurvey(tagSearch.get(), idSurvey);
		else
			throw new TagNotFoundException("Tag no encontrado con id('"+ idTag +"')");
	}	

}
