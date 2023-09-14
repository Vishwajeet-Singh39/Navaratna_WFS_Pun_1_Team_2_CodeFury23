package com.navaratna.dao;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.exception.ValidationException;
import com.navaratna.model.PlanRequest;
import com.navaratna.model.Quotation;
import com.navaratna.model.User;

public interface UserDao {
	public boolean login(String userName,String password) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean validateStatus(int userId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean createUser(User user) throws AlreadyExistsException,ClassNotFoundException, SQLException;
	public User readUser(int userId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean updatePassword(int userId,String newPassword,String oldPassword) throws NotFoundException, ValidationException,ClassNotFoundException, SQLException;
	public boolean createPlanRequest(PlanRequest request) throws AlreadyExistsException, ValidationException,ClassNotFoundException, SQLException;
	public List<Quotation> readQuotation(int userId) throws NotFoundException,ClassNotFoundException, SQLException; 
	public List<PlanRequest>  readPlanRequest(int requestId) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean updateDetails(int userId,String email, long mobile, String location) throws NotFoundException,ClassNotFoundException, SQLException;
	public boolean updateQuotationStatus(int userId,int quotationId,String status) throws NotFoundException,ClassNotFoundException, SQLException;
}
