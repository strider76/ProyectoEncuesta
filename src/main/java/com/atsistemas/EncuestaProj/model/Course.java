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
public class Course {

	@Id
	@GeneratedValue
	private Integer idCourse;
	
	@Column(nullable=false)
	private String nombre;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="course")
	private List<UserCourse> users;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "course")
	private List<Cuestionario> cuestionarios;
}
