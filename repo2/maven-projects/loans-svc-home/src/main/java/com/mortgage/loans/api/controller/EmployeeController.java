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

import com.mortgage.loans.api.bo.EmployeeBo;
import com.mortgage.loans.api.configuration.LoginRequired;
import com.mortgage.loans.api.models.Employee;
import com.mortgage.loans.api.utils.Constants;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeBo employeeBo;
	
	private ResponseEntity<Map<String, Object>> createUpdate(Employee employee) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = employeeBo.saveUpdate(employee);
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
	public ResponseEntity<Map<String, Object>> create(@RequestBody Employee employee) {
		return createUpdate(employee);
	}
	
	@LoginRequired
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> update(@RequestBody Employee employee) {
		return createUpdate(employee);
	}
	
	@LoginRequired
	@RequestMapping(value="/all", method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> getAll() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Employee> list = new ArrayList<>();
		
		try {
			list = employeeBo.findAll();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Employee>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/non-delete/all", method=RequestMethod.GET)
	public ResponseEntity<List<Employee>> findAllNonDeleted() {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Employee> list = new ArrayList<>();
		
		try {
			list = employeeBo.findAllNonDeleted();
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Employee>>(list, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/get/{id}", method=RequestMethod.GET)
	public ResponseEntity<Employee> findById(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Employee app = new Employee();
		try {
			app = employeeBo.findById(id);
			status = HttpStatus.OK;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Employee>(app, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/remove/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = employeeBo.remove(id);
			if(!rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.OK;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/change-role/{id}/{role}", method=RequestMethod.PUT)
	public ResponseEntity<Map<String, Object>> remove(@PathVariable("id") Long id, @PathVariable("role") int role) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = employeeBo.changeRole(id, role);
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
	public ResponseEntity<List<Employee>> search(@RequestBody Employee emp) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		List<Employee> list = new ArrayList<>();
		
		try {
			list = employeeBo.seacrh(emp);
			status = HttpStatus.OK;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<List<Employee>>(list, status);
	}
}
