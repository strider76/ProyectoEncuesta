package com.atsistemas.EncuestaProj.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Pregunta {

	public static final String FK_ETIQUETA = "id_etiqueta";
	
	@Id
	@GeneratedValue
	private Integer idPregunta;
	
	@Column(nullable=false)
	private String pregunta;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="preguntas")
	private List<Cuestionario> cuestionarios;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tag")
	private Tag tag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dificultad")
	private Dificultad dificultad;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "pregunta")
	@Size(min=0,max=4)
	private List<Respuesta> respuestas;
}
