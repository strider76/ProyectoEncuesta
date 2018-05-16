package com.atsistemas.EncuestaProj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes= {@Index(name="dificultad_tag",columnList="id_tag,nombre")})
public class Tag {

	public final static String TAG_DIFICULTAD = "dificultad";
	
	@Id
	@GeneratedValue
	@Column(name="id_tag")
	private Integer idTag;
	
	@Column(name="nombre",nullable=false)
	private String nombre;
	
	@ManyToOne(fetch =FetchType.LAZY)
	@JoinColumn(name="id_dificultad")
	private Dificultad dificultad;
	
	@ManyToMany(fetch=FetchType.LAZY,
				cascade={	CascadeType.MERGE,
							CascadeType.PERSIST
						
				})
	@JoinTable(name="tag_cuestionario",
				joinColumns = {@JoinColumn(name="id_tag")},
				inverseJoinColumns = {@JoinColumn(name="id_cuestionario")})
	private List<Cuestionario> cuestionarios;
	
	@OneToMany(fetch=FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.PERSIST
			},
			mappedBy="etiqueta")
	private List<Pregunta> preguntas;
}
