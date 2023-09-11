package com.navratna.model;

public class ServiceList {
	private int serviceId;
	private String serviceName;
	private double serviceCost;
	private String packageType;
	public ServiceList() {
		super();
	}
	
	public ServiceList(int serviceId, String serviceName, double serviceCost, String packageType) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceCost = serviceCost;
		this.packageType = packageType;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public double getServiceCost() {
		return serviceCost;
	}

	public void setServiceCost(double serviceCost) {
		this.serviceCost = serviceCost;
	}

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	@Override
	public String toString() {
		return "ServiceList [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceCost=" + serviceCost
				+ ", packageType=" + packageType + "]";
	}

}
