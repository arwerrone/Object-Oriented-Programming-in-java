package workshop7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ws07task1 {

	public static ArrayList<String> lines = new ArrayList<>();
    public static ArrayList<String> boys = new ArrayList<>();
    public static ArrayList<String> girls = new ArrayList<>();
    public static ArrayList<String> equalsNames = new ArrayList<>();
    
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	Boolean fileNotFound = false;
    	
    	BufferedReader br = null;
    	
        do {
            try {
                System.out.print("\nPlease enter a file name: ");
                String filename = sc.next();
                File sourceFile = new File(System.getProperty("user.dir")+"\\babynamesfolder\\" + filename);
            	br = new BufferedReader(new FileReader(sourceFile));
            	
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
                
                fileNotFound = false;
                
            } catch (FileNotFoundException ex) {
            	System.out.println("FILE NOT FOUND!!");
            	System.out.println("Try again... :)");
            	fileNotFound = true;
            	
            }
            catch (Exception err) {
                err.printStackTrace();
                System.out.println("SOMETHIN WENT REALLY WRONG!!");
            }

        }while(fileNotFound);	// loop until user types a valid filename.
    	
        // this will real all the names and add to the arraylist for boys and girls.
        for (int j=0; j < lines.size(); j++) {
            String[] arr = lines.get(j).toString().split("\t");
            
            for (int i=0; i< arr.length;i++) {
                arr[i].trim();
            }
            
            boys.add(arr[1]);
            girls.add(arr[3].substring(0, arr[3].length() - 1));
        }

        // this will add the names used for both genders into equals arraylist
        for (int i = 0; i < boys.size(); i++) {
        	
            for (int j = 0; j < girls.size(); j++) {
                if (boys.get(i).equals(girls.get(j))) {
                	equalsNames.add(boys.get(i));
                }
            }
            
        }
        
        System.out.println("\nThere are "+equalsNames.size()+" names used in both genders");
        System.out.println("The names are:");
        
        for (int i =0; i < equalsNames.size(); i++) {
        	System.out.print( equalsNames.get(i) +"   ");
        }

        // sorts name in alfabetical order.
        Collections.sort(boys);
        Collections.sort(girls);

        for (int i = 0; i < equalsNames.size(); i++) {
        	boys.remove(equalsNames.get(i));
        	girls.remove(equalsNames.get(i));
        }

        System.out.println("\n\n***** boys and girls names list sorted with all duplicates removed *****");
        System.out.println("\nboys names: ");
        
        for (int i = 0; i< boys.size(); i++) {
        	System.out.print( boys.get(i) +"   ");
        }
        
        System.out.println("\n\ngirls names: ");
        for (int i = 0; i< girls.size(); i++) {
        	System.out.print( girls.get(i) +"   ");
        }

        sc.close();
        
    }
	
}
