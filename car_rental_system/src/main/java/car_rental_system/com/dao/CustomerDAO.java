package car_rental_system.com.dao;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import car_rental_system.com.entity.Customer;
import car_rental_system.com.util.DBConnection;



public class CustomerDAO {

	public static int rentCar(String name, long adhar, String gender, String dlicence, long cno, LocalDate date) {
	
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		Customer c = new Customer();
		
		c.setAdhar(adhar);
		c.setCno(cno);
		c.setName(name);
		c.setGender(gender);
		c.setDlicence(dlicence);
		c.setSdate(date);
		
		LocalDate rdate = LocalDate.now();
		c.setRdate(rdate);
		
		et.begin();
		em.persist(c);
		et.commit();
		
		return c.getId();
		
	}

	public static void allocateCar(int cid, int car_id) {
		
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		String sql = "update customer set car_cid=:car_id where id=:id";
		
		et.begin();
		
		Query q=em.createNativeQuery(sql,Customer.class);
		
		q.setParameter("car_id", car_id);
		q.setParameter("id", cid);
		
		int count =q.executeUpdate();
		
		et.commit();
		
		CarDAO.changeStatus(car_id);
		
		if(count ==1 )
		{
			System.out.println(car_id+"Car is allocated to customer...");
		}
	}
	
	
	public static void deallocateCar(int cid,int car_id)
	{
		EntityManager em = DBConnection.createConnection();
		
		EntityTransaction et = em.getTransaction();
		
//		String sql = "update customer  set c_cid=:car_id where id=:cid";
		
//		String jpql = "update Customer cu set cu.c_cid=:car_id where c.id=:cid";
		

		String jpql = "update Customer c set c.car.id=:car_id where c.id=:cid";
		
		et.begin();
		
//		Query q = em.createNativeQuery(sql,Customer.class);
		
		Query q = em.createQuery(jpql);
		q.setParameter("cid", cid);
		q.setParameter("car_id", null);
		
		int count = q.executeUpdate();
		
		et.commit();
		
		CarDAO.changeStatus(car_id);
		if(count == 1)
		{
			System.out.println("Car is Deallocated...");
		}
	}
	
	public static void deleteCustomer(int id)
	{
		EntityManager em = DBConnection.createConnection();
		
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		
		Customer c =em.find(Customer.class, id);
		
		em.remove(c);
		
		et.commit();
	}
	
	public static int getdays(int cid)
	{
		EntityManager em = DBConnection.createConnection();
		EntityTransaction et = em.getTransaction();
		
		 Customer c = em.find(Customer.class, cid);
		
		 LocalDate rdate= 	c.getRdate();
		
		 LocalDate sdate = c.getSdate();
		  
		 
		return 0;
	}
	
}
