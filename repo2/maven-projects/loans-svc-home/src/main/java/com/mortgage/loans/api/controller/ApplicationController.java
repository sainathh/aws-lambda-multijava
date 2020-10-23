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

import com.mortgage.loans.api.bo.ApplicationBo;
import com.mortgage.loans.api.configuration.LoginRequired;
import com.mortgage.loans.api.models.Application;
import com.mortgage.loans.api.utils.Constants;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

	@Autowired
	private ApplicationBo applicationBo;
	
	private ResponseEntity<Map<String, Object>> createUpdate(Application application) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = applicationBo.saveUpdate(application);
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
	
	@LoginRequired
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> create(@RequestBody Application application) {
		return createUpdate(application);
	}
	
	@LoginRequired
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> update(@RequestBody Application application) {
		return createUpdate(application);
	}
	
	@LoginRequired
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.findAll();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/non-deleted/all", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> findAllNonDeleted() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.findAllNonDeleted();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/all/customer/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> findAllByCustomerId(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.findAllByCustomerId(id);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/all/assigned/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> findAllByAssignedId(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.findAllByAssignedId(id);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<Application> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Application app = new Application();
		try {
			app = applicationBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Application>(app, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = applicationBo.remove(id);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/read/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> changeRead(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = applicationBo.changeRead(id);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/assign/to", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> assignTo(@RequestBody Application app) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = applicationBo.assignTo(app);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/change/status", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> changeStatus(@RequestBody Application app) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = applicationBo.changeStatus(app);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/status/{status}", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> findByStatus(@PathVariable("status") int status)  {
		HttpStatus statuss = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.findByStatus(status);
			statuss = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, statuss);
	}
	
	@LoginRequired
	@RequestMapping(value="/status/{status}/{empId}", method=RequestMethod.GET)
	public ResponseEntity<List<Application>> getApplicationsByStatusIdAndEmpId(@PathVariable("status") int status, @PathVariable("empId") Long empId)  {
		HttpStatus statuss = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.getApplicationsByStatusIdAndEmpId(status, empId);
			statuss = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, statuss);
	}
	
	@LoginRequired
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public ResponseEntity<List<Application>> search(@RequestBody Application app) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.search(app);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/search/empid", method=RequestMethod.POST)
	public ResponseEntity<List<Application>> searchByEmpId(@RequestBody Application app) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Application> list = new ArrayList<>();
		
		try {
			list = applicationBo.searchByEmpId(app);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Application>>(list, status);
	}
}
