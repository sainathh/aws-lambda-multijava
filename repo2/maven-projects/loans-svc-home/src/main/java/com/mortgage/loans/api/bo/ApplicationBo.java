package com.mortgage.loans.api.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.loans.api.dao.IApplicationDao;
import com.mortgage.loans.api.models.Application;
import com.mortgage.loans.api.utils.Constants;
import com.mortgage.loans.api.utils.SendEmail;

@Service
public class ApplicationBo {

	@Autowired
	private IApplicationDao applicationDao;
	
	@Autowired
	private SendEmail sendEmail;

	public Map<String, Object> saveUpdate(Application app) {
		Application a = new Application();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			if(app.getFirst_name() == null || app.getFirst_name().isEmpty() || app.getFirst_name().trim().length() == 0) {
				rsMap.put("missing", "First name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getLast_name() == null || app.getLast_name().isEmpty() || app.getLast_name().trim().length() == 0) {
				rsMap.put("missing", "Last name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getDob() == null ) {
				rsMap.put("missing", "Date Of Birth should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getEmail() == null || app.getEmail().isEmpty() || app.getEmail().trim().length() == 0) {
				rsMap.put("missing", "Email should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getPhone() == null || app.getPhone().isEmpty() || app.getPhone().trim().length() == 0) {
				rsMap.put("missing", "Phone should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getAddress() == null || app.getAddress() .isEmpty() || app.getAddress() .trim().length() == 0) {
				rsMap.put("missing", "Address should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getDriving_license() == null || app.getDriving_license().isEmpty() || app.getDriving_license() .trim().length() == 0) {
				rsMap.put("missing", "Driving license should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getEmployer_name() == null || app.getEmployer_name().isEmpty() || app.getEmployer_name() .trim().length() == 0) {
				rsMap.put("missing", "Employer name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getEmployer_address() == null || app.getEmployer_address().isEmpty() || app.getEmployer_address().trim().length() == 0) {
				rsMap.put("missing", "Address should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getAnnual_salary() == null || app.getAnnual_salary().isEmpty() || app.getAnnual_salary().trim().length() == 0) {
				rsMap.put("missing", "Annual salary should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getMonthly_inhand_salary() == null || app.getMonthly_inhand_salary().isEmpty() || app.getMonthly_inhand_salary().trim().length() == 0) {
				rsMap.put("missing", "Monthly salary should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getBank_name() == null || app.getBank_name() .isEmpty() || app.getBank_name() .trim().length() == 0) {
				rsMap.put("missing", "Bank name should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getAccount_number() == null || app.getAccount_number().isEmpty() || app.getAccount_number().trim().length() == 0) {
				rsMap.put("missing", "Account number should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getAccout_type() == null || app.getAccout_type().isEmpty() || app.getAccout_type().trim().length() == 0) {
				rsMap.put("missing", "Accout type should not be empty");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if(app.getId() == null) {
				app.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
			} else {
				Application e =  applicationDao.findById(app.getId()).orElse(null);
				app.setCreated_on(e.getCreated_on());
			}
			
			app.setRead(false);
			app.setModified_on((new java.sql.Timestamp(new java.util.Date().getTime())));
			app.setDelete(false);
			a = applicationDao.save(app);
			rsMap.put("application", a);
			rsMap.put("message", Constants.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public List<Application> findAll() {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Application> findAllNonDeleted() {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.findAllNonDeleted();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Application> findAllByCustomerId(Long id) {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.findAllByCustomerId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Application> findAllByAssignedId(Long id) {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.findAllByAssignedId(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Application> getApplicationsByStatusIdAndEmpId(int statusId, Long empId) {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.getApplicationsByStatusIdAndEmpId(statusId, empId);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Application findById(Long id) {
		Application app = new Application();
		try {
			app = applicationDao.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return app;
	}

	public Map<String, Object> remove(Long id) {
		Application a = new Application();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Application ap = applicationDao.findById(id).orElse(null);
			if (ap != null) {
				ap.setDelete(true);
				a = applicationDao.save(ap);
				rsMap.put("message", a);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public Map<String, Object> changeRead(Long id) {
		Application a = new Application();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Application ap = applicationDao.findById(id).orElse(null);
			if (ap != null) {
				ap.setRead(true);;
				a = applicationDao.save(ap);
				rsMap.put("message", a);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public List<Application> findByStatus(int status) {
		List<Application> list = new ArrayList<>();
		try {
			list = (List<Application>) applicationDao.findByStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Application> search(Application app) {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.search(app.getFirst_name(), app.getLast_name(), app.getEmail(), app.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<Application> searchByEmpId(Application app) {
		List<Application> list = new ArrayList<>();

		try {
			list = (List<Application>) applicationDao.searchByEmpId(app.getAssigned_to(), app.getFirst_name(), app.getLast_name(), app.getEmail(), app.getPhone());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public Map<String, Object> assignTo(Application app) {
		Application a = new Application();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Application ap = applicationDao.findById(app.getId()).orElse(null);
			if (ap != null) {
				ap.setRead(app.isRead());
				ap.setAssigned(true);
				ap.setAssigned_to(app.getAssigned_to());
				a = applicationDao.save(ap);
				rsMap.put("message", a);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}

	public Map<String, Object> changeStatus(Application app) {
		Application a = new Application();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Application ap = applicationDao.findById(app.getId()).orElse(null);
			if (ap != null) {
				ap.setStatus_changed_by(app.getStatus_changed_by());
				ap.setStatus(app.getStatus());
				a = applicationDao.save(ap);
				
				String stat = null;
				if(a.getStatus() == 1) {
					stat = "Open";
				} else if(a.getStatus() == 2) {
					stat = "In Progress";
				} else if(a.getStatus() == 3) {
					stat = "Approved";
				} else {
					stat = "Rejected";
				}
				
				sendEmail.email(Constants.STATUS_SUBJECT, a.getEmail(), "Your Application status chnaged to : " + stat );
				rsMap.put("message", a);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
}
