package com.wolox.challenge.domain.album.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wolox.challenge.domain.album.dto.AlbumClientDTO;
import com.wolox.challenge.util.ConfigProperties;

@Service
public class AlbumServiceClient {

	@Autowired
	ConfigProperties configProp;

	private static final String SERVICE_ALBUMS = "service.album";
	private static final String SERVICE_ALBUMS_BY_USER = "service.album.user";

	private static final Logger LOGGER = LoggerFactory.getLogger(AlbumServiceClient.class);

	@HystrixCommand(fallbackMethod = "reliable", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
	public List<AlbumClientDTO> albumsClient() {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<AlbumClientDTO[]> response = cliente.getForEntity(configProp.getConfigValue(SERVICE_ALBUMS),
				AlbumClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public List<AlbumClientDTO> albumsByUserClient(Long idUser) {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<AlbumClientDTO[]> response = cliente.getForEntity(
				String.format(configProp.getConfigValue(SERVICE_ALBUMS_BY_USER), idUser), AlbumClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public List<AlbumClientDTO> reliable() {
		LOGGER.error("circuitBreak::AlbumServiceClient ");
		return new ArrayList<>();
	}
}
