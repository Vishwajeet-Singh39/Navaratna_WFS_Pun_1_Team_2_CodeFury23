package com.navaratna.model;

import java.util.List;

public class Package {
	private int packageId;
	private String packageName;
	private String packageType;
	private double amount;
	private int vendorId;
	private List<ServiceList> listOfServices;
	
	public Package() {
		super();
	}

	public Package(int packageId, String packageName, String packageType, double amount, int vendorId,
			List<ServiceList> listOfServices) {
		super();
		this.packageId = packageId;
		this.packageName = packageName;
		this.packageType = packageType;
		this.amount = amount;
		this.vendorId = vendorId;
		this.listOfServices = listOfServices;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public List<ServiceList> getListOfServices() {
		return listOfServices;
	}

	public void setListOfServices(List<ServiceList> listOfServices) {
		this.listOfServices = listOfServices;
	}
	
	
	
}
