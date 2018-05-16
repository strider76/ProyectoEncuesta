package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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
	
	@ManyToMany(fetch=FetchType.LAZY,
			cascade={
					CascadeType.MERGE,
					CascadeType.PERSIST
			})
	@JoinTable(name= "user_course",
		joinColumns =  { @JoinColumn(name="id_course")},
		inverseJoinColumns = { @JoinColumn(name="id_user")}
	)
	private List<User> usuarios;
	
}
