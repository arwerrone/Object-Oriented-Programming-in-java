package workshop6;

import java.util.Scanner;

public class task2Lambda {

	public static final ArrayProcessor maximum = (arr) -> {
		double max = arr[0];
		
        for(int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        
        return max;
	};
	
	public static final ArrayProcessor minimum = (arr) -> {
		double min = arr[0];
		
        for(int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        
        return min;
	};
	
	public static final ArrayProcessor sum = (arr) ->{
		double sum = 0;
		
		for(int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return sum;
	};

	
	public static final ArrayProcessor avg = (arr) ->{
		double sm = sum.apply(arr);
		double avg = sm/arr.length;
		
		return avg;
	};

	public static ArrayProcessor counter(double value) {
        ArrayProcessor cnt = (arr) -> {
            int count = 0;
            
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == value) {
                    count++;
                }
            }
            
            return count;
        };
        
        return cnt;
    }
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Hello there!");
		System.out.print("How many indexes you want in your array: ");
		int indexes = sc.nextInt();
		double[] arr = new double[indexes];
		
		for (int i = 0; i < indexes; i++) {
			System.out.print("Enter value for index "+ i +": ");
			arr[i] = sc.nextDouble();
		}
		
		System.out.print("Your array of "+indexes+" has the current numbers:");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(" "+arr[i]);
		}
		
		System.out.println("\n");
		System.out.println("The maximum value is: " + maximum.apply(arr));
		System.out.println("The minimum value is: " + minimum.apply(arr));
        System.out.println("The sum value is: " + sum.apply(arr));
        System.out.println("The average value is: " + avg.apply(arr));
        
        System.out.print("\nNow, tell me, which number you want to see how many occurance it has in the array: ");
        double val = sc.nextDouble();
        System.out.println("The Number of occurrences of "+val+" is: " + counter(val).apply(arr));
        
        sc.close();
        
	}
	
	
	
}
