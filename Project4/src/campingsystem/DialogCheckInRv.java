package campingsystem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/***********************************************************************
 * DialogueCheckInRV - Simulates a prompt for reserving a campsite for 
 * an RV 
 * @version November 1 2017
 **********************************************************************/

@SuppressWarnings("serial")
public class DialogCheckInRv extends JDialog implements ActionListener{
	/* A text field that represents the name on the camp site */
	private JTextField nameTxt;

	/* A text field that represents the camp site number */
	private JTextField siteNumberTxt;

	/* A text field that represents the date the camp site will be occupied */
	private JTextField OccupyedOnTxt;

	/* A text field that represents the number of days the campers will stay */
	private JTextField stayingTxt;

	/* A label that represents the name of the occupant. */
	private JLabel nameLabel;

	/* A label that represents the date the occupant is staying. */
	private JLabel occupyingLabel;

	/* A label that represents the number of days the occupant is staying. */
	private JLabel stayingLabel;

	/* A label that represents the site number. */
	private JLabel siteLabel;
	
	/* A label that represents the amperages a RV can use */
	private JLabel ampLabel;

	/* A Combo Box that holds the amperages a RV can use  */
	private JComboBox <String> powerTxt;

	/* A String that instantiates the amps that are available */ 
	private final String[] AMPLIST = {"30","40","50"};

	/* A button that confirms the values put into the dialogue box */
	private JButton okButton;

	/* A button that cancels the reservation and exits the dialogue box */
	private JButton cancelButton;

	/* An object representing the site model being modified */
	private SiteModel siteList;
	
	/* An object representing the site being requested */
	private RV unit;  

	/* A panel that holds the dialogue box */
	private JPanel panel;


	public DialogCheckInRv(JFrame paOccupy, RV rvType, SiteModel listSites){
		super(paOccupy);
		siteList = listSites;
		unit = rvType;
		panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.setPreferredSize(new Dimension(1, 2));
		
		Box nameBox = new Box(BoxLayout.X_AXIS);
		Box siteBox = new Box(BoxLayout.X_AXIS);
		Box occupyBox = new Box(BoxLayout.X_AXIS);
		Box stayBox = new Box(BoxLayout.X_AXIS);
		Box powerBox = new Box(BoxLayout.X_AXIS);
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		
		nameTxt = new JTextField();
		nameLabel = new JLabel("Name of reserver");
		nameTxt.setText("jane doe");
		nameTxt.setColumns(25);
		nameTxt.setSize(30, 1);
		
		nameBox.add(nameLabel);
		nameBox.add(Box.createHorizontalStrut(76));
		nameBox.add(nameTxt);
		panel.add(nameBox);
		
		siteNumberTxt = new JTextField();
		siteLabel = new JLabel("Site number");
		siteNumberTxt.setText("4");
		siteNumberTxt.setColumns(30);

		siteBox.add(siteLabel);
		siteBox.add(Box.createHorizontalStrut(112));
		siteBox.add(siteNumberTxt);
		panel.add(siteBox);
		
		
		OccupyedOnTxt = new JTextField();
		occupyingLabel = new JLabel("Date of Reservation");
		OccupyedOnTxt.setText("1/1/2018");
		OccupyedOnTxt.setColumns(25);

		occupyBox.add(occupyingLabel);
		occupyBox.add(Box.createHorizontalStrut(57));
		occupyBox.add(OccupyedOnTxt);
		panel.add(occupyBox);

		stayingTxt = new JTextField();
		stayingLabel = new JLabel("Days planning on staying");
		stayingTxt.setText("5");
		stayingTxt.setColumns(25);

		stayBox.add(stayingLabel);
		stayBox.add(Box.createHorizontalStrut(18));
		stayBox.add(stayingTxt);
		panel.add(stayBox);
		
		powerTxt = new JComboBox<String>(AMPLIST);
		ampLabel = new JLabel("Number of Amps Requested");

		powerBox.add(ampLabel);
		powerBox.add(Box.createHorizontalStrut(52));
		powerBox.add(powerTxt);
		panel.add(powerBox);
		
		okButton = new JButton("Enter");
		cancelButton = new JButton("Cancel");

		ButtonListener listener = new ButtonListener();
		okButton.addActionListener(listener);
		cancelButton.addActionListener(listener);

		buttonBox.add(okButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(cancelButton);
		
		panel.add(Box.createVerticalStrut(20));
		
		panel.add(buttonBox);
		
		panel.add(Box.createVerticalStrut(20));
		
		panel.setVisible(true);
		
		paOccupy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(panel);
	}
	
	public void setSiteDefault(int site) {
		siteNumberTxt.setText(site + "");
	}
	
	public void setDateDefault(String date) {
		OccupyedOnTxt.setText(date);
	}
	
	public DialogCheckInRv( RV d, SiteModel listSites,
			int site, String date) {
		DialogCheckInRv Rv = new DialogCheckInRv(new JFrame(),d,listSites);
		Rv.setSiteDefault(site);
		Rv.setDateDefault(date);
		Rv.setVisible(true);
		System.out.println("DIalogCheckIn");
		//Rv.add(new JButton("Test"));
	}

	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(okButton == e.getSource()){
				try{
					unit.setNameReserving(nameTxt.getText());
					unit.setDaysStaying(Integer.parseInt(stayingTxt.getText()));
					unit.setSiteNum(Integer.parseInt(siteNumberTxt.getText()));
					unit.setPow(Integer.parseInt(powerTxt.getSelectedItem().toString()));

					SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy");
					java.util.Date date = null;
					try {
						date = df.parse(OccupyedOnTxt.getText());
						GregorianCalendar cal = new GregorianCalendar();
						cal.setTime(date);
						unit.setCheckIn(cal);
					} catch (Exception e1) {
						throw new InvalidDate();
					}
					try {
					if(siteList.isValidInput(unit))
						siteList.addRV(unit);
					else	
						throw new IllegalArgumentException();
					}
					catch (ParseException e1) {
						JOptionPane.showMessageDialog(null,
								"ERROR: Invalid Date");
					} catch (InvalidDate e2) {
						JOptionPane.showMessageDialog(null, 
								"ERROR: Date out of range");
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, 
								"Site already reserved!");
					}
				}
				//FIXME REMOVE ERROR THROW EXCEPTION HAVE THE GUI CATCH IT AND LET THE GUUI DECIDE WHAT TO DO
				catch(InvalidDate e1) {
					JOptionPane.showMessageDialog
					(null, "Invalid Date Format!");
				}

				catch(IllegalArgumentException e1){
					JOptionPane.showMessageDialog
					(null, "Invalid Numerical Input!");
				}

			}

			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}