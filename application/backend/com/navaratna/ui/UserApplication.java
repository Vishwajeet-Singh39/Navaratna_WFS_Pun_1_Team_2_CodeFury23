package com.navaratna.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.navaratna.dao.UserDao;
import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.exception.ValidationException;
import com.navaratna.factory.UserDaoFactory;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.User;
import com.navaratna.service.UserServiceImpl;

public class UserApplication {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		UserServiceImpl service=new UserServiceImpl();
		
		UserDao dao=UserDaoFactory.createUserDaoFactory();
		service.setDao(dao);
		
		int userId=1;					// When user login get userId initially
		boolean isActivated=false;
		try {
			isActivated = service.checkStatus(userId);	
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
		
		if(isActivated) {
			System.out.println("0. Login");
			System.out.println("1. Add user");
			System.out.println("2. View Profile");
			System.out.println("3. Change Password");
			System.out.println("4. Send plan request");
			System.out.println("5. View Quotation");
			System.out.println("6. Update other details");
			System.out.println("7. View Plan Request");
			System.out.println("8. Accept/Reject Quotation");
			
			System.out.println("Enter choice");
			int ch=sc.nextInt();
			
			switch(ch) {
			case 0:
				try {
					boolean isValidUser=service.login("abc", "abcc");
					if(isValidUser) {
						System.out.println("User logged in");
					}
					else {
						System.out.println("Problem with login");
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
			case 1:	//create user
				SimpleDateFormat dateUser=new SimpleDateFormat("yyyy-MM-DD");
				String join="2020-02-21";
				String birth="2001-05-20";
				Date joiningDate=null, birthDate=null;
				try {
					joiningDate = dateUser.parse(join);
					birthDate=dateUser.parse(birth);
				} catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				User u=new User(1,"abc","abc","abc",joiningDate,birthDate,"IT",1234567890,"abc@gmail.com","Pune",false);
				try {
					boolean isAddedUser=service.addUser(u);
					if(isAddedUser) {
						System.out.println("User added");
					}
					else {
						System.out.println("Problem with adding user");
					}
				}
				catch(AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				catch(ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			break;
			case 2:	//select user
				User user=new User();
				try {
					user=service.displayProfile(userId);
					System.out.println(user.toString());
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
			case 3:	//update password
				boolean isUpdatedPassword=false;
				try {
					isUpdatedPassword = service.changePassword(1, "abcc","abcd");
					if(isUpdatedPassword) {
						System.out.println("Password updated successfully");
					}
					else {
						System.out.println("Problem with updating password. Newpassword should not be same as old or oldpassword doesn't match");
					} 
				}
				catch (NotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(ValidationException e) {
					System.out.println(e.getMessage());
				}
				catch(ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			break;
			case 4:	//send plan req
				SimpleDateFormat datePlanRequest=new SimpleDateFormat("yyyy-MM-dd");
				String from="2023-10-21";
				String to="2023-10-20";
				Date fromDate=null, toDate=null;
				try {
					fromDate = datePlanRequest.parse(from);
					toDate=datePlanRequest.parse(to);
				} 
				catch (ParseException e) {
					System.out.println(e.getMessage());
				}
				
				List<String> serviceList=new ArrayList<>();
				serviceList.add("Decoration");
				serviceList.add("Photo");
				
				PlanRequest sendRequest=new PlanRequest(5,fromDate,toDate,90,1);
				boolean isAddedRequest;
				try {
					isAddedRequest = service.sendPlanRequest(sendRequest);
					if(isAddedRequest) {
						System.out.println("Plan request sent sucessfully");
					}
					else {
						System.out.println("Problem with sending plan request");
					}
				} 
				catch (AlreadyExistsException e) {
					System.out.println(e.getMessage());
				}
				catch(ValidationException e) {
					System.out.println(e.getMessage());
				}
				catch(ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			break;
			case 5:	//display quotation
				List<Quotation> quotationList=new ArrayList<>();
				try {
					quotationList=service.displayQuotation(userId);
					for(Quotation quotation:quotationList) {
						System.out.println(quotation.toString());
					}
				} 
				catch (NotFoundException e) {
					System.out.println(e.getMessage());;
				}
				catch(ClassNotFoundException e) {
					System.out.println(e.getMessage());
				}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
			break;
			case 6:	//update details
				boolean isUpdatedDetails;
				try {
					isUpdatedDetails = service.changeDetails(2, "pqr@gmail.com", 989999, "Mumbai");
					if(isUpdatedDetails) {
						System.out.println("Profile updated successfully");
					}
					else {
						System.out.println("Problem with updating profile");
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
			case 7:	//display plan req
				List<PlanRequest> requestList=new ArrayList<>();
				try {
					requestList=service.displayPlanRequest(userId);
					for(PlanRequest request: requestList ) {
						System.out.println(request.toString());
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
			case 8:	//accept/reject quotation
				boolean isUpdatedStatus;
				try {
					isUpdatedStatus = service.changeQuotationStatus(userId, 1, "accepted");
					if(isUpdatedStatus) {
						System.out.println("Profile updated successfully");
					}
					else {
						System.out.println("Problem with updating profile");
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
			default:
				System.out.println("Invalid Option");
			}
		}
		else {
			System.out.println("User not activated yet");
		}
		sc.close();
	}
}
