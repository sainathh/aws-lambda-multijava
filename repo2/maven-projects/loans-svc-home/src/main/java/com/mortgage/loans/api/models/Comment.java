package com.mortgage.loans.api.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@Column(name = "comment_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String comment;

	@Column
	private Long application_id;

	@Column
	private Long employee_id;

	@Column(name = "is_delete")
	private boolean delete;

	@Column
	private Timestamp created_on;

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Long id, String comment, Long application_id, Long employee_id, boolean delete,
			Timestamp created_on) {
		super();
		this.id = id;
		this.comment = comment;
		this.application_id = application_id;
		this.employee_id = employee_id;
		this.delete = delete;
		this.created_on = created_on;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getApplication_id() {
		return application_id;
	}

	public void setApplication_id(Long application_id) {
		this.application_id = application_id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", application_id=" + application_id + ", employee_id="
				+ employee_id + ", delete=" + delete + ", created_on=" + created_on + "]";
	}

}
