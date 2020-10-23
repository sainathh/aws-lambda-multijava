package com.mortgage.loans.api.controller;

import java.util.ArrayList;
import java.util.Date;
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
import com.mortgage.loans.api.bo.StatusBo;
import com.mortgage.loans.api.configuration.LoginRequired;
import com.mortgage.loans.api.models.Status;
import com.mortgage.loans.api.utils.Constants;

@RestController
@RequestMapping("/api/status")
public class StatusController {

	@Autowired
	private StatusBo statusBo;

	public ResponseEntity<Map<String, Object>> createUpdate(Status status) {
		HttpStatus statuss = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = statusBo.saveUpdate(status);
			if (!rsMap.get("message").equals(Constants.FAILED)) {
				statuss = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(rsMap, statuss);
	}

	@LoginRequired
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody Status status) {
		return createUpdate(status);
	}

	@LoginRequired
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> update(@RequestBody Status status) {
		return createUpdate(status);
	}
	
	@LoginRequired
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Status>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Status> list = new ArrayList<>();
		try {
			list = statusBo.findAll();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Status>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<Status> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Status s = new Status();
		try {
			s = statusBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Status>(s, status);
	}
	
	@RequestMapping(value="/info", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> checkConnection() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			Map<String, Object> drsMap = statusBo.checkDb();
			if(drsMap.get("message").equals(Constants.SUCCESS)) {				
				status = HttpStatus.OK;
				rsMap.put("name", "mortgage-api");
				rsMap.put("currentTime", new Date());
				rsMap.put("host", "loansapp-aw02");
				rsMap.put("database", "localhost:3306/mortgage_db");
				rsMap.put("status", "connected");
				rsMap.put("version", "v1.20");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Map<String, Object>>(rsMap, status);
	}
	
}
