package car_rental_system.com.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Customer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY,generator="cus_id")
	@SequenceGenerator(name="cus_id",initialValue = 1001,allocationSize=1)
	int id;
	
	String name;
	long adhar;
	String gender;
	long cno;
	String dlicence;
	
	@OneToOne
	Car car;
	
	LocalDate rdate;
	LocalDate sdate;
	
}
