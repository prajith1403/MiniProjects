import java.util.Scanner;
import javax.swing.JFrame;

/**
 * class CoinSimViewer
 * 
 * This class is contains the main method. The class will prompt for the user's input.
 * The input entered by the user will then be checked to make sure the user enters only positive integers.
 * Once the input is entered, the CoinSimViewer class calls the CoinSimComponent method with the user-entered value.
 * This class also imports the JFrame class to create the frame which will display the results of the simulation. 
 */
public class CoinSimViewer
{
	public static void main(String[] args)
	{
		//Declaring a new object of the Scanner class to read the user input
		Scanner in = new Scanner(System.in);
		
		//Initializing a new boolean variable to make sure the user enters a number which is greater than 0
		boolean validInput = false;
		
		//Initializing  a new integer to store the user-enter value of number of trials
		int numberOfTrials = 0;
		
		//Creating a while loop that repeats itself as long as the boolean validInput is false. Once the user enters a positive integer, the boolean is set to true.
		while(!validInput)
		{
			System.out.print("Enter number of trials: ");
			numberOfTrials = in.nextInt();
			
			//Checking if the user-entered input is greater than 0
			if (numberOfTrials > 0)
			{
				validInput = true;
			}
			else
			{
				System.out.println("ERROR: Number entered must be greater than 0.");
			}
		}
		
		//Creating a new object of the type JFrame
		JFrame frame = new JFrame();
		
		//Setting the size of the frame
		frame.setSize(800,500);
		
		//Setting the title of the frame
		frame.setTitle("CoinSim");
		
		//When the user closes the frame, the program should automatically exit
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating a new object of the type CoinSimComponent
		CoinSimComponent coinSim = new CoinSimComponent(numberOfTrials);
		
		//Adding the object to the frame
		frame.add(coinSim);
		
		//Making the frame visible
		frame.setVisible(true);
		
	}
}