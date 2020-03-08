package com.wolox.challenge.domain.user.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

	private static final long serialVersionUID = -1631640691920779973L;
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String website;
	private List<PhotoDTO> photos;
	
}
