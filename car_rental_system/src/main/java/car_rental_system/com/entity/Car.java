package car_rental_system.com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Data;

@Entity
@Data
@Table(name = "cars")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "car_id")
	@SequenceGenerator(name = "car_id", initialValue = 101, allocationSize = 1)
	@Column(name = "cid")
	int id;

	String model;
	String brand;
	String type;
	String color;
	String status;
	
	@OneToOne
	Engine e;
	
	LocalDate date;
	String number;



}
