package com.atsistemas.EncuestaProj.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
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
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_pregunta",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_pregunta"))
	private List<Pregunta> preguntas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_tag",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_tag"))
	private List<Tag> tags;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private List<Result> results;
	
}
