package com.navaratna.model;

import java.util.Date;

public class User {
	private int userId;
	private String fullName;
	private String userName;
	private String password;
	private Date dateJoining;
	private Date dateBirth;
	private String department;
	private long mobile;
	private String email;
	private String location;
	private Boolean status=false;
	
	public User() {
		super();
	}

	public User(int userId, String fullName, String userName, String password, Date dateJoining, Date dateBirth,
			String department, long mobile, String email, String location,Boolean status) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.userName = userName;
		this.password = password;
		this.dateJoining = dateJoining;
		this.dateBirth = dateBirth;
		this.department = department;
		this.mobile = mobile;
		this.email = email;
		this.location = location;
		this.status=status;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateJoining() {
		return dateJoining;
	}

	public void setDateJoining(Date dateJoining) {
		this.dateJoining = dateJoining;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public Boolean getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", fullName=" + fullName + ", userName=" + userName + ", password=" + password
				+ ", dateJoining=" + dateJoining + ", dateBirth=" + dateBirth + ", department=" + department
				+ ", mobile=" + mobile + ", email=" + email + ", location=" + location + ", status=" + status + "]";
	}
	
	
}
