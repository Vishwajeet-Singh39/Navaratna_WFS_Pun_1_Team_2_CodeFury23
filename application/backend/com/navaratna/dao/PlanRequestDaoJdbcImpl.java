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
import com.navaratna.model.ServiceList;

public class PlanRequestDaoJdbcImpl implements PlanRequestDao{
	
	private Connection connection=null;
	private Statement stmt=null;
	
//	Function to opening resources all at once
	public void openResource() throws SQLException, ClassNotFoundException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver is loaded...");
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/EventDB","root","");
			System.out.println("Database is connected");
			stmt=connection.createStatement();
	}
	
//	Function to closing resources all at once
	private void closeResource() {
		try {
			connection.close();
			stmt.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
//	Function to create PlanRequest
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
	
//	Function to get specific plan request
	@Override
	public PlanRequest getPlanRequest(int requestId) throws NotFoundException,ClassNotFoundException, SQLException {
		PlanRequest request = null;
		List<ServiceList> listOfService = new ArrayList<ServiceList>();
		openResource();
//		getting the plan request basic info
		String query = "SELECT * FROM PlanRequest WHERE plan_request_id="+requestId+";";
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
		    if(requestId!=rs.getInt("plan_request_id")) {
		    	throw new NotFoundException("Plan Request not found");
		    }
		    request=new PlanRequest(rs.getInt("plan_request_id"),
					rs.getDate("from_date"),
					rs.getDate("to_date"),
					rs.getInt("no_of_persons"),
					rs.getString("package_type"),
					null
					);
		    
		}
		rs.close();
//		Now fetching the services    
		query = "SELECT * FROM ServiceList WHERE service_id IN " 
				+"(SELECT service_id FROM  RequestServiceRef WHERE plan_request_id = ?)";
		  
		PreparedStatement pstmt = connection.prepareStatement(query);
		pstmt.setInt(1, requestId);
		ResultSet rs2 = pstmt.executeQuery();		
		while (rs2.next()) {
	    	listOfService.add(new ServiceList(
	    			rs2.getInt("service_id"),
	    			rs2.getString("service_name"),
	    			rs2.getDouble("service_cost"),
	    			rs2.getString("service_type")
	    			));
		}
		request.setListOfServices(listOfService);    		
		rs2.close();
		closeResource();
		return request;
	}
	
//	Function to show all the available planRequest to vendors
	@Override
	public List<PlanRequest> getPlanRequestList() throws NotFoundException,ClassNotFoundException, SQLException {
	    List<PlanRequest> requestList=new ArrayList<PlanRequest>();
		openResource();
		String query = "SELECT * FROM PlanRequest";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {		    
		    requestList.add(getPlanRequest(rs.getInt("plan_request_id")));
		}
		closeResource();
		return requestList;
	}

}
