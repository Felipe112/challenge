package com.wolox.challenge.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.domain.album.business.AlbumService;
import com.wolox.challenge.domain.album.dto.AlbumClientDTO;
import com.wolox.challenge.domain.album.dto.AlbumUserDTO;
import com.wolox.challenge.service.configuration.AbstractRestController;
import com.wolox.challenge.service.dto.Response;

@RestController
@RequestMapping("rest/album")
public class AlbumController extends AbstractRestController {

	@Autowired
	private AlbumService albumService;

	@GetMapping("/fidAll")
	public ResponseEntity<Response<List<AlbumClientDTO>>> fidAll() {
		Response<List<AlbumClientDTO>> response = new Response<>();
		response.setData(albumService.getAlbums());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/fidByUser/{id}")
	public ResponseEntity<Response<List<AlbumClientDTO>>> fidByUser(@PathVariable("id") Long id) {
		Response<List<AlbumClientDTO>> response = new Response<>();
		response.setData(albumService.getAlbumsByUser(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/album-user")
	public ResponseEntity<Response<Void>> createAlbumUser(@Valid @RequestBody AlbumUserDTO albumUser) {
		return new ResponseEntity<>(albumService.createRelationShip(albumUser), HttpStatus.CREATED);
	}

	@PutMapping("/album-user/{id}")
	public ResponseEntity<Response<Void>> updateAlbumUser(@Valid @RequestBody AlbumUserDTO albumUser,
			@PathVariable Long id) {
		return new ResponseEntity<>(albumService.updateRelationShip(id, albumUser), HttpStatus.CREATED);
	}

}
