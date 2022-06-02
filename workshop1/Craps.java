
package workshop1;
import java.util.Random;

public class Craps{
	
	public static int rollDices() {
		Random rand = new Random();
		
		int dice1 = rand.nextInt(6) + 1;
		int dice2 = rand.nextInt(6)+1;
		int sum = dice1+dice2;
		
		System.out.println("You rolled "+dice1+" + "+dice2+" = "+sum);
		return sum;
	}
	
	public static void main(String[] args) {
		
		int x = rollDices();
		
		if (x == 2 || x == 3 || x == 12) {
			System.out.println("Craps, Better Luck Next Time, you lose");
			
		}else if (x == 7 || x == 11){
			System.out.println("Congratulations, you win");
			
		}else {
			int temp = x;
			System.out.println("Point is (established) set to "+x);
			
			do {
				x = rollDices();
			}while(x != 7 && x != temp);
			
			if (x == 7) {
				System.out.println("Craps, Better Luck Next Time, you lose");
			}
			else if (x == temp) {
				System.out.println("Congratulations, you win");
			}
			else {
				System.out.println("Something went wrong!");
			}


		}
		
	}
	
}
