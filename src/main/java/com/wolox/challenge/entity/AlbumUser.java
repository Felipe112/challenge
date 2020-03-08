package com.wolox.challenge.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.wolox.challenge.entity.enums.EPermit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "album_user", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_user", "id_album" }) })
public class AlbumUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_user", referencedColumnName = "id_user_client", nullable = false)
	private User user;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_album", referencedColumnName = "id_album_client", nullable = false)
	private Album album;

	@Enumerated(EnumType.STRING)
	private EPermit permit;

}
