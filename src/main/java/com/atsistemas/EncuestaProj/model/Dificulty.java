package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
@Entity
public class Dificulty {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idDificulty;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="dificulty")
	private List<Question> questions;

	public Dificulty(String name){
		this.name =name;
	}
	
	@Override
	public String toString() {
		return "Dificultad [idDificulty=" + idDificulty + ", name=" + name + "]";
	}

	
	
	
}
