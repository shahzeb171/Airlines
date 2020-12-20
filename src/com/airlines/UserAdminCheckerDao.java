package com.airlines;

import java.sql.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserAdminCheckerDao {
	String table;
	String username;
	String password;
	String name;
	public UserAdminCheckerDao(String table, String username, String password) {
		super();
		this.table = table;
		this.username = username;
		this.password = password;
		name="";
	}
	public boolean check() throws ClassNotFoundException, SQLException{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		String hashDB;
		if(table.equals("users")) {
			Users user = em.find(Users.class, username);
			hashDB = user.getPassword();
			name = user.getName();
		}else {
			Admin admin = em.find(Admin.class, username);
			hashDB = admin.getPassword();	
			name = admin.getName();
		}
			if((new Hashed("")).login(password, hashDB))
				return true;
			return false;
	}
	public String getName(){
		return name;
	}
}