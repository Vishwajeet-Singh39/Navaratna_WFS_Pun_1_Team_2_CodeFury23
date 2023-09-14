package com.navaratna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.User;
import com.navaratna.model.Vendor;

public class VendorDaoJdbcImpl implements VendorDao {
	
	private Connection connection=null;
	private Statement stmt=null;
	public void openResource() throws SQLException, ClassNotFoundException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded...");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/EventDB","root","");
			System.out.println("Database is connected");
			stmt=connection.createStatement();
	}
	
	private void closeResource() {
		try {
			connection.close();
			stmt.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public boolean addVendor(Vendor vendor) throws AlreadyExistsException, SQLException, ClassNotFoundException {
		openResource();
		boolean result = false;
		boolean check = false;
		//checking if vendor already exists	
		try {
			this.getVendor(vendor.getVendorId());
			check = true;
		}
		catch(NotFoundException ex) {
			check = false;
		}
		if(check == true) {
			throw new AlreadyExistsException("Vendor with "+vendor.getVendorId()+"Already exisits");
		}
		
		connection.setAutoCommit(false);
		String query="insert into User(vendor_id,name,username,password,contact_no,email,address) values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,vendor.getVendorId() );
		pstmt.setString(2, vendor.getName());
		pstmt.setString(3, vendor.getUsername());
		pstmt.setString(4, vendor.getPassword());
		pstmt.setLong(5, vendor.getContactNo());
		pstmt.setString(6, vendor.getEmail());
		pstmt.setString(7, vendor.getAddress());
		int record=pstmt.executeUpdate();
		if(record>0) {
			result = true;
		}
		connection.commit();	
		closeResource();
		return result;
	}


	@Override
	public Vendor getVendor(int vendorId) throws NotFoundException, SQLException, ClassNotFoundException {
		Vendor vendor=null;
		openResource();
			
		String query = "SELECT * FROM Vendor WHERE vendor_id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, vendorId);

	    ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
		      vendor = new Vendor(
		      rs.getInt("vendor_id"),
		      rs.getString("username"),
		      rs.getString("password"),
		      rs.getString("name"),
		      rs.getString("address"),
		      rs.getString("email"), 
		      rs.getLong("contact_no")
		  );
		} 
		else {
		      throw new NotFoundException("Vendor not found");
		}
		rs.close();
		closeResource();
		return vendor;
	}



}
