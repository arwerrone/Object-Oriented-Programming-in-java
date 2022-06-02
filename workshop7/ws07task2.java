package workshop7;

import java.util.HashMap;
import java.util.Scanner;

public class ws07task2 {

	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);
        HashMap<String, String> map = new HashMap<String, String>();
        Boolean repeat = true;

        map.put("canada", "Ottawa");
        map.put("brazil", "Brasilia");
        map.put("united kingdom", "London");
        map.put("australia", "Canberra");
        map.put("united states of america", "Washington D.C.");
        map.put("mexico", "Mexico City");
        map.put("china", "Beijing");
        map.put("india", "New Delhi");
        map.put("japan", "Tokyo");
        map.put("south korea", "Seul");
        map.put("new zealand", "Wellington");
        map.put("republic of cameroon", "Yaoundé");
        map.put("nigeria", "Abuja");
        map.put("portugal", "Lisboa");
        map.put("belize", "Belmopã");
        map.put("greenland", "Nuuk");
        map.put("iceland", "Reykjavik");
        map.put("ireland", "Dublin");
        map.put("Denmark", "Copenhage");
        map.put("angola", "Luanda");
        map.put("vietnam", "Hanói");
        map.put("sweden", "Estocolmo");
        map.put("switzerland", "NONE or Bern"); // Switzerland does not have a capital, but for political issues they consider Bern as a capital
        map.put("colombia", "Bogotá");
        map.put("zimbabwe", "Harare");

        do {
        
        System.out.print("Please, type any country that you wish to know the captial: ");
        String country = sc.next().toLowerCase();	// this let the user type any country without minding case sensitive.

        if (map.get(country) != null) {
        	String upCountry = country;
        	upCountry = upCountry.substring(0,1).toUpperCase() + upCountry.substring(1).toLowerCase();
            System.out.println("The capital of "+upCountry+" is " + map.get(country) );
        } else {
            System.out.println("Sorry, the country that you typed is not in the list");
        }

        Boolean notValid = true;
        do {
        System.out.print("\nWant to do another search? (Y/n)");
        String option = sc.next();
        
        if (option.charAt(0) == 'Y' || option.charAt(0) == 'y') {
        	repeat = true;
        	notValid = false;
        }else if (option.charAt(0) == 'N' || option.charAt(0) == 'n') {
        	repeat = false;
        	notValid = false;
        }else {
        	System.out.println("Please choose a valid option, (Y/n)");
        	notValid = true;
        }
        }while(notValid);	//This will loop until the user type a valid option.
        
        }while(repeat);		//This will loop until the user chooses to stop.
        
       sc.close();
       
		System.out.println("\n*************************");
		System.out.println("*     BYE BYE! (^-^)    *");
		System.out.println("*************************");
    }
	
}
