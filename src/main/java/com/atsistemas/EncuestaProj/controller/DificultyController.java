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

import com.atsistemas.EncuestaProj.dto.DificultyDTO;
import com.atsistemas.EncuestaProj.dto.DificultyDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.DificultyMapper;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.service.DificultyService;

@RestController
@RequestMapping(value="/dificulty")
public class DificultyController {
	
	@Autowired
	DificultyService dificultyService;
	
	@Autowired
	DificultyMapper dificultyMapper;
	
	@GetMapping
	@ResponseStatus(code=HttpStatus.OK)
	public Set<DificultyDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
										 @RequestParam(defaultValue="10",required=false) Integer size) {
		return dificultyMapper.dificultyGetsDaoToDto(dificultyService.findAll(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{idDificultad}")
	@ResponseStatus(code=HttpStatus.OK)
	public DificultyDTOPost findById(@PathVariable Integer idDificultad) throws DificultyNotFoundException {
		Optional<Dificulty> dificultySearch = dificultyService.findById(idDificultad);
		if (dificultySearch.isPresent())
			return dificultyMapper.dificultyDaoToDto(dificultySearch.get());
		else
			throw new DificultyNotFoundException("Dificultad no encontrada idDificultad('"+idDificultad+"')");
	}
	
	
	@GetMapping("/name")
	@ResponseStatus(code=HttpStatus.OK)
	public DificultyDTOPost findByName(@RequestParam(defaultValue="",required=true) String name) throws DificultyNotFoundException {
		Optional<Dificulty> dificulty = dificultyService.findByName(name);
		if (dificulty.isPresent())
			return dificultyMapper.dificultyDaoToDto(dificulty.get());
		else
			throw new DificultyNotFoundException("Dificultad no encontrada name('"+ name +"')");
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public DificultyDTOPost create(@RequestBody DificultyDTO dificulty) {
		
		return dificultyMapper.dificultyDaoToDto(dificultyService.create(dificultyMapper.dificultyDtoToDao(dificulty)));
		
	}
	
	@DeleteMapping("/{idDificulty}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void delete(@PathVariable Integer idDificulty) throws DificultyNotFoundException {
		Optional<Dificulty> dificulty = dificultyService.findById(idDificulty);
		if (dificulty.isPresent())
			dificultyService.delete(dificulty.get());
		else
			throw new DificultyNotFoundException("Dificultad no encontrada idDificultad('"+ idDificulty +"')");
	}

	
	@PutMapping("/{idDificulty}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void update(@PathVariable Integer idDificulty, @RequestBody DificultyDTO dificultyDTO) throws NotFoundException {
		Optional<Dificulty> dificultySearch = dificultyService.findById(idDificulty);
		if (dificultySearch.isPresent())
			dificultyService.update(dificultySearch.get(), dificultyDTO);
		else
			throw new DificultyNotFoundException("Dificultad no encontrada idDificultad('"+ idDificulty +"')");
	}
	
	
}
