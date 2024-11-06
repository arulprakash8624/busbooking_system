package zobusproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class views {
	Connection con = null;
	Scanner sn=new Scanner(System.in);
	void connect() {
		
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zobus", "root", "");
        }
        catch (Exception e) {
            System.out.println(e);
        }
	}
	views(){
		try {	
			connect();
            PreparedStatement pst = con.prepareStatement("create table if not exists booking (booking_id int not null auto_increment primary key, user_name varchar(50),bus_id int ,passenger_name varchar(50),seat_no int not null ,dates date,gender int)");
            pst.executeUpdate();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
	try {	
			connect();
            PreparedStatement pst = con.prepareStatement("create table if not exists buses (id int, name varchar(50), route varchar(50), type int, amount int,total_number_seats int,dates date)");
            pst.executeUpdate();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        try {
    		connect();
            PreparedStatement pst = con.prepareStatement("create table if not exists passengers (email varchar(50),password varchar(20),name varchar(20), age int, gender int)");
            pst.executeUpdate();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        try {
    		connect();
            PreparedStatement pst = con.prepareStatement("create table if not exists admins (username varchar(50),password varchar(20),name varchar(20), age int)");
            pst.executeUpdate();
            con.close();
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        
	}
	void register() {
		try {
			user u1=new user();
			connect();
			PreparedStatement pst = con.prepareStatement("insert into passengers (email,password,name,age,gender) values(?,?,?,?,?)");
            pst.setString(1, u1.email);
            pst.setString(2, u1.password);
            pst.setString(3, u1.name);
            pst.setInt(4, u1.age);
            pst.setInt(5, u1.gender);
            pst.executeUpdate();
            con.close();
            System.out.println("register successfully!");

		}
        catch (Exception e) {
			// TODO Auto-generated catch block
        	System.out.println(e);
		}
	}
	void login1() {
		
		try
		{	connect();
			PreparedStatement ps=con.prepareStatement("select *from passengers where email=? and password=?");
			System.out.println("enter the email :");
			String email=sn.next();
			ps.setString(1, email);
			System.out.println("enter your password:");
			sn.nextLine();
			String pass=sn.nextLine();
			ps.setString(2, pass);
			ResultSet rst=ps.executeQuery(); 
			if(rst.next()) {
				login l=new login(rst.getString(3),email);
				}
			else {
				System.out.println("enter the correct username and password!");
			}	
			con.close();
			

		}
		catch(Exception e)
		{
			System.out.println("enter the correct username and password"+e);
		}
	}
	void view_bus() {
		try
		{	connect();
			PreparedStatement ps=con.prepareStatement("select *from buses");
			ResultSet rst=ps.executeQuery();
			Formatter fmt=new Formatter();
			fmt.format("%12s %12s %30s %12s %10s %15s %15s\n", "id","name","route","type","amount","total_seat","date");
			while(rst.next())
			{
				fmt.format("%12s %12s %30s %12s %10s %15s %15s\n", rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getInt(6),rst.getString(7));

			}
			System.out.println(fmt);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	void admin() {
		connect();
		bus b=new bus();
		try
		{
			PreparedStatement ps=con.prepareStatement("select *from admins where username=? and password=?");
			System.out.println("enter the username :");
			ps.setString(1, sn.next());
			System.out.println("enter your password:");
			sn.nextLine();
			String pass=sn.nextLine();
			ps.setString(2, pass);
			ResultSet rst=ps.executeQuery(); 
		if(rst.next()) {
			System.out.println("login succsssfully");
			while(true) {
				System.out.print("1->insert bus\n2->delete bus \n3->view\n4->exit\n");
				System.out.println("enter your option: ");
				int bus_choice=sn.nextInt();
				if (bus_choice==1) {
					b.insert();
				}
				else if(bus_choice==2){
					b.delete();
				}
				else if(bus_choice==3) {
					b.view();
				}
				else if(bus_choice==4) {
					break;
				}
				else {
					System.out.print("enter the choice correctly:");
				}
					
			}
		}
		else {
			System.out.println("enter the correct username and password!");
		}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
	}
	String view_seates(int idss) {
		try
		{	connect();
			PreparedStatement ps=con.prepareStatement("select seat_no,gender from booking where bus_id=?");
			ps.setInt(1, idss);
			ResultSet rst=ps.executeQuery();
			ArrayList<Integer> arr=new ArrayList<Integer>();
			ArrayList<Integer> arr1=new ArrayList<Integer>();
			while(rst.next()) {
				arr1.add(rst.getInt(1));
			}
			PreparedStatement pst=con.prepareStatement("select total_number_seats,dates,type from buses where id=?");
			pst.setInt(1, idss);
			ResultSet rst1=pst.executeQuery();
			rst1.next();
			String date=rst1.getString(2);
			int totalseat=rst1.getInt(1);
			int bus_type=rst1.getInt(3);
			for(int i=1;i<=totalseat;i++) {
				boolean b=false;
				for(int j:arr1) {
					if(i==j) {
						b=true;
						break;
					}
				}
				if(b==true) {
					
				}
				else {
					arr.add(i);
				}
			}
			System.out.println("the available seates :");
			bus_structure bus_struct=new bus_structure(arr1,bus_type);
			System.out.println();
			con.close();
			return date;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}

