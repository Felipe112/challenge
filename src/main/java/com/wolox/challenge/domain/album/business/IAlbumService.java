package com.wolox.challenge.domain.album.business;

import java.util.List;

import com.wolox.challenge.domain.album.dto.AlbumClientDTO;
import com.wolox.challenge.domain.album.dto.AlbumUserDTO;
import com.wolox.challenge.service.dto.Response;

public interface IAlbumService {

	public List<AlbumClientDTO> getAlbums();

	public List<AlbumClientDTO> getAlbumsByUser(Long id);

	public Response<Void> createRelationShip(AlbumUserDTO albumUser);

	public Response<Void> updateRelationShip(Long id, AlbumUserDTO albumUser);
}
