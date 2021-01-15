import java.util.Scanner;
import java.util.ArrayList;

/*
  class BulgarianSolitaireSimulator - This class contains the main method.
  Depending on the command-line arguments the class will either generate 
  the Solitaire Board configuration randomly, or will take in as a user input.
  Also depending on the command-line arguments, the class will run the steps
  continuously until the end result is reached or will go step by step, 
  waiting for the user to press return.
*/

public class BulgarianSolitaireSimulator 
{

	public static void main(String[] args) 
	{
     
		boolean singleStep = false;
		boolean userConfig = false;
		
		Scanner in = new Scanner(System.in);
		
		//Integer to store the number of Solitaire Steps
		int solitaireSteps = 0;
			
		SolitaireBoard BulgarianSolitaireBoard;

		for (int i = 0; i < args.length; i++) 
		{
			if (args[i].equals("-u")) 
			{
				userConfig = true;
			}
			else if (args[i].equals("-s")) 
			{
				singleStep = true;
			}
		}

		if (userConfig == true)
		{
			//Creates a new Solitaire Board with the user-entered Integer ArrayList
			BulgarianSolitaireBoard = new SolitaireBoard(userConfigInput(in));
			
			System.out.println("Initial configuration: " + BulgarianSolitaireBoard.configString());
		}
		else
		{
			//Randomly generates the Solitaire Board configuration
			BulgarianSolitaireBoard = new SolitaireBoard();
			
			System.out.println("Initial configuration: " + BulgarianSolitaireBoard.configString());
			
		}
		
		//Plays one round of Solitaire, while checking to see if the final configuration is achieved
		while(!BulgarianSolitaireBoard.isDone())
		{
			//Calls the playRound method to play one round
			BulgarianSolitaireBoard.playRound();
			
			solitaireSteps++;
			
			System.out.println("[" + solitaireSteps + "] Current configuration: " + BulgarianSolitaireBoard.configString());
			
			//Checking if the command-line argument is set
			if (singleStep == true)
			{
				//Prompts the user to hit return after every round if the command-line argument is set
				promptReturn(in);
			}
		}
		
		System.out.println("Done!");
      
	}
	
	/**	Reads the user-entered String, converts the user-entered String input 
		into an Integer ArrayList and returns it
		
		@param Scanner in - Scanner object used for reading the User-entered String
		
	*/
	private static ArrayList<Integer> userConfigInput(Scanner in)
	{
		//boolean to keep a check if the user-entered input is valid or not
		boolean isValidInput = false;
		
		//ArrayList that stores the user-entered input
		ArrayList<Integer> boardInputReader = new ArrayList<Integer>();
		
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
		System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
		
		while(!isValidInput)
		{
			//boolean to keep a check if the user-entered input contains only integers or not
			boolean isValidInteger = true;
			
			//clearing the ArrayList every loop so that the same input is not stored multiple times
			boardInputReader.clear();
			
			System.out.println("Please enter a space-separated list of positive integers followed by newline:");
			
			//Reads the entire input until the new-line character is encountered
			String boardConfig = in.nextLine();
			
			//Splits the String input based by one or more whitespace(\s+) and stores in a String array 
			String[] boardConfigArray = boardConfig.split("\\s+");
			
			//Loop to convert the String array into an Integer ArrayList
			for (int i = 0; i < boardConfigArray.length; i++)
			{
				//Checks if the String contains an Integer
				if(stringIsNum(boardConfigArray[i]))
				{
					//Converts the String to int and adds to the ArrayList
					boardInputReader.add(Integer.parseInt(boardConfigArray[i]));
				}
				else
				{
					isValidInteger = false;
				}
			}
			
			//If input doesn't contain an integer, repeats the loop
			if (isValidInteger == false)
			{
				isValidInput = false;
				
				System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
				
			}
			else
			{
				//If input contains a zero, repeats the loop
				if(boardInputReader.contains(0))
				{
					isValidInput = false;
					
					System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
				}
				//If sum of input adds up to SolitairBoard.CARD_TOTAL, stops the loop
				else if (sumOfArrayList(boardInputReader))
				{
					isValidInput = true;
				}
				else
				{
					isValidInput = false;
				
					System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be " + SolitaireBoard.CARD_TOTAL);
				}
			}
		}
		
		return boardInputReader;
	}
	
	/**	Checks if the user-entered input has Integers
		
		@param String str - the user-entered initial Board Configuration
		
	*/
	private static boolean stringIsNum(String str)
	{
		if(!str.isEmpty())
		{
			//traverses the String character by character
			for (char c : str.toCharArray())
			{
				//checks if the character is a digit or not. This ensures that alphabets and decimals (double input) will be rejected
				if (!Character.isDigit(c))
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/** Helper method to check if the values of the Integer ArrayList
		adds up to 45
		
		@param ArrayList<Integer> arrList - this is the ArrayList entered by the user
		
	*/
	private static boolean sumOfArrayList(ArrayList<Integer> arrList)
	{
		int sum = 0;
		for (int i = 0; i < arrList.size(); i++)
		{
			sum = sum + arrList.get(i);
		}
		if (sum == SolitaireBoard.CARD_TOTAL)
		{
			return true;
		}
		return false;
	}
	
	/**	Helper method to prompt the User to hit return
		if the command-line argument has been set
		
		@param Scanner in - Scanner object used for reading the User-entered return
		
	*/
	private static void promptReturn(Scanner in)
	{
		System.out.print("<Type return to continue>");
		in.nextLine();
	}
  
}
