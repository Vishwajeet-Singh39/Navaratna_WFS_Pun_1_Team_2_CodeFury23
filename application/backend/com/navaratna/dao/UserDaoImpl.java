package com.navaratna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.exception.ValidationException;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.User;

public class UserDaoImpl implements UserDao{
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
	public boolean login(String userName, String password) throws NotFoundException,ClassNotFoundException, SQLException {
		boolean isValid=false;
		
		openResource();
			
		String query = "SELECT * FROM User WHERE username = ?";

	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setString(1, userName);

	    ResultSet rs = preparedStatement.executeQuery();
			
	    if(rs.next()) {
			String storedPassword = rs.getString("password");
			if(password.equals(storedPassword)) {
				isValid=true;
			}
		}
		else {
			throw new NotFoundException();
		}
	    closeResource();
		return isValid;
	}
	
	@Override
	public boolean validateStatus(int userId) throws NotFoundException,ClassNotFoundException, SQLException {
		boolean isActivated=false;
		openResource();
			
		String query="Select user_id from User where user_id="+userId;
		ResultSet rs=stmt.executeQuery(query);
			
		query="Select status from User where user_id="+userId;
		rs=stmt.executeQuery(query);
		if(rs.next()) {
			if(rs.getBoolean("status")==true) {
				isActivated=true;
			}
		}
		else {
			throw new NotFoundException("User not found");
		}
		closeResource();
		return isActivated;
	}
	
	@Override
	public boolean createUser(User user) throws AlreadyExistsException,ClassNotFoundException, SQLException{
		boolean isAdded=false;
		openResource();
		String query = "SELECT status FROM User WHERE user_id = ?";
	        
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, user.getUserId());

	    ResultSet rs = preparedStatement.executeQuery();
			
		if(rs.next()) {
			if(user.getUserId()==rs.getInt("user_id")) {
				throw new AlreadyExistsException("User already exists");
			}
		}
			
		connection.setAutoCommit(false);
		query="insert into User(user_id,name,username,password,date_joining,birth_date,department,mobile,email,location) values (?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,user.getUserId() );
		pstmt.setString(2, user.getFullName());
		pstmt.setString(3, user.getUserName());
		pstmt.setString(4, user.getPassword());
			
		java.sql.Date dateJoining = new java.sql.Date(user.getDateJoining().getTime());
		java.sql.Date dateBirth = new java.sql.Date(user.getDateBirth().getTime());

		pstmt.setDate(5,dateJoining);
		pstmt.setDate(6, dateBirth );
		pstmt.setString(7, user.getDepartment());
		pstmt.setLong(8, user.getMobile());
		pstmt.setString(9, user.getEmail());
		pstmt.setString(10, user.getLocation());
		int record=pstmt.executeUpdate();
		if(record>0) {
			isAdded=true;
		}
		connection.commit();	
		closeResource();
		return isAdded;
	}
	
	@Override
	public User readUser(int userId) throws NotFoundException,ClassNotFoundException, SQLException {
		User user=null;
		openResource();
			
		String query = "SELECT * FROM User WHERE user_id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, userId);

	    ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
		      user = new User(
		      rs.getInt("user_id"),
		      rs.getString("name"),
		      rs.getString("username"),
		      rs.getString("password"),
		      rs.getDate("date_joining"),
		      rs.getDate("birth_date"),
		      rs.getString("department"),
		      rs.getInt("mobile"),
		      rs.getString("email"),
		      rs.getString("location"),
		      rs.getBoolean("status")
		  );
		} 
		else {
		      throw new NotFoundException("User not found");
		}
		rs.close();
		closeResource();
		return user;
	}

	@Override
	public boolean updatePassword(int userId, String newPassword,String oldPassword) throws NotFoundException, ValidationException,ClassNotFoundException, SQLException {
		boolean isUpdated=false;
		openResource();
			 	
		String checkQuery = "SELECT password FROM User WHERE user_id = ?";

		PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
		checkStatement.setInt(1, userId);

		ResultSet rs = checkStatement.executeQuery();
			     
		if(rs.next()){
			if(!oldPassword.equals(rs.getString("password")) && (newPassword.equals(rs.getString("password")))) {
				throw new ValidationException("Password doesn't match and Don't enter new password same as old");
			}
		}
		else {
				throw new NotFoundException("User not found");
		}
				
	    String updateQuery = "UPDATE User SET password = ? WHERE user_id = ?";
	    PreparedStatement pstmt = connection.prepareStatement(updateQuery);

	    pstmt.setString(1, newPassword);
	    pstmt.setInt(2, userId);

	    int record = pstmt.executeUpdate();

	    if (record > 0) {
	         isUpdated=true;
	     } 
		closeResource();
		return isUpdated;
	}

	@Override
	public boolean createPlanRequest(PlanRequest request) throws AlreadyExistsException, ValidationException,ClassNotFoundException, SQLException {	
		boolean isAdded=false;
		openResource();
			
		String query = "SELECT 1 FROM PlanRequest WHERE plan_request_id = ?";
		PreparedStatement checkStatement = connection.prepareStatement(query);
		checkStatement.setInt(1, request.getPlanRequestId());
		ResultSet rs = checkStatement.executeQuery();
		while(rs.next()) {
				if(request.getPlanRequestId()==rs.getInt("plan_request_id")) {
					throw new AlreadyExistsException("Plan Request already exists");
				}
		}
				
			
		connection.setAutoCommit(false);
		query="insert into PlanRequest(plan_request_id,from_date,to_date,no_of_persons) values (?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
			
		java.sql.Date dateFrom = new java.sql.Date(request.getFromDate().getTime());
		java.sql.Date dateTo = new java.sql.Date(request.getToDate().getTime());
			
		if (request.getNoOfPersons() < 30 || request.getFromDate().before(new Date())) {
		      throw new ValidationException("No of person should be greater than 30 and planned date should not be less than current date");
		}

		pstmt.setInt(1,request.getPlanRequestId());
		pstmt.setDate(2, dateFrom);
		pstmt.setDate(3, dateTo);
		pstmt.setInt(4, request.getNoOfPersons());
			
		int record=pstmt.executeUpdate();
		if(record>0) {
			isAdded=true;
		}
		connection.commit();	
		closeResource();
		return isAdded;
	}

	@Override
	public List<Quotation> readQuotation(int userId) throws NotFoundException,ClassNotFoundException, SQLException{
		Quotation quotation=null;
		List<Quotation> quotationList=new ArrayList<>();
		openResource();
			
		String query = "SELECT * FROM Quotation WHERE user_id="+userId;

		ResultSet rs = stmt.executeQuery(query);
		     
		rs=stmt.executeQuery(query);
		    
		while(rs.next()) {
		    if(userId!=rs.getInt("user_id")) {
		    	throw new NotFoundException("Quotation not found");
		    }
		}
		    
		rs=stmt.executeQuery(query);
		while(rs.next()) {
			quotation=new Quotation(rs.getInt("id"),
								rs.getString("package_type"),
								rs.getDouble("estimated_amount"),
								rs.getInt("vendor_id"),
								rs.getInt("user_id"),
								rs.getInt("plan_request_id"),
								rs.getString("status"),
								null
					);
								quotationList.add(quotation);
			}
			
		rs.close();
		closeResource();
		return quotationList;
	}

	@Override
	public List<PlanRequest> readPlanRequest(int requestId) throws NotFoundException,ClassNotFoundException, SQLException {
		PlanRequest request;
	    List<PlanRequest> requestList=new ArrayList<>();
		openResource();
		String query = "SELECT * FROM PlanRequest WHERE request_id="+requestId;
		    
		ResultSet rs = stmt.executeQuery(query);
			
		while(rs.next()) {
		    if(requestId!=rs.getInt("request_id")) {
		    	throw new NotFoundException("Plan Request not found");
		    }
		}
		    
		query = "SELECT pr.plan_request_id, pr.from_date, pr.to_date, pr.no_of_persons, pr.service_id, q.package_type " +
	                "FROM PlanRequest pr " +
	                "INNER JOIN Quotation q ON pr.plan_request_id = q.plan_request_id " +
	                "WHERE pr.plan_request_id = ?";
		  
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, requestId);
	
		while (rs.next()) {
		    request=new PlanRequest(rs.getInt("plan_request_id"),
		    									rs.getDate("from_date"),
		    									rs.getDate("to_date"),
		    									rs.getInt("no_of_persons"),
		    									
		    									rs.getString("package_type"),
		    									null
		    									);
		    requestList.add(request);
		}
		    		
		List<Integer> serviceIds = new ArrayList<>();
		String selectQuery = "SELECT service_id FROM RequestServiceRef WHERE plan_request_id = ?";
		pstmt = connection.prepareStatement(selectQuery);
		pstmt.setInt(1, requestId);
		rs= pstmt.executeQuery();
		while (rs.next()) {
			  serviceIds.add(rs.getInt("service_id"));
		}
	    		
		if (!serviceIds.isEmpty()) {
			      System.out.println("Associated Services (Service IDs): " + serviceIds);
		} 
		else {
			      System.out.println("No associated services found.");
		}
		closeResource();
		return requestList;
	}

	@Override
	public boolean updateDetails(int userId,String email, long mobile, String location) throws NotFoundException,ClassNotFoundException, SQLException {
		boolean isUpdated=false;
		openResource();
				
		String query="select * from User where user_id="+userId;
		ResultSet rs=stmt.executeQuery(query);
			     
		if(!rs.next()){
				throw new NotFoundException("User not found");	
		}
				
	    String updateQuery = "UPDATE User SET email=?,mobile=?,location=? WHERE user_id = ?";
	    PreparedStatement pstmt = connection.prepareStatement(updateQuery);

	     pstmt.setString(1, email);
	     pstmt.setLong(2, mobile);
	     pstmt.setString(3,location);
	     pstmt.setInt(4, userId);

	     int record = pstmt.executeUpdate();

	     if (record > 0) {
	          isUpdated=true;
	     } 
		closeResource();
		return isUpdated;
	}

	@Override
	public boolean updateQuotationStatus(int userId,int quotationId, String status) throws NotFoundException,ClassNotFoundException, SQLException {
		boolean isUpdated=false;
		openResource();
				
		String query="select quotation_id from Quotation where user_id="+userId;
		ResultSet rs=stmt.executeQuery(query);
			     
		if(!rs.next()){
				throw new NotFoundException("Quotation not found");	
		}
				
	     String updateQuery = "UPDATE Quotation SET status=? WHERE quotation_id = ?";
	     PreparedStatement pstmt = connection.prepareStatement(updateQuery);

	     pstmt.setString(1, status);
	     pstmt.setInt(2, quotationId);

	     int record = pstmt.executeUpdate();

	     if (record > 0) {
	          isUpdated=true;
	     }  
		closeResource();
		return isUpdated;
	}
}
