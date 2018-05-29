package com.atsistemas.EncuestaProj.service.impl;


import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.EncuestaProj.dao.QuestionDAO;
import com.atsistemas.EncuestaProj.dto.QuestionDTO;
import com.atsistemas.EncuestaProj.dto.QuestionDTOPost;
import com.atsistemas.EncuestaProj.excepciones.DificultyNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.NotFoundException;
import com.atsistemas.EncuestaProj.excepciones.QuestionNotFoundException;
import com.atsistemas.EncuestaProj.excepciones.TagNotFoundException;
import com.atsistemas.EncuestaProj.mapper.QuestionMapper;
import com.atsistemas.EncuestaProj.model.Answer;
import com.atsistemas.EncuestaProj.model.Dificulty;
import com.atsistemas.EncuestaProj.model.Question;
import com.atsistemas.EncuestaProj.model.Survey;
import com.atsistemas.EncuestaProj.model.Tag;
import com.atsistemas.EncuestaProj.service.AnswerService;
import com.atsistemas.EncuestaProj.service.DificultyService;
import com.atsistemas.EncuestaProj.service.QuestionService;
import com.atsistemas.EncuestaProj.service.SurveyService;
import com.atsistemas.EncuestaProj.service.TagService;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionDAO questionDAO;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private DificultyService dificultyService;
	
	@Autowired
	private SurveyService surveyService;
	
	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	
	
	
	
	@Override
	public Question create(Question model) {
		return questionDAO.save(model);
	}

	@Override
	public Question findById(Integer idQuestion) throws QuestionNotFoundException {
		Optional<Question> questionSearch = questionDAO.findById(idQuestion);
		if (questionSearch.isPresent())
			return questionSearch.get();
		else
			throw new QuestionNotFoundException("Tag No encontrado idTag('"+ idQuestion +"')");
	}

	@Override
	public void update(Integer idQuestion, QuestionDTO dto) throws NotFoundException {
		Optional<Question> questionSearch = questionDAO.findById(idQuestion);
		if (questionSearch.isPresent()) {
			Question question = questionSearch.get();
			Dificulty dificultySearch = dificultyService.findById(dto.getIdDificulty());
			Tag tagSearch = tagService.findById(dto.getIdTag());
			question.setName(dto.getName());
			question.setDificulty(dificultySearch);
			question.setTag(tagSearch);
			questionDAO.save(question);	
		}
	}

	@Override
	public void delete(Integer idQuestion) throws QuestionNotFoundException {
		Optional<Question> questionSearch = questionDAO.findById(idQuestion);
		if (questionSearch.isPresent())
			questionDAO.delete(questionSearch.get());
		else
			throw new QuestionNotFoundException("Question no encontrada idQuestion('"+ idQuestion +"')");
	}

	@Override
	public Set<Question> findAll(Pageable pagina) {
		return questionDAO.findAll(pagina).stream().collect(Collectors.toSet());
	}	
	
	@Override
	public Set<QuestionDTOPost> findAllByTag(Integer idTag, Pageable page) throws NotFoundException {
		return questionMapper.QuestionGetDaoToDto(questionDAO.findAllByTag(page, tagService.findById(idTag)).stream().collect(Collectors.toSet()));
	}

	@Override
	public Set<QuestionDTOPost> findAllByDificulty(Integer idDificulty, Pageable page) throws NotFoundException {
		return questionMapper.QuestionGetDaoToDto(questionDAO.findAllByDificulty(page, dificultyService.findById(idDificulty)).stream().collect(Collectors.toSet()));
	}

	@Override
	public void assignSurvey(Integer idQuestion, Integer idSurvey) throws NotFoundException {
		Survey surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.getPreguntas().size()<surveySearch.getMaxPreguntas()){
			Optional<Question> questionSearch = questionDAO.findById(idQuestion);
			if (questionSearch.isPresent()){
				Question question = questionSearch.get();
				question.getCuestionarios().add(surveySearch);
				questionDAO.save(question);
			} else
				throw new QuestionNotFoundException("Question no encontrada idQuestion('"+ idQuestion +"')");
		} 
	}

	@Override
	public void removeSurvey(Integer idQuestion, Integer idSurvey) throws NotFoundException {
		Survey surveySearch = surveyService.findById(idSurvey);
		if (surveySearch.getPreguntas().size()<surveySearch.getMaxPreguntas()){
			Optional<Question> questionSearch = questionDAO.findById(idQuestion);
			if (questionSearch.isPresent()){
				Question question = questionSearch.get();
				question.getCuestionarios().remove(surveySearch);
				questionDAO.save(question);
			} else
				throw new QuestionNotFoundException("Question no encontrada idQuestion('"+ idQuestion +"')");
			
			
		} 		
	}

	@Override
	public Set<Question> findAllbyTags(Set<Tag> tags) {
		return questionDAO.findAllByTag(tags);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		initDatos();
		
	}

	private void initDatos() throws NotFoundException {
		
		if (dificultyService.findByName("facil")==null) { dificultyService.create(new Dificulty("facil")); }
		if (dificultyService.findByName("medio")==null) { dificultyService.create(new Dificulty("medio")); }
		if (dificultyService.findByName("dificil")==null) { dificultyService.create(new Dificulty("dificil")); }
		
		if (tagService.findByName("jpa")==null) {tagService.create(new Tag("jpa"));}
		if (tagService.findByName("mongodb")==null) {tagService.create(new Tag("mongodb"));}
		if (tagService.findByName("hibernate")==null) {tagService.create(new Tag("hibernate"));}
		
		Question question;
		for (int i=1;i<20;i++) {
			question = addQuestion("facil", "jpa", "pregunta jpa"+i);
			int posTrue =1 + new Random().nextInt(4);
			for (int j=1;j<=4;j++)
				addRespuesta(question, "pregunta jpa"+ i +" - respuesta"+j, (j==posTrue)?true:false);
		}
		
		for (int i=1;i<5;i++) {
			question = addQuestion("facil", "mongodb", "pregunta mongodb"+i);
			int posTrue =1 + new Random().nextInt(4);
			for (int j=1;j<=4;j++)
				addRespuesta(question, "pregunta mongodb"+ i +" - respuesta"+j, (j==posTrue)?true:false);
		}
		
		for (int i=1;i<40;i++) {
			question = addQuestion("facil", "hibernate", "pregunta hibernate"+i);
			int posTrue =1 + new Random().nextInt(4);
			for (int j=1;j<=4;j++)
				addRespuesta(question, "pregunta hibernate"+ i +" - respuesta"+j, (j==posTrue)?true:false);
		}

		
	}
	
	private Question addQuestion(String dificultad, String tag, String textoQuestion) throws DificultyNotFoundException, TagNotFoundException {
		Question question = new Question();
		question.setDificulty(dificultyService.findByName(dificultad));
		question.setTag(tagService.findByName(tag));
		question.setName(textoQuestion);
		return questionDAO.save(question);
	}
	
	private void addRespuesta(Question question, String respuesta, Boolean esCorrecta) throws NotFoundException {

		if (question.getAnswers().size()<4) {
			Answer answer = new Answer();
			answer.setQuestion(question);
			answer.setRespuesta(respuesta);
			answer.setEsCorrecta(esCorrecta);
			answerService.create(answer);
		}
		

		
	}

	@Override
	public Set<QuestionDTOPost> findAllBySurvey(Integer idSurvey, PageRequest of) throws NotFoundException {
		Survey survey = surveyService.findById(idSurvey);
		return questionMapper.QuestionGetDaoToDto(questionDAO.findAllByCuestionarios(of, survey).stream().collect(Collectors.toSet()));
	}





}
