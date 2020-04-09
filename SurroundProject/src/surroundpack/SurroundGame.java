package surroundpack;

/***********************************************************************
 * 
 * Implement the following methods and properties in SurroundGame. 
 * It has a board of a two dimensional array of type Cell, a current
 * player, a maximum player, a row size maximum, a cplumn size.  Also
 * a constructor, setters, getters, a check for winner and, next 
 * player and a select.
 * 
 * @author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/
public class SurroundGame {
	/**
	 * Type 2 dimensional array of type Cell
	 */
	private Cell[][] board;
	/**
	 * type integer that controls the current player
	 */
	private int currentPlayer;
	/**
	 * type integer that holds the current max amount of players
	 */
	private int maxPlayers = 2;
	/**
	 * type integer that holds the current size of rows
	 */
	private int rowSize;
	/**
	 * type integer that holds the current size of columns
	 */
	private int colSize;
	/**
	 * type integer that holds the maximum time
	 */
	private int time;
	
	/**
	 * type integer that holds the current time
	 */
	private int currentTime;
    
	/***********************************************************************
	 * A constructor method that initializes the board. 
	 * 
	 * @param row represents an integer that is assigned to rowSize
	 * @param col represents an integer that is assigned to colSize
	 * @param players represents an integer of amount of players
	 * @param playerStart represents an integer of which player starts
	 */
	public SurroundGame(int row, int col, int players, int playerStart,
			int maxTime) {
       rowSize = row;
       colSize = col;
       maxPlayers = players;
       currentPlayer = playerStart;
       time = maxTime;
		board = new Cell[row][col];
	}

	/***********************************************************************
	 * Sets the game size to the indicated row and column sizes
	 * @param rowSize represents an integer of row size
	 * @param colSize represents and integer of col size
	 */
	public void setGameSize(int rowSize, int colSize) {
		this.rowSize = rowSize;
		this.colSize = colSize;
	}
	
	/***********************************************************************
	 * this method retrieves the row size.
	 * @return row size
	 */
	public int getRowSize() {
	  return rowSize;
	}
	
	/***********************************************************************
	 * This method retrieves the column size
	 * @return column size
	 */
	public int getColSize() {
		return colSize;
	}
	/***********************************************************************
	 * this method is called from the SurroundPanel class and is invoked
	 *  when the user has selected a JButton.  This method determines if 
	 *  the row, column that was selected was an empty square.  Return true 
	 *  if valid, otherwise return false. 
	 *  
	 * @param row represents the row that is being checked in board to
	 * select
	 * @param col represents the column that is being checked in 
	 * board to select
	 * @return true if that space is selectable
	 */
	public Boolean select(int row, int col) {
		if (board[row][col] == null) {
			board[row][col] = new Cell(currentPlayer);

			return true;

		} else
			return false;

	}
	
	/***********************************************************************
	 * This test the board size ratio between row size and column size so
	 * that it can be sized correctly.
	 * @return 1 if rowsize is greater than col size, return -1 if column
	 * size is greater tyhan row size and return 0 if they are the same.
	 */
	public int boardSize() {
		if(rowSize >colSize)
			return 1;
	     else if(colSize > rowSize)
	    	 return -1;
	     
		return 0;
		
	}
	/***********************************************************************
	 * this method picks the next player and then returns the player.
	 * @return the player is next
	 */
	public int nextPlayer() {
		if (currentPlayer == maxPlayers)
			currentPlayer = 1;
		else
			currentPlayer += 1;
		currentTime = time;
		return currentPlayer;
	}

	/***********************************************************************
	 * This method resets the game by creating a new board and assigns
	 * board sizes.
	 * @param rowSize is the size of the game board for rows
	 * @param colSize is the size of the game board for column
	 */
	public void reset() {
		board = new Cell[rowSize][colSize];

	}

	/***********************************************************************
	 * retrieves the Cell object at row and col
	 * @param row is an integer that represents the current board space
	 * @param col is and integer that represents the currect board space
	 * @return Cell at board of row and col
	 */
	public Cell getCell(int row, int col) {
		return board[row][col];

	}

	/***********************************************************************
	 * this method checks to see if someone has won.
	 * @return -1 if no winner or the player number if someone has won.
	 */
	public int isWinner() {
		for (int row = 0; row < rowSize; row++) {
			for (int col = 0; col < colSize; col++) {
                if(board[row][col] != null) {
                	
				// checks winners in corners
				//top left
				if (row == 0 && col == 0) {
					if(board[0][1] != null && board[1][0] != null)
					if(board[0][0].getPlayerNumber() !=
					board[1][0].getPlayerNumber())
					if (board[0][1].getPlayerNumber() == 
					board[1][0].getPlayerNumber())
						return board[0][1].getPlayerNumber();
				}
				
				//top right
				if (row == 0 && col == colSize-1) {
					if(board[0][col-1] != null && board[1][col] != null)
						if (board[row][col].getPlayerNumber() != 
						board[row][col-1].getPlayerNumber()
					      && board[0][col-1].getPlayerNumber() == 
					      board[1][col].getPlayerNumber())
						
						return board[0][colSize-2].getPlayerNumber();
				}

				//bottom left
				if (row == rowSize-1 && col == 0) {
					if(board[row][1] != null && board[row-1][0] != null)
					if (board[row - 1][col].getPlayerNumber() ==
					board[row][col + 1].getPlayerNumber()
						&& board[row][col].getPlayerNumber() != 
						board[row][col+1].getPlayerNumber())
						return board[row - 1][col].getPlayerNumber();
				}

				//bottom right
				if (row == rowSize-1 && col == colSize-1) {
				if(board[row][col-1] != null && board[row-1][col] != null)					
					if (board[row][col].getPlayerNumber() !=
					board[row][col-1].getPlayerNumber()
						&& board[row - 1][col].getPlayerNumber() == 
						board[row][col - 1].getPlayerNumber())
						return board[row][col - 1].getPlayerNumber();
				}

				// checks winner on sides
				//top
				if (row == 0 && col > 0 && col < colSize-1) {
					if(board[1][col] != null && board[0][col+1] != null &&
							board[0][col-1] != null)
					if (((board[1][col].getPlayerNumber() == 
					board[0][col + 1].getPlayerNumber())
							&& (board[1][col]).getPlayerNumber() == 
							board[0][col - 1].getPlayerNumber())
							&& board[0][col].getPlayerNumber() != 
							board[0][col+1].getPlayerNumber())
						return board[1][col].getPlayerNumber();
				}
				//bottom
				if (row == rowSize-1 && col > 0 && col < colSize-1) {
					if(board[row][col + 1] != null && board[row - 1][col] 
							!= null &&	board[row][col-1] != null)
					if (((board[row - 1][col].getPlayerNumber() == 
							board[row][col + 1].getPlayerNumber())
							&& (board[row - 1][col]).getPlayerNumber() == 
							board[row][col - 1].getPlayerNumber())
							&& board[row][col].getPlayerNumber() != 
							board[row][col+1].getPlayerNumber())
						return board[row - 1][col].getPlayerNumber();
				}
				//left
				if (col == 0 && row > 0 && row < rowSize-1) {
					if(board[row][col + 1] != null && board[row + 1][col] 
							!= null &&	board[row - 1][col] != null)
					if (((board[row][col + 1].getPlayerNumber() ==
							board[row + 1][col].getPlayerNumber())
							&& (board[row][col + 1]).getPlayerNumber() == 
							board[row - 1][col].getPlayerNumber())
							&& board[row][col].getPlayerNumber() != 
							board[row][col+1].getPlayerNumber())
						return board[row + 1][col].getPlayerNumber();
				}
				//right
				if (col == colSize-1 && row > 0 && row < rowSize-1) {
					if(board[row][col - 1] != null && board[row + 1][col]
							!= null &&	board[row - 1][col] != null)
					if (((board[row][col - 1].getPlayerNumber() ==
							board[row + 1][col].getPlayerNumber())
							&& (board[row][col - 1]).getPlayerNumber() == 
							board[row - 1][col].getPlayerNumber())
							&& board[row][col].getPlayerNumber() != 
							board[row][col-1].getPlayerNumber())
						return board[row + 1][col].getPlayerNumber();
				}
				// check winner on middles
				if (col < colSize-1 && col > 0 && row < rowSize-1 && 
						row > 0) {
					if(board[row - 1][col] != null && board[row][col + 1] 
							!= null && board[row + 1][col] != null && 
							board[row][col - 1] != null)
					if ((board[row - 1][col].getPlayerNumber() ==
							board[row][col + 1].getPlayerNumber())
							&& (board[row - 1][col].getPlayerNumber() ==
							board[row + 1][col].getPlayerNumber())
							&& (board[row - 1][col].getPlayerNumber() == 
							board[row][col - 1].getPlayerNumber())
							&& board[row][col].getPlayerNumber() !=
							board[row][col+1].getPlayerNumber())
						return board[row - 1][col].getPlayerNumber();
				}
				}
			}
			}
		return -1;
	}
	/***********************************************************************
	 * Checks to see if it would be a good choice to go someewhere
	 * 
	 * @param row at the certain board space
	 * @param col at the certain board space
	 * @return a count depending on if that space is good.
	 */
	public int goodChoice(int row, int col) {
		int count = 0;
		try {
		if(board[row][col+1] != null)
			count++;
		}
		catch(Exception ex){}
		try {
	    if(board[row][col-1] !=null)
	    	count++;
		}
		catch(Exception ex) {}
		try {
	    if(board[row-1][col] !=null)
	    	count++;
		}
		catch(Exception e) {}
		try {
	    if(board[row+1][col] != null)
		    count++;
		}
		catch(Exception e) {}
		return count;
	}
	
	/***********************************************************************
	 * a method that runs a Timer Countdown
	 */
	public void Timer() {
		currentTime--;
		if(currentTime <= 0) {
			this.nextPlayer();
		}
	}
	/***********************************************************************
	 * A get time method that retrieves the current time
	 * @return an int of current time for timer
	 */
	public int getTimer() {
		return currentTime;
	}
}
