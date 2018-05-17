package com.atsistemas.EncuestaProj.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.validation.annotation.Validated;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"id_pregunta","correcta"})})
@Validated
public class Respuesta {

	@Id
	@GeneratedValue
	private Integer idResultado;
	
	@Column(nullable=false)
	private String respuesta;
	
	@Column(name="correcta")
	private Boolean esCorrecta;
	
	@ManyToOne(fetch=FetchType.LAZY,
			cascade={
					CascadeType.MERGE,
					CascadeType.PERSIST,
			})
	@JoinColumn(name="id_pregunta")
	private Pregunta pregunta;
}
