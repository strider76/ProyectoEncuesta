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
public class CuestionarioTag {

	@Id
	@GeneratedValue
	private Integer idCuestionarioTag;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_cuestionario")
	private Cuestionario cuestionario;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_tag")
	private Tag tag;
	
}
