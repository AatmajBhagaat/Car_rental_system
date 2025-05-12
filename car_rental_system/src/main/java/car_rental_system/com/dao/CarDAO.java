package car_rental_system.com.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import car_rental_system.com.entity.Car;
import car_rental_system.com.entity.Engine;
import car_rental_system.com.util.DBConnection;

public class CarDAO {

	public static void addCar(String brand,String color,String model,String number,String status,String type,double cc,String etype)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Car c = new Car();
		
		c.setBrand(brand);
		c.setColor(color);
		c.setModel(model);
		c.setNumber(number);
		c.setStatus(status);
		c.setType(type);
		
		Engine e =  EnginDAO.addEngine(cc, etype);
		c.setE(e);
		
		em.persist(c);
		
		et.commit();
		
	}
	
	public static void addCar(String brand,String color, String model, String number,String status, String type, int eid)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Car c = new Car();
		
		c.setBrand(brand);
		c.setColor(color);
		c.setModel(model);
		c.setNumber(number);
		c.setStatus(status);
		c.setType(type);
		
		em.persist(c);
		et.commit();
		
		et.begin();
		
		String sql = "update cars  set e_eid=:eid where cid=(select max(cid)  from cars)";
		
		Query q =em.createNativeQuery(sql);
		
		int count =q.executeUpdate();
		
		et.commit();
		
		
	}
	
	public static void carDetialsById(int id)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		String jpql = "Select c from Car c,Engine e where c.id=:id";
		Query q = em.createQuery(jpql);
		
		q.setParameter("id", id);
		
		Car c = (Car) q.getSingleResult();
		
		System.out.println("========================================================");
		
		System.out.println(c.getId()+" | "+c.getBrand()+" | "+c.getModel()+" | "+c.getNumber()+" | "+c.getColor()+" | "+c.getType()+" | "+ c.getStatus());
		
	}
	
	public static void carDetials()
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		String jpql = "Select c from Car c";
		Query q = em.createQuery(jpql);
		
		List <Car> list = q.getResultList();
		
		System.out.println("======================== Cars ==========================");
		
		list.forEach(al->{System.out.println(al.getId()+" | "+al.getBrand()+" | "+al.getModel()+" | "+al.getNumber()+" | "+al.getColor()+" | "+al.getType()+" | "+ al.getStatus()); System.out.println();});
		
	}
	
	public static void getCarByDate(LocalDate date)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		String sql ="select c from Car c where c.date =?1";
		Query q = em.createQuery(sql);
		et.begin();
		q.setParameter(1, date);
		List <Car> list = q.getResultList();
		System.out.println(list);
		et.commit();
		list.forEach(al->System.out.println(al));
		
	}
	
	public static void allowcateEnginId(int id,int eid)
	{
		
			EntityManager em=DBConnection.createConnection();
			EntityTransaction et =em.getTransaction();
			
			et.begin(); 
//			String sql = "update cars  set engin_eid=:eid where cid=:cid";   //Native SQL query
			
			String jpql ="update Car c set c.engin.id=:eid where c.id=:cid";  // JPQL query
			Query query=em.createQuery(jpql);
//			Query query=em.createNativeQuery(sql,Car.class);
			query.setParameter("eid",eid);
			query.setParameter("cid",id);
			
			int row =query.executeUpdate();
			System.out.println(row);
			et.commit();
			
	}
	
	public static void deallowcateEngin(int id)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		String jpql = "update Car c set c.engin.id=:eid where c.id=:cid";
		
		Query q = em.createQuery(jpql);
		
		q.setParameter("eid", null);
		q.setParameter("cid", id);
		
		int count = q.executeUpdate();
		System.out.println(count);
		et.commit();
		
	}

	public static void deleteCar(int id)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Car c =em.find(Car.class, id);
		em.remove(c);
		et.commit();
	
		System.out.println("Deleted");
		
		
	}
	
	public static void deleteCarWithEngin(int id)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Car c =em.find(Car.class, id);
		Engine e =c.getE();
		int eid = e.getId();
		
		c.setE(null);
		em.merge(c);
		em.remove(c);
	
		et.commit();
		
	
		EnginDAO.deleteEngin(eid);      
		
		System.out.println("Deleted");
	}

	public static void changeStatus(int car_id) {
		
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Car c = em.find(Car.class, car_id);
		
		String s = c.getStatus();
		if(s.equals("Available"))
		{
		c.setStatus("Not Available");
		}
		
		else if(s.equals("Not Available"))
		{
			c.setStatus("Available");
		}
			
		em.merge(c);
		et.commit();
		
		
		
	}
}
