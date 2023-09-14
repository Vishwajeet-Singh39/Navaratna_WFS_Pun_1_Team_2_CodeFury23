package com.navaratna.model;

public class QuotationService {
	private int quotationId;
	private int serviceId;
	
	
	public QuotationService() {
		super();
	}

	public QuotationService(int quotationId, int serviceId) {
		super();
		this.quotationId = quotationId;
		this.serviceId = serviceId;
	}

	public int getQuotationId() {
		return quotationId;
	}

	public void setQuotationId(int quotationId) {
		this.quotationId = quotationId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	
	
	
}
