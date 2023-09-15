package com.navaratna.ui;

import java.sql.SQLException;
import java.util.Scanner;

import com.navaratna.dao.PackageDao;
import com.navaratna.dao.PackageDaoJdbcImpl;
import com.navaratna.dao.PlanRequestDao;
import com.navaratna.dao.PlanRequestDaoJdbcImpl;
import com.navaratna.dao.QuotationDao;
import com.navaratna.dao.QuotationDaoJdbcImpl;
import com.navaratna.dao.VendorDao;
import com.navaratna.dao.VendorDaoJdbcImpl;
import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.Vendor;
import com.navaratna.service.VendorService;
import com.navaratna.service.VendorServiceImpl;
import com.navaratna.model.Package;

public class VendorApplication {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
//		Initializing the objects of dao layer
		VendorDao vendorDao = new VendorDaoJdbcImpl();
		QuotationDao quoteDao = new QuotationDaoJdbcImpl();
		PlanRequestDao planRequestDao = new PlanRequestDaoJdbcImpl();
		PackageDao packDao = new PackageDaoJdbcImpl();
		
//		Initialiizing the object of service layer
		VendorService vendorService = new VendorServiceImpl(vendorDao,quoteDao,planRequestDao,packDao);
		Vendor vendor = new Vendor(); // Need to enter vendor credentials for it to work
		System.out.println("Enter vendor credentials");
		System.out.println("Enter vendor id : ");
		vendor.setVendorId(sc.nextInt());
		System.out.println("Enter vendor password : ");
		vendor.setPassword(sc.next());	
		
		
//		First Authentication the vendor to only allow the user other functionalities if he has logged in
		boolean userLoggedIn = false;
		try {
			userLoggedIn = vendorService.vendorLogin(vendor);
		}
		catch (NotFoundException e) {
			System.out.println(e.getMessage());
		} 
		catch(ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}

			
		if(userLoggedIn) {
//				Now creating a factory to do certain actions		
			if(true) {
				System.out.println("Type 0 : Exit");
				System.out.println("Type 2 : GetVendorInfo");
				System.out.println("Type 3 : Get All Plan Request");
				System.out.println("Type 4 : Create Quotation");
				System.out.println("Type 5 : Add Package");
				
				System.out.println("Enter choice");
				int ch=sc.nextInt();
				
				switch(ch) {
				case 0: // exit the application
					break;
				case 2: // get vendor info
					try {
						vendor = vendorService.getVendorInfo(vendor);
						System.out.println(vendor);
					}
					catch (NotFoundException e) {
						System.out.println(e.getMessage());
					} 
					catch(ClassNotFoundException e) {
						System.out.println(e.getMessage());
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 3: // get all plan Request
					try {
						for( PlanRequest plan : vendorService.getPlanRequestList()) {
							System.out.println(plan);
						}
					}
					catch (NotFoundException e) {
						System.out.println(e.getMessage());
					} 
					catch(ClassNotFoundException e) {
						System.out.println(e.getMessage());
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 4: // AddQuotation to database
					Quotation quote = new Quotation();
					quote.setQuotationId(100);
					try {
						vendorService.createQuotation(quote);
					}
					catch (AlreadyExistsException e) {
						System.out.println(e.getMessage());
					} 
					catch(ClassNotFoundException e) {
						System.out.println(e.getMessage());
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				case 5: // Add package
					Package pack = new Package();
					pack.setPackageId(100);
					try {
						vendorService.addPackage(pack);
					}
					catch (AlreadyExistsException e) {
						System.out.println(e.getMessage());
					} 
					catch(ClassNotFoundException e) {
						System.out.println(e.getMessage());
					}
					catch(SQLException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					System.out.println("Invalid Option");
				
				}
			}
		}

		sc.close();

	}
}
