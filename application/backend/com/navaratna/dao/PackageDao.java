package com.navaratna.dao;

import java.sql.SQLException;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Package;


public interface PackageDao {
	public boolean addPackage(Package pack) throws AlreadyExistsException, SQLException, ClassNotFoundException;
	public Package getPackage(int packageId) throws NotFoundException, SQLException, ClassNotFoundException;
}
