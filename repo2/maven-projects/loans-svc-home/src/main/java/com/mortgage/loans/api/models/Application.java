package com.mortgage.loans.api.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class Application {

	@Id
	@Column(name = "application_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String first_name;

	@Column
	private String last_name;

	@Column
	private Timestamp dob;

	@Column
	private String email;

	@Column
	private String phone;

	@Column
	private String address;

	@Column
	private String driving_license;

	@Column
	private String employer_name;

	@Column
	private String employer_address;

	@Column
	private String job_title;

	@Column
	private String annual_salary;

	@Column
	private String monthly_inhand_salary;

	@Column
	private String bank_name;

	@Column
	private String account_number;

	@Column
	private String accout_type;

	@Column
	private int status;

	@Column
	private Long status_changed_by;

	@Column(name = "is_read")
	private boolean read;
	
	@Column(name = "assigned_to")
	private Long assigned_to;

	@Column(name = "is_assigned")
	private boolean assigned;

	@Column
	private Timestamp created_on;

	@Column(name="customer_id")
	private Long customer_id;

	@Column(name = "is_delete")
	private boolean delete;

	@Column
	private Timestamp modified_on;

	@Column
	private Long modified_by;
	
	public Application() {
		super();
	}

	public Application(Long id, String first_name, String last_name, Timestamp dob, String email, String phone,
			String address, String driving_license, String employer_name, String employer_address, String job_title,
			String annual_salary, String monthly_inhand_salary, String bank_name, String account_number,
			String accout_type, int status, Long status_changed_by, boolean read, Long assigned_to, boolean assigned,
			Timestamp created_on, Long customer_id, boolean delete, Timestamp modified_on, Long modified_by) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.driving_license = driving_license;
		this.employer_name = employer_name;
		this.employer_address = employer_address;
		this.job_title = job_title;
		this.annual_salary = annual_salary;
		this.monthly_inhand_salary = monthly_inhand_salary;
		this.bank_name = bank_name;
		this.account_number = account_number;
		this.accout_type = accout_type;
		this.status = status;
		this.status_changed_by = status_changed_by;
		this.read = read;
		this.assigned_to = assigned_to;
		this.assigned = assigned;
		this.created_on = created_on;
		this.customer_id = customer_id;
		this.delete = delete;
		this.modified_on = modified_on;
		this.modified_by = modified_by;
	}

	public Long getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public Timestamp getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getDriving_license() {
		return driving_license;
	}

	public String getEmployer_name() {
		return employer_name;
	}

	public String getEmployer_address() {
		return employer_address;
	}

	public String getJob_title() {
		return job_title;
	}

	public String getAnnual_salary() {
		return annual_salary;
	}

	public String getMonthly_inhand_salary() {
		return monthly_inhand_salary;
	}

	public String getBank_name() {
		return bank_name;
	}

	public String getAccount_number() {
		return account_number;
	}

	public String getAccout_type() {
		return accout_type;
	}

	public int getStatus() {
		return status;
	}

	public Long getStatus_changed_by() {
		return status_changed_by;
	}

	public boolean isRead() {
		return read;
	}

	public Long getAssigned_to() {
		return assigned_to;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public boolean isDelete() {
		return delete;
	}

	public Timestamp getModified_on() {
		return modified_on;
	}

	public Long getModified_by() {
		return modified_by;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setDob(Timestamp dob) {
		this.dob = dob;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDriving_license(String driving_license) {
		this.driving_license = driving_license;
	}

	public void setEmployer_name(String employer_name) {
		this.employer_name = employer_name;
	}

	public void setEmployer_address(String employer_address) {
		this.employer_address = employer_address;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public void setAnnual_salary(String annual_salary) {
		this.annual_salary = annual_salary;
	}

	public void setMonthly_inhand_salary(String monthly_inhand_salary) {
		this.monthly_inhand_salary = monthly_inhand_salary;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}

	public void setAccout_type(String accout_type) {
		this.accout_type = accout_type;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setStatus_changed_by(Long status_changed_by) {
		this.status_changed_by = status_changed_by;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public void setAssigned_to(Long assigned_to) {
		this.assigned_to = assigned_to;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public void setDelete(boolean delete) {
		this.delete = delete;
	}

	public void setModified_on(Timestamp modified_on) {
		this.modified_on = modified_on;
	}

	public void setModified_by(Long modified_by) {
		this.modified_by = modified_by;
	}

	@Override
	public String toString() {
		return "Application [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", driving_license="
				+ driving_license + ", employer_name=" + employer_name + ", employer_address=" + employer_address
				+ ", job_title=" + job_title + ", annual_salary=" + annual_salary + ", monthly_inhand_salary="
				+ monthly_inhand_salary + ", bank_name=" + bank_name + ", account_number=" + account_number
				+ ", accout_type=" + accout_type + ", status=" + status + ", status_changed_by=" + status_changed_by
				+ ", read=" + read + ", assigned_to=" + assigned_to + ", assigned=" + assigned + ", created_on="
				+ created_on + ", customer_id=" + customer_id + ", delete=" + delete + ", modified_on=" + modified_on
				+ ", modified_by=" + modified_by + "]";
	}
}
