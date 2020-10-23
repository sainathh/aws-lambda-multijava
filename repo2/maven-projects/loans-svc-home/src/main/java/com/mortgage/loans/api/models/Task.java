package com.mortgage.loans.api.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
	
	@Id
	@Column(name = "task_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="is_read")
	private boolean read;
	
	@Column
	private Long assign_to;
	
	@Column
	private Timestamp task_date;
	
	@Column
	private String task_description;
	
	@Column
	private Long application_id;
	
	@Column
	private Timestamp created_on;
	
	@Column
	private Long created_by;
	
	@Column
	private Timestamp modifed_on;
	
	@Column
	private Long modified_by;
	
	@Column(name="is_delete")
	private boolean delete;
	
	@Column
	private String status;

	public Task() {
		super();
	}

	public Task(Long id, boolean read, Long assign_to, Timestamp task_date, String task_description,
			Long application_id, Timestamp created_on, Long created_by, Timestamp modifed_on, Long modified_by,
			boolean delete, String status) {
		super();
		this.id = id;
		this.read = read;
		this.assign_to = assign_to;
		this.task_date = task_date;
		this.task_description = task_description;
		this.application_id = application_id;
		this.created_on = created_on;
		this.created_by = created_by;
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
		return "Task [id=" + id + ", read=" + read + ", assign_to=" + assign_to + ", task_date=" + task_date
				+ ", task_description=" + task_description + ", application_id=" + application_id + ", created_on="
				+ created_on + ", created_by=" + created_by + ", modifed_on=" + modifed_on + ", modified_by="
				+ modified_by + ", delete=" + delete + ", status=" + status + "]";
	}

}
