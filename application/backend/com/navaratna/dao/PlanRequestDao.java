package com.navaratna.dao;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.exception.ValidationException;
import com.navaratna.model.PlanRequest;

public interface PlanRequestDao {
	public boolean createPlanRequest(PlanRequest request) throws AlreadyExistsException, ValidationException,ClassNotFoundException, SQLException;
	public PlanRequest getPlanRequest(int requestId) throws NotFoundException,ClassNotFoundException, SQLException;
	public List<PlanRequest> getPlanRequestList() throws NotFoundException,ClassNotFoundException, SQLException;
}
