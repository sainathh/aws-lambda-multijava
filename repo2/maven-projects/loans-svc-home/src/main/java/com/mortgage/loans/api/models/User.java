package com.mortgage.loans.api.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String first_name;

	@Column
	private String last_name;

	@Column
	private String gender;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private Timestamp created_on;

	@Column
	private Long created_by;

	@Column
	private Timestamp modifed_on;

	@Column
	private Long modified_by;

	@Column(name = "is_delete")
	private boolean delete;

	public User() {
		super();
	}

	public User(Long id, String first_name, String last_name, String gender, String email, String password,
			Timestamp created_on, Long created_by, Timestamp modifed_on, Long modified_by, boolean delete) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.created_on = created_on;
		this.created_by = created_by;
		this.modifed_on = modifed_on;
		this.modified_by = modified_by;
		this.delete = delete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public Long getCreated_by() {
		return created_by;
	}

	public void setCreated_by(Long created_by) {
		this.created_by = created_by;
	}

	public Timestamp getModifed_on() {
		return modifed_on;
	}

	public void setModifed_on(Timestamp modifed_on) {
		this.modifed_on = modifed_on;
	}

	public Long getModified_by() {
		return modified_by;
	}

	public void setModified_by(Long modified_by) {
		this.modified_by = modified_by;
	}

	public boolean isDelete() {
		return delete;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", created_on=" + created_on + ", created_by="
				+ created_by + ", modifed_on=" + modifed_on + ", modified_by=" + modified_by + ", delete=" + delete
				+ "]";
	}

}
