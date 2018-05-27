package com.atsistemas.EncuestaProj.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
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
	
	@ManyToMany(fetch= FetchType.LAZY, mappedBy = "users")
	private Set<Course> courses;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private Set<Result> results;

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", userName=" + userName + "]";
	}
	
	
}
