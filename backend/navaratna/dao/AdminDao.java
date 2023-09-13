package com.navaratna.dao;

import java.sql.ResultSet;

import com.navaratna.exception.DbConnectionException;
import com.navaratna.exception.IncorrectDetailsException;

public interface AdminDao {
	public ResultSet getAdmin(String username, String password) throws DbConnectionException;
	public int changeUserStatus(int userId) throws DbConnectionException;
	public ResultSet getAllVendors() throws DbConnectionException;
	public ResultSet getAllUsers() throws DbConnectionException;
}
