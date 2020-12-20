package com.airlines;

import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;


public class CancelDao {
	int bsno;
	String username;
	public CancelDao(int bsno,String username) {
		super();
		this.bsno = bsno;
		this.username=username;
	}
	public boolean cancelled(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaDelete<Bookings> cd = cb.createCriteriaDelete(Bookings.class);
		Root<Bookings> rt = cd.from(Bookings.class);
		
		Predicate p1 =  cb.equal( rt.get("bsno"), bsno);
		Predicate p2 = cb.equal( rt.get("username"), username);
		
		Predicate p3 = cb.and(p1,p2);
		
		cd.where( p3);
		em.getTransaction().begin();
		int result = em.createQuery(cd).executeUpdate();
		em.getTransaction().commit();
		System.out.println("DELETE RESULT "+result);
		return true;
	}
}