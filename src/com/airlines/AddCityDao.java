package com.airlines;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddCityDao {
	String code;
	String city;
	public AddCityDao(String code, String city) {
		super();
		this.code = code;
		this.city = city;
	}
	public boolean added(){
		
		Cities c = new Cities();
		c.setCcode(code);
		c.setCname(city);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
		em.close();
		return true;
		
	}
}
