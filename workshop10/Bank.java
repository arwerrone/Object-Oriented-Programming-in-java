package workshop10;

import java.util.Locale;
import java.util.Scanner;

public class Bank {
	
	public static void main(String[] args) {
		
		int bankID, limit;
		double LoanAmount;
		boolean ok = false;
		Scanner sc = new Scanner(System.in).useLocale(Locale.US); //locale us to be able to get "."
		
		System.out.print("Enter number of banks: ");
		int n = sc.nextInt();
		
		double[] balance = new double[n];
		double[][] borrowers = new double[n][n];
		
		System.out.print("Minimum asset limit: ");
		limit = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			System.out.println("For Bank #" + i + " :");
			System.out.print("\tBalance: ");
			balance[i] = sc.nextDouble();
			
			System.out.print("\tNumber of banks Loaned: ");
			int banksLoaned = sc.nextInt();
			
			for (int j = 0; j < banksLoaned; j++) {
				
				System.out.print("\t\tBank ID who gets the loan: ");
				bankID = sc.nextInt();
				
				System.out.print("\t\tLoaned Amount: ");
				LoanAmount = sc.nextDouble();
				borrowers[i][bankID] = LoanAmount;
			}
			
		}
		
		boolean[] check = new boolean[n];
		
		do {
			ok = false;
			for (int i = 0; i < n; i++) {
				double asset = balance[i];
				for (int j = 0; j < borrowers[i].length; j++) 
					asset += borrowers[i][j];
		
				if (asset < limit) {
					check[i] = true;
					
					for (int j = 0; j < borrowers.length; j++) {
						if (borrowers[j][i] != 0) {
							borrowers[j][i] = 0;
							ok = true;
						}
					}
				}
			}
		} while (ok);
		
		System.out.println("\n\n");
		System.out.print("Unsafe banks are:");
		for (int i = 0; i < check.length; i++) {
			if (check[i]) 
				  System.out.print("Bank " + i + " ");
		  }
		 
		sc.close();
	}
}