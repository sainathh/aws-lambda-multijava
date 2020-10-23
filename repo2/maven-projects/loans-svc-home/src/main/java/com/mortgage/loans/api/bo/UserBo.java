package com.mortgage.loans.api.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.loans.api.dao.IUserDao;
import com.mortgage.loans.api.models.User;
import com.mortgage.loans.api.utils.Constants;
import com.mortgage.loans.api.utils.PasswordUtil;
import com.mortgage.loans.api.utils.SendEmail;

@Service
public class UserBo {

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private SendEmail sendEmail;

	public Map<String, Object> saveUpdate(User app) {
		User a = new User();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		String pwd = "";
		String str = null;
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
			
			if (app.getId() == null) {
				app.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
				pwd = app.getFirst_name() + app.getEmail().split("@")[0];
				app.setPassword(new PasswordUtil().encrypt(pwd));
				str = "create";
			} else {
				User e =  userDao.findById(app.getId()).orElse(null);
				app.setCreated_on(e.getCreated_on());
				app.setPassword(e.getPassword());
			}

			app.setModifed_on((new java.sql.Timestamp(new java.util.Date().getTime())));
			app.setDelete(false);
			a = userDao.save(app);
			
			if (str != null) { 
				sendEmail.email(Constants.EMAIL_SUBJECT_PROFILE, app.getEmail(), "Your password : "+pwd);
			}

			User respEmp = new User();
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

			rsMap.put("user", respEmp);
			rsMap.put("message", Constants.SUCCESS);
			rsMap.put("messageEmail", "Password sent to email.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public List<User> findAll() {
		List<User> list = new ArrayList<>();
		List<User> respEmpList = new ArrayList<>();

		try {
			list = (List<User>) userDao.findAll();
			for (User a : list) {
				User respEmp = new User();
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

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}
	
	public List<User> findAllNonDeleted() {
		List<User> list = new ArrayList<>();
		List<User> respEmpList = new ArrayList<>();

		try {
			list = (List<User>) userDao.findAllNonDeleted();
			for (User a : list) {
				User respEmp = new User();
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

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}

	public User findById(Long id) {
		User a = new User();
		User respEmp = new User();
		try {
			a = userDao.findById(id).orElse(null);
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
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmp;
	}

	public Map<String, Object> remove(Long id) {
		User a = new User();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			User ap = userDao.findById(id).orElse(null);
			if (ap != null) {
				ap.setDelete(true);
				a = userDao.save(ap);

				if (a.getId() != null) {
					User respEmp = new User();
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

	public User login(String email, String password) throws Exception {
		User respEmp = new User();
		List<User> stud = userDao.findByEmail(email);
		if (stud.size() > 0 && !stud.get(0).isDelete()) {
			User a = stud.get(0);
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
			}
		}

		return respEmp;
	}

	public Map<String, String> forgotPassword(String email) throws Exception {
		Map<String, String> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		List<User> stud = userDao.findByEmail(email);
		if (stud.size() > 0) {
			User s = stud.get(0);
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
		User emp = userDao.findById(id).orElse(null);
		if (emp != null) {
			PasswordUtil pwd = new PasswordUtil();
			String pass = pwd.decrypt(emp.getPassword());

			if (pass.equals(oldPassword)) {
				emp.setPassword(new PasswordUtil().encrypt(newPassword));
				userDao.save(emp);
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
			List<User> user = userDao.findByEmail(email);
			if (user.size() > 0 && user.get(0).getEmail() != null) {
			
				rsMap.put("message", Constants.EXIST_EMAIL);
			} else {
				rsMap.put("message", Constants.NOT_FOUND_EMAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsMap;
	}
	
	public List<User> seacrh(User emp) {
		List<User> list = new ArrayList<>();
		List<User> respEmpList = new ArrayList<>();

		try {
			list = (List<User>) userDao.searchWithoutRole(emp.getFirst_name(), emp.getLast_name(), emp.getEmail());
			
			for (User a : list) {
				User respEmp = new User();
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

				respEmpList.add(respEmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respEmpList;
	}

}
