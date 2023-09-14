package com.navaratna.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.navaratna.exception.DbConnectionException;

public class DatabaseConnection {
	static Connection conn=null;
	public static Connection openResources() throws DbConnectionException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/EventDB","root","hsbc");
			} catch (ClassNotFoundException | SQLException e) {
				throw new DbConnectionException();
			}
			return conn;
	}
	public static void closeResources() throws DbConnectionException{
		try {
			conn.close();
		} catch (SQLException e) {
			throw new DbConnectionException();
		}
	}
}
