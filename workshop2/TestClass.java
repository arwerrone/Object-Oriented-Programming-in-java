package workshop2;
import java.util.Scanner;
import java.util.InputMismatchException;

public class TestClass {
	
	public static void printer() {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("Taxable		Single		  Married Joint	      Married		 Head of");
		System.out.println("Income				  or Qualifying       Separate		 a House");
		System.out.println("				  Widow(er)");
		System.out.println("----------------------------------------------------------------------------------");
	}
	
	public static void menu() {
		System.out.println("Select the option desired:");
		System.out.println("1 - Compute personal income Tax");
		System.out.println("2 - Print the tax tables for taxable incomes (with range)");
		System.out.println("0 - Exit :(");
		System.out.print("Option selected: ");
	}
	
	public static void opt1() {
		System.out.println("0 - single filer");
		System.out.println("1 - married jointly or qualifying widow(er)");
		System.out.println("2 - married separately");
		System.out.println("3 - head of household");
		System.out.print("Enter the filing status: ");
	}
	
	public static void opt2(int amountF, int amountT, int[][] intervals, double[] rates) {
		int calc = (amountT - amountF) / 1000 + 1;
		for(int j = 0; j < calc; j++) {
			System.out.print(amountF + 1000 * j);
			
			for(int k = 0; k < 4; k++) {
				IncomeTax incT = new IncomeTax(k, intervals, rates, amountF + 1000 * j);
				double tax = incT.getIncomeTax();
				System.out.printf("%19.2f",tax);
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	public static void main(String[] args) {
		int[][] intervalsFor2011 = {{27050, 65550, 136750, 297350},
				{45200, 109250, 166500, 297350},
				{22600, 54625, 83250, 148675},
				{36250, 93650, 151650, 297350}};
		
		double[] ratesFor2011 = {0.15, 0.275, 0.305, 0.355, 0.391};
		
		int[][] intervalsFor2021 = {{8350, 33950, 82250, 171550, 372950},
				{16700, 67900, 137050, 208850, 273950},
				{8350, 33950, 68525, 104425, 186475},
				{11950, 45500, 117450, 190200, 372950}};
		
		double[] ratesFor2021 = {0.1, 0.15, 0.25, 0.28, 0.33, 0.35};
		
		int option = 0;
		boolean keepGoing = true;
		Scanner scan = new Scanner(System.in);
		do {
			try {

			menu();
			option = scan.nextInt();
			int fun = 0;
			
			while(option != 1 && option != 2 && option != 0) {
				if(fun >= 3) System.out.println("\nYeah, I think we are going to stay a while :D");
				System.out.println("Sorry, you typed an invalid option, try again");
				menu();
				fun++;
				option = scan.nextInt();
			};
			
			switch (option){
			case 0:
				keepGoing = false;
				break;
			case 1:
				opt1();
				int status = scan.nextInt();
				
				while(status <0 || status > 3){
					System.out.println("\nInvalid status, try again");
					opt1();
					status = scan.nextInt();
				}
				
				System.out.print("Enter the Taxable Income: ");
				int TaxableIncome = scan.nextInt();
				
				IncomeTax incTax = new IncomeTax(status, intervalsFor2021, ratesFor2021, TaxableIncome);
				double x = incTax.getIncomeTax();
				System.out.printf("Tax is: $%.2f\n\n",x);
				break;
				
			case 2:
				System.out.print("Enter the amount From: $");
				int amountF = scan.nextInt();
				System.out.print("Enter the amount To: $");
				int amountT = scan.nextInt();
				System.out.println("2011 tax tables for taxable income from $" + amountF + " to $" + amountT);
				printer();
				opt2(amountF, amountT, intervalsFor2011, ratesFor2011);
				
			    System.out.println("2021 tax tables for taxable income from $" + amountF + " to $" + amountT);
			    printer();
			    opt2(amountF, amountT, intervalsFor2021, ratesFor2021);
			    System.out.println("\n");
			    break;
			    
			default:
				System.out.println("Something went wrong, call the doctor");
				break;
			}
			
		//Trying exception from todays class :D
		}catch(InputMismatchException ex) {
			System.out.println("\nPlease type only numbers, try again\n");
			scan.nextLine(); // discard input 
		}
		}while(keepGoing);
		
		System.out.println("\n*************************");
		System.out.println("*     BYE BYE! (^-^)    *");
		System.out.println("*************************");
		scan.close();
		
	}
	
}