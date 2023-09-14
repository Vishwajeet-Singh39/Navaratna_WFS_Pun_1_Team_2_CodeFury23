package com.navaratna.dao;

import java.sql.SQLException;
import java.util.List;

import com.navaratna.exception.AlreadyExistsException;
import com.navaratna.exception.NotFoundException;
import com.navaratna.model.Quotation;

public interface QuotationDao {
	public boolean addQuotation(Quotation quote) throws AlreadyExistsException, SQLException, ClassNotFoundException;
	public boolean updateQuotationStatus(Quotation quote)  throws ClassNotFoundException, SQLException ;
	public Quotation getQuotation(int quotationId) throws NotFoundException, SQLException, ClassNotFoundException; 
	public List<Quotation> getAllQuotationByUserId(int vendorId)  throws NotFoundException, ClassNotFoundException, SQLException ;

}
