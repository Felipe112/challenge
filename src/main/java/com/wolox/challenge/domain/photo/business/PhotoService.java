package com.wolox.challenge.domain.photo.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.domain.photo.dto.PhotoClientDTO;
import com.wolox.challenge.domain.photo.repository.PhotoServiceClient;

@Service
public class PhotoService implements IPhotoService {

	@Autowired
	private PhotoServiceClient photoServiceClient;

	/**
	 * Se consultan todas las fotos
	 */
	@Override
	public List<PhotoClientDTO> getPhotos() {
		return photoServiceClient.usersClient();
	}

	/**
	 * Se consulta la foto por id
	 */
	@Override
	public PhotoClientDTO getPhotoById(Long id) {
		return photoServiceClient.getUserByIdClient(id);
	}

	/**
	 * Consulta de fotos pertenecientes a un album especifico
	 */
	@Override
	public List<PhotoClientDTO> getPhotoByAlbum(Long idAlbum) {
		return photoServiceClient.getUserByIdAlbum(idAlbum);
	}

}
