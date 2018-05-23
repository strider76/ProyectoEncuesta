package com.atsistemas.EncuestaProj.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class UserDTOPost extends UserDTO {

	private Integer idUser;
}
