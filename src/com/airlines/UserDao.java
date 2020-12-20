package com.airlines;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserDao {
	String name;
	String username;
	String password;
	public UserDao(String name, String username, String password) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
	}
	public boolean signup(){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		Users user = new Users();
		String hash = (new Hashed(password)).getHash();
		
		user.setName(name);
		user.setUsername(username);
		user.setPassword(hash);
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		return true;
		
	}
	public boolean existUser(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
	    return (em.find(Users.class, username) != null ? true : false);
		
	}
}