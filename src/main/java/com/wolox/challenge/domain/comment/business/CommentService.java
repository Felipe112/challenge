package com.wolox.challenge.domain.comment.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wolox.challenge.domain.comment.dto.CommentClientDTO;
import com.wolox.challenge.domain.comment.dto.PostClientDTO;
import com.wolox.challenge.domain.comment.repository.CommentServiceClient;
import com.wolox.challenge.domain.comment.repository.PostServiceClient;

@Service
public class CommentService implements ICommentService {

	@Autowired
	private CommentServiceClient commentServiceCliente;

	@Autowired
	private PostServiceClient postServiceCliente;

	/**
	 * Se consultan todos los comentarios
	 */
	@Override
	public List<CommentClientDTO> getComments() {
		return commentServiceCliente.getComments();
	}

	/**
	 * se reciben dos variables idusuario y name, en caso de que el nombre llegue se
	 * realiza una consulta por este para retornar los comentarios en caso contrario
	 * se toma el iduser y se consultan los post y por cada post se traen los
	 * comentarios
	 */
	@Override
	public List<CommentClientDTO> getFilter(Long idUser, String name) {

		List<CommentClientDTO> comments = new ArrayList<>();
		if (!name.isEmpty()) {
			comments = commentServiceCliente.getByName(name);
		} else {
			List<PostClientDTO> posts = postServiceCliente.getPostUser(idUser);
			if (!posts.isEmpty()) {
				for (PostClientDTO post : posts) {
					comments.addAll(commentServiceCliente.getByPost(post.getId()));
				}
			}
		}
		return comments;
	}

}
