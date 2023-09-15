package com.navaratna.model;

public class Vendor {
	private int vendorId;
	private String username;
	private String password;
	private String name;
	private String address;
	private String email;
	private long contactNo;
	
	public Vendor() {
		super();
	}
	
	public Vendor(int vendorId, 
			String username, 
			String password, 
			String name, 
			String address, 
			String email,
			long contactNo) {
		super();
		this.vendorId = vendorId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.address = address;
		this.email = email;
		this.contactNo = contactNo;
	}


	public int getVendorId() {
		return vendorId;
	}


	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public long getContactNo() {
		return contactNo;
	}


	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	@Override
	public String toString() {
		return "Vendor [vendorId=" + vendorId + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", address=" + address + ", email=" + email + ", contactNo=" + contactNo + "]";
	}
	
	
	
	
}
