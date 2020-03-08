package com.wolox.challenge.domain.comment.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wolox.challenge.domain.comment.dto.CommentClientDTO;
import com.wolox.challenge.util.ConfigProperties;

@Service
public class CommentServiceClient {

	@Autowired
	ConfigProperties configProp;

	private static final String SERVICE_COMMENTS = "service.comment";
	private static final String SERVICE_COMMENT_NAME = "service.comment.name";
	private static final String SERVICE_COMMENT_POST = "service.comment.post";

	public List<CommentClientDTO> getComments() {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<CommentClientDTO[]> response = cliente.getForEntity(configProp.getConfigValue(SERVICE_COMMENTS),
				CommentClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public List<CommentClientDTO> getByName(String name) {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<CommentClientDTO[]> response = cliente.getForEntity(
				String.format(configProp.getConfigValue(SERVICE_COMMENT_NAME), name), CommentClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public List<CommentClientDTO> getByPost(Long idPost) {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<CommentClientDTO[]> response = cliente.getForEntity(
				String.format(configProp.getConfigValue(SERVICE_COMMENT_POST), idPost), CommentClientDTO[].class);
		return Arrays.asList(response.getBody());
	}
}
