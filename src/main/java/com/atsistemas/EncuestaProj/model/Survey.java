package com.atsistemas.EncuestaProj.model;

import java.util.List;

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
import javax.persistence.UniqueConstraint;
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
	
	
	@Min(1)
	private Integer maxPreguntas;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_course")
	private Course course;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_pregunta",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_pregunta"),
				uniqueConstraints=@UniqueConstraint(columnNames={"id_cuestionario", "id_pregunta"})
	)
	
	private List<Question> preguntas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="cuestionario_tag",
				joinColumns=@JoinColumn(name="id_cuestionario"),
				inverseJoinColumns=@JoinColumn(name="id_tag"),
				uniqueConstraints=@UniqueConstraint(columnNames={"id_cuestionario", "id_tag"})
	)
	private List<Tag> tags;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="cuestionario")
	private List<Result> results;

	@Override
	public String toString() {
		return "Survey [idCuestionario=" + idCuestionario + ", identificador=" + identificador + ", esAleatorio="
				+ ", maxPregunas=" + maxPreguntas + "]";
	}




	
	
}
