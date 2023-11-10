import java.util.*;

import javax.swing.ImageIcon;

public class BombSquare extends GameSquare
{


	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;
	public BombSquare(int x, int y, GameBoard board)
	{
		super(x, y, "Resources/images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);
	}

	
	GameSquare currentSquare;

	public void clicked()
	{
		
			if (thisSquareHasBomb){
				setImage("Resources/images/bomb.png");

			} 
	}




}
