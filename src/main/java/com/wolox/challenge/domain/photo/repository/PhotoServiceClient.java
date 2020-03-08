package com.wolox.challenge.domain.photo.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wolox.challenge.domain.photo.dto.PhotoClientDTO;
import com.wolox.challenge.util.ConfigProperties;

@Service
public class PhotoServiceClient {

	@Autowired
	ConfigProperties configProp;

	private static final String SERVICE_PHOTOS = "service.photos";
	private static final String SERVICE_PHOTO_ID = "service.photo.id";
	private static final String SERVICE_PHOTO_ALBUM = "service.photo.albumId";

	public List<PhotoClientDTO> usersClient() {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<PhotoClientDTO[]> response = cliente.getForEntity(configProp.getConfigValue(SERVICE_PHOTOS),
				PhotoClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public PhotoClientDTO getUserByIdClient(Long id) {
		return new RestTemplate().getForObject(String.format(configProp.getConfigValue(SERVICE_PHOTO_ID), id),
				PhotoClientDTO.class);
	}

	public List<PhotoClientDTO> getUserByIdAlbum(Long idAlbum) {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<PhotoClientDTO[]> response = cliente.getForEntity(
				String.format(configProp.getConfigValue(SERVICE_PHOTO_ALBUM), idAlbum), PhotoClientDTO[].class);
		return Arrays.asList(response.getBody());
	}
}
