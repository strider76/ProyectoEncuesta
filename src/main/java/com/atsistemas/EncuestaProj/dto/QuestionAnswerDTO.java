package com.atsistemas.EncuestaProj.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class QuestionAnswerDTO {

	private Integer idQuestion;
	private String pregunta;
	private List<AnswerRes> respuestas;
	
	@Getter
	@Setter
	@NoArgsConstructor
	private class AnswerRes {
		
		private Integer idAnswer;
		private String answer;
		
		public AnswerRes(Integer idAnswer, String answer) {
			this.idAnswer = idAnswer;
			this.answer = answer;
		}
		
	}
}
