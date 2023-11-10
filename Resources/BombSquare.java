import java.util.*;

import javax.swing.ImageIcon;

public class BombSquare extends GameSquare
{


	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;
	private boolean clicked = false;
	private int currentX;
	private int currentY;


	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "Resources/images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	
	GameSquare currentSquare;

	public void clicked()
	{
		if (clicked == false){
			if (thisSquareHasBomb){
				//set the image to bomb if square has a bomb
				setImage("Resources/images/bomb.png");
				//square has been clicked, so makes boolean true
				clicked = true;

			}  else { //square has no bombs
				//loops through the adjacent squares in both x and y directions allowing for diagonals to be detected too
				for (int x = -1; x <= 1; x++){
					for (int y = -1; y <= 1; y++){
						
						//uses currentX and currentY to move around adjacent squares
						currentX = xLocation + x;
						currentY = yLocation + y;
						
					}
				}

			}
		}
	}



}
