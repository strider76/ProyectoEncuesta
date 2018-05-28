package com.atsistemas.EncuestaProj.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idCourse;
	
	@Column(nullable=false, unique=true)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="user_course",
				joinColumns=@JoinColumn(name="id_course"),
				inverseJoinColumns=@JoinColumn(name="id_user"))
	private Set<User> users;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "course")
	private Set<Survey> cuestionarios;

	@Override
	public String toString() {
		return "Course [idCourse=" + idCourse + ", name=" + name + "]";
	}

	
	
}
