package com.wolox.challenge.domain.comment.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wolox.challenge.domain.comment.dto.PostClientDTO;
import com.wolox.challenge.util.ConfigProperties;

@Service
public class PostServiceClient {

	@Autowired
	ConfigProperties configProp;

	private static final String SERVICE_POST_USER = "service.posts";

	public List<PostClientDTO> getPostUser(Long idUser) {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<PostClientDTO[]> response = cliente.getForEntity(
				String.format(configProp.getConfigValue(SERVICE_POST_USER), idUser), PostClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

}
