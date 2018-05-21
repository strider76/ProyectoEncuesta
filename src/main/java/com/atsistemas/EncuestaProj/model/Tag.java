package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
public class Tag {

	@Id
	@GeneratedValue
	@Column(name="id_tag")
	private Integer idTag;
	
	@Column(name="nombre",nullable=false, unique=true)
	private String nombre;
	
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="tags")
	private List<Cuestionario> cuestionarios;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tag")
	private List<Pregunta> preguntas;
}
