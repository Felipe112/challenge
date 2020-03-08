package com.wolox.challenge.domain.album.dto;

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
public class AlbumClientDTO implements Serializable {

	private static final long serialVersionUID = -2258956156789799012L;
	private Long id;
	private Long userId;
	private String title;
}
