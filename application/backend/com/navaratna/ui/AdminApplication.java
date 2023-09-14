package com.navaratna.ui;

import com.navaratna.exception.IncorrectDetailsException;
import com.navaratna.service.AdminService;
import com.navaratna.service.AdminServiceImpl;

public class AdminApplication {
	public static void main(String[] args) {
		AdminService service=new AdminServiceImpl();
		try {
			if(service.isValid("abc", "abc@123")) {
				System.out.println("Admin Logged in");
			}
			if(service.toggleUserStatus(12)) {
				System.out.println("Status Changed");
			}
			System.out.println(service.displayVendors());
			System.out.println(service.displayUsers());
		} catch (IncorrectDetailsException e) {
			System.out.println(e.getMessage());
		}
	}
}
