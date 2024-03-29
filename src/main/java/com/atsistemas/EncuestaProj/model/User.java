package com.atsistemas.EncuestaProj.model;

import java.util.List;

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
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUser;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false, unique=true)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	@ManyToMany(fetch= FetchType.LAZY, mappedBy = "users")
	private List<Course> courses;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="user")
	private List<Result> results;

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", userName=" + userName + "]";
	}
	
	
}
