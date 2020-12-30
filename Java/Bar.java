import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar 
{
	//Declaring the variables to store the x and y coordinates of the Bar
	private int xCoordinateOfBar;
	private int yCoordinateOfBar;
	
	//Declaring a variable to store the width of the bar (in pixels)
	private int widthOfBar;
	
	//Declaring a variable to store the height of Bar (in Application Units)
	private int barHeightAU; 
	
	//Declaring a variable to store the Label
	private String labelOfBar;
	
	//Declaring the variables to store the x and y coordinates of the Label
	private int xCoordinateOfLabel;
	private int yCoordinateOfLabel;
	
	//Declaring a variable to store the Color of the Bar
	private Color colorOfBar;
	
	//Declaring a variable to store the Scale of the bar (to be multiplied with the height of the bar in application units to give the height of the bar in pixels
	private double scaleOfBar;

	/**
		Creates a labeled bar.  You give the height of the bar in application
		units (e.g., population of a particular state), and then a scale for how
		tall to display it on the screen (parameter scale). 
	
		@param bottom  location of the bottom of the label
		@param left  location of the left side of the bar
		@param width  width of the bar (in pixels)
		@param barHeight  height of the bar in application units
		@param scale  how many pixels per application unit
		@param color  the color of the bar
		@param label  the label at the bottom of the bar
	*/
	public Bar(int bottom, int left, int width, int barHeight, double scale, Color color, String label) 
	{
		//Assigning the parameters of the Constructor call
		yCoordinateOfLabel = bottom;
		xCoordinateOfBar = left;
		widthOfBar = width;
		barHeightAU = barHeight;
		scaleOfBar = scale;
		colorOfBar = color;
		labelOfBar = label;		
	}
   
	/**
		Draw the labeled bar. 
		@param g2  the graphics context
	*/
	public void draw(Graphics2D g2) 
	{
		
		//Getting the Width and Height (in pixels) of the Label
		Font font = g2.getFont();
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D labelBounds = font.getStringBounds(labelOfBar, context);
		double labelHeight = labelBounds.getHeight();
		double labelWidth = labelBounds.getWidth();
		
		//Calculating the Height of the Bar in pixels
		// The Height of the Bar in pixels is the product of the Height of the Bar in Application Units, and the Scale provided
		int heightOfBar = (int) Math.round(barHeightAU * scaleOfBar);
		
		/**Width of the Bar is stored in the variable widthOfBar*/
		
		/**X-Coordinate of the bar is stored in the variable xCoordinateOfBar*/
		
		//Calculating the Y-Coordinate of the bar
		/**Given that we know the Y-Coordinate of the string, the Height of the string and 
		 * the height of the Bar in pixels, the Y-Coordinate of the Bar can be calculated as follows:
		 * The variable yCoordinateOfLabel denotes the Y-Coordinate of the base-line of the label.
		 * If we subtract the Height of the string (in pixels) from yCoordinateOfLabel, we get the Y-Coordinate of the top of the label
		 * If we further subtract the Height of the Bar (in pixels) from the difference above, we can get the Y-Coordinate of the bar. 
		 */
		yCoordinateOfBar = (int) (yCoordinateOfLabel - labelHeight - heightOfBar);
		
		/**Y-Coordinate of the Label is stored in the variable yCoordinateOfLabel*/
		
		//Calculating the X-Coordinate of the Label
		/**Since the Label is centered under the Bar, the mid-point of the Label must be equal to the mid-point of the Bar.
		 * The two X-Coordinates of the Bar are denoted by xCoordinateOfBar and (xCoordinateOfBar + widthOfBar).
		 * The mid-point of Bar is thus calculated as (xCoordinateOfBar + (xCoordinateOfBar + widthOfBar)) / 2.
		 * The two X-Coordinates of the Label are denoted by xCoordinateOfLabel and (xCoordinateOfLabel + labelWidth).
		 * The mid-point of the Label is thus calculated as (xCoordinateOfLabel + (xCoordinateOfLabel + labelWidth)) / 2.
		 * Since the mid-point of the Label is equal to the mid-point of the bar, we get the following equations:
		 * => (xCoordinateOfBar + (xCoordinateOfBar + widthOfBar)) / 2 = (xCoordinateOfLabel + (xCoordinateOfLabel + labelWidth)) / 2
		 * => (xCoordinateOfBar + (xCoordinateOfBar + widthOfBar)) = (xCoordinateOfLabel + (xCoordinateOfLabel + labelWidth))
		 * => 2*xCoordinateOfBar + widthOfBar = 2*xCoordinateOfLabel + labelWidth
		 * => 2*xCoordinateOfLabel = 2*xCoordinateOfBar + widthOfBar - labelWidth
		 * Thus, xCoordinateOfLabel = ((2*xCoordinateOfBar) + widthOfBar - labelWidth) / 2. 
		 */
		xCoordinateOfLabel = (int) Math.round(((2*xCoordinateOfBar) + widthOfBar - labelWidth) / 2);
		 
		//Drawing the Bar Graph, filling it with the color, and drawing the label
		g2.setColor(colorOfBar);
		Rectangle barGraph = new Rectangle(xCoordinateOfBar, yCoordinateOfBar, widthOfBar, heightOfBar);
		g2.fill(barGraph);
		g2.drawString(labelOfBar, xCoordinateOfLabel, yCoordinateOfLabel);
	}
}
