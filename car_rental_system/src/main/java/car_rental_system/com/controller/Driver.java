package car_rental_system.com.controller;

import java.time.LocalDate;
import java.util.Scanner;


import car_rental_system.com.dao.CarDAO;
import car_rental_system.com.dao.CustomerDAO;
import car_rental_system.com.dao.EnginDAO;


public class Driver {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Car Rental System...");
		
		System.out.println("=========================Available Cars=================================");
		
		
		System.out.println("Press 1 for Review the Car...");
		System.out.println("Press 2 for rent the Car...");
		System.out.println("Press 3 for Sumbit the Car...");
		System.out.println("Press 4 To add a car...");
		System.out.println("Press 5 to delete a car");
		System.out.println("Press 6 to delete a engin");
		System.out.println("Press 7 to delete a customer");
		
		int choice = sc.nextInt();
		
		switch (choice) {
		
		case 1:{
			
			CarDAO.carDetials();
			
			System.out.println("========================================================");
			System.out.println();
			System.out.print("Enter Car id which you want to review... : ");
			int cid = sc.nextInt();
			System.out.println();
			
			
			CarDAO.carDetialsById(cid);
			
			break;
		
		}
		case 2:{
				System.out.print("Enter your name: ");
				String name = sc.next();
				String surname = sc.next();
				
				 sc.nextLine();
				System.out.print("Enter your Adhar: ");
				long adhar = sc.nextLong();
				System.out.print("Enter your gender: ");
				String gender= sc.next();
				sc.nextLine();
				System.out.print("Enter driving licence number: ");
				String dlicence = sc.next();
				System.out.print("Enter Contact Number: ");
				long cno = sc.nextLong();
				
				System.out.print("Enter date: ");
				int day = sc.nextInt();
				int month = sc.nextInt();
				int year = sc.nextInt();
				
				LocalDate date=LocalDate.of(year, month, day);
				
				System.out.println("Enter car id: ");
				
				int car_id = sc.nextInt();
				
				int cid =CustomerDAO.rentCar(name+" "+surname,adhar,gender,dlicence,cno,date);
				
				CustomerDAO.allocateCar(cid,car_id);
				
			break;
			
		}
		case 3:{
			
			System.out.println("Enter Customer id :");
			int cid= sc.nextInt();
			
			System.out.println("Enter car id : ");
			int car_id=sc.nextInt();
			CustomerDAO.deallocateCar(cid,car_id);
			break;
		}
		case 4:{

			
			System.out.print("Enter Brand of Car: ");
			String brand = sc.next();
			
			System.out.print("Enter Color of Car:");
			String color = sc.next();
			
			System.out.print("Enter model of Car: ");
			String model = sc.next();
			
			System.out.print("Enter number of Car: ");
			String number =sc.next();
			
			System.out.print("Enter status of Car: ");
			String status =sc.next();
			
			System.out.print("Enter type of Car: ");
			String type = sc.next();
			
			System.out.print("Enter cc of Engine : ");
			double cc = sc.nextDouble();
			
			sc.nextLine();
			System.out.print("Enter type of Engine: ");
			String etype =sc.nextLine();
			
			sc.nextLine();
			
			CarDAO.addCar(brand, color, model, number, status, type, cc, etype);
			
			break;
		}
		case 5:{
			System.out.println("Enter a car id which you want to delete...");
			
			int car_id = sc.nextInt();
			CarDAO.deleteCar(car_id);
			break;
		}
		
		case 6:{
			System.out.println("Enter engine id which you want to delete...");
			
			int eid = sc.nextInt();
			EnginDAO.deleteEngin(eid);
			break;
		}
		
		case 7:{
			System.out.print("Enter id of customer : ");
			
			int id = sc.nextInt();
			
			CustomerDAO.deleteCustomer(id);
			
			break;
		}
		default:System.out.println("Enter valid  choice options.... ");
			
			break;
		}
		
		sc.close();
	}
}
