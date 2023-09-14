package com.navaratna.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.navaratna.dao.AdminDao;
import com.navaratna.dao.AdminDaoImpl;
import com.navaratna.dao.VendorDao;
import com.navaratna.dao.VendorDaoJdbcImpl;
import com.navaratna.db.DatabaseConnection;
import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.IncorrectDetailsException;
import com.navaratna.model.Vendor;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao=new AdminDaoImpl();
	VendorDao vendorDao=new VendorDaoJdbcImpl();
	@Override
	public boolean isValid(String username,String password) throws IncorrectDetailsException {
		try {
			ResultSet res = adminDao.getAdmin(username, password);
			int bool=0;
			while(res.next()) {
				bool=res.getInt("login");
			}
			DatabaseConnection.closeResources();
			if(bool==1) {
				return true;
			}
			else{
				throw new IncorrectDetailsException("Incorrect username or password");
			}
		} catch (Exception e) {
			throw new IncorrectDetailsException(e.getMessage());
		}
	}
	@Override
	public boolean toggleUserStatus(int userId) throws IncorrectDetailsException {
		try {
			if(adminDao.changeUserStatus(userId)>0) {
				DatabaseConnection.closeResources();
				return true;
			}
			else {
				DatabaseConnection.closeResources();
				throw new IncorrectDetailsException("Incorrect User ID");
			}
		} catch (Exception e) {
			throw new IncorrectDetailsException(e.getMessage());
		}
	}
	@Override
	public List<Object> displayVendors() throws IncorrectDetailsException {
		try {
			ResultSet res = adminDao.getAllVendors();
			List<Object> vendorList=new ArrayList<>();
			while(res.next()) {
				int i=0;
				while(i++<11) {
					vendorList.add(res.getString(i));
				}
			}
			DatabaseConnection.closeResources();
			return vendorList;
		} catch (Exception e) {
			throw new IncorrectDetailsException(e.getMessage());
		}
	}
	@Override
	public List<Object> displayUsers() throws IncorrectDetailsException {
		try {
			ResultSet res = adminDao.getAllUsers();
			List<Object> userList=new ArrayList<>();
			while(res.next()) {
				int i=0;
				while(i++<11) {
					userList.add(res.getString(i));
				}
			}
			DatabaseConnection.closeResources();
			return userList;
		} catch (Exception e) {
			throw new IncorrectDetailsException(e.getMessage());
		}
	}
	@Override
	public boolean addVendor(Vendor vendor) throws ClassNotFoundException, AlreadyExistsException, SQLException {
		return vendorDao.addVendor(vendor);
	}
}
