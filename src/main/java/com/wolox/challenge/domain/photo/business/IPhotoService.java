package com.wolox.challenge.domain.photo.business;

import java.util.List;

import com.wolox.challenge.domain.photo.dto.PhotoClientDTO;

public interface IPhotoService {

	public List<PhotoClientDTO> getPhotos();

	public PhotoClientDTO getPhotoById(Long id);

	public List<PhotoClientDTO> getPhotoByAlbum(Long idAlbum);
}
