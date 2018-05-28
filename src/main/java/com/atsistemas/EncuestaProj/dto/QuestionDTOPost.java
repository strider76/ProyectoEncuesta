package com.atsistemas.EncuestaProj.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class QuestionDTOPost extends QuestionDTO {

	private Integer idQuestion;
	
}
