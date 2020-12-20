package com.airlines;

import javax.persistence.criteria.Predicate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class TimeTableDao {
	String toCity;
	String fromCity; 
	public TimeTableDao(String toCity, String fromCity) {
		super();
		this.toCity = toCity;
		this.fromCity = fromCity;
	}
	List<Time_Table> TimeTableFetcher() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Time_Table> cq = cb.createQuery(Time_Table.class);
		
		Root<Time_Table> rt = cq.from(Time_Table.class);
		
		Predicate p1 = cb.equal(rt.get("arrival_ccode"), toCity);
		Predicate p2 =  cb.equal(rt.get("departure_ccode"), fromCity);
		Predicate p3 =  cb.and(p1,p2);
		
		cq.where(p3);
		
		TypedQuery<Time_Table> tq = em.createQuery(cq);
		List<Time_Table> list = tq.getResultList();
		
		
		return list;
	}
}
