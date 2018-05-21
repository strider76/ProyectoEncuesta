package com.atsistemas.EncuestaProj.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Validated
public class Respuesta {

	@Id
	@GeneratedValue
	private Integer idResultado;
	
	@Column(nullable=false)
	private String respuesta;
	
	@Column(name="correcta")
	private Boolean esCorrecta;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_pregunta")
	private Pregunta pregunta;
	
}
