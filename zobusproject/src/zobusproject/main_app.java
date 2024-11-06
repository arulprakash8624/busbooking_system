package zobusproject;
import java.util.Scanner;
public class main_app {

	public static void main(String[] args) 
	{
		views crt=new views();
		Scanner sn=new  Scanner(System.in);
		int op;
		while(true)
		{
			System.out.print("1->register\n2->login\n3->view\n4->admin\n5->exit\n");
			
			System.out.println("enter your option: ");
			op=sn.nextInt();
			switch(op) 
			{
			
			case 1:
			{
				crt.register();
				break;
			}
			case 2:
			{
				crt.login1();
				break;
			}
			case 3:
			{
				crt.view_bus();
				break;
			}
			
			case 4:
			{
				crt.admin();
				break;
				
			}
			
			case 5:
			{
				System.out.println("varata mamaee duuuuurrrrrr");
				break;
			}
			default:
			{
				System.out.println("option ah ozhunga padi da body soda\n");
			}
		}
			
		}

	}

}
