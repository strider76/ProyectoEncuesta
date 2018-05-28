package com.atsistemas.EncuestaProj.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
@Entity
public class Survey {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCuestionario;
	
	@Column(nullable=false, unique=true)
	private String identificador;
	
	private Boolean esAleatorio;
	
	private Boolean esCerrado;
	
	@Min(1)
	private Integer maxPreguntas;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_course")
	private Course course;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_pregunta",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_pregunta"))
	private Set<Question> preguntas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_tag",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_tag"))
	private Set<Tag> tags;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private Set<Result> results;

	@Override
	public String toString() {
		return "Survey [idCuestionario=" + idCuestionario + ", identificador=" + identificador + ", esAleatorio="
				+ esAleatorio + ", esCerrado=" + esCerrado + ", maxPregunas=" + maxPreguntas + "]";
	}




	
	
}
