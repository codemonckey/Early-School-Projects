package project1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/***********************************************************************
 * A class that implements StopWatch methods and other methods to create 
 * a panel that has a StopWatch on it with a stop, start, add, sub,
 * reset.
 * 
 * @Author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/

public class StopWatchPanel extends JPanel {

	private JButton start;
	private JButton stop;
	private JButton reset;
	private JButton add;
	private JButton sub;
	private JButton save;
	private JButton load;
	private StopWatch s1;
	private JLabel text;
	private JMenuItem quitMenu;
	private JMenuItem suspendMenu;
	private Timer javaTimer;
	private String file = null;

	/***********************************************************************
	  A class that implements StopWatch methods and other methods to create 
	 * a panel that has a StopWatch on it with a stop, start, add, sub, 
	 * reset.
	 * 
	 * @param quit represent a quit Jmenu that closes the window on click
	 * @param suspend represents a suspend item that stops some methods
	 * @param file represents the seperate file names for individual panels
	 **********************************************************************/
	public StopWatchPanel(JMenuItem quit, JMenuItem suspend, String file) {

		ButtonListener buttonList = new ButtonListener();
		start = new JButton("start");
		stop = new JButton("stop");
		reset = new JButton("reset");
		add = new JButton("add");
		sub = new JButton("sub");
		save = new JButton("save");
		load = new JButton("load");
		s1 = new StopWatch();
		text = new JLabel(s1.toString());
		this.file = file;

		setBackground(Color.gray);
		setLayout(new GridLayout(8, 1, 6, 6));

		add(text);
		add(start);
		add(stop);
		add(add);
		add(sub);
		add(reset);
		add(save);
		add(load);

		this.quitMenu = quit;
		this.suspendMenu = suspend;

		start.addActionListener(buttonList);
		stop.addActionListener(buttonList);
		this.quitMenu.addActionListener(buttonList);
		this.suspendMenu.addActionListener(buttonList);
		add.addActionListener(buttonList);
		sub.addActionListener(buttonList);
		reset.addActionListener(buttonList);
		load.addActionListener(buttonList);
		save.addActionListener(buttonList);

		javaTimer = new Timer(3, new TimerListener());
	}

	/***********************************************************************
	 * A class controls action listeners and actions done by the GUI
	 * 
	 * @Author Isaac Dessert
	 * @version 1.0
	 * 
	 **********************************************************************/
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == quitMenu) {
				System.exit(1);
			}
			if (e.getSource() == suspendMenu)
				StopWatch.suspend(true);

			if (e.getSource() == stop) {
				javaTimer.stop();
			}

			if (e.getSource() == start) {
				javaTimer.start();
				StopWatch.suspend(false);
			}

			if (e.getSource() == add) {

				String numStr = JOptionPane.showInputDialog
						("Enter and integer " + "to add: ");
				int num = Integer.parseInt(numStr);
				s1.add(num);
			}

			if (e.getSource() == sub) {
				String numStr = JOptionPane.showInputDialog
						("Enter and integer " + "to subtract: ");
				int num = Integer.parseInt(numStr);
				s1.sub(num);
			}

			if (e.getSource() == reset) {
				s1.setMilliseconds(0);
				s1.setSeconds(0);
				s1.setMinutes(0);
			}

			if (e.getSource() == save) {
				s1.save(file);
			}
			if (e.getSource() == load) {
				s1.load(file);
			}
			text.setText(s1.toString());
		}
	}

	/***********************************************************************
	 *A Class that controls a timer for the Stopwatch by incrementing 
	 *every millisecond.
	 * 
	 * @Author Isaac Dessert
	 * @version 1.0
	 * 
	 **********************************************************************/
	private class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			s1.add(3);
			text.setText(s1.toString());
		}
	}

}
