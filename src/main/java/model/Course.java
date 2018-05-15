package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Course {

	@Id
	@GeneratedValue
	private Integer idCourse;
	
	@Column(nullable=false)
	private String nombre;
	
	
}
