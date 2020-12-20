package com.airlines;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import java.util.List;
import java.sql.SQLException;
import java.util.HashSet;

import java.util.Set;


public class FlightsDao {

public Set<String> flights() throws SQLException, ClassNotFoundException{
		
	
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		 CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
         CriteriaQuery<Flights> criteriaQuery = criteriaBuilder.createQuery(Flights.class);
        
         Root<Flights> query = criteriaQuery.from(Flights.class);
         criteriaQuery.select(query);
         TypedQuery<Flights> typedQuery = em.createQuery(criteriaQuery);
         List<Flights> allData = typedQuery.getResultList();
         Set<String> set = new HashSet<String>();
         for(Flights f : allData) {
        	 String s = f.fname+"("+f.fid+")";
        	 set.add(s);
         }
       
         return set;
		//em.close();
		/*Connection con = (new Connect()).connect();
		String sql = "select * from flights";
		Statement st = ((java.sql.Connection) con).createStatement();
		ResultSet rs = st.executeQuery(sql);
		Set<String> set = new HashSet<String>();
		while(rs.next()){
			set.add(rs.getString(2)+"("+rs.getString(1)+")");
		}
		st.close();
		con.close();
		
		return set;*/
	}
}
