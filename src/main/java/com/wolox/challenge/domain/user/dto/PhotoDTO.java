package com.wolox.challenge.domain.user.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDTO implements Serializable {

	private static final long serialVersionUID = -1631640691920779973L;
	private Long id;
	private String title;
	private String url;
	private String thumbnailUrl;
	
}
