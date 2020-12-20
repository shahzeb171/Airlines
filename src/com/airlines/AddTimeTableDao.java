package com.airlines;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AddTimeTableDao {
	String fid;
	String departure_city;
	String arrival_city ;
	String departure_time;
	String arrival_time ;
	int price ;
	public AddTimeTableDao(String fid, String departure_city,
			String arrival_city, String departure_time, String arrival_time,
			int price) {
		super();
		this.fid = fid;
		this.departure_city = departure_city;
		this.arrival_city = arrival_city;
		this.departure_time = departure_time;
		this.arrival_time = arrival_time;
		this.price = price;
	}
	public boolean added(){
		Time_Table tt = new Time_Table();
		tt.setFid(fid);
		tt.setDeparture_ccode(departure_city);
		tt.setArrival_ccode(arrival_city);
		tt.setPrice(price);
		tt.setArrival_time(arrival_time);
		tt.setDeparture_time(departure_time);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(tt);
		em.getTransaction().commit();
		em.close();
		
		return true;
	}
}