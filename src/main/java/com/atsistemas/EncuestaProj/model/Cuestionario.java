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
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cuestionario {

	@Id
	@GeneratedValue
	private Integer idCuestionario;
	
	@Column(nullable=false, unique=true)
	private String identificador;
	
	@ManyToOne(fetch=FetchType.LAZY,
				cascade = {
						CascadeType.MERGE,
						CascadeType.PERSIST,
				}
			)
	@JoinColumn(name="id_curso")
	private Course curso;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade = { CascadeType.MERGE,
							CascadeType.PERSIST
							}
			)
	@JoinTable(name="cuestionario_preguntas",
				joinColumns = { @JoinColumn(name="id_cuestionario")},
				inverseJoinColumns = { @JoinColumn(name="id_pregunta")}
			)
	private List<Pregunta> preguntas;
	
}
