package com.navaratna.service;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Package;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.Vendor;

public interface VendorService {
	public boolean vendorLogin(Vendor vendor) throws ClassNotFoundException, NotFoundException, SQLException;
	public boolean createQuotation(Quotation quotation) throws AlreadyExistsException , SQLException, ClassNotFoundException;
	public List<PlanRequest> getPlanRequestList();
	public boolean addPackage(Package pack) throws ClassNotFoundException, AlreadyExistsException, SQLException;
	
}
