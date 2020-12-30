/**
 * class CoinTossSimulatorTester
 * 
 * This class is used for testing the CoinTossSimulator.java class. It contains the main method.
 * This class will call the CoinTossSimulator Constructor and store the outcomes of the trials. It will then display the outcomes.
 * In this class, we will be hard-coding three trials, namely:
 * 1) First initial trial where we will be hard-coding a certain number of trails
 * 2) Second trial where we will be hard-coding a second number of trials without calling the reset method (to show that the numbers add up)
 * 3) Third trial where we will be calling the reset method first and then hard-coding a third number
 */
public class CoinTossSimulatorTester 
{
	public static void main(String[] args) 
	{
		//Initializing the trail count for the three trials
		final int FIRST_NUMBER_OF_TRIALS = 10;
		final int SECOND_NUMBER_OF_TRIALS = 25;
		final int THIRD_NUMBER_OF_TRIALS = 50;
		
		//Declaring variables to store the outcomes of each coin toss and the number of trials to be done
		int numberOfTwoHeadsTosses;
		int numberOfTwoTailsTosses;
		int numberOfHeadsTailsTosses;
		int numberOfTrials;
		
		//Initializing a boolean variable that checks if the sum of numberOfTwoHeadsTosses, numberOfTwoTailsTosses and numberOfHeadsTailsTosses is adding up to numberOfTrials
		boolean tossCountMatch = false;
		
		//Creating a new object twoCointToss of the type CoinTossSimulator
		CoinTossSimulator twoCoinToss = new CoinTossSimulator();
		
		//Running the first trial
		twoCoinToss.run(FIRST_NUMBER_OF_TRIALS);
		
		//Getting the outcome of the First Coin Toss Trial
		numberOfTrials = twoCoinToss.getNumTrials();
		numberOfTwoHeadsTosses = twoCoinToss.getTwoHeads();
		numberOfTwoTailsTosses = twoCoinToss.getTwoTails();
		numberOfHeadsTailsTosses = twoCoinToss.getHeadTails();
		
		//Checking if the sum of numberOfTwoHeadsTosses, numberOfTwoTailsTosses and numberOfHeadsTailsTosses is adding up to numberOfTrials
		if (numberOfTrials == (numberOfTwoHeadsTosses + numberOfTwoTailsTosses + numberOfHeadsTailsTosses))
		{
			tossCountMatch = true;
		}
		
		System.out.println("Number of trials [exp:" + FIRST_NUMBER_OF_TRIALS + "]: " + numberOfTrials);
		System.out.println("Two-head tosses: " + numberOfTwoHeadsTosses);
		System.out.println("Two-tail tosses: " + numberOfTwoTailsTosses);
		System.out.println("One-head one-tail tosses: " + numberOfHeadsTailsTosses);
		System.out.println("Tosses add up correctly? " + tossCountMatch);
		System.out.println();
		
		//Running the second trial without calling the Reset method
		twoCoinToss.run(SECOND_NUMBER_OF_TRIALS);
		
		//Getting the outcome of the Second Coin Toss Trial
		numberOfTrials = twoCoinToss.getNumTrials();
		numberOfTwoHeadsTosses = twoCoinToss.getTwoHeads();
		numberOfTwoTailsTosses = twoCoinToss.getTwoTails();
		numberOfHeadsTailsTosses = twoCoinToss.getHeadTails();
		
		//Checking if the sum of numberOfTwoHeadsTosses, numberOfTwoTailsTosses and numberOfHeadsTailsTosses is adding up to numberOfTrials
		if (numberOfTrials == (numberOfTwoHeadsTosses + numberOfTwoTailsTosses + numberOfHeadsTailsTosses))
		{
			tossCountMatch = true;
		}
		
		System.out.println("Number of trials [exp:" + SECOND_NUMBER_OF_TRIALS + "]: " + numberOfTrials); //It is noticed that the Expected Nuber of Trials will not match the actual number
		System.out.println("Two-head tosses: " + numberOfTwoHeadsTosses);
		System.out.println("Two-tail tosses: " + numberOfTwoTailsTosses);
		System.out.println("One-head one-tail tosses: " + numberOfHeadsTailsTosses);
		System.out.println("Tosses add up correctly? " + tossCountMatch);
		System.out.println();
		
		//Calling the Reset method
		twoCoinToss.reset();
		
		//Running the third trial after calling the Reset method
		twoCoinToss.run(THIRD_NUMBER_OF_TRIALS);
		
		//Getting the outcome of the Third Coin Toss Trial
		numberOfTrials = twoCoinToss.getNumTrials();
		numberOfTwoHeadsTosses = twoCoinToss.getTwoHeads();
		numberOfTwoTailsTosses = twoCoinToss.getTwoTails();
		numberOfHeadsTailsTosses = twoCoinToss.getHeadTails();
		System.out.println();
		
		//Checking if the sum of numberOfTwoHeadsTosses, numberOfTwoTailsTosses and numberOfHeadsTailsTosses is adding up to numberOfTrials
		if (numberOfTrials == (numberOfTwoHeadsTosses + numberOfTwoTailsTosses + numberOfHeadsTailsTosses))
		{
			tossCountMatch = true;
		}
		
		System.out.println("Number of trials [exp:"+THIRD_NUMBER_OF_TRIALS+"]: "+numberOfTrials);
		System.out.println("Two-head tosses: "+numberOfTwoHeadsTosses);
		System.out.println("Two-tail tosses: "+numberOfTwoTailsTosses);
		System.out.println("One-head one-tail tosses: "+numberOfHeadsTailsTosses);
		System.out.println("Tosses add up correctly? "+tossCountMatch);
		System.out.println();
		
		//Calling the reset method for the final time
		twoCoinToss.reset();
		
	}
}