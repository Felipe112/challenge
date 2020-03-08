package com.wolox.challenge.domain.user.business;

import java.util.List;

import com.wolox.challenge.domain.user.dto.UserClientDTO;
import com.wolox.challenge.domain.user.dto.UserDTO;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.entity.enums.EPermit;
import com.wolox.challenge.service.dto.Response;

public interface IUserService {

	public List<UserClientDTO> getUsers();

	public UserClientDTO getUserById(Long id);

	public User getUserByIdUserClient(Long id);

	public Response<UserDTO> getuserPhotos(Long id);

	public Response<List<User>> getUserAlbumPermit(Long idAlbum, EPermit permit);
}
