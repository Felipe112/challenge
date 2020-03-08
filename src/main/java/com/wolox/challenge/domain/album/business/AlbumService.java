package com.wolox.challenge.domain.album.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.domain.album.dto.AlbumClientDTO;
import com.wolox.challenge.domain.album.dto.AlbumUserDTO;
import com.wolox.challenge.domain.album.repository.AlbumServiceClient;
import com.wolox.challenge.domain.album.repository.IAlbumRepository;
import com.wolox.challenge.domain.album.repository.IAlbumUserRepository;
import com.wolox.challenge.domain.user.business.UserService;
import com.wolox.challenge.entity.Album;
import com.wolox.challenge.entity.AlbumUser;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.entity.enums.EPermit;
import com.wolox.challenge.service.dto.Response;

@Service
public class AlbumService implements IAlbumService {

	@Autowired
	private AlbumServiceClient albumServiceClient;

	@Autowired
	private IAlbumUserRepository albumUserRepository;

	@Autowired
	private IAlbumRepository albumRepository;

	@Autowired
	private UserService userService;

	/**
	 * Metodo empleado para consultar todos los albunes
	 */
	@Override
	public List<AlbumClientDTO> getAlbums() {
		return albumServiceClient.albumsClient();
	}

	/**
	 * Se obtinen los albunes por usuario
	 */
	@Override
	public List<AlbumClientDTO> getAlbumsByUser(Long id) {
		return albumServiceClient.albumsByUserClient(id);
	}

	/**
	 * Se crea la relacion de usuarios con albunes de otros usuarios y los permisos
	 */
	@Override
	public Response<Void> createRelationShip(AlbumUserDTO albumUserDTO) {
		Response<Void> response = new Response<>();

		AlbumUser albumExist = albumUserRepository.findAllByAlbumAndUser(albumUserDTO.getIdAlbum(),
				albumUserDTO.getIdUser());

		if (albumExist != null) {
			response.setState(Boolean.FALSE);
			response.setMessage("El registro que trata de crear ya existe");
			return response;
		}

		Optional<Album> album = albumRepository.findById(albumUserDTO.getIdAlbum());

		AlbumUser albumUser = validateSave(albumUserDTO);
		if (albumUser != null) {
			if (album.isPresent()) {
				try {
					albumUser.setAlbum(album.get());
					albumUserRepository.saveAndFlush(albumUser);

					response.setMessage("Registro Guardado");
				} catch (Exception e) {
					response.setState(Boolean.FALSE);
					response.setMessage("Problema al guardar el registro");
				}
			} else {
				response.setState(Boolean.FALSE);
				response.setMessage("El album no existe en la base de datos");
			}
		} else {
			response.setState(Boolean.FALSE);
			response.setMessage("El usuario ya posee una relación con el album");
		}
		return response;
	}

	/**
	 * Se actualiza la relacion de usuarios con albunes de otros usuarios y los
	 * permisos
	 */
	@Override
	public Response<Void> updateRelationShip(Long id, AlbumUserDTO albumUserDTO) {
		Response<Void> response = new Response<>();

		Optional<Album> album = albumRepository.findById(albumUserDTO.getIdAlbum());

		AlbumUser albumUser = validateSave(albumUserDTO);
		if (albumUser != null) {
			if (album.isPresent()) {
				try {
					albumUser.setId(id);
					albumUser.setAlbum(album.get());
					albumUserRepository.save(albumUser);
					response.setMessage("Registro Actualizado");
				} catch (Exception e) {
					response.setState(Boolean.FALSE);
					response.setMessage("Problema al actualizar el registro");
				}
			} else {
				response.setState(Boolean.FALSE);
				response.setMessage("El album no existe en la base de datos");
			}
		} else {
			response.setState(Boolean.FALSE);
			response.setMessage("El usuario ya posee una relación con el album");
		}
		return response;
	}

	/**
	 * Metodo en el cual se valida que el registro a guardar no sea un album del
	 * usuario.
	 * 
	 * @param albumUserDTO
	 * @return
	 */
	private AlbumUser validateSave(AlbumUserDTO albumUserDTO) {
		AlbumUser albumUser = null;
		User user = userService.getUserByIdUserClient(albumUserDTO.getIdUser());

		if (null == user.getAlbums().stream().filter(x -> albumUserDTO.getIdAlbum().equals(x.getIdAlbumClient()))
				.findFirst().orElse(null)) {
			albumUser = AlbumUser.builder().user(user).permit(albumUserDTO.getPermit()).build();
		}
		return albumUser;
	}

	public List<User> getAlbumPermit(Long idAlbum, EPermit permit) {
		return albumUserRepository.getUserByAlbumAndPermit(idAlbum, permit);
	}

}
