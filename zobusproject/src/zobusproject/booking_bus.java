package zobusproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class booking_bus 
{
	Connection con = null;
	void connect() 
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zobus", "root", "");
        }
        catch (Exception e) 
		{
            System.out.println(e);
        }
	}
	booking_bus(String email,int ch,String date)
	{
		try 
		{		
		Scanner sc=new Scanner(System.in);
		System.out.println("welcome to AP bus booking System!");
		System.out.println("hello !"+email);
		System.out.println("your bus no :"+ch);
		System.out.println("date of travel:"+date);
		System.out.println("do you want to book the ticket!(y/n)");
		String s=sc.next();
		if(s.equals("y")||s.equals("Y")) 
		{
			int filled_seats=fill_seat(ch);
			System.out.println(filled_seats);
			int total_available_seat=total_seat(ch);
			int ava=total_available_seat-filled_seats;
			int seats=0;
			while(true) 
			{
				System.out.println("enter the total number of seats you want!");
				System.out.println("the available seats are:"+ava);
				seats=sc.nextInt();
				if(seats>=ava) 
				{
					System.out.println("enter the seat you want is less then or equal to available seat!");
					continue;
				}
				else 
				{
					ArrayList<booking_detail> book=new ArrayList<booking_detail>();
					ArrayList<Integer> arrs=new ArrayList<Integer>();
					while(seats>0) 
					{
							System.out.println("enter passenger gender\n1->male\n2->female:");
							int gender=sc.nextInt();
							if(gender==2) 
							{
								System.out.println("enter the seat number:");
								int seat_no=sc.nextInt();
								if(check_seat(seat_no,ch) && !(arrs.contains(seat_no)) && seat_no<=total_available_seat) 
								{
										arrs.add(seat_no);
										System.out.println(arrs);
										booking_detail temp=new booking_detail(email,ch,date,gender,seat_no);
										book.add(temp);
										seats--;
								}
								else 
								{
										System.out.println("the seat is already accupied!");
								}
							}
							else 
							{
								if(gender==1) 
								{
								System.out.println("enter the seat number:");
								int seat_no=sc.nextInt();
								if(check_seat(seat_no,ch) && !(arrs.contains(seat_no)) && seat_no<=total_available_seat) 
								{
										arrs.add(seat_no);
										System.out.println(arrs);
										booking_detail temp=new booking_detail(email,ch,date,gender,seat_no);
										book.add(temp);
										seats--;
								}
								else 
								{
										System.out.println("the seat is already accupied!");
								}
								}
								else {
									System.out.println("enter your gender correctly !");
									continue;
								}
							}
					}
			
					for(booking_detail i:book) 
					{
							System.out.println(i.bus_id+" "+i.date+" "+i.email+" "+i.gender+" "+i.passenger_name+" "+i.seat_no);
					}
					int amount=calculate_bill(book,ch);
					
					System.out.println("your bill amount ="+amount);
					System.out.println("do you want to confirm the booking press 1");
					int confirm=sc.nextInt();
					if(confirm==1) 
					{
						ArrayList<Integer> arraylist=add_booking(book);
						int index=0;
						System.out.println("bus_id\tdate\t\tusername\t\tgender\tpassenger_name\t\tseat_no\tbooking_id");
						for(booking_detail i:book) 
						{
								System.out.println(i.bus_id+" \t"+i.date+" "+i.email+" \t"+i.gender+"\t "+i.passenger_name+" \t"+i.seat_no+" \t"+arraylist.get(index));
								index++;
						}
						System.out.println("your booking is confirmed!");
						break;
					}
			
				}
			}
		}
			
		else 
		{
			System.out.println("do you want to cancel the ticket(Y/N):");
			String can=sc.next();
			if(can.equals("y")||can.equals("Y")) 
			{
				connect();
				PreparedStatement pst=con.prepareStatement("select * from booking where user_name=? and bus_id=?");
				pst.setString(1, email);
				pst.setInt(2, ch);
				ResultSet rst=pst.executeQuery();
				try (Formatter fmt = new Formatter()) {
					fmt.format("%15s %20s %10s %20s %10s %15s %10s\n", "booking_id","user_name","bus_id","passenger_name","seat_no","date","gender");
					while(rst.next())
					{
						fmt.format("%15s %20s %10s %20s %10s %15s %10s\n", rst.getInt(1),rst.getString(2),rst.getInt(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getInt(7));

					}
					System.out.println(fmt);				}
				while(true) 
				{
					System.out.println("enter the booking_id to cancel the ticket:");
					int can_booking=sc.nextInt();
					boolean bl=cancel_ticket(can_booking);
					if(bl)
					{
					System.out.println("your amount will be refunded");
					}
					else {
						System.out.println("the cancellation is incomplete!!!");
					}
					System.out.println("do you want to contiue cancelation (y/n):");
					String flag=sc.next();
					if(flag.equals("y")||flag.equals("Y")) 
					{
						continue;
					}
					else {
						break;
					}
				}
				
			}
			System.out.println("exit");
		}
		sc.close();
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		
}
 boolean cancel_ticket(int can_booking)
 {
		// TODO Auto-generated method stub
	 try 
	 {
		 	connect();
			PreparedStatement ps=con.prepareStatement("delete  from booking where booking_id=?");
			ps.setInt(1,can_booking);
			ps.executeUpdate();
			System.out.println("cancelation was successful");
			con.close();
			return true;
	 }
	catch(Exception e) 
	 {
		System.out.println(e);
	}
	return false;
	}
boolean check_seat(int seat_no,int c)
{
		// TODO Auto-generated method stub
	 try {
			connect();
			PreparedStatement ps=con.prepareStatement("select seat_no from booking where bus_id=?");
			ps.setInt(1,c);
			ResultSet rst=ps.executeQuery();
			while(rst.next()) 
			{
				if(seat_no==rst.getInt(1)) 
				{
					return false;
				}
			}

			}
			catch(Exception e) {
				System.out.println(e);
			}
		return true;
	}
int fill_seat(int c)  
{
	try {
	connect();
	PreparedStatement ps=con.prepareStatement("select count(*) from booking where bus_id=?");
	ps.setInt(1,c);
	ResultSet rst=ps.executeQuery();
	rst.next();
	int fill=rst.getInt(1);
	con.close();
	return(fill);
	}
	catch(Exception e) 
	{
		System.out.println(e);
	}
	return 0;
	
}
int total_seat(int c) 
{
	try 
	{
		connect();
		PreparedStatement pst=con.prepareStatement("select total_number_seats from buses where id=?");
		pst.setInt(1,c);
		ResultSet rst=pst.executeQuery();
		rst.next();
		int fill=rst.getInt(1);
		con.close();
		return fill;
	}
	catch(Exception e)
	{
		System.out.println(e);
		
	}
	return 0;
}
int calculate_bill(ArrayList<booking_detail> book,int c)
{
	try 
	{
		connect();
		PreparedStatement pst=con.prepareStatement("select amount from buses where id=?");
		pst.setInt(1,c);
		ResultSet rst=pst.executeQuery();
		rst.next();
		int bill_amount=rst.getInt(1)*book.size();
		con.close();
		return bill_amount; 
	}
	catch(Exception e) 
	{
		System.out.println(e);
	}
	return 0;
}
ArrayList<Integer> add_booking(ArrayList<booking_detail>book) 
{
	try 
	{
		ArrayList<Integer> booking_id=new ArrayList<Integer>();
		for(booking_detail i:book) 
		{
			connect();
			PreparedStatement pst=con.prepareStatement("insert into booking (user_name,bus_id,passenger_name,seat_no,dates,gender) values(?,?,?,?,?,?)");
			pst.setString(1,i.email);
			pst.setInt(2,i.bus_id);
			pst.setString(3,i.passenger_name);
			pst.setInt(4,i.seat_no);
			pst.setString(5,i.date);
			pst.setInt(6,i.gender);
			pst.executeUpdate();
			con.close();
			connect();
			PreparedStatement ps=con.prepareStatement("select booking_id from booking where seat_no=?");
			ps.setInt(1, i.seat_no);
			ResultSet rst=ps.executeQuery();
			rst.next();
			booking_id.add(rst.getInt(1));
			con.close();
		}
		return booking_id;
	}
	catch(Exception e) 
	{
		System.out.println(e);
	}
	return null;
}
}

