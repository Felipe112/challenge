package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.domain.user.business.UserService;
import com.wolox.challenge.domain.user.dto.UserClientDTO;
import com.wolox.challenge.domain.user.dto.UserDTO;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.entity.enums.EPermit;
import com.wolox.challenge.service.configuration.AbstractRestController;
import com.wolox.challenge.service.dto.Response;

@RestController
@RequestMapping("rest/user")
public class UserController extends AbstractRestController {

	@Autowired
	private UserService userService;

	@GetMapping("/fidAll")
	public ResponseEntity<Response<List<UserClientDTO>>> fidAll() {
		Response<List<UserClientDTO>> response = new Response<>();
		response.setData(userService.getUsers());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/fidById/{id}")
	public ResponseEntity<Response<UserClientDTO>> fidById(@PathVariable("id") Long id) {
		Response<UserClientDTO> response = new Response<>();
		response.setData(userService.getUserById(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/fidByPhotos/{idUser}")
	public ResponseEntity<Response<UserDTO>> fidByPhotos(@PathVariable("idUser") Long idUser) {
		return new ResponseEntity<>(userService.getuserPhotos(idUser), HttpStatus.OK);
	}

	@GetMapping("/album/{idAlbum}/permit/{permit}")
	public ResponseEntity<Response<List<User>>> fidAlbumPermit(@PathVariable("idAlbum") Long idAlbum,
			@PathVariable("permit") EPermit permit) {
		return new ResponseEntity<>(userService.getUserAlbumPermit(idAlbum, permit), HttpStatus.OK);
	}
}
