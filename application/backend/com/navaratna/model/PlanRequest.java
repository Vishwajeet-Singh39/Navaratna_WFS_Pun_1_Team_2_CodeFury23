package com.navaratna.model;

import java.util.Date;
import java.util.List;

public class PlanRequest {
	private int planRequestId;
	private Date fromDate;
	private Date toDate;
	private int noOfPersons;
	private String packageType; 
	private List<ServiceList> listOfServices;
	
	public PlanRequest() {
		super();
	}

	public PlanRequest(int planRequestId, Date fromDate, Date toDate, int noOfPersons, String packageType,
			List<ServiceList> listOfServices) {
		super();
		this.planRequestId = planRequestId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.noOfPersons = noOfPersons;
		this.packageType = packageType;
		this.listOfServices = listOfServices;
	}

	public int getPlanRequestId() {
		return planRequestId;
	}

	public void setPlanRequestId(int planRequestId) {
		this.planRequestId = planRequestId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public int getNoOfPersons() {
		return noOfPersons;
	}

	public void setNoOfPersons(int noOfPersons) {
		this.noOfPersons = noOfPersons;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public List<ServiceList> getListOfServices() {
		return listOfServices;
	}

	public void setListOfServices(List<ServiceList> listOfServices) {
		this.listOfServices = listOfServices;
	}

	
	
}
