package com.wolox.challenge.domain.user.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.domain.album.business.AlbumService;
import com.wolox.challenge.domain.photo.business.PhotoService;
import com.wolox.challenge.domain.user.dto.PhotoDTO;
import com.wolox.challenge.domain.user.dto.UserClientDTO;
import com.wolox.challenge.domain.user.dto.UserDTO;
import com.wolox.challenge.domain.user.repository.IUserRepository;
import com.wolox.challenge.domain.user.repository.UserServiceClient;
import com.wolox.challenge.entity.Album;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.entity.enums.EPermit;
import com.wolox.challenge.service.dto.Response;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserServiceClient userServiceClient;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private AlbumService albumService;

	/**
	 * Se consultan todos los usuarios
	 */
	@Override
	public List<UserClientDTO> getUsers() {
		return userServiceClient.usersClient();
	}

	/**
	 * Se consulta el usuario por id
	 */
	@Override
	public UserClientDTO getUserById(Long id) {
		return userServiceClient.getUserByIdClient(id);
	}

	/**
	 * Metodo empleado para consultar las fotos de un usuario, siempre que se
	 * encuentre registrado dentro de la base de datos. Ya que primero se consultan
	 * los albunes de un usuario y por cada album se traen todas las fotos
	 * correspondientes
	 */
	@Override
	public Response<UserDTO> getuserPhotos(Long id) {
		Response<UserDTO> response = new Response<>();

		List<PhotoDTO> photos = new ArrayList<>();
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			List<Album> albums = user.get().getAlbums();

			albums.stream().forEach(x -> {
				photos.addAll(photoService
						.getPhotoByAlbum(x.getIdAlbumClient()).stream().map(p -> PhotoDTO.builder().id(p.getId())
								.title(p.getTitle()).url(p.getUrl()).thumbnailUrl(p.getThumbnailUrl()).build())
						.collect(Collectors.toList()));
			});
			response.setData(UserDTO.builder().id(user.get().getIdUserClient()).name(user.get().getName())
					.email(user.get().getEmail()).phone(user.get().getPhone()).website(user.get().getWebsite())
					.photos(photos).build());
		} else {
			response.setState(Boolean.FALSE);
			response.setMessage("Usuario no encontrado");
		}
		return response;
	}

	@Override
	public User getUserByIdUserClient(Long id) {
		return userRepository.findByIdUserClient(id);
	}

	/**
	 * Lista los usuario que posee un permiso especifico a un albun especifico
	 */
	@Override
	public Response<List<User>> getUserAlbumPermit(Long idAlbum, EPermit permit) {
		Response<List<User>> response = new Response<>();
		response.setData(albumService.getAlbumPermit(idAlbum, permit));
		return response;
	}

}
