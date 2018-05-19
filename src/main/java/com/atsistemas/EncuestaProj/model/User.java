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
public class User {

	@Id
	@GeneratedValue
	private Integer idUser;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false, unique=true)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@OneToMany(fetch= FetchType.LAZY, mappedBy = "user")
	private List<UserCourse> courses;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Result> results;
}
