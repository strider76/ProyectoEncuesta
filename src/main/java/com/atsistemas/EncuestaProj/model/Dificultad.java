package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.CascadeType;
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
public class Dificultad {

	@Id
	@GeneratedValue
	private Integer idDificultad;
	
	@Column(nullable=false, unique=true)
	private String dificultad;
	
	@OneToMany(fetch=FetchType.LAZY,
				cascade = {
						CascadeType.MERGE,
						CascadeType.PERSIST
				},
				mappedBy="dificultad")
	private List<Tag> etiquetas;
	
	
}