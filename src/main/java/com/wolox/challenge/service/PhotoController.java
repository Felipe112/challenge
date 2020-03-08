package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.domain.photo.business.PhotoService;
import com.wolox.challenge.domain.photo.dto.PhotoClientDTO;
import com.wolox.challenge.service.configuration.AbstractRestController;
import com.wolox.challenge.service.dto.Response;

@RestController
@RequestMapping("rest/photo")
public class PhotoController extends AbstractRestController {

	@Autowired
	private PhotoService photoService;

	@GetMapping("/fidAll")
	public ResponseEntity<Response<List<PhotoClientDTO>>> fidAll() {
		Response<List<PhotoClientDTO>> response = new Response<>();
		response.setData(photoService.getPhotos());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/fidById/{id}")
	public ResponseEntity<Response<PhotoClientDTO>> fidById(@PathVariable("id") Long id) {
		Response<PhotoClientDTO> response = new Response<>();
		response.setData(photoService.getPhotoById(id));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
