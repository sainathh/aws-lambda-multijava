package com.mortgage.loans.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mortgage.loans.api.bo.CommentBo;
import com.mortgage.loans.api.configuration.LoginRequired;
import com.mortgage.loans.api.models.Comment;
import com.mortgage.loans.api.utils.Constants;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private CommentBo commentBo;
	
	private ResponseEntity<Map<String, Object>> createUpdate(Comment comment) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = commentBo.save(comment);
			if (!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody Comment comment) {
		return createUpdate(comment);
	}
	
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Comment> list = new ArrayList<>();

		try {
			list = commentBo.findAll();
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<List<Comment>>(list, status);
	}
	
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Comment> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Comment c = new Comment();
		try {
			c = commentBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Comment>(c, status);
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = commentBo.remove(id);
			if (!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value = "/get/comment/application/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> findByApplicationId(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Comment> list = new ArrayList<>();
		try {
			list =  commentBo.findByApplicationId(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<Comment>>(list, status);
	}

}
