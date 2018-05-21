package com.atsistemas.EncuestaProj.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Result {

	@Id
	@GeneratedValue
	private Integer idResult;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_user")
	private User user;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cuestionario")
	private Cuestionario cuestionario;
	@Size(min=0,max=10)
	private Integer puntuacion;
}
