package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tag {

	public final static String TAG_DIFICULTAD = "dificultad";
	
	@Id
	@GeneratedValue
	@Column(name="id_tag")
	private Integer idTag;
	
	@Column(name="nombre",nullable=false)
	private String nombre;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="tag")
	private List<CuestionarioTag> cuestionarios;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dificultad")
	private List<Pregunta> preguntas;
}
