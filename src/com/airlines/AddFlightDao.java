package com.airlines;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddFlightDao {
	String code;
	String flight;
	public AddFlightDao(String code, String flight) {
		super();
		this.code = code;
		this.flight = flight;
	}
	public boolean added(){
		Flights f = new Flights();
		f.setFid(code);
		f.setFname(flight);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		em.close();
		return true;

	}
}
