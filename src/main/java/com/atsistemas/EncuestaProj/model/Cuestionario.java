package com.atsistemas.EncuestaProj.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cuestionario {

	@Id
	@GeneratedValue
	private Integer idCuestionario;
	
	@Column(nullable=false, unique=true)
	private String identificador;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_course")
	private Course course;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private List<CuestionarioPregunta> preguntas;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private List<CuestionarioTag> tags;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private List<Result> results;
	
}
