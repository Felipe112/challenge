package com.wolox.challenge.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeoClientDTO implements Serializable {

	private static final long serialVersionUID = -3324173670071356472L;
	
	private double lat;
    private double lng;        
}
