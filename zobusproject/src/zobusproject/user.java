package zobusproject;
import java.util.Scanner;
class user {
		String email;
		String password;
		String name;
		int age;
		int gender;
	user() {
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your emailid:");
		this.email=sc.next();
		System.out.print("enter your password:");
		this.password=sc.next();
		System.out.println("enter your name:");
		this.name=sc.next();
		System.out.println("enter your age:");
		this.age=sc.nextInt();
		System.out.println("enter your gender\n1->MALE\n2->FEMALE:");
		this.gender=sc.nextInt();
		sc.close();
	}

}
