package com.atsistemas.EncuestaProj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Result {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idResult;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_course")
	private Course course;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cuestionario")
	private Survey cuestionario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_question")
	private Question question;
	
	private String questionName;
	
	private String answer;
	
	private Boolean esCorrecto;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	
}
