package com.airlines;


import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MaxSnoDao {
	
		int sno;
		
	
	public MaxSnoDao(int sno) {
			super();
			this.sno = sno;
		}


	public boolean checkTimeTable() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		Time_Table tt = em.find(Time_Table.class, sno);
		return (tt!=null)?true:false;
	}
	public boolean checkBookings(String username) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Bookings> cq = cb.createQuery(Bookings.class);
		
		Root<Bookings> rt = cq.from(Bookings.class);
		
		Predicate p1 = cb.equal(rt.get("bsno"), sno);
		Predicate p2 = cb.equal(rt.get("username"), username);
		Predicate p3 = cb.and(p1,p2);
		
		cq.where( p3);
		
		TypedQuery<Bookings> tq = em.createQuery(cq);
		int res = tq.getFirstResult();
		System.out.println(res);
		return (res == 0 ? true : false);
		
	}
}
