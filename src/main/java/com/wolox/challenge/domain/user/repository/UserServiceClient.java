package com.wolox.challenge.domain.user.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wolox.challenge.domain.user.dto.UserClientDTO;
import com.wolox.challenge.util.ConfigProperties;

@Service
public class UserServiceClient {

	@Autowired
	ConfigProperties configProp;

	private static final String SERVICE_USERS = "service.users";
	private static final String SERVICE_USER_ID = "service.user.id";

	public List<UserClientDTO> usersClient() {
		RestTemplate cliente = new RestTemplate();
		ResponseEntity<UserClientDTO[]> response = cliente.getForEntity(configProp.getConfigValue(SERVICE_USERS),
				UserClientDTO[].class);
		return Arrays.asList(response.getBody());
	}

	public UserClientDTO getUserByIdClient(Long id) {
		return new RestTemplate().getForObject(String.format(configProp.getConfigValue(SERVICE_USER_ID), id),
				UserClientDTO.class);
	}
}
