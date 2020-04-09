package surroundpack;

import javax.swing.JFrame;

import javax.swing.*;

public class Surround {



	public static void main(String[] args) {
		JFrame frame = new JFrame("Surround Four");		

		JMenuItem quitItem;
		JMenuItem newGameItem;
		JMenuBar menu;
		JMenu fileMenu;

		fileMenu = new JMenu("File");
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");
		menu = new JMenuBar();
		fileMenu.add(quitItem);
		fileMenu.add(newGameItem);
		menu.add(fileMenu);
		SurroundPanel panel1 = new SurroundPanel(quitItem, newGameItem);



		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1950, 1050);
		frame.setJMenuBar(menu);
		frame.getContentPane().add(panel1);
		frame.setVisible(true);
	}
}

