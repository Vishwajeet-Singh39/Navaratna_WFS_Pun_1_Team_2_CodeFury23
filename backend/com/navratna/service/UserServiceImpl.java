package com.navratna.service;

import java.sql.SQLException;
import java.util.List;

import com.navratna.dao.UserDao;
import com.navratna.exception.AlreadyExistsException;
import com.navratna.exception.NotFoundException;
import com.navratna.exception.ValidationException;
import com.navratna.model.PlanRequest;
import com.navratna.model.Quotation;
import com.navratna.model.User;

public class UserServiceImpl implements UserService{
	UserDao dao;
	
	public UserDao getDao() {
		return dao;
	}

	public void setDao(UserDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean addUser(User user) throws AlreadyExistsException,ClassNotFoundException, SQLException{
		return dao.createUser(user);
	}
	
	@Override
	public User displayProfile(int userId) throws NotFoundException,ClassNotFoundException, SQLException{
		return dao.readUser(userId);
	}

	@Override
	public boolean changePassword(int userId, String newPassword,String oldPassword) throws NotFoundException, ValidationException,ClassNotFoundException, SQLException {
		return dao.updatePassword(userId, newPassword,oldPassword);
	}

	@Override
	public boolean sendPlanRequest(PlanRequest request) throws AlreadyExistsException, ValidationException,ClassNotFoundException, SQLException {
		return dao.createPlanRequest(request);
	}

	@Override
	public List<Quotation> displayQuotation(int userId) throws NotFoundException,ClassNotFoundException, SQLException{
		return dao.readQuotation(userId);
	}

	@Override
	public boolean checkStatus(int userId) throws NotFoundException,ClassNotFoundException, SQLException {
		return dao.validateStatus(userId);
	}

	@Override
	public List<PlanRequest>  displayPlanRequest(int userId) throws NotFoundException,ClassNotFoundException, SQLException {
		return dao.readPlanRequest(userId);
	}

	@Override
	public boolean changeDetails(int userId,String email, long mobile, String location) throws NotFoundException,ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return dao.updateDetails(userId,email,mobile,location);
	}

	@Override
	public boolean changeQuotationStatus(int requestId,int quotationId,String status) throws NotFoundException,ClassNotFoundException, SQLException {
		return dao.updateQuotationStatus(requestId, quotationId,status);
	}

	@Override
	public boolean login(String userName, String password) throws NotFoundException,ClassNotFoundException, SQLException{
		return dao.login(userName, password);
	}
}
