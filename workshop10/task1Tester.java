package workshop10;

import java.util.Scanner;

public class task1Tester {
	
   public static void main(String args[]) {
	   
       Scanner sc = new Scanner(System.in);
       
       System.out.print("Enter time1 (hour minute second): ");
       Time Time1 = new Time(sc.nextInt(), sc.nextInt(), sc.nextInt());
       System.out.println(Time1);
       System.out.println("Elapsed seconds in time1: " + Time1.getSeconds());
       
       System.out.print("\nEnter time2 (elapsed time in seconds): ");
       long newTime = sc.nextLong();
       Time Time2 = new Time(newTime);
       System.out.println(Time2);
       System.out.println("Elapsed seconds in time2: " + Time2.getSeconds());

       System.out.println();
       System.out.println("time1.compareTo(time2)? " + Time1.compareTo(Time2));
       
       Time Time3 = Time1.clone();
       System.out.println("time3 is created as a clone of time1");
       System.out.println("time1.compareTo(time3)? " + Time1.compareTo(Time3));
       
       sc.close();
   }
   
}