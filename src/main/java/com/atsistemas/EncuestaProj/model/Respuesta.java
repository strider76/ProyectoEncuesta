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
import lombok.Setter;


@Entity
@Getter
@Setter
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

	@Override
	public String toString() {
		return "Respuesta [idResultado=" + idResultado + ", respuesta=" + respuesta + ", esCorrecta=" + esCorrecta
				+ "]";
	}
	
	
}
