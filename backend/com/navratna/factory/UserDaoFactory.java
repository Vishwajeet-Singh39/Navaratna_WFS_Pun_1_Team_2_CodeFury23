package com.navratna.factory;

import com.navratna.dao.UserDao;
import com.navratna.dao.UserDaoImpl;

public class UserDaoFactory {
	public static UserDao createUserDaoFactory() {
		UserDao dao;
		dao=new UserDaoImpl();
		return dao;
	}
}
