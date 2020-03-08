package com.wolox.challenge.domain.comment.business;

import java.util.List;

import com.wolox.challenge.domain.comment.dto.CommentClientDTO;

public interface ICommentService {

	public List<CommentClientDTO> getComments();

	public List<CommentClientDTO> getFilter(Long idUser, String name);

}
