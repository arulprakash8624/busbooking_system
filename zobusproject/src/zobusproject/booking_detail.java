package zobusproject;

import java.util.Scanner;

public class booking_detail 
{
	String email;
	int bus_id;
	String passenger_name;
	int seat_no;
	String date;
	int gender;
	Scanner sc=new Scanner(System.in);
	booking_detail(String email,int id,String date,int gender,int seat_no)
	{
		this.email=email;
		this.bus_id=id;
		this.date=date;
		this.gender=gender;
		System.out.println("enter the passenger name:");
		this.passenger_name=sc.next();
		this.seat_no=seat_no;
		
		
		
	}
	
}
