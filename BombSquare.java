import java.util.*;

/**
 * This class inherits GameSquare class to provide the actions from clicking on a square
 * @author Mahmoud Abdelfattah and joe finney
 */

public class BombSquare extends GameSquare
{


	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;

	private boolean clicked = false;
	private int currentX;
	private int currentY;
	private int bombCount;
	private GameSquare currentSquare;



	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	
	/**
	 * clicked method
	 * Makes sure the boolean is on false (i.e. nothing is clicked).
	 * Checks for bombs and sets the bomb image if there is one in the square clicked.
	 * If there is no bomb on clicked square then checks adjacent squares for bombs.
	 * Adds to the counter when finding any bombs and changes the clicked boolean respectively.
	 * Then sets the image based on the bomb counter and goes to the next method if the square is empty.
	 */

	public void clicked()
	{
		if (clicked == false){
			if (thisSquareHasBomb){
				//set the image to bomb if square has a bomb
				setImage("images/bomb.png");
				//square has been clicked, so makes boolean true
				clicked = true;

			}  else { //square has no bombs
				//loops through the adjacent squares in both x and y directions allowing for diagonals to be detected too
				for (int x = -1; x <= 1; x++){
					for (int y = -1; y <= 1; y++){
						
						//uses currentX and currentY to move around adjacent squares
						currentX = xLocation + x;
						currentY = yLocation + y;

						//creates gameSquare instance (called square) to get the right location
 						GameSquare square = board.getSquareAt(currentX, currentY); 
						
						//make square an instancce of BombSquare to allow checking for bombs
						 if (square instanceof BombSquare && ((BombSquare) square).thisSquareHasBomb) {
							//once it finds a bomb it will add to bomb counter
							bombCount++;
							clicked  = true; //limits any changes from pressing multiple times
						}

					}
				}
				board.getSquareAt(xLocation, yLocation).setImage("images/"+bombCount+".png");
				clicked = true;

				if (bombCount == 0){
					expandingSquares();
				}

			}
		}
	}

	/**
	 * expandingSquares method
	 * This method is called when bombCount is 0 on the current square.
	 * It looks for adjacent squares and sees which is empty (i.e. bombCount = 0).
	 * Then sets the image of that square to 0, to make it revealed and updates clicked boolean respectively, then calls clicked method.
	 * Uses recursion by calling the same method itself if the square is not null and not clicked.
	 */
	public void expandingSquares(){
		//loops through the adjacent squares in both x and y directions allowing for diagonals to be detected too
		for (int x = -1; x <= 1; x++){
			for (int y = -1; y <= 1; y++){
				
				//uses currentX and currentY to move around adjacent squares
				currentX = xLocation + x;
				currentY = yLocation + y;
				
				//creates gameSquare instance (called square) to get the right location
				GameSquare square = board.getSquareAt(currentX, currentY); 

				//if no bombs in adjaccent squares
				if (square instanceof BombSquare && bombCount == 0){
					//sets the image as "0" to reveal the square allowing for expansion
					board.getSquareAt(xLocation, yLocation).setImage("images/0.png");
					square.clicked(); //goes back to the clicked method
				} else if (square != null && clicked == false){ //if the square is not clicked/revealed then go back to same method
					expandingSquares(); //implements recurrsion
				}
			}
		}
	}



}
