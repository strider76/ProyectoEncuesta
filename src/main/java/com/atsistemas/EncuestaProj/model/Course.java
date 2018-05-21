package com.atsistemas.EncuestaProj.model;

import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Course {

	@Id
	@GeneratedValue
	private Integer idCourse;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_course",
				joinColumns=@JoinColumn(name="id_course"),
				inverseJoinColumns=@JoinColumn(name="id_user"))
	private List<User> users;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "course")
	private List<Cuestionario> cuestionarios;
	
	
}
