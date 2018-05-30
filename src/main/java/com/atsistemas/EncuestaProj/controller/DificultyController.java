package com.atsistemas.EncuestaProj.controller;

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
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.mapper.DificultyMapper;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.service.DificultyService;

@RestController
@RequestMapping(value="/dificulty")
public class DificultyController {
	
	@Autowired
	DificultyService dificultyService;
	
	@Autowired
	DificultyMapper dificultyMapper;
	
	@Autowired
	QuestionMapper questionMapper;
	
	@GetMapping
	public Set<DificultyDTOPost> findAll(@RequestParam(defaultValue="0",required=false) Integer page,
										 @RequestParam(defaultValue="10",required=false) Integer size) {
		return dificultyMapper.dificultyGetsDaoToDto(dificultyService.findAll(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{idDificulty}")
	public DificultyDTOPost findById(@PathVariable Integer idDificulty) throws NotFoundException {
		return dificultyMapper.dificultyDaoToDto(dificultyService.findById(idDificulty));
	}
	
	@GetMapping("/{idDificulty}/question")
	public Set<QuestionDTOPost> findQuestionByDificulty ( @PathVariable Integer idDificulty,
														@RequestParam(defaultValue="0", required=false) Integer page,
														@RequestParam(defaultValue="10", required=false) Integer size) throws DificultyNotFoundException {
		return questionMapper.QuestionGetDaoToDto(dificultyService.findQuestionsByDificulty(PageRequest.of(page, size),idDificulty));
	}
	
	@GetMapping("/name")
	public DificultyDTOPost findByName(@RequestParam(defaultValue="",required=true) String name) throws DificultyNotFoundException {
		return dificultyMapper.dificultyDaoToDto(dificultyService.findByName(name));
	}
	
	@PostMapping
	@ResponseStatus(code=HttpStatus.CREATED)
	public DificultyDTOPost create(@RequestBody DificultyDTO dificulty) throws NotFoundException {
		
		return dificultyMapper.dificultyDaoToDto(dificultyService.create(dificultyMapper.dificultyDtoToDao(dificulty)));
		
	}
	
	@DeleteMapping("/{idDificulty}")
	public void delete(@PathVariable Integer idDificulty) throws NotFoundException {
		dificultyService.delete(idDificulty);
	}

	
	@PutMapping("/{idDificulty}")
	@ResponseStatus(code=HttpStatus.ACCEPTED)
	public void update(@PathVariable Integer idDificulty, @RequestBody DificultyDTO dificultyDTO) throws NotFoundException {
		dificultyService.update(idDificulty, dificultyDTO);
	}
	
	
}
