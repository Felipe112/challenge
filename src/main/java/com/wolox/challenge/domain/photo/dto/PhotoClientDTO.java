package com.wolox.challenge.domain.photo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhotoClientDTO implements Serializable {

	private static final long serialVersionUID = -2258956156789799012L;
	private Long id;
	private Long albumId;
	private String title;
	private String url;
	private String thumbnailUrl;
}
