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
public class User {

	@Id
	@GeneratedValue
	private Integer idUser;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false)
	private String password;
	
	
}
