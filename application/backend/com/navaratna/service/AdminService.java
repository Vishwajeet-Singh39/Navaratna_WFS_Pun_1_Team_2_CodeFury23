package com.navaratna.service;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.IncorrectDetailsException;
import com.navaratna.model.Vendor;

public interface AdminService {
	public boolean isValid(String usermname,String password) throws IncorrectDetailsException;
	public boolean toggleUserStatus(int userId) throws IncorrectDetailsException;
	public List<Object> displayVendors() throws IncorrectDetailsException;
	public List<Object> displayUsers() throws IncorrectDetailsException;
	public boolean addVendor(Vendor vendor) throws ClassNotFoundException, AlreadyExistsException, SQLException;
}
