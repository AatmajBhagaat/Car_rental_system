package car_rental_system.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;


import car_rental_system.com.entity.Engine;
import car_rental_system.com.util.DBConnection;

public class EnginDAO {

	public static Engine addEngine( double cc,String type)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Engine e = new Engine();
		
		e.setCc(cc);
		e.setType(type);
		
		em.persist(e);
		
		et.commit();
		
		return e;
		
	}
	public static void getEnginDetailsById(int id)
	{
		
			EntityManager em = DBConnection.createConnection();
			EntityTransaction et = em.getTransaction();
			
			et.begin();
			String jpql = "Select e Engin e";
			Query q = em.createQuery(jpql);
			
			List <Engine> list = q.getResultList();
			
			list.forEach(al->System.out.println(al.getId()+" "+al.getCc()+" "+al.getType()));
			et.commit();
		
	}
	
public static void deleteEngin (int id) {
		
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		Engine e=em.find(Engine.class, id);
		em.remove(e);
		et.commit();

		
		
		
	}
}
