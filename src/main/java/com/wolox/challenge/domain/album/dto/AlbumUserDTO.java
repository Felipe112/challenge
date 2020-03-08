package com.wolox.challenge.domain.album.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.wolox.challenge.entity.enums.EPermit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AlbumUserDTO implements Serializable {

	private static final long serialVersionUID = -2258956156789799012L;
	@NonNull
	private Long idUser;
	@NonNull
	private Long idAlbum;
	@NonNull
	private EPermit permit;
}
