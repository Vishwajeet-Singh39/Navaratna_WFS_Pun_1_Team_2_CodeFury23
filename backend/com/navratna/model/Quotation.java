package com.navratna.model;

public class Quotation {
	private int quotationId;
	private String packageType;
	private double estimatedAmount;
	private int vendorId;
	private int userId;
	private int planRequestId;
	private String status;
	
	
	public Quotation() {
		super();
	}

	public Quotation(int quotationId, String packageType, double estimatedAmount, int vendorId, int userId,
			int planRequestId, String status) {
		super();
		this.quotationId = quotationId;
		this.packageType = packageType;
		this.estimatedAmount = estimatedAmount;
		this.vendorId = vendorId;
		this.userId = userId;
		this.planRequestId = planRequestId;
		this.status = status;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}

	public int getVendorId() {
		return vendorId;
	}

	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPlanRequestId() {
		return planRequestId;
	}

	public void setPlanRequestId(int planRequestId) {
		this.planRequestId = planRequestId;
	}

	public String isStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Quotation [quotationId=" + quotationId + ", packageType=" + packageType + ", estimatedAmount="
				+ estimatedAmount + ", vendorId=" + vendorId + ", userId=" + userId + ", planRequestId=" + planRequestId
				+ ", status=" + status + "]";
	}
	
	
}
