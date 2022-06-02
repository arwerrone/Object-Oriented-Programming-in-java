package workshop8;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Tester {
	
    private static int twoT = 2000;
    private static double[][] mat1 = new double[twoT][twoT];;
    private static double[][] mat2 = new double[twoT][twoT];;

    public static void main(String[] args) throws InterruptedException {
    	
    	long startTime, endTime ;
    	int option = 0;
    	Scanner sc = new Scanner(System.in);
    	boolean keepGoing = true;
    	
	    	do {
	    	   	try {
	    		System.out.println("Please select an option");
		    	System.out.println("1 - Matrix Addition");
		    	System.out.println("2 - Reverse Thread");
		    	System.out.print("My option is: ");
		    	option = sc.nextInt();
		    	System.out.print("\n");
		    	
		    	if (option == 1) {
		    		
		    		startTime = System.currentTimeMillis();
		            for(int i = 0; i < mat1.length; i++) {
		                for(int j = 0; j < mat2[i].length; j++) {
		                    mat1[i][j] = Math.random();
		                    mat2[i][j] = Math.random();
		                }
		            }

		            Thread th1 = new Thread(String.valueOf(MatrixAddition.parallelAddMatrix(mat1, mat2)));
		            Thread th2 = new Thread(String.valueOf(MatrixAddition.parallelAddMatrix(mat1, mat2)));
		            Thread th3 = new Thread(String.valueOf(MatrixAddition.parallelAddMatrix(mat1, mat2)));
		            Thread th4 = new Thread(String.valueOf(MatrixAddition.parallelAddMatrix(mat1, mat2)));
		            th1.start();
		            th2.start();
		            th3.start();
		            th4.start();
		            th1.join();
		            th2.join();
		            th3.join();
		            th4.join();
		            endTime = System.currentTimeMillis();
		            System.out.println("It passed: " + (endTime - startTime) + " milliseconds using parallelAddMatrix");
		            
		            startTime = System.currentTimeMillis();
		            MatrixAddition.sequentialAddMatrix(mat1, mat2);
		            endTime = System.currentTimeMillis();
		            System.out.println("It passed: " + (endTime - startTime) + " milliseconds using sequentialAddMatrix");
		            keepGoing = false;
		    		
		    	} else if (option == 2) {
		    		keepGoing = false;
		            ReverseThread thread = new ReverseThread(1);
		            thread.start();
		    	}
		    	else {
		    		System.out.println("Please type a valid option");
		    		keepGoing = true;
		    	}
		    	
		   		
		    	}catch(InputMismatchException err) {
		    		System.out.println("Please type a number\n");
		    		sc.next();
		    	}catch(Exception e) {
		    		e.printStackTrace();
		    	}
	    		
	    	} while(keepGoing);

        sc.close();
        
    }
}