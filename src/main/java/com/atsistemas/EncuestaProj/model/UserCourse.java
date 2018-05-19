package com.atsistemas.EncuestaProj.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UserCourse {

	@Id
	@GeneratedValue
	private Integer idUserCourse;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_course")
	private Course course;
}
