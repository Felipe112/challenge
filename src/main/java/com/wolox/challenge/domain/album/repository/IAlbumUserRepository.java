package com.wolox.challenge.domain.album.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wolox.challenge.entity.AlbumUser;
import com.wolox.challenge.entity.User;
import com.wolox.challenge.entity.enums.EPermit;

@Repository
public interface IAlbumUserRepository extends JpaRepository<AlbumUser, Long> {

	@Query("SELECT u FROM AlbumUser au JOIN au.user u WHERE au.album.idAlbumClient =:album AND au.permit=:permit ")
	List<User> getUserByAlbumAndPermit(@Param("album") Long album, @Param("permit") EPermit permit);

	@Query("SELECT au FROM AlbumUser au WHERE au.album.idAlbumClient =:album AND au.user.idUserClient=:idUser ")
	AlbumUser findAllByAlbumAndUser(@Param("album") Long album, @Param("idUser") Long idUser);

}
