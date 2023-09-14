package com.navaratna.service;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.dao.PackageDao;
import com.navaratna.dao.PlanRequestDao;
import com.navaratna.dao.QuotationDao;
import com.navaratna.dao.VendorDao;
import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Package;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.Vendor;

public class VendorServiceImpl implements VendorService {
	VendorDao vendorDao;
	QuotationDao quoteDao;
	PlanRequestDao planRequestDao;
	PackageDao packDao;

	@Override
	public boolean vendorLogin(Vendor vendor) throws ClassNotFoundException, NotFoundException, SQLException {
		Vendor crossCheck = vendorDao.getVendor(vendor.getVendorId());
		if (crossCheck.getEmail().equals(vendor.getEmail()) && crossCheck.getPassword().equals(vendor.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean createQuotation(Quotation quotation) throws AlreadyExistsException , SQLException, ClassNotFoundException {
		return quoteDao.addQuotation(quotation);
	}

	@Override
	public List<PlanRequest> getPlanRequestList() {
		return planRequestDao.readPlanRequest();
		
	}

	@Override
	public boolean addPackage(Package pack) throws ClassNotFoundException, AlreadyExistsException, SQLException {
		return packDao.addPackage(pack);
	}

}
