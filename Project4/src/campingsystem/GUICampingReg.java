package campingsystem;

import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**********************************************************************
 * GUI frame for Camping Sites lists
 *  
 * @version November 1 2017
 *********************************************************************/  


@SuppressWarnings("serial")
public class GUICampingReg extends JFrame implements ActionListener {


	/* Holds all JMenus*/
	private JMenuBar menus;

	/* JMenu for file operations*/
	private JMenu fileMenu;
	/* JMenu for checking in*/
	private JMenu checkingInMenu;
	/* JMenu for edit methods*/
	private JMenu editMenu;

	private JMenu sorts;

	private JMenuItem openSerItem;
	private JMenuItem exitItem;
	private JMenuItem saveSerItem;
	private JMenuItem openTextItem;
	private JMenuItem saveTextItem;
	private JMenuItem tentCheckInItem;
	private JMenuItem rvCheckInItem;
	private JMenuItem visualOfSiteItem;
	private JMenuItem bookedDaysItem;
	private JMenuItem sortName;
	private JMenuItem sortDate;
	private JMenuItem sortDays;
	private JMenuItem checkOut;
	private JMenuItem StreamSort;

	private JMenu viewMenu;

	private JMenuItem removeItem;
	private JMenuItem undoItem;

	private JTable jListTable;
	private SiteModel siteList;

	private JPanel datePanel;

	public GUICampingReg() {

		fileMenu = new JMenu("File");
		editMenu = new JMenu("Edit");
		checkingInMenu = new JMenu("Checking In");
		viewMenu = new JMenu("View");
		sorts = new JMenu("Sort");

		StreamSort = new JMenuItem("Sort With Stream");
		visualOfSiteItem = new JMenuItem("Visual of Site");
		bookedDaysItem = new JMenuItem("Fully booked days");

		tentCheckInItem = new JMenuItem("Check-in Tent site");
		rvCheckInItem = new JMenuItem("Check-in RV site");

		StreamSort = new JMenuItem("Sort With Stream");
		sortName = new JMenuItem("Sort by Name");
		sortDate = new JMenuItem("Sort by Date");
		sortDays = new JMenuItem("Sort by Days Staying");

		checkingInMenu.add(tentCheckInItem);
		checkingInMenu.add(rvCheckInItem);

		removeItem = new JMenuItem("Remove");
		undoItem = new JMenuItem("Undo");
		checkOut = new JMenuItem("Check Out Date");

		openSerItem = new JMenuItem("Open Serial");
		saveSerItem = new JMenuItem("Save Serial");
		exitItem = new JMenuItem("Exit");
		openTextItem = new JMenuItem("Open Text");
		saveTextItem = new JMenuItem("Save Text");

		sorts.add(sortDays);
		sorts.add(sortDate);
		sorts.add(sortName);
		sorts.add(StreamSort);
		fileMenu.add(openSerItem);
		fileMenu.add(saveSerItem);
		fileMenu.addSeparator();
		fileMenu.add(openTextItem);
		fileMenu.add(saveTextItem);
		fileMenu.addSeparator();		
		fileMenu.add(exitItem);


		sorts.addActionListener(this);
		openSerItem.addActionListener(this);
		exitItem.addActionListener(this);
		saveSerItem.addActionListener(this);
		openTextItem.addActionListener(this);
		saveTextItem.addActionListener(this);
		StreamSort.addActionListener(this);

		visualOfSiteItem.addActionListener(this);
		bookedDaysItem.addActionListener(this);
		sortDays.addActionListener(this);
		sortDate.addActionListener(this);
		sortName.addActionListener(this);

		tentCheckInItem.addActionListener(this);
		rvCheckInItem.addActionListener(this);

		removeItem.addActionListener(this);
		undoItem.addActionListener(this);
		checkOut.addActionListener(this);;

		editMenu.add(removeItem);
		editMenu.add(undoItem);
		editMenu.add(checkOut);

		viewMenu.add(visualOfSiteItem);
		viewMenu.add(bookedDaysItem);

		menus = new JMenuBar();

		menus.add(fileMenu); 
		menus.add(editMenu);
		menus.add(checkingInMenu);
		menus.add(viewMenu);
		menus.add(sorts);
		setJMenuBar(menus);

		siteList = new SiteModel();
		jListTable = new JTable(siteList);

		add(new JScrollPane(jListTable));


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800,500);

	}

	private void visualOfCampsite() {
		datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(2,1));

		SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");

		JLabel dateRequest = new JLabel("Enter desired date:");
		JTextField userDate = new JTextField();
		userDate.setText("12/12/2018");
		datePanel.add(dateRequest);
		datePanel.add(userDate);

		boolean validDate = false;

		int result = JOptionPane.showConfirmDialog(null,
				datePanel, "", JOptionPane.OK_CANCEL_OPTION);

		if(result == JOptionPane.OK_OPTION) {
			try {
				java.util.Date date = null;
				date = formatCal.parse(userDate.getText());
				GregorianCalendar cal1 = new GregorianCalendar();
				cal1.setTime(date);
				validDate = true;
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Incorrect date format.");
			}
		}

		if (validDate) {
			try {
				SiteVisual siteVis = new SiteVisual
						(this, siteList, userDate.getText());
				siteVis.setSize(600, 400);
				siteVis.setVisible(true);
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Date out of range.");
			}

		}
	}

	private void daysBookedFrame() {
		JFrame bookedFrame = new JFrame();
		BookedDaysList bookedDays = new BookedDaysList(siteList);
		JTable bookedTable = new JTable(bookedDays);
		bookedFrame.add(new JScrollPane(bookedTable));
		bookedFrame.setSize(300, 600);
		bookedFrame.setVisible(true);
	}
   
	public void actionPerformed(ActionEvent e) {

		//Exit button
		if(e.getSource() == exitItem) {
			System.exit(0);
		}

		if(e.getSource() == openSerItem) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				try {
					siteList.loadFromSerialized(filename);
				}
				catch(IOException e1) {
					JOptionPane.showMessageDialog
					(null, "Invalid File!");
				}
				catch(ClassNotFoundException e2) {
					JOptionPane.showMessageDialog
					(null, "Invalid File!");
				}
				catch(Exception e3) {
					JOptionPane.showMessageDialog
					(null, "Invalid File!");
				}

			}
		}

		if(e.getSource() == saveSerItem) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				siteList.saveAsSerialized(filename);
			}
		}

		if(e.getSource() == openTextItem) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				try {
					siteList.loadText(filename);
				}
				catch(FileNotFoundException e1) {
					JOptionPane.showMessageDialog (null, "Cannot Find File!");
					siteList.makeSiteDates();
				}
				catch(InvalidDate e2) {
					JOptionPane.showMessageDialog(null, "Invalid Dates");
					siteList.makeSiteDates();
				}
				catch(Exception e3) {
					JOptionPane.showMessageDialog(null, "Invalid File!");
					siteList.makeSiteDates();
				}
			}
		}

		if(e.getSource() == saveTextItem) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				try {
					siteList.saveText(filename);
				}
				catch (IOException ex) {
					System.out.println("IO Error!");
				}
			}
		}

		if(e.getSource() == rvCheckInItem) {
			RV RVRental = new RV();

			// Creates a dialog box for tent check in
			DialogCheckInRv RV = new DialogCheckInRv
					(this, RVRental, siteList);

			// Waits for dialogue box to close before continuing
			RV.setModal(true);
			// Displays dialog box
			RV.setSize(350, 250);
			RV.setVisible(true);
			if(RV.isActive())
				displayCost(RVRental);

			jListTable.revalidate();
			jListTable.repaint();

		}

		if(e.getSource() == tentCheckInItem) {

			Tent tentRental = new Tent();

			// Creates a dialog box for tent check in
			DialogCheckInTent tent = new DialogCheckInTent
					(this, tentRental, siteList);

			// Waits for dialogue box to close before continuing
			try {
				tent.setModal(true);
			} 
			catch (Exception e1) {

			}
			// Displays dialog box
			tent.setSize(350, 250);
			tent.setVisible(true);
			if(tent.isActive())
				displayCost(tentRental);

			jListTable.revalidate();
			jListTable.repaint();
		}

		if(e.getSource() == visualOfSiteItem) {
			visualOfCampsite();
			jListTable.revalidate();
			jListTable.repaint();
		}


		if(e.getSource() == removeItem) {
			siteList.deleteReservation(jListTable.getSelectedRows());
		}

		if(e.getSource() == undoItem) {
			try {
				siteList.undo();
			}
			catch(EmptyStackException e1) {
				JOptionPane.showMessageDialog
				(null, "Nothing to Undo");
			}
			catch(Exception e2) {
				JOptionPane.showMessageDialog
				(null, "ERROR Parse Exception");
			}
		}

		if(e.getSource() == bookedDaysItem) {
			daysBookedFrame();
		}

		if(e.getSource() == sortName) {
			siteList.SortByName();
			repaint();
		}

		if(e.getSource() == sortDate) {
	           siteList.SortByDatesCheckedIn();
	           repaint();
		}

		if(e.getSource() == sortDays) {
	          siteList.SortByDaysStaying();
	          repaint();
		}

		if(e.getSource() == StreamSort) {
			Site[] list = siteList.SortStream();
			String temp = "";
			for(int x=0; x< list.length; x++) {
				temp = temp + list[x].toString() + "\n";
			}

			JOptionPane.showMessageDialog(null, temp);

		}

		if(e.getSource() == checkOut) {
			int check = 0;
			String temp = JOptionPane.showInputDialog(
					"What CheckOut Date Would You Like at "
					+ "" + jListTable.getSelectedRow(),"12/12/2018");
			try {
				int days = siteList.checkOutDate
						(jListTable.getSelectedRow(), temp);
				boolean type = siteList.isTypeRV
						(jListTable.getSelectedRow());
				double cost = siteList.cost(days, type, 
						jListTable.getSelectedRow());
				displayCost(cost);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog
				(null, "ERROR");
				check = 1;
			} 
			if(check == 0)
				siteList.deleteReservation(jListTable.getSelectedRow());

		}
	}

	public static void main (String[] args) {
		new GUICampingReg();
	}

	/******************************************************************
	 * Displays the cost of the requested reservation using 
	 * cost methods.
	 * @param s Site to display cost for site
	 * 0(1)
	 *****************************************************************/
	public void displayCost(double s) {
		JOptionPane.showMessageDialog
		(null, "You owe: " + s + " Dollars");
	}


	/******************************************************************
	 * Displays the cost of the requested reservation using 
	 * cost methods.
	 * @param s Site to display cost for site
	 * 0(1)
	 *****************************************************************/
	public void displayCost(Site s) {
		JOptionPane.showMessageDialog
		(null, "You owe: " + s.cost() + " Dollars");
	}



}

