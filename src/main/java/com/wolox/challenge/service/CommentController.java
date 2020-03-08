package com.wolox.challenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.domain.comment.business.CommentService;
import com.wolox.challenge.domain.comment.dto.CommentClientDTO;
import com.wolox.challenge.service.configuration.AbstractRestController;
import com.wolox.challenge.service.dto.Response;

@RestController
@RequestMapping("rest/comment")
public class CommentController extends AbstractRestController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/fidAll")
	public ResponseEntity<Response<List<CommentClientDTO>>> fidAll() {
		Response<List<CommentClientDTO>> response = new Response<>();
		response.setData(commentService.getComments());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/find")
	@ResponseBody
	public ResponseEntity<Response<List<CommentClientDTO>>> find(@RequestParam Long idUser, @RequestParam String name) {
		Response<List<CommentClientDTO>> response = new Response<>();
		response.setData(commentService.getFilter(idUser,name));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
