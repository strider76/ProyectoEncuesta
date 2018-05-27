package com.atsistemas.EncuestaProj.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Entity
public class Dificulty {

	@Id
	@GeneratedValue
	private Integer idDificulty;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dificulty")
	private Set<Question> questions;

	public Dificulty(String name){
		this.name =name;
	}
	
	@Override
	public String toString() {
		return "Dificultad [idDificulty=" + idDificulty + ", name=" + name + "]";
	}

	
	
	
}
