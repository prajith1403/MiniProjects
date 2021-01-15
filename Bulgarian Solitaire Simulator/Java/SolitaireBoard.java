import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard 
{   
	public static final int NUM_FINAL_PILES = 9;
	// number of piles in a final configuration
	// (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
	public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
	// Bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
	// see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
	// the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
	
	// Note to students: you may not use an ArrayList -- see assgt description for details.
	
	
	/**
		Representation invariant:
	
		-> cardStack array stores the number of piles of cards
		-> The size of cardStack is CARD_TOTAL + 1
		-> The capacity of cardStack is stackCapacity
		-> 0 < stackCapacity <= CARD_TOTAL
	
	*/
	private int[] cardStack = new int[CARD_TOTAL + 1];
	private int stackCapacity = 0;
  
 
	/**
		Creates a solitaire board with the configuration specified in piles.
		piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
		PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
	*/
	public SolitaireBoard(ArrayList<Integer> piles) 
	{
		//Converts the ArrayList to int array cardStack, while incrementing the capacity
		for (int i = 0; i < piles.size(); i++)
		{
			cardStack[i] = piles.get(i);
			stackCapacity++;
		}
		
		assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
	}
 
   
	/**
		Creates a solitaire board with a random initial configuration.
	*/
	public SolitaireBoard() 
	{
		Random pileGenerator = new Random();
		boolean cardTotalFlag = true;
		int sumOfPiles = 0;
		
		//Randomly generates the piles of cards
        while (cardTotalFlag) 
        {
			//checks if the sumOfPiles is equal to the final card total or not
            if (sumOfPiles < CARD_TOTAL) 
            {
				//if less than the final total, randomly generates an int with CARD_TOTAL - sumOfPiles as the upper bound (inclusive)
            	addCards(pileGenerator.nextInt(CARD_TOTAL - sumOfPiles + 1));
            } 
            else 
            {
            	cardTotalFlag = false;
            }

            sumOfPiles = 0;
            
			//loop to calculate the sumOfPiles
            for (int i = 0; i < stackCapacity; i++) 
            {
            	sumOfPiles += cardStack[i];
            }
        }
        
        assert isValidSolitaireBoard();
        
	}
	
	
	/**
		Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
		of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
		The old piles that are left will be in the same relative order as before, 
		and the new pile will be at the end.
	*/
	public void playRound()
	{
		//Takes one card from each pile by decrementing each pile
		for (int i = 0; i < stackCapacity; i++)
	    {
	    	cardStack[i]--;
	    }
		
		//Adding the decremented values to the end of the array, in a new pile
		cardStack[stackCapacity] = stackCapacity;
		stackCapacity++;
		
		assert isValidSolitaireBoard();
		
		//Ensures there are no zeros in the array
		zeroRemoval();
		
	}
	
	/**
		Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
		piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
	*/
	
	public boolean isDone()
	{
		assert isValidSolitaireBoard();
		
		//creating a copy of the cardStack array so that the original is not altered
		int[] cardStackCopy = Arrays.copyOf(cardStack, stackCapacity);
		
		//creating a new array which will store the final configuration in ascending order
		int[] finalPile = new int[NUM_FINAL_PILES];
		
		for(int i = 1; i <= NUM_FINAL_PILES; i++)
		{
			finalPile[i-1] = i;
		}
		
		//sorting the copy of the cardStack array so the piles are in ascending order
		Arrays.sort(cardStackCopy);
		
		//comparing the two arrays. return true if the arrays are same
		if (Arrays.equals(cardStackCopy, finalPile) && stackCapacity == NUM_FINAL_PILES)
		{
			return true;
		}
		
		return false;
	}
	
	
	/**
		Returns current board configuration as a string with the format of
		a space-separated list of numbers with no leading or trailing spaces.
		The numbers represent the number of cards in each non-empty pile.
	*/
	public String configString() 
	{
		String cardStackString = String.valueOf(cardStack[0]);
		
		for (int i = 1; i < stackCapacity; i++)
		{
			cardStackString = cardStackString + " " + cardStack[i];
		}
		
		return cardStackString;
	}
	
	
	/**
		Returns true iff the solitaire board data is in a valid state
		(See representation invariant comment for more details.)
	*/
	private boolean isValidSolitaireBoard() 
	{
	      int sumOfPiles = 0;
	      
	      for (int i = 0; i < stackCapacity; i++)
	      {
	    	  sumOfPiles += cardStack[i];
	      }
	      
	      if(sumOfPiles == CARD_TOTAL)
	      {
	    	  return true;
	      }
	      
	      return false;

	}
	
	
	//Private Helper Methods
	
	/**
		Adds the randomly generated pile to the cardStack array and
		increments the stackCapacity to reflect the capacity of the array
		
		@param cardNumber - the randomly generated pile, generated by the nextInt method of the Random object
	*/
	private void addCards(int cardNumber)
	{
		//Ignoring card piles which have a value of zero
		if(cardNumber > 0)
        {
			cardStack[stackCapacity] = cardNumber;
	    	stackCapacity++;
        } 
	}
	
	/**
		Removes zero from the cardStack array and shifts the elements to the left
		This ensures the order of the piles are not changed
	*/
	private void zeroRemoval()
	{
		//keeps a count of the non-zero elements in the cardStack array, so that the new stackCapacity will be this nonZeroCount
		int nonZeroCount = 0;
		
		//this loop will shift the non-zero elements to the left in the same order
		for (int i = 0; i < stackCapacity; i++)
		{
			if (cardStack[i] != 0)
			{
				cardStack[nonZeroCount] = cardStack[i];
				nonZeroCount++;
			}
		}
		
		//updating the stackCapacity now that the zeros are removed
		stackCapacity = nonZeroCount;
		
		//asserting the cardStack array is still valid
		assert isValidSolitaireBoard();
	}
	
}
