package project1;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/***********************************************************************
 * A class that creates a GUI and creates a JmenuBar that has a quit
 * and suspend in menu.
 * 
 * @Author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/
public class GUI {

	public static void main(String[] args) {

		JMenu fileMenu;
		JMenuItem quit;
		JCheckBoxMenuItem suspendIT;
		JMenuBar men;

		fileMenu = new JMenu("File");
		quit = new JMenuItem("Quit");
		suspendIT = new JCheckBoxMenuItem("Suspend");
		fileMenu.add(suspendIT);
		fileMenu.add(quit);
		men = new JMenuBar();

		men.add(fileMenu);

		JFrame gui = new JFrame("Stop Watch");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StopWatchPanelMain panel = new StopWatchPanelMain(quit, suspendIT);
		gui.getContentPane().add(panel);

		gui.setSize(300, 400);
		gui.setJMenuBar(men);
		gui.setSize(300, 400);
		gui.setVisible(true);

	}
}