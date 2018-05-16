package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pregunta {

	@Id
	@GeneratedValue
	private Integer idPregunta;
	
	@Column(nullable=false)
	private String pregunta;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade = {
						CascadeType.MERGE,
						CascadeType.PERSIST
				},
				mappedBy="preguntas"
			)
	
	private List<Cuestionario> cuestionario;
}
