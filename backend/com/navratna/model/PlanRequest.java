package com.navratna.model;

import java.util.Date;

public class PlanRequest {
	private int planRequestId;
	private Date fromDate;
	private Date toDate;
	private int noOfPersons;
	private String packageType; 
	private int serviceId;
	
	public PlanRequest() {
		super();
	}

	public PlanRequest(int planRequestId, Date fromDate, Date toDate, int noOfPersons,int serviceId,String packageType) {
		super();
		this.planRequestId = planRequestId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.noOfPersons = noOfPersons;
		this.serviceId=serviceId;
		this.packageType=packageType;
	}


	public PlanRequest(int planRequestId, Date fromDate, Date toDate, int noOfPersons,int serviceId) {
		super();
		this.planRequestId = planRequestId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.noOfPersons = noOfPersons;
		this.serviceId=serviceId;
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

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		return "PlanRequest [planRequestId=" + planRequestId + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", noOfPersons=" + noOfPersons + ", serviceId=" + serviceId + "]";
	}
	
}
