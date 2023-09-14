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
import com.navaratna.model.Package;
import com.navaratna.model.ServiceList;

public class PackageDaoJdbcImpl implements PackageDao {

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
	public boolean addPackage(Package pack) throws AlreadyExistsException, SQLException, ClassNotFoundException {
		openResource();
		boolean result = false;
		boolean check = false;
		//checking if pack already exists	
		try {
			this.getPackage(pack.getPackageId());
			check = true;
		}
		catch(NotFoundException ex) {
			check = false;
		}
		if(check == true) {
			throw new AlreadyExistsException("Package with "+pack.getPackageId()+"Already exisits");
		}
		//	Executing Sql query to insert package
		connection.setAutoCommit(false);
		String query="insert into User(package_id, package_name, package_type, amount, vendor_id) values (?,?,?,?,?)";
		PreparedStatement pstmt=connection.prepareStatement(query);
		pstmt.setInt(1,pack.getPackageId() );
		pstmt.setString(2, pack.getPackageName());
		pstmt.setString(3, pack.getPackageType());
		pstmt.setDouble(4, pack.getAmount());
		pstmt.setInt(5, pack.getVendorId());
		
		int record=pstmt.executeUpdate();
		
//		Now adding the list of services
//		Query for servicelist table
		String querryServiceList = "insert into ServiceList (service_id,service_name,service_cost,service_type) values (?,?,?,?)";
		PreparedStatement pstmt2=connection.prepareStatement(querryServiceList);
//		Querry for service ref table
		String querryRef = "insert into ServicePackageRef (package_id, service_id) values (?,?)";
		PreparedStatement pstmt3=connection.prepareStatement(querryRef);
		for(ServiceList sl : pack.getListOfServices() ) {
			
			pstmt2.setInt(1, sl.getServiceId());
			pstmt2.setString(2, sl.getServiceName());
			pstmt2.setDouble(3, sl.getServiceCost());
			pstmt2.setString(4, sl.getServiceType());
			pstmt2.executeUpdate();
			
			pstmt3.setInt(1,pack.getPackageId());
			pstmt3.setInt(2, sl.getServiceId());
			pstmt3.executeUpdate();
			
		}
		
		if(record>0) {
			result = true;
		}
		
		
		
		connection.commit();	
		closeResource();
		return result;
		
	}
	@Override
	public Package getPackage(int packageId) throws NotFoundException, SQLException, ClassNotFoundException  {
		Package pack=null;
		List<ServiceList> listOfService = new ArrayList<ServiceList>();
		openResource();
		
		String query = "SELECT * FROM Package WHERE package_id = ?";
	    PreparedStatement preparedStatement = connection.prepareStatement(query);
	    preparedStatement.setInt(1, packageId);

	    ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
		      pack = new Package(
		      rs.getInt("package_id"),
		      rs.getString("package_name"),
		      rs.getString("package_type"),
		      rs.getDouble("amount"),
		      rs.getInt("vendor_id"),
		      null
		  );
		} 
		else {
		      throw new NotFoundException("Package not found");
		}
		rs.close();
		
//		Now we will fetch the services
		String query2 = "SELECT * FROM ServiceList WHERE service_id IN " 
						+"(SELECT service_id FROM  ServicePackageRef WHERE package_id = ?)";
	    PreparedStatement preparedStatement2 = connection.prepareStatement(query2);
	    preparedStatement2.setInt(1, packageId);
	    ResultSet rs2 = preparedStatement2.executeQuery();
	    while(rs2.next()) {
	    	listOfService.add(new ServiceList(
	    			rs2.getInt("service_id"),
	    			rs2.getString("service_name"),
	    			rs2.getDouble("service_cost"),
	    			rs2.getString("service_type")
	    			));
	    }
	    pack.setListOfServices(listOfService);
		rs2.close();
		
		closeResource();
		return pack;
		
		
	}
	
}
