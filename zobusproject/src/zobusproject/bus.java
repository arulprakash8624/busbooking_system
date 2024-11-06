package zobusproject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Formatter;
import java.util.Scanner;
class bus{
	Connection con = null;
    Scanner sn = new Scanner(System.in);
    void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/zobus", "root", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void insert() {
        try {
            connect();
            PreparedStatement ps = con.prepareStatement("insert into buses (id, name, route,type,amount,total_number_seats,dates) values(?, ?, ?,?,?,?,?)");
            System.out.print("enter sno: ");
            ps.setInt(1, sn.nextInt());
            System.out.print("enter name of the bus: ");
            ps.setString(2, sn.next());
            System.out.print("enter route: ");
            ps.setString(3, sn.next());
            System.out.print("enter name type of the bus\n1->AC-Sleeper\n2->Ac-seater\n3->Non-Ac-Sleeper\n4->Non-Ac-seater: ");
            int type=sn.nextInt();
            ps.setInt(4, type);
            System.out.print("enter the amount per seat: ");
            ps.setInt(5, sn.nextInt());
            int no_of_seat=calculate_no_seat(type);
            System.out.print(" the total_number of seats: "+no_of_seat);
            ps.setInt(6, no_of_seat);
            System.out.println("enter the date of the bus travel (yyyy-mm-dd): ");
            String date=sn.next();
            ps.setString(7,date);
            ps.executeUpdate();
            System.out.println("bus information added successfully!");
            con.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
     int  calculate_no_seat(int type) {
    	 if(type==1 ||type==3) {
    		 return 30;
    	 }
    	 else {
    		 return 50;
    	 }
		// TODO Auto-generated method stub
		
	}
	void delete()
	{
		try
		{
			connect();
			PreparedStatement ps=con.prepareStatement("delete from buses where id=?"); 
			System.out.print("enter bus_id to delete: ");
			ps.setInt(1, sn.nextInt());
			ps.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
    void view()
	{
		try
		{
			connect();
			PreparedStatement ps=con.prepareStatement("select *from buses");
			ResultSet rst=ps.executeQuery();
			Formatter fmt=new Formatter();
			fmt.format("%12s %12s %30s %12s %10s %15s %15s\n", "id","name","route","type","amount","total_seat","date");
			while(rst.next())
			{
				fmt.format("%12s %12s %30s %12s %10s %15s %15s\n", rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4),rst.getInt(5),rst.getInt(6),rst.getString(7));

			}
			System.out.println(fmt);

			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}



