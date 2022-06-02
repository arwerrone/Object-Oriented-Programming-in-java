package workshop3;

import java.util.Scanner;

public class TestClass{
	
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		double x, y, z;
		String colour;
		Boolean filled;

		try {
			System.out.print("Enter the first side of the Triangle: ");
			x = input.nextDouble();
			input.nextLine();	//clear buffer
			System.out.print("Enter the second side of the Triangle: ");
			y = input.nextDouble();
			input.nextLine();	//clear buffer
			System.out.print("Enter the third side of the Triangle: ");
			z = input.nextDouble();
			input.nextLine();	//clear buffer
			System.out.print("Colour of the Triangle: ");
			colour = input.nextLine();
			System.out.print("Is the Triangle filled? (true/false): ");
			filled = input.nextBoolean();
			input.nextLine();	//clear buffer
			
			Triangle t1 = new Triangle(x, y, z);
			t1.setColor(colour);
			t1.setFilled(filled);
			
			System.out.println("\n Workshop 3 JAC444 Triangle :D \n");
			System.out.print(t1.toString());
			
//			Triangle t2 = new Triangle(5,5,5);
//			t2.setColor("Green");
//			t2.setFilled(false);
//			
//			System.out.println("\n Workshop 3 JAC444 Triangle :D \n");
//			System.out.print(t2.toString());
//			
//			Triangle t3 = new Triangle(9,1,0);
//			t3.setColor("Orange");
//			t3.setFilled(true);
//			
//			System.out.println("\n Workshop 3 JAC444 Triangle :D \n");
//			System.out.print(t3.toString());
			
			}catch(Triangle.TriangleException ex) {
				System.out.println("\n Workshop 3 JAC444 NOT Triangle :P \n");
				System.out.print(ex.toExString());
			}
			
		input.close();
		

	}
	
}