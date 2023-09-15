package com.navaratna.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Quotation;
import com.navaratna.model.ServiceList;


public class QuotationDaoJdbcImpl implements QuotationDao{
	
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
	
//	//	Function to closing resources all at once
	private void closeResource() {
		try {
			connection.close();
			stmt.close();
		} 
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

//	Function to create a quotation for any plan request
	@Override
	public boolean addQuotation(Quotation quote) throws AlreadyExistsException , SQLException, ClassNotFoundException{
		openResource();
		boolean result = false;
		boolean check = false;
		//checking if quote already exists	
		try {
			this.getQuotation(quote.getQuotationId());
			check = true;
		}
		catch(NotFoundException ex) {
			check = false;
		}
		if(check == true) {
			throw new AlreadyExistsException("Quotation with "+quote.getQuotationId()+"Already exisits");
		}
//		Now Adding the basic info
		connection.setAutoCommit(false);
		String query="insert into User(quotation_id,package_type, estimated_amount, quote_id, user_id, plan_request_id, status) values (?,?,?,?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,quote.getQuotationId() );
		pstmt.setString(2, quote.getPackageType());
		pstmt.setDouble(3, quote.getEstimatedAmount());
		pstmt.setInt(4, quote.getVendorId());
		pstmt.setInt(5, quote.getUserId());
		pstmt.setInt(6, quote.getPlanRequestId());
		pstmt.setString(7, quote.getStatus());
		int record=pstmt.executeUpdate();
		if(record>0) {
			result = true;
		}
		
//		Now adding the list of services
//		Query for servicelist table
		String querryServiceList = "insert into ServiceList (service_id,service_name,service_cost,service_type) values (?,?,?,?)";
		PreparedStatement pstmt2=connection.prepareStatement(querryServiceList);
//		Querry for service ref table
		String querryRef = "insert into QuotationServiceRef (quotation_id, service_id) values (?,?)";
		PreparedStatement pstmt3=connection.prepareStatement(querryRef);
		for(ServiceList sl : quote.getListOfServices()) {
			pstmt2.setInt(1, sl.getServiceId());
			pstmt2.setString(2, sl.getServiceName());
			pstmt2.setDouble(3, sl.getServiceCost());
			pstmt2.setString(4, sl.getServiceType());
			pstmt2.executeUpdate();
			
			pstmt3.setInt(1,quote.getQuotationId());
			pstmt3.setInt(2, sl.getServiceId());
			pstmt3.executeUpdate();
			
		}
		
		connection.commit();	
		closeResource();
		return result;
		
	}

//	Function to get quotation info via quotationId
	@Override
	public Quotation getQuotation(int quotationId) throws NotFoundException, SQLException, ClassNotFoundException  {
		
		Quotation quote=null;
		List<ServiceList> listOfService = new ArrayList<ServiceList>();
		openResource();
//		Get quotation basic info
		String query = "SELECT * FROM Quotation WHERE quotation_id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, quotationId);
	    ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
		      quote = new Quotation(
		      rs.getInt("quotation_id"),
		      rs.getString("package_type"),
		      rs.getDouble("estimated_amount"),
		      rs.getInt("quote_id"),
		      rs.getInt("user_id"),
		      rs.getInt("plan_request_id"),
		      rs.getString("status"),
		      null
		  );
		} 
		else {
		      throw new NotFoundException("Quotation not found");
		}
		rs.close();
//		Now we will fetch the services
		String query2 = "SELECT * FROM ServiceList WHERE service_id IN " 
						+"(SELECT service_id FROM  QuotationServiceRef WHERE quotation_id = ?)";
	    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
	    preparedStatement2.setInt(1, quotationId);
	    ResultSet rs2 = preparedStatement2.executeQuery();
	    while(rs2.next()) {
	    	listOfService.add(new ServiceList(
	    			rs2.getInt("service_id"),
	    			rs2.getString("service_name"),
	    			rs2.getDouble("service_cost"),
	    			rs2.getString("service_type")
	    			));
	    }
	    quote.setListOfServices(listOfService);
		rs2.close();
		closeResource();
		return quote;
		
	}

//	Function to change the status of the quotation to accepted or rejected
	@Override
	public boolean updateQuotationStatus(Quotation quote) throws ClassNotFoundException, SQLException {
		boolean isUpdated=false;
		openResource();
		
	    String updateQuery = "UPDATE Quotation SET status = ? WHERE quotation_id = ?";
	    PreparedStatement pstmt = connection.prepareStatement(updateQuery);
	    pstmt.setString(1, quote.getStatus());
	    pstmt.setInt(2, quote.getQuotationId());
	    int record = pstmt.executeUpdate();
	    if (record > 0) {
	         isUpdated=true;
	     } 
	    
		closeResource();
		return isUpdated;
	}
	
//	Function to get all the quotation recieved by any user
	@Override
	public List<Quotation> getAllQuotationByUserId(int userId) throws NotFoundException, ClassNotFoundException, SQLException {
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
				quotationList.add(getQuotation(rs.getInt("quotation_id")));
			}
			
		rs.close();
		closeResource();
		return quotationList;
	}

}
