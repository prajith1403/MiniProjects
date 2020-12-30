import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Color;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * class CoinSimComponent
 * 
 * The CoinSimComponent class calls both the CoinTossSimulator class and the Bar class.
 * The CoinSimComponent class reads the user-entered value from the CoinSimViewer class and sends it to the CoinTossSimulator class.
 * The CoinSimComponent class then reads the outcomes of the CoinTossSimulator class and sends it to the Bar class.
 */
public class CoinSimComponent extends JComponent
{
	//Declaring the variables to store the outcomes of the Coin Toss trial via the CoinTossSimulator class
	private int numberOfTrials;
	private int headHeadTosses;
	private int headTailTosses;
	private int tailTailTosses;
	
	/**Default Constructor to initialize the variables to zero.
	 */
	public CoinSimComponent()
	{
		numberOfTrials = 0;
		headHeadTosses = 0;
		headTailTosses = 0;
		tailTailTosses = 0;
	}
	
	/**Constructor to calculate the outcomes of the the Coin Toss Trials
	 *
	 * @param trialCount - The number of Coin toss trials to be performed
	 */
	public CoinSimComponent(int trialCount)
	{
		numberOfTrials = trialCount;
		
		CoinTossSimulator coinToss = new CoinTossSimulator();
		coinToss.run(numberOfTrials);
		
		headHeadTosses = coinToss.getTwoHeads();
		headTailTosses = coinToss.getHeadTails();
		tailTailTosses = coinToss.getTwoTails();
	}
	
	/**Calculates all the necessary parameters to send to the Bar.java class
	 * 
	 * @param g - Graphics contextOfLabel
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		
		//Calculating the height and width of the frame from the Graphics context
		int heightOfFrame = getHeight();
		int widthOfFrame = getWidth();
		
		//Setting the Vertical Buffer (in pixel)
		final int VERTICAL_BUFFER = 50;
		
		//Setting the width of the Bar Graphs (in pixel)
		final int GRAPH_WIDTH = 100;
		
		//Calculating the width between each Bar Graphs and the Frame
		/* As seen from the illustration, the total width of the frame (denoted by the variable widthOfFrame)
		 * is equal to four times the width between each graph + three times the width of each graph.
		 * In other words, widthOfFrame = (4 * widthBetweenBars) + (3 * GRAPH_WIDTH)
		 * So, widthBetweenBars = (widthOfFrame - (3 * GRAPH_WIDTH)) / 4 */
		double widthBetweenBars = (widthOfFrame - (3 * GRAPH_WIDTH)) / 4.0; 
		
		//Calculating the percentage distribution of each of the coin toss outcomes
		double headHeadPercentage = (100 * ((double)headHeadTosses/numberOfTrials));
		double headTailPercentage = (100 * ((double)headTailTosses/numberOfTrials));
		double tailTailPercentage = (100 * ((double)tailTailTosses/numberOfTrials));
		
		//Rounding up the percentage to two decimal places
		headHeadPercentage = (Math.round(100.0 * headHeadPercentage) / 100.0);
		headTailPercentage = (Math.round(100.0 * headTailPercentage) / 100.0);
		tailTailPercentage = (Math.round(100.0 * tailTailPercentage) / 100.0);
		
		//Declaring the maximum percentage that a single Bar Graph can occupy
		final int MAX_PERCENTAGE = 100;
		
		//Creating the labels of each Bar Graphs
		String labelHeadHead = "Two Heads: " + headHeadTosses + "(" + headHeadPercentage + "%)";
		String labelHeadTail = "A Head and a Tail: " + headTailTosses + "(" + headTailPercentage + "%)";
		String labelTailTail = "Two Tails: " + tailTailTosses + "(" + tailTailPercentage + "%)";
		
		//Calculating the Height of the Labels (in pixels)
		Font fontOfLabel = g2.getFont();
		FontRenderContext contextOfLabel = g2.getFontRenderContext();
		
		Rectangle2D boundsOfLabelHeadHead = fontOfLabel.getStringBounds(labelHeadHead, contextOfLabel);
		double heightOfLabelHeadHead = boundsOfLabelHeadHead.getHeight();
		
		Rectangle2D boundsOfLabelHeadTail = fontOfLabel.getStringBounds(labelHeadTail, contextOfLabel);
		double heightOfLabelHeadTail = boundsOfLabelHeadTail.getHeight();
		
		Rectangle2D boundsOfLabelTailTail = fontOfLabel.getStringBounds(labelTailTail, contextOfLabel);
		double heightOfLabelTailTail = boundsOfLabelTailTail.getHeight();
		
		//Calculating the height of the Bar Graphs to be drawn (in pixels)
		/* The size of the output window, or the frame is denoted by the variable heightOfFrame.
		 * We are leaving buffers at the top and bottom of the frame, both denoted by the variable VERTICAL_BUFFER.
		 * We are also leaving the space for the Label to be put in, denoted by the variables heightOfLabelHeadHead, heightOfLabelHeadTail, heightOfLabelTailTail
		 * Thus, the maximum height available for the Bar Graphs is (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabel).
		 * If the Maximum Percentage available (MAX_PERCENTAGE) corresponds to (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabel)
		 * Then the actual Percentages will be calculated in the following manner:
		 * MAX_PERCENTAGE -> (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabel)
		 * headHeadPercentage -> headHeadPercentage * (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabelHeadHead) / MAX_PERCENTAGE
		 * headTailPercentage -> headTailPercentage * (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabelHeadTail) / MAX_PERCENTAGE
		 * tailTailPercentage -> tailTailPercentage * (heightOfFrame - 2*VERTICAL_BUFFER - heightOfLabelTailTail) / MAX_PERCENTAGE */
		int heightHeadHead = (int)Math.round(headHeadPercentage * ((heightOfFrame - (2*VERTICAL_BUFFER) - heightOfLabelHeadHead) / MAX_PERCENTAGE));
		int heightHeadTail = (int)Math.round(headTailPercentage * ((heightOfFrame - (2*VERTICAL_BUFFER) - heightOfLabelHeadTail) / MAX_PERCENTAGE));
		int heightTailTail = (int)Math.round(tailTailPercentage * ((heightOfFrame - (2*VERTICAL_BUFFER) - heightOfLabelTailTail) / MAX_PERCENTAGE));
		
		//Calculating the Scale of each Bar Graph
		/* Scale represents the number of Pixels per Application Unit. Here, 1 application unit is equal to one outcome of the two-coin tosses
		 * The variable headHeadTosses represents the total application units for a Head-Head coin toss outcome.
		 * Similarly, the variables headTailTosses and tailTailTosses represent the total application units for a Head-Tail outcome and Tail-Tail outcome respectively.
		 * For headHeadTosses application units, the total number of pixels is the height of the Head-Head bar graph i.e. the variable heightHeadHead.
		 * Therefore, for one application unit, the total number of pixels will be heightHeadHead / headHeadTosses and so on.*/
		double scaleHeadHead = (double) heightHeadHead / headHeadTosses;
		double scaleHeadTail = (double) heightHeadTail / headTailTosses;
		double scaleTailTail = (double) heightTailTail / tailTailTosses;
		
		//Initializing the colors of each bar graphs
		Color HEAD_HEAD_COLOR = Color.RED;
		Color HEAD_TAIL_COLOR = Color.GREEN;
		Color TAIL_TAIL_COLOR = Color.BLUE;
		
		//Calculating the Y-Coordinate of the labels
		/* The total height of the frame is denoted by the variable heightOfFrame.
		 * We are leaving a buffer at the bottom of the frame, denoted by the variable VERTICAL_BUFFER.
		 * To ensure the labels don't overlay on the graphs, we will be taking into consideration the height of the labels (in pixels)
		 * Thus, the Y-coordinates of the label will be equal to heightOfFrame - VERTICAL_BUFFER + heightOfLabel.*/
		int yOfLabelHeadHead = heightOfFrame - VERTICAL_BUFFER + (int)heightOfLabelHeadHead;
		int yOfLabelHeadTail = heightOfFrame - VERTICAL_BUFFER + (int)heightOfLabelHeadTail;
		int yOfLabelTailTail = heightOfFrame - VERTICAL_BUFFER + (int)heightOfLabelTailTail;
		
		//Calculating the X-Coordinates of the three Bar Graphs
		/* As per the illustration the X-coordinate of the first bar graph will be equal to the width between the frame and the first bar */
		int xOfHeadHead = (int) Math.round(widthBetweenBars);
		
		/* As per the illustration the X-coordinate of the second bar graph will be the sum of the X-coordinate of the first bar graph,
		 * the width of the first bar graph, and the width between the first and second bar graphs. */
		int xOfHeadTail = (int) Math.round(xOfHeadHead + GRAPH_WIDTH + widthBetweenBars);
		
		/* As per the illustration the X-coordinate of the second bar graph will be the sum of the X-coordinate of the second bar graph,
		 * the width of the second bar graph, and the width between the second and third bar graphs. */
		int xOfTailTail = (int) Math.round(xOfHeadTail + GRAPH_WIDTH + widthBetweenBars);
		
		//Initializing the Bar Constructor public Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label) 
		Bar headHeadBar = new Bar(yOfLabelHeadHead, xOfHeadHead, GRAPH_WIDTH, headHeadTosses, scaleHeadHead, HEAD_HEAD_COLOR, labelHeadHead);
		Bar headTailBar = new Bar(yOfLabelHeadTail, xOfHeadTail, GRAPH_WIDTH, headTailTosses, scaleHeadTail, HEAD_TAIL_COLOR, labelHeadTail);
		Bar tailTailBar = new Bar(yOfLabelTailTail, xOfTailTail, GRAPH_WIDTH, tailTailTosses, scaleTailTail, TAIL_TAIL_COLOR, labelTailTail);
		
		//Drawing the three Bars
		headHeadBar.draw(g2);
		headTailBar.draw(g2);
		tailTailBar.draw(g2);
	}
}