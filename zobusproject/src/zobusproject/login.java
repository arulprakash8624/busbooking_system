package zobusproject;

import java.util.Scanner;

public class login {
	 login(String name, String email) {
		// TODO Auto-generated constructor stub
		System.out.println("Hi!"+" "+name);
		System.out.println("this are the available bus ");
		views v=new views();
		v.view_bus();
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("enter the bus id do you want to travel..");
			int ch=sc.nextInt();
			String date=v.view_seates(ch);
			booking_bus b=new booking_bus(email,ch,date);
			break;
		}
		sc.close();
	}
}
