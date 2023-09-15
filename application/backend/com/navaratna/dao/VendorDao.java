package com.navaratna.dao;

import java.sql.SQLException;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Vendor;

public interface VendorDao {
	public boolean addVendor(Vendor vend) throws AlreadyExistsException, SQLException, ClassNotFoundException;
	public Vendor getVendor(int vendorId) throws NotFoundException, SQLException, ClassNotFoundException;
	
}
