package com.navaratna.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.navaratna.db.DatabaseConnection;
import com.navaratna.exception.DbConnectionException;

public class AdminDaoImpl implements AdminDao{
	@Override
	public ResultSet getAdmin(String username, String password) throws DbConnectionException{
		Connection conn=DatabaseConnection.openResources();
		try {
			Statement stmt=conn.createStatement();
			String query="select case when exists(select 1 from Admin where username=\""+username+"\" and password=\""+password+"\") then true else false end as login";
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			throw new DbConnectionException();
		}	
	}

	@Override
	public int changeUserStatus(int userId) throws DbConnectionException {
		Connection conn=DatabaseConnection.openResources();
		try {
			Statement stmt=conn.createStatement();
			String query="update User set status=not status where user_id="+userId+";";
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new DbConnectionException();
		}	
	}

	@Override
	public ResultSet getAllVendors() throws DbConnectionException {
		Connection conn=DatabaseConnection.openResources();
		try {
			Statement stmt=conn.createStatement();
			String query="select * from Vendor;";
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			throw new DbConnectionException();
		}	
	}

	@Override
	public ResultSet getAllUsers() throws DbConnectionException {
		Connection conn=DatabaseConnection.openResources();
		try {
			Statement stmt=conn.createStatement();
			String query="select * from User;";
			return stmt.executeQuery(query);
		} catch (SQLException e) {
			throw new DbConnectionException();
		}	
	}
	
}
