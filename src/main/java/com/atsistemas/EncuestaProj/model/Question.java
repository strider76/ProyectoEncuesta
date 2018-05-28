package com.atsistemas.EncuestaProj.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Question {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idQuestion;
	
	@Column(nullable=false)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY, mappedBy="preguntas")
	private Set<Survey> cuestionarios;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tag")
	private Tag tag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_dificulty")
	private Dificulty dificulty;
	
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "question")
	@Size(min=0,max=4)
	private Set<Answer> answers = new HashSet<>();

	@Override
	public String toString() {
		return "Pregunta [idQuestion=" + idQuestion + ", name=" + name + "]";
	}
	
	
}
