package campingCenter;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class DialogCheckInTent extends JDialog implements ActionListener {

	private JLabel nameLabel,powLabel,requestLabel,occuLabel,daysLabel,tentersLabel;

	private JTextField nameTxt;
	private JTextField OccupiedOnTxt;
	private JTextField stayingTxt;
	private JTextField siteNumberTxt;
	private JTextField tentersTxt;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel panel;
	private boolean closeStatus;
	private Tent unit;  	


	public DialogCheckInTent(JFrame paOccupy, Tent d) {
		super(paOccupy,"Reserve an RV site", true);
		closeStatus = false;
		unit = d;

		panel = new JPanel();
		panel.setLayout(new GridLayout(6,2));

		nameLabel = new JLabel ("Name of Reserver:");
		requestLabel = new JLabel ("Requested site number:");
		occuLabel = new JLabel ("Occupied on Date:");
		daysLabel = new JLabel ("Days planning on staying:");
		tentersLabel = new JLabel ("Number of Tenters:");

		nameTxt = new JTextField(5);
		siteNumberTxt = new JTextField(1);
		OccupiedOnTxt = new JTextField(10);
		stayingTxt = new JTextField(6);
		tentersTxt = new JTextField(6);

		okButton = new JButton("Ok");
		cancelButton = new JButton("Cancel");

		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
	  

		panel.add(nameLabel);
		panel.add(nameTxt);
		panel.add(requestLabel);
		panel.add(siteNumberTxt);
		panel.add(occuLabel);
		panel.add(OccupiedOnTxt);
		panel.add(daysLabel);
		panel.add(stayingTxt);
		panel.add(tentersLabel);
		panel.add(tentersTxt);

		panel.add(okButton);
		panel.add(cancelButton);

		getContentPane().add(panel);
		pack();


	}
	public Site getUnit( ) {
		return unit;
	}
	public void actionPerformed(ActionEvent e) {

		if(okButton == e.getSource())
		{
			closeStatus = true;
			SimpleDateFormat dateF;
			dateF = new SimpleDateFormat("MM/dd/yyyy");
			Date inDate;

			try {
					
				GregorianCalendar curDate = new GregorianCalendar();
				String name = nameTxt.getText();			
				int siteNum = Integer.parseInt(siteNumberTxt.getText());
				inDate = dateF.parse(OccupiedOnTxt.getText());
				
				/**
				 * Checks if the input is 00/00/YYYY and throws an exception.
				 * 
				 **/
				if(inDate.toString().substring(3,4)=="00" && inDate.toString().substring(0, 1) == "00" && Integer.parseInt(inDate.toString().substring(6,9)) >= 0000)
				{
					throw new Exception();
				}
				
				
	
				int daysStay = Integer.parseInt(stayingTxt.getText());
				int tenters = Integer.parseInt(tentersTxt.getText());	
				GregorianCalendar cal = new GregorianCalendar();
				cal.setLenient(false);
				cal.setTime(inDate);
				cal.getTime();
				
				if(cal.compareTo(curDate) < 0)
				{
					throw new Exception();
				}

				if(name.length() == 0 || siteNum > 5 || siteNum <= 0 || daysStay <= 0){
					throw new Exception();
				}
				if(siteNum <= 0 || daysStay <= 0 || tenters <= 0) {
					throw new IllegalArgumentException();
				}
				
			
				unit.setNameReserving(name);
				unit.setSiteNumber(siteNum);
				unit.setCheckIn(cal);				
				unit.setDaysStaying(daysStay);
				unit.setNumOfTenters(tenters);	

				System.out.println("Name of Reserved: " + name);
				System.out.println("Site Number: " + siteNum);
				System.out.println("Reserved starting on: " + cal.getTime());
				System.out.println("Number of Days Staying: " + daysStay);
				System.out.println("Number of Tenters: " + tenters);
											
				dispose();
			}
			catch(IllegalArgumentException ex){
				JOptionPane.showMessageDialog(panel, "A negative number, no info, or a zero was entered, please try again. Number of sites is 1-5");
			}
			catch(Exception e1)	{
				JOptionPane.showMessageDialog(panel, "An error occured / invalid input entered, please try again (Format of date is MM/DD/YYYY) ");
			}				
		}
		
		if(cancelButton == e.getSource()) {
			closeStatus = false;
			dispose();
		}
	}

	public boolean closeChk() {
		return closeStatus;
	}
}

