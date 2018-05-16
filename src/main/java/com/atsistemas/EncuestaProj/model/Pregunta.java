package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pregunta {

	public static final String FK_ETIQUETA = "id_etiqueta";
	
	@Id
	@GeneratedValue
	private Integer idPregunta;
	
	@Column(nullable=false)
	private String pregunta;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade = {
						CascadeType.MERGE,
						CascadeType.PERSIST
				},
				mappedBy="preguntas"
			)
	private List<Cuestionario> cuestionario;
	
	@ManyToOne(fetch=FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.PERSIST
			})
	@JoinColumn(name="id_tag")
	private Tag etiqueta;
}
