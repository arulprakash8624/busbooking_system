package zobusproject;

import java.util.ArrayList;

public class bus_structure {

	bus_structure(ArrayList<Integer> arr,int type){
		if(type==1||type==3) {
			int[] arr_bus=new int[30];
			for(int i=0;i<arr_bus.length;i++) {
				arr_bus[i]=0;
			}
			for(int i=0;i<arr_bus.length;i++) {
				if(arr.contains(i+1)) {
					arr_bus[i]=1;
				}
			}
			System.out.println("LOWER COMPARTMENT!");
			int k=0;
			for(int i=0;i<3;i++) {
				System.out.print("");
				char ch=(char) (i+65);
				System.out.print("L"+ch+"\t");
				for(int j=k;j<k+5;j++) {
					System.out.print(arr_bus[j]+"\t\t");				
					}
				if(i==1) {
					System.out.println("");
					System.out.print("___________________________________________________________________________");
					System.out.println("");
					
				}
				k=k+5;
				System.out.println("");

			}
			System.out.println("UPPER COMPARTMENT!");
			int k1=0;
			for(int i=0;i<3;i++) {
				System.out.print("");
				char ch=(char) (i+65);
				System.out.print("U"+ch+"\t");
				for(int j=k1;j<k1+5;j++) {
					System.out.print(arr_bus[j]+"\t\t");				
					}
				if(i==1) {
					System.out.println("");
					System.out.print("___________________________________________________________________________");
					System.out.println("");
					
				}
				k1=k1+5;
				System.out.println("");

			}
			
		}
		else {
			int[] arr_bus=new int[50];
			for(int i=0;i<arr_bus.length;i++) {
				arr_bus[i]=0;
			}
			for(int i=0;i<arr_bus.length;i++) {
				if(arr.contains(i+1)) {
					arr_bus[i]=1;
				}
			}
			int k=0;
			for(int i=0;i<5;i++) {
				System.out.print("");
				char ch=(char) (i+65);
				System.out.print(ch+"\t");
				for(int j=k;j<k+10;j++) {
					System.out.print(arr_bus[j]+"\t");				
					}
				if(i==2) {
					System.out.println("");
					System.out.print("_________________________________________________________________________________");
					System.out.println("");
					
				}
				k=k+10;
				System.out.println("");

			}

		}
	}
}
