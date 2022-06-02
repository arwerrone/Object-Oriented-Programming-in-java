package workshop4;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LetterCounter {
	
	public static void main(String[] args) throws IOException {
		try {
		Scanner input = new Scanner(System.in);
		
		char[] alph = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz".toCharArray();
        int[] count = new int[alph.length];
        
        
		System.out.print("Enter a filename: ");
		String fileName = input.nextLine();
		Scanner rFile = new Scanner(new FileInputStream(System.getProperty("user.dir")+"\\src\\workshop4\\"+ fileName));
		
		
		while(rFile.hasNext()) {
			String data = rFile.nextLine();
			for(int i = 0; i < data.length(); i++) {
				char ch = data.charAt(i);
				
				for(int n = 0; n < alph.length; n++) {
					if(ch == alph[n]) {
						count[n]++;
					}

				}
			}
		}
		
		for(int i = 0; i < alph.length; i++) {
			System.out.println("Number of "+ alph[i]+ "'s: " + count[i]);
		}
		
		input.close();
		rFile.close();
		}catch(FileNotFoundException ex) {
			System.out.println("File not found! Put the correct file path");
			System.out.println("Your current path is: " + System.getProperty("user.dir"));
		}
	}

}