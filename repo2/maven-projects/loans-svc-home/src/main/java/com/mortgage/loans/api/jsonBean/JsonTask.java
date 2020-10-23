package com.mortgage.loans.api.jsonBean;

import java.sql.Timestamp;

public class JsonTask {
	private Long id;

	private boolean read;

	private Long assign_to;

	private String assign_fname;

	private String assign_lname;

	private Timestamp task_date;
	private String task_description;

	private Long application_id;

	private Timestamp created_on;

	private Long created_by;

	private String assign_by_fname;

	private String assign_by_lname;

	private Timestamp modifed_on;

	private Long modified_by;

	private boolean delete;

	private String status;

	public JsonTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JsonTask(Long id, boolean read, Long assign_to, String assign_fname, String assign_lname,
			Timestamp task_date, String task_description, Long application_id, Timestamp created_on, Long created_by,
			String assign_by_fname, String assign_by_lname, Timestamp modifed_on, Long modified_by, boolean delete,
			String status) {
		super();
		this.id = id;
		this.read = read;
		this.assign_to = assign_to;
		this.assign_fname = assign_fname;
		this.assign_lname = assign_lname;
		this.task_date = task_date;
		this.task_description = task_description;
		this.application_id = application_id;
		this.created_on = created_on;
		this.created_by = created_by;
		this.assign_by_fname = assign_by_fname;
		this.assign_by_lname = assign_by_lname;
		this.modifed_on = modifed_on;
		this.modified_by = modified_by;
		this.delete = delete;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public boolean isRead() {
		return read;
	}

	public Long getAssign_to() {
		return assign_to;
	}

	public String getAssign_fname() {
		return assign_fname;
	}

	public String getAssign_lname() {
		return assign_lname;
	}

	public Timestamp getTask_date() {
		return task_date;
	}

	public String getTask_description() {
		return task_description;
	}

	public Long getApplication_id() {
		return application_id;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public String getAssign_by_fname() {
		return assign_by_fname;
	}

	public String getAssign_by_lname() {
		return assign_by_lname;
	}

	public Timestamp getModifed_on() {
		return modifed_on;
	}

	public Long getModified_by() {
		return modified_by;
	}

	public boolean isDelete() {
		return delete;
	}

	public String getStatus() {
		return status;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public void setAssign_to(Long assign_to) {
		this.assign_to = assign_to;
	}

	public void setAssign_fname(String assign_fname) {
		this.assign_fname = assign_fname;
	}

	public void setAssign_lname(String assign_lname) {
		this.assign_lname = assign_lname;
	}

	public void setTask_date(Timestamp task_date) {
		this.task_date = task_date;
	}

	public void setTask_description(String task_description) {
		this.task_description = task_description;
	}

	public void setApplication_id(Long application_id) {
		this.application_id = application_id;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public void setAssign_by_fname(String assign_by_fname) {
		this.assign_by_fname = assign_by_fname;
	}

	public void setAssign_by_lname(String assign_by_lname) {
		this.assign_by_lname = assign_by_lname;
	}

	public void setModifed_on(Timestamp modifed_on) {
		this.modifed_on = modifed_on;
	}

	public void setModified_by(Long modified_by) {
		this.modified_by = modified_by;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "JsonTask [id=" + id + ", read=" + read + ", assign_to=" + assign_to + ", assign_fname=" + assign_fname
				+ ", assign_lname=" + assign_lname + ", task_date=" + task_date + ", task_description="
				+ task_description + ", application_id=" + application_id + ", created_on=" + created_on
				+ ", created_by=" + created_by + ", assign_by_fname=" + assign_by_fname + ", assign_by_lname="
				+ assign_by_lname + ", modifed_on=" + modifed_on + ", modified_by=" + modified_by + ", delete=" + delete
				+ ", status=" + status + "]";
	}
	
}
