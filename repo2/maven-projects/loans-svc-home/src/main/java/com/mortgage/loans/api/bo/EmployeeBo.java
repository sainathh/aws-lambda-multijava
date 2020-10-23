package com.mortgage.loans.api.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.loans.api.dao.IEmployeeDao;
import com.mortgage.loans.api.models.Employee;
import com.mortgage.loans.api.utils.Constants;
import com.mortgage.loans.api.utils.PasswordUtil;
import com.mortgage.loans.api.utils.SendEmail;

@Service
public class EmployeeBo {

	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private SendEmail sendEmail;

	public Map<String, Object> saveUpdate(Employee app) {
		Employee a = new Employee();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			if (app.getFirst_name() == null || app.getFirst_name().isEmpty()
					|| app.getFirst_name().trim().length() == 0) {
				rsMap.put("missing", "First name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (app.getLast_name() == null || app.getLast_name().isEmpty() || app.getLast_name().trim().length() == 0) {
				rsMap.put("missing", "Last name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (app.getGender() == null || app.getGender().isEmpty() || app.getGender().trim().length() == 0) {
				rsMap.put("missing", "Gender should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (app.getEmail() == null || app.getEmail().isEmpty() || app.getEmail().trim().length() == 0) {
				rsMap.put("missing", "Email should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if (app.getPassword() == null || app.getPassword().isEmpty() || app.getPassword().trim().length() == 0) {
				rsMap.put("missing", "Password should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if (app.getId() == null) {
				app.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
				app.setPassword(new PasswordUtil().encrypt(app.getPassword()));
			} else {
				Employee e =  employeeDao.findById(app.getId()).orElse(null);
				app.setCreated_on(e.getCreated_on());
				app.setPassword(e.getPassword());
			}

			app.setModifed_on((new java.sql.Timestamp(new java.util.Date().getTime())));
			app.setDelete(false);
			a = employeeDao.save(app);

			Employee respEmp = new Employee();
			respEmp.setId(a.getId());
			respEmp.setFirst_name(a.getFirst_name());
			respEmp.setLast_name(a.getLast_name());
			respEmp.setGender(a.getGender());
			respEmp.setEmail(a.getEmail());
			respEmp.setCreated_on(a.getCreated_on());
			respEmp.setCreated_by(a.getCreated_by());
			respEmp.setModifed_on(a.getModifed_on());
			respEmp.setModified_by(a.getModified_by());
			respEmp.setDelete(a.isDelete());
			respEmp.setRole(a.getRole());

			rsMap.put("employee", a);
			rsMap.put("message", Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public List<Employee> findAll() {
		List<Employee> list = new ArrayList<>();
		List<Employee> respEmpList = new ArrayList<>();

		try {
			list = (List<Employee>) employeeDao.findAll();
			for (Employee a : list) {
				Employee respEmp = new Employee();
				respEmp.setId(a.getId());
				respEmp.setFirst_name(a.getFirst_name());
				respEmp.setLast_name(a.getLast_name());
				respEmp.setGender(a.getGender());
				respEmp.setEmail(a.getEmail());
				respEmp.setCreated_on(a.getCreated_on());
				respEmp.setCreated_by(a.getCreated_by());
				respEmp.setModifed_on(a.getModifed_on());
				respEmp.setModified_by(a.getModified_by());
				respEmp.setDelete(a.isDelete());
				respEmp.setRole(a.getRole());

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}
	
	public List<Employee> findAllNonDeleted() {
		List<Employee> list = new ArrayList<>();
		List<Employee> respEmpList = new ArrayList<>();

		try {
			list = (List<Employee>) employeeDao.findAllNonDeleted();
			for (Employee a : list) {
				Employee respEmp = new Employee();
				respEmp.setId(a.getId());
				respEmp.setFirst_name(a.getFirst_name());
				respEmp.setLast_name(a.getLast_name());
				respEmp.setGender(a.getGender());
				respEmp.setEmail(a.getEmail());
				respEmp.setCreated_on(a.getCreated_on());
				respEmp.setCreated_by(a.getCreated_by());
				respEmp.setModifed_on(a.getModifed_on());
				respEmp.setModified_by(a.getModified_by());
				respEmp.setDelete(a.isDelete());
				respEmp.setRole(a.getRole());

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}

	public Employee findById(Long id) {
		Employee a = new Employee();
		Employee respEmp = new Employee();
		try {
			a = employeeDao.findById(id).orElse(null);
			if (a.getId() != null) {
				respEmp.setId(a.getId());
				respEmp.setFirst_name(a.getFirst_name());
				respEmp.setLast_name(a.getLast_name());
				respEmp.setGender(a.getGender());
				respEmp.setEmail(a.getEmail());
				respEmp.setCreated_on(a.getCreated_on());
				respEmp.setCreated_by(a.getCreated_by());
				respEmp.setModifed_on(a.getModifed_on());
				respEmp.setModified_by(a.getModified_by());
				respEmp.setDelete(a.isDelete());
				respEmp.setRole(a.getRole());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmp;
	}

	public Map<String, Object> remove(Long id) {
		Employee a = new Employee();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Employee ap = employeeDao.findById(id).orElse(null);
			if (ap != null) {
				ap.setDelete(true);
				a = employeeDao.save(ap);

				if (a.getId() != null) {
					Employee respEmp = new Employee();
					respEmp.setId(a.getId());
					respEmp.setFirst_name(a.getFirst_name());
					respEmp.setLast_name(a.getLast_name());
					respEmp.setGender(a.getGender());
					respEmp.setEmail(a.getEmail());
					respEmp.setCreated_on(a.getCreated_on());
					respEmp.setCreated_by(a.getCreated_by());
					respEmp.setModifed_on(a.getModifed_on());
					respEmp.setModified_by(a.getModified_by());
					respEmp.setDelete(a.isDelete());
					respEmp.setRole(a.getRole());

					rsMap.put("message", respEmp);
					rsMap.put("message", Constants.SUCCESS);
				}
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public Map<String, Object> changeRole(Long id, int role) {
		Employee a = new Employee();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Employee ap = employeeDao.findById(id).orElse(null);
			if (ap != null) {
				if(ap.getRole() != role) {
					ap.setRole(role);
					a = employeeDao.save(ap);
					
					Employee respEmp = new Employee();
					respEmp.setId(a.getId());
					respEmp.setFirst_name(a.getFirst_name());
					respEmp.setLast_name(a.getLast_name());
					respEmp.setGender(a.getGender());
					respEmp.setEmail(a.getEmail());
					respEmp.setCreated_on(a.getCreated_on());
					respEmp.setCreated_by(a.getCreated_by());
					respEmp.setModifed_on(a.getModifed_on());
					respEmp.setModified_by(a.getModified_by());
					respEmp.setDelete(a.isDelete());
					respEmp.setRole(a.getRole());

					rsMap.put("message", respEmp);
					rsMap.put("message", Constants.SUCCESS);
				}
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public Employee login(String email, String password) throws Exception {
		Employee respEmp = new Employee();
		List<Employee> stud = employeeDao.findByEmail(email);
		if (stud.size() > 0 && !stud.get(0).isDelete()) {
			Employee a = stud.get(0);
			PasswordUtil pwd = new PasswordUtil();
			String pass = pwd.decrypt(a.getPassword());

			if (pass.equals(password)) {
				respEmp.setId(a.getId());
				respEmp.setFirst_name(a.getFirst_name());
				respEmp.setLast_name(a.getLast_name());
				respEmp.setGender(a.getGender());
				respEmp.setEmail(a.getEmail());
				respEmp.setCreated_on(a.getCreated_on());
				respEmp.setCreated_by(a.getCreated_by());
				respEmp.setModifed_on(a.getModifed_on());
				respEmp.setModified_by(a.getModified_by());
				respEmp.setDelete(a.isDelete());
				respEmp.setRole(a.getRole());
			}
		}

		return respEmp;
	}

	public Map<String, String> forgotPassword(String email) throws Exception {
		Map<String, String> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		List<Employee> stud = employeeDao.findByEmail(email);
		if (stud.size() > 0) {
			Employee s = stud.get(0);
			PasswordUtil pwd = new PasswordUtil();
			String pass = pwd.decrypt(s.getPassword());

			String message = sendEmail.email(Constants.EMAIL_SUBJECT_PROFILE, email, "Your password : "+pass);
			rsMap.put("message", message);

		} else {
			rsMap.put("message", Constants.INVALID_EMAIL);
		}

		return rsMap;
	}

	public Map<String, String> changePassword(Long id, String oldPassword, String newPassword) throws Exception {
		Map<String, String> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		Employee emp = employeeDao.findById(id).orElse(null);
		if (emp != null) {
			PasswordUtil pwd = new PasswordUtil();
			String pass = pwd.decrypt(emp.getPassword());

			if (pass.equals(oldPassword)) {
				emp.setPassword(new PasswordUtil().encrypt(newPassword));
				employeeDao.save(emp);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.WRONG_PASSWORD);
			}

		} else {
			rsMap.put("message", Constants.INVALID_EMAIL);
		}

		return rsMap;
	}
	
	public Map<String, Object> findByEmail(String email) {
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			List<Employee> emp = employeeDao.findByEmail(email);
			if (emp.size() > 0 && emp.get(0).getEmail() != null) {
			
				rsMap.put("message", Constants.EXIST_EMAIL);
			} else {
				rsMap.put("message", Constants.NOT_FOUND_EMAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsMap;
	}
	
	public List<Employee> seacrh(Employee emp) {
		List<Employee> list = new ArrayList<>();
		List<Employee> respEmpList = new ArrayList<>();

		try {
			list = (List<Employee>) employeeDao.searchWithoutRole(emp.getFirst_name(), emp.getLast_name(), emp.getEmail());
			
			for (Employee a : list) {
				Employee respEmp = new Employee();
				respEmp.setId(a.getId());
				respEmp.setFirst_name(a.getFirst_name());
				respEmp.setLast_name(a.getLast_name());
				respEmp.setGender(a.getGender());
				respEmp.setEmail(a.getEmail());
				respEmp.setCreated_on(a.getCreated_on());
				respEmp.setCreated_by(a.getCreated_by());
				respEmp.setModifed_on(a.getModifed_on());
				respEmp.setModified_by(a.getModified_by());
				respEmp.setDelete(a.isDelete());
				respEmp.setRole(a.getRole());

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}

}
