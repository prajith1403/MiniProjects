import java.util.Random;
/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
public class CoinTossSimulator 
{
	/**
	 * The possible outcomes of tossing a single are mapped to the numbers 0 & 1
	 * [Heads] = 0
	 * [Tails] = 1
	 */
	private final int HEADS = 0;
	private final int TAILS = 1;
	
	//Initializing a variable to store the count of the trails done
	private int trialCount;
	
	//Creating new objects (each for one coin) of the Random Class
	Random firstCoin = new Random();
	Random secondCoin = new Random();
	
	//Initializing the upper Bound for the Coin Toss simulation
	/**
	 * 2 is the (exclusive) upper limit for the nextInt method, 
	 * where the output is a randomly generated integer between 
	 * 0 (starting integer) and 1 (inclusive limit)
	 */
	private final int RANDOM_NUMBER_BOUND = 2; 
	
	//Declaring two integers to store the outcomes of the two coin tosses
	private int firstCoinToss;
	private int secondCoinToss;
	
	//Declaring the Integers to store the Count of each outcome of the coin tosses
	private int twoHeads;
	private int twoTails;
	private int headsTails;
	
	/**
	 *Creates a coin toss simulator with no trials done yet.
	 */
	public CoinTossSimulator() 
	{
		trialCount = 0;
		twoHeads = 0;
		twoTails = 0;
		headsTails = 0;
	}

	/**
	 *	Runs the simulation for numTrials more trials. Multiple calls to this method
	 *	without a reset() between them *add* these trials to the current simulation.
     * 
	 *	@param numTrials  number of trials to for simulation; must be >= 1
     */
	public void run(int numTrials) 
	{
		for (int i = 0; i < numTrials; i++)
		{
			firstCoinToss = firstCoin.nextInt(RANDOM_NUMBER_BOUND);
			secondCoinToss = secondCoin.nextInt(RANDOM_NUMBER_BOUND);
			
			if (firstCoinToss == HEADS && secondCoinToss == HEADS)
			{
				twoHeads++;
			}
			else if (firstCoinToss == TAILS && secondCoinToss == TAILS)
			{
				twoTails++;
			}
			else if ((firstCoinToss == HEADS && secondCoinToss == TAILS) || (firstCoinToss == TAILS && secondCoinToss == HEADS))
			{
				headsTails++;
			}
			
			trialCount++;
		}
	}

	/**
	 * Get number of trials performed since last reset.
	 */
	public int getNumTrials() 
	{
       return trialCount;
	}


	/**
	 *	Get number of trials that came up two heads since last reset.
	 */
	public int getTwoHeads() 
	{
       return twoHeads;
	}


	/**
	 *	Get number of trials that came up two tails since last reset.
	 */  
	public int getTwoTails() 
	{
       return twoTails;
	}


	/**
	 *	Get number of trials that came up one head and one tail since last reset.
	 */
	public int getHeadTails() 
	{
       return headsTails;
	}


	/**
	 *	Resets the simulation, so that subsequent runs start from 0 trials done.
	 */
	public void reset() 
	{
		trialCount = 0;
		twoHeads = 0;
		twoTails = 0;
		headsTails = 0;
	}
}
