package com.atsistemas.EncuestaProj.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Tag {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_tag")
	private Integer idTag;
	
	@Column(name="name",nullable=false, unique=true)
	private String name;
	
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="tags")
	private Set<Survey> cuestionarios;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="tag")
	private Set<Question> preguntas;

	@Override
	public String toString() {
		return "Tag [idTag=" + idTag + ", nombre=" + name + "]";
	}
	
	
}
