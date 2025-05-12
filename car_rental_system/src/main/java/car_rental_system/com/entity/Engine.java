package car_rental_system.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "engine")
public class Engine {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "e_id")
	@SequenceGenerator(name = "e_id", initialValue = 10, allocationSize = 1)
	@Column(name = "eid")
	int id;
	double cc;
	String type;
	
}
