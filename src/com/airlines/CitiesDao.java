package com.airlines;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CitiesDao {
	public Set<String> cities() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cities> cq = cb.createQuery(Cities.class);
		
		Root<Cities> rt = cq.from(Cities.class);
		cq.select(rt);
		
		TypedQuery<Cities> tq = em.createQuery(cq);
		List<Cities> list = tq.getResultList();
		Set<String> set = new HashSet<String>();
		for(Cities c : list){
			String s = c.getCname()+"("+c.getCcode()+")";
			set.add(s);
		}
		
		return set;
	}
}
