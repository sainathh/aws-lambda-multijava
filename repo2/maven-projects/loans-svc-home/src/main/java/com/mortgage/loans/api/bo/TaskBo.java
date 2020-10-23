package com.mortgage.loans.api.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mortgage.loans.api.dao.IEmployeeDao;
import com.mortgage.loans.api.dao.ITaskDao;
import com.mortgage.loans.api.jsonBean.JsonTask;
import com.mortgage.loans.api.models.Employee;
import com.mortgage.loans.api.models.Task;
import com.mortgage.loans.api.utils.Constants;
import com.mortgage.loans.api.utils.SendEmail;

@Service
public class TaskBo {
	
	@Autowired
	private ITaskDao taskDao;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired
	private SendEmail sendEmail;

	public Map<String, Object> saveUpdate(Task task) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		String cr = null;
		try {
			
			if (task.getAssign_to() == null) {
				rsMap.put("missing", "Assign to should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (task.getTask_date() == null) {
				rsMap.put("missing", "Task date should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}

			if (task.getCreated_by() == null) {
				rsMap.put("missing", "Created by should not be null");
				rsMap.put("message", Constants.BAD_REQUEST);
				return rsMap;
			}
			
			if (task.getId() == null) {
				task.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
			} else {
				Task e = taskDao.findById(task.getId()).orElse(null);
				task.setCreated_on(e.getCreated_on());
			}
			
			if (task.getId() == null) {
				task.setCreated_on(new java.sql.Timestamp(new java.util.Date().getTime()));
				cr = "create";
			} else {
				Task e =  taskDao.findById(task.getId()).orElse(null);
				task.setCreated_on(e.getCreated_on());
			}
			
			task.setStatus("Open");
			task.setRead(false);
			task.setDelete(false);
			task.setModifed_on((new java.sql.Timestamp(new java.util.Date().getTime())));
			t = taskDao.save(task);
			Employee em = employeeDao.findById(task.getAssign_to()).orElse(null);
			
			if(cr != null && em != null) {
				sendEmail.email(Constants.EMAIL_SUBJECT_TASK, em.getEmail(), "Your task is scheduled on : " + task.getTask_date());
			}
			
			rsMap.put("task", t);
			rsMap.put("message", Constants.SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;

	}

	public List<JsonTask> findAll() {
		List<Task> list = new ArrayList<>();

		List<JsonTask> respList = new ArrayList<>();

		try {
			list = (List<Task>) taskDao.findAll();
			for (Task task : list) {
				Employee ae = employeeDao.findById(task.getAssign_to()).orElse(null);
				Employee ce = employeeDao.findById(task.getCreated_by()).orElse(null);
				
				JsonTask t = new JsonTask();
				
				if(ae != null) {
					t.setAssign_fname(ae.getFirst_name());
					t.setAssign_lname(ae.getLast_name());
				}
				
				if(ce != null) {
					t.setAssign_by_fname(ce.getFirst_name());
					t.setAssign_by_lname(ce.getLast_name());
				}
				
				t.setId(task.getId());
				t.setAssign_to(task.getAssign_to());
				t.setCreated_by(task.getCreated_by());
				t.setRead(task.isRead());
				t.setDelete(task.isDelete());
				t.setTask_description(task.getTask_description());
				t.setTask_date(task.getTask_date());
				t.setStatus(task.getStatus());
				
				respList.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respList;
	}
	
	public List<JsonTask> getTaskByEmpId(Long id) {
		List<Task> list = new ArrayList<>();
		List<JsonTask> respList = new ArrayList<>();

		try {
			list = (List<Task>) taskDao.getTaskByEmpId(id);
			for (Task task : list) {
				Employee ae = employeeDao.findById(task.getAssign_to()).orElse(null);
				Employee ce = employeeDao.findById(task.getCreated_by()).orElse(null);
				
				JsonTask t = new JsonTask();
				
				if(ae != null) {
					t.setAssign_fname(ae.getFirst_name());
					t.setAssign_lname(ae.getLast_name());
				}
				
				if(ce != null) {
					t.setAssign_by_fname(ce.getFirst_name());
					t.setAssign_by_lname(ce.getLast_name());
				}
				
				t.setId(task.getId());
				t.setAssign_to(task.getAssign_to());
				t.setCreated_by(task.getCreated_by());
				t.setRead(task.isRead());
				t.setDelete(task.isDelete());
				t.setTask_description(task.getTask_description());
				t.setTask_date(task.getTask_date());
				t.setStatus(task.getStatus());
				
				respList.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respList;
	}

	public Task findById(Long id) {
		Task t = new Task();
		try {
			t = taskDao.findById(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	public Map<String, Object> remove(Long id) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Task ts = taskDao.findById(id).orElse(null);
			if (ts != null) {
				ts.setDelete(true);
				t = taskDao.save(ts);
				rsMap.put("message", t);
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
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Task ts = taskDao.findById(id).orElse(null);
			if (ts != null) {
				ts.setRead(true);
				t = taskDao.save(ts);
				rsMap.put("message", t);
				rsMap.put("message", Constants.SUCCESS);
			} else {
				rsMap.put("message", Constants.NOT_FOUND);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rsMap;
	}
	
	public Map<String, Object> changeStatus(Long id) {
		Task t = new Task();
		Map<String, Object> rsMap = new HashMap<>();
		rsMap.put("message", Constants.FAILED);
		try {
			Task ts = taskDao.findById(id).orElse(null);
			if (ts != null) {
				ts.setStatus("Completed");
				t = taskDao.save(ts);
				rsMap.put("message", t);
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
