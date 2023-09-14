package com.navaratna.factory;

import com.navaratna.dao.UserDao;
import com.navaratna.dao.UserDaoImpl;

public class UserDaoFactory {
	public static UserDao createUserDaoFactory() {
		UserDao dao;
		dao=new UserDaoImpl();
		return dao;
	}
}
