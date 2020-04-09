package surroundpack;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/***********************************************************************
 * 
 * A class that implements methods from SurroundGame and creates a 
 * Panel and GUI with such information.
 * 
 * @author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/


public class SurroundPanel  extends JPanel  {

	/**
	 *An array of JButtons that create the board.
	 **/
	private JButton[][] board;
	/**
	 * A Dimension that holds the dimension sizes of the buttons
	 */
	private Dimension d;
	/**
	 * A Jpanel of upper panel which is the only panel
	 */
	private JPanel panelUpper;
	/**
	 * An object of SurroundGame
	 */
	private SurroundGame game;
	/**
	 * An object of button listener
	 */
	private ButtonListener butListener;
	/**
	 * An Object of JMenu Item that is the quit item
	 */
	private JMenuItem quitItem;
	/**
	 * An Object of JMenu Item that is the new game item
	 */
	private JMenuItem newGameItem;
	/**
	 * An Object of timer
	 */
	private Timer timer;
	/**
	 * An object that is a a label for the current time.
	 */
	private JLabel TimerLabel;
	
	/***********************************************************************
	 * A consturctor for Surround Panel
	 *
	 * @param quitItem represents a quit item of type
	 * @param newGameItem represents a new game item of type
	 */
	public SurroundPanel(JMenuItem quitItem, JMenuItem newGameItem) {
		game = new SurroundGame(10, 10, 2, 1, 30);
		panelUpper = new JPanel();
		board = new JButton[game.getRowSize()][game.getColSize()];
		d = new Dimension(170, 90);
		this.quitItem = quitItem;
		this.newGameItem = newGameItem;
		timer = new Timer(1000, new TimerListener());
		TimerLabel = new JLabel("Timer: ");
		timer.start();
		
		panelUpper.setLayout(new GridLayout(game.getRowSize()+1, game.getColSize()));
		
		butListener = new ButtonListener();
		this.newGameItem.addActionListener(butListener);
		this.quitItem.addActionListener(butListener);
		
		for (int i = 0; i < game.getRowSize(); i++)
			for(int j = 0; j < game.getColSize(); j++) {
				board[i][j] = new JButton(" ");
				board[i][j].setBackground(Color.BLUE);
				board[i][j].addActionListener(butListener);
				board[i][j].setPreferredSize(d);
				panelUpper.add(board[i][j]);
			}
		panelUpper.add(TimerLabel);
		add(panelUpper, BorderLayout.NORTH);
		
	}
	
	/***********************************************************************
	 * This method is a generic action listener method
	 * @author Isaac
	 *
	 */
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if(e.getSource() == quitItem) {
				System.exit(1);				
			}

			if(e.getSource() == newGameItem) {
			int players = 0, playerStart = 0, row =0, col =0, maxTime=0; 

				panelUpper.removeAll();
				while(row < 3 || row > 20)
					try {
						row = Integer.parseInt
						(JOptionPane.showInputDialog(null, "Row length"));
						if(row < 3 || row > 20)
					    JOptionPane.showMessageDialog
						(null, "Please pick between 3 and 20 board size");
					}
				catch (Exception error) {
					JOptionPane.showMessageDialog
					(null, "Type nice integers please");
				}

				while(col < 3 || col > 20)
					try {
						col = Integer.parseInt
						(JOptionPane.showInputDialog(null, "Col length"));
						if(col < 3 || col > 20)
							JOptionPane.showMessageDialog
						(null, "Please pick between 3 and 20 board size");
					}
				
				catch (Exception error) {}
				//Number of Players
				while(players < 2 || players>100) {
					try {
					players = Integer.parseInt
					(JOptionPane.showInputDialog(null, "How many players?"));
					if( players < 2 || players>100)
					JOptionPane.showMessageDialog
					(null, "Please pick between 2 and 100 players");
					}
					catch (Exception error) {
					JOptionPane.showMessageDialog
					(null, "Type nice integers please");
					}
				}

				//player starting
				while(playerStart <= 0 || playerStart >players)
					try {
					playerStart = Integer.parseInt
					 (JOptionPane.showInputDialog(null, "Who starts"));
					if(playerStart <= 0 || playerStart >players)
					 JOptionPane.showMessageDialog
					 (null, "Please enter a a player"
					 + " that is from 1 to " + players );
					 }
				catch (Exception error) {
					JOptionPane.showMessageDialog
					(null, "Type nice integers please");
				}

				while(maxTime <= 0 || maxTime >100000)
					try {
						maxTime = Integer.parseInt
						(JOptionPane.showInputDialog(null, "How much time"
								+ " for each player"));
						if(playerStart <= 0 || playerStart >players)
							JOptionPane.showMessageDialog
					   (null, "Please enter a number between 1 and 100000");
					}
				catch (Exception error) {
					JOptionPane.showMessageDialog
					(null, "Type nice integers please");
				}

				game.setGameSize(row,col);
				board = new JButton[row][col];
				panelUpper.setLayout(new GridLayout(row+1, col));
				if(game.boardSize() == -1)
					d = new Dimension(700/row, 400/col);
				if(game.boardSize() == 1)
					d = new Dimension(700/row, 400/col);
				else
					d = new Dimension(1750/row, 950/col);

				for (int r = 0; r < row; r++)
					for (int c = 0; c < col; c++) {
						board[r][c] = new JButton(" ");
						board[r][c].setBackground(Color.BLUE);
						board[r][c].addActionListener(butListener);
						board[r][c].setPreferredSize(d);
						panelUpper.add(board[r][c]);
					}
				game = new SurroundGame(row, col, players,
						playerStart, maxTime);
				panelUpper.add(TimerLabel);
				panelUpper.revalidate();
				repaint();
				timer.start();
			}
			
			
			for (int row = 0; row < game.getRowSize(); row++) 
			  for (int col = 0; col < game.getColSize(); col++)						
					if (board [row][col] == e.getSource()) 
						if (game.select(row, col)) {
							   game.nextPlayer();
								display();
						}
				else 									
				JOptionPane.showMessageDialog(null, "Pick again.");

		    int winner = game.isWinner();
			if (winner != -1) {
			JOptionPane.showMessageDialog(null,"Player "+winner+" Wins!");
				game.reset();
				display();
			}
		}
	}
	
	/***********************************************************************
	 * This method redisplays the board when the game has a selection made
	 */
	public void display( ) {
		for (int row = 0; row < game.getRowSize(); row++)
			for (int col = 0; col < game.getColSize(); col++) {
				Cell c = game.getCell(row,col);
				if(c != null)
					board[row][col].setBackground(Color.GRAY);
				else if(game.goodChoice(row, col) == 0) {
					board[row][col].setBackground(Color.BLUE);
				}
				else if(game.goodChoice(row, col) == 3) {
					board[row][col].setBackground(Color.RED);
				}
				else if(game.goodChoice(row, col) == 2) {
					board[row][col].setBackground(Color.ORANGE);
				}
				else if(game.goodChoice(row, col) == 1) {
					board[row][col].setBackground(Color.YELLOW);
				}
				
				if (c != null) {
				board[row][col].setFont(new Font("Arial", Font.ITALIC, 40));
					board[row][col].setText(""+c.getPlayerNumber());
					
				}
				else
					board[row][col].setText("");
				}
		TimerLabel.setText("Timer: " + game.getTimer());
		
	}
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ex) {
			game.Timer();
			display();
		}
	}
	
	
}

		

