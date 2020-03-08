package com.wolox.challenge.domain.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wolox.challenge.entity.Album;

@Repository
public interface IAlbumRepository extends JpaRepository<Album, Long> {

}
