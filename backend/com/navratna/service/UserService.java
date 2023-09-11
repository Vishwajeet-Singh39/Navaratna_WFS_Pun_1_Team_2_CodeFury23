package com.navratna.service;

import java.sql.SQLException;
import java.util.List;

import com.navratna.exception.AlreadyExistsException;
import com.navratna.exception.NotFoundException;
import com.navratna.exception.ValidationException;
import com.navratna.model.PlanRequest;
import com.navratna.model.Quotation;
import com.navratna.model.User;

public interface UserService {
	public boolean login(String userName,String password) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean checkStatus(int userId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean addUser(User user) throws AlreadyExistsException,ClassNotFoundException, SQLException;
	public User displayProfile(int userId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean changePassword(int userId,String newPassword,String oldPassword) throws NotFoundException, ValidationException,ClassNotFoundException, SQLException;
	public boolean sendPlanRequest(PlanRequest request) throws AlreadyExistsException, ValidationException,ClassNotFoundException, SQLException;
	public List<Quotation> displayQuotation(int userId) throws NotFoundException,ClassNotFoundException, SQLException; 
	public List<PlanRequest> displayPlanRequest(int requestId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean changeDetails(int userId,String email, long mobile, String location) throws NotFoundException,ClassNotFoundException, SQLException; 
	public boolean changeQuotationStatus(int userId,int quotationId,String status) throws NotFoundException,ClassNotFoundException, SQLException;
}
