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
public class AddressClientDTO implements Serializable {

	private static final long serialVersionUID = -3324173670071356472L;

	private String street;
	private String suite;
	private String city;
	private String zipcode;
	private GeoClientDTO geo;
}
