package com.navaratna.service;

import java.util.List;

import com.navaratna.exception.IncorrectDetailsException;

public interface AdminService {
	public boolean isValid(String usermname,String password) throws IncorrectDetailsException;
	public boolean toggleUserStatus(int userId) throws IncorrectDetailsException;
	public List<Object> displayVendors() throws IncorrectDetailsException;
	public List<Object> displayUsers() throws IncorrectDetailsException;
}
