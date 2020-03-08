package com.wolox.challenge.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album")
public class Album implements Serializable {

	private static final long serialVersionUID = -3884482232076174462L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "id_album_client")
	private Long idAlbumClient;

	@ManyToOne(optional = false, cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "id_user_client", nullable = false)
	private User user;

	@Column(name = "title")
	private String title;

}
