package com.mortgage.loans.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import com.mortgage.loans.api.models.Login;
import com.mortgage.loans.api.models.Password;
import com.mortgage.loans.api.utils.Constants;
import com.mortgage.loans.api.utils.JWTUtil;


@RestController
@RequestMapping("/employee-ctr")
public class LoginEmployeeController {
	
	@Autowired
	private EmployeeBo employeeBo;

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> login(@RequestBody Login login) {
		Map<String, Object> rsMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			Employee s = employeeBo.login(login.getEmail(), login.getPassword());
			if(s.getId() != null) {
				JWTUtil util = new JWTUtil();
				String token = util.createToken(login.getEmail());
				headers.add("x-token", token);
				status = HttpStatus.OK;
				rsMap.put("message", "Login Success");
				rsMap.put("info", s);
			} else {
				status = HttpStatus.UNAUTHORIZED;
				rsMap.put("message", "Login Failed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,Object>>(rsMap, headers, status);
	}
	
	@RequestMapping(value="/forgot-password/{email}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, String>> forgotPassword(@PathVariable String email) {
		Map<String, String> rsMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			rsMap = employeeBo.forgotPassword(email);
			if(rsMap.get("message").equals(Constants.INVALID_EMAIL) || rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.NOT_FOUND;
			} else {
				status = HttpStatus.OK;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,String>>(rsMap, headers, status);
	}
	
	@LoginRequired
	@RequestMapping(value="/change-password/{id}", method=RequestMethod.POST)
	public ResponseEntity<Map<String, String>> changePassword(@RequestBody Password pwd, @PathVariable("id") Long id) {
		Map<String, String> rsMap = new HashMap<>();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		HttpHeaders headers = new HttpHeaders();
		
		try {
			rsMap = employeeBo.changePassword(id, pwd.getOldPassword(), pwd.getNewPassword());
			if(rsMap.get("message").equals(Constants.WRONG_PASSWORD) || rsMap.get("message").equals(Constants.FAILED)) {
				status = HttpStatus.NOT_FOUND;
			} else {
				status = HttpStatus.OK;
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Map<String,String>>(rsMap, headers, status);
	}
	
	@RequestMapping(value="/check-email/{email}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>>  findByEmail(@PathVariable("email") String email){
		HttpStatus status=HttpStatus.INTERNAL_SERVER_ERROR;
		Map<String, Object> rsMap = new HashMap<>();
		try {
			rsMap = employeeBo.findByEmail(email);
			if(rsMap.get("message").equals(Constants.EXIST_EMAIL) || rsMap.get("message").equals(Constants.NOT_FOUND_EMAIL)) {
				status = HttpStatus.OK;
			}
		}catch(Exception e) {
			
		}
		return new ResponseEntity<Map<String,Object>>(rsMap, status);
		
	}
}
