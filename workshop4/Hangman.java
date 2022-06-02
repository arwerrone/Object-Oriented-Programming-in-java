package workshop4;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Hangman {
	
	static int miss = 0;
	static Scanner input = new Scanner(System.in);
	
	public static String[] newLib(FileInputStream hangman) {
		
		Scanner rFile = new Scanner(hangman);
		List<String> lines = new ArrayList<String>();
		
		while (rFile.hasNextLine()) {
			lines.add(rFile.nextLine());
		}
		
		String [] library = lines.toArray(new String[0]);
		rFile.close();
		return library;
	}
	
	
	public static void Play(String word) throws Exception {
		String usedLetters = "";
		String hidden  = "";
        for (int i = 0; i < word.length(); i++) {
        	hidden  += "*";
        }
        
        while(hidden.indexOf("*") != -1) {
        
        	System.out.print("(Guess) Enter a letter in word "+ hidden + " > ");
        	//Character letter = '\0';
        	String letter = input.next();

        	for (int i = 0; i < word.length(); i++) {
        		
        		if (hidden.charAt(i) == letter.charAt(0))
        			System.out.println("        " + letter + " is already in the word" );

        	}
        	
        	if (word.indexOf(letter) != -1) {
        		
        		String temp = hidden;
        		hidden = "";
        		
        		for(int i = 0; i < word.length(); i++) {
        			
        			if(word.charAt(i) == letter.charAt(0))
        				hidden += word.charAt(i);
        			else
        				hidden += temp.charAt(i);
        		}
        	}else {
        		if (usedLetters.contains(letter)) {
        			System.out.println("        You have already tried " + letter + ", try a new letter");
        		}else {
            		System.out.println("        " + letter + " is not in the word");
            		miss++;
        		}
        		usedLetters += letter;
        	}
        }
	}
	
	public static void newWord(FileOutputStream output) throws IOException {
		
		System.out.print("Enter a new word to be added in the memory> ");

		String newWord = input.nextLine();
		newWord = input.nextLine();
		
		String toAdd = newWord + "\n";
		byte[] myBytes = toAdd.getBytes();
		output.write(myBytes);

	}
	
	public static void main(String[] args) throws Exception {
		
		try {
			Boolean keepGoing = true;
			char yn;
			do {
			System.getProperty("user.dir");
			//NOTE: this path might not work in your computer, so please change it if it does not work.
			FileInputStream hangman = new FileInputStream(System.getProperty("user.dir")+"\\src\\workshop4\\hangman.txt");
			FileOutputStream output = new FileOutputStream(System.getProperty("user.dir")+"\\src\\workshop4\\hangman.txt", true);
			String Library[] = newLib(hangman);
			
	        Random rand = new Random();
			String word = Library[rand.nextInt(Library.length)];

			//System.out.println(word);
			Play(word);

			System.out.println("Congratulations!");
			String timeMiss = ". You did not miss any time, impressive!!";
			if (miss == 1) {
				timeMiss = ". You missed 1 time";
			}else if (miss > 1) {
				timeMiss = ". You missed "+miss+" times";
			}
			System.out.println("The word is "+ word + timeMiss);
			
			newWord(output);
			
			do {
				System.out.print("Do you want to guess another word? Enter y or n> ");
				String yesNo = input.nextLine();
				yn = yesNo.charAt(0);
			}while(yn != 'Y' && yn != 'y' && yn != 'N' && yn != 'n');
			
			if (yn == 'N' || yn == 'n') {
				keepGoing = false;
			}else if (yn == 'Y' || yn == 'y') {
				keepGoing = true;
				miss = 0;
			}else {
				System.out.println("SOMETHING WENT WRONG OH NO");
			}
			
			}while(keepGoing);
			
			System.out.println("\n*************************");
			System.out.println("*     BYE BYE! (^-^)    *");
			System.out.println("*************************");
			input.close();
		
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
}