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

import com.mortgage.loans.api.bo.UserBo;
import com.mortgage.loans.api.configuration.LoginRequired;
import com.mortgage.loans.api.models.User;
import com.mortgage.loans.api.utils.Constants;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserBo userBo;
	
	private ResponseEntity<Map<String, Object>> createUpdate(User user) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = userBo.saveUpdate(user);
			if(rsMap.get("message").equals(Constants.SUCCESS)) {
				status = HttpStatus.OK;
			} else if(rsMap.get("message").equals(Constants.BAD_REQUEST)) {
				status = HttpStatus.BAD_REQUEST;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody User user) {
		return createUpdate(user);
	}
	
	@LoginRequired
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> update(@RequestBody User user) {
		return createUpdate(user);
	}
	
	@LoginRequired
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<User> list = new ArrayList<>();
		
		try {
			list = userBo.findAll();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<User>>(list, status);
	}
	@LoginRequired
	@RequestMapping(value="/non-delete/all", method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAllNonDeleted() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<User> list = new ArrayList<>();
		
		try {
			list = userBo.findAllNonDeleted();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<User>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		User app = new User();
		try {
			app = userBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<User>(app, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = userBo.remove(id);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ResponseEntity<List<User>> search(@RequestBody User emp) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<User> list = new ArrayList<>();
		
		try {
			list = userBo.seacrh(emp);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<User>>(list, status);
	}
}
