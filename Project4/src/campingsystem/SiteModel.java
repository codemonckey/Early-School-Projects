package campingsystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javax.swing.table.AbstractTableModel;

/**********************************************************************
 * SiteModel - Creates the model that the camp sites interact with.
 * Can add, remove, undo, display, and modify the lists.
 *  
 * @version November 1 2017
 *********************************************************************/

public class SiteModel extends AbstractTableModel{
	
	
	
	private static final long serialVersionUID = 1L;

	/** Constant for number of columns*/
	private final int COLUMN_COUNT = 5;
	
	/** Constant for amount of sites*/
	private final int SITE_AMOUNT = 5; 

	/** Number of sites used in saving and loading*/
	private int countSites;

	/** ArrayList of Sites (RV or Tents)*/
	private LinkedList<Site> listSites;
	
	/** Array of Strings for name of columns*/
	private String[] columnNames = { "Name Reserving", "Checked In",
			"Days Staying", "Site #", "Tent/RV Info" };
	
	/** SiteDates variable to check if reserved*/
	private SiteDates listDates;
	
	/** Stack of strings for actions to undo*/
	private Stack<String> undoActions;
	
	/** Stack of sites to undo*/
	private Stack<Site> undoSites;
	
	/** Stack of index*/
	private Stack<Integer> index;
	
	/******************************************************************
	 * Constructor method for SiteModel class. Instantiates a new
	 * ArrayList for sites (Takes both Tent and RV classes)
	 * 0(1)
	 *****************************************************************/
	public SiteModel() {
		listSites = new LinkedList<Site>();
		listDates = new SiteDates();
		undoSites = new Stack<Site>();
		undoActions = new Stack<String>();
		index = new Stack<Integer>();
	}
	
	/******************************************************************
	 * Method for AbstractTableModel class that returns amount
	 * of rows needed for siteModel based on siteList size
	 * @return returns the size of the site list, which is the amount
	 * 			of checked in sites.
	 * 0(1)
	 *****************************************************************/
	public int getRowCount() {
		return listSites.size();
	}
	
	/******************************************************************
	 * Method for AbstractTableModel class that returns amount of 
	 * columns, which is always 5 in this project.
	 * @return	amount of columns, which is a constant.
	 * 0(1)
	 *****************************************************************/
	public int getColumnCount() {
		return COLUMN_COUNT;
	}
	
	/******************************************************************
	 * Returns name of columns for AbstractTableModel
	 * @param  column number to get name for
	 * @return string of column name 
	 * 0(1)
	 *****************************************************************/
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	/******************************************************************
	 * Calls dialogue box for Tent to add new Tent listing, and
	 * updates the SiteModel to display it.
	 * @throws ParseException Error in parsing dates
	 * @throws InvalidDate	Date out of range
	 * @param  tentRental tent to be added to site
	 * 0(1)
	 * @throws Exception 
	 *****************************************************************/
	public void addTent(Tent tentRental) throws Exception {
		
		try {
			if(!listDates.isReserved(tentRental)) {
				
				//Add undo information to stacks
				undoSites.push(tentRental);
				undoActions.push("add");
				
				listDates.reserve(tentRental);
				listSites.add(tentRental);
				fireTableRowsUpdated(listSites.size(), COLUMN_COUNT);
				
			}
			else
				throw new Exception();

		} catch (ParseException e1) {
			throw new Exception();
		} catch (InvalidDate e2) {
			throw new InvalidDate();
		}

	}
	
	/******************************************************************
	 * Calls dialogue box for RV to add new Tent listing, and
	 * updates the SiteModel to display it.
	 * @param RVRental RV to add to list of sites
	 * 0(1)
	 * @throws Exception 
	 *****************************************************************/
	public void addRV(RV RVRental) throws Exception {
		
		try {
			if(!listDates.isReserved(RVRental)) {
				
				//Add undo stacks
				undoSites.push(RVRental);
				undoActions.push("add");;
				listDates.reserve(RVRental);
				listSites.add(RVRental);
				fireTableRowsUpdated(listSites.size(), COLUMN_COUNT);
				
			}
			else
				throw new IllegalArgumentException();

		} 
		catch (ParseException e1) {
			throw new Exception();
		} catch (InvalidDate e2) {
			throw new InvalidDate();
		}

		
	}
	
	/******************************************************************
	 * Deletes the selected row(s) from the table and listSites
	 * and also adds to the undo list.
	 * @param row  integer for row to delete
	 * 0(n^2)
	 *****************************************************************/
	
	public void deleteReservation(int[] rows) {
      for(int x = 0; x<rows.length; x++) {
		undoSites.push(listSites.get(rows[0]));
		undoActions.push("remove");
		index.push(rows[0]);
		listDates.removeReservation(listSites.get(rows[0]));
		listSites.remove(rows[0]);
}

		fireTableDataChanged();
	}
	
	public void deleteReservation(int rows) {
			undoSites.push(listSites.get(rows));
			undoActions.push("remove");
			index.push(rows);
			listDates.removeReservation(listSites.get(rows));
			listSites.remove(rows);
	

			fireTableDataChanged();
		}
	/******************************************************************
	 * Undoes the last action performed including adding, deleting,
	 * and loading text and serial files.
	 * 0(1)
	 * @throws Exception 
	 *****************************************************************/

	public void undo() throws Exception {
		try {
			undoActions.peek();
			String action = undoActions.pop();
			
			//If action was to add, remove it instead
			if(action.equals("add")) {
				Site undone = undoSites.pop();
				listSites.remove(listSites.indexOf(undone));
				listDates.removeReservation(undone);
			}
			
			//If action was to remove, re-add it.
			else if(action.equals("remove")) {
				Site undone = undoSites.pop();
				listSites.add(index.pop(), undone);
				listDates.reserve(undone);
			}
			fireTableDataChanged();
		}
		catch(EmptyStackException e1) {
			throw new EmptyStackException();
		}
		catch(Exception e2) {
			throw new Exception();
		}
	}
	
	/******************************************************************
	 * Gets site availability for parameter date entered
	 * @param date date to check site availability
	 * @return boolean array, true if reserved, false if open.
	 * 0(1)
	 *****************************************************************/
	public boolean[] getArrayReserves(String date) {
		return listDates.reservedForDay(date);
	}
	
	public void makeSiteDates() {
		listDates = new SiteDates();
		listSites.clear();
	}
	
	
	/******************************************************************
	 * Tests site values to ensure integers are not out of ranges.
	 * @param site
	 * @return boolean true if good input, false if invalid input.
	 * 0(1)
	 *****************************************************************/
	public boolean isValidInput(Site site) {
		
		//Test site number
		if(site.getSiteNum() <= 0 || site.getSiteNum() > SITE_AMOUNT)
			return false;
			
		//Test days staying
		if(site.getDaysStaying() <= 0)
			return false;
		
		//Test number of people at tent site
		if(site instanceof Tent) {
			if(((Tent)site).getNumTenters() <= 0)
				return false;
		}
		
		//Test power usage at RV
		else if(site instanceof RV) {
			if (((RV)site).getPow() != 30  &&
				((RV)site).getPow() != 40  &&
				((RV)site).getPow() != 50 )
				
				return false;
		}
		return true;
	}

	/******************************************************************
	 * Method to display values on the table based on the row and 
	 * column accessed based on parameters
	 * @param row number of row find variable of list
	 * @param col number of column to find what value to print
	 * @return String object of what to display in row/column cell
	 * 0(1)
	 *****************************************************************/
	public Object getValueAt(int row, int col) {
		
		String temp = "";
		
		//Switch statements for all 5 columns (0,1,2,3,4)
		switch(col) {
			case 0:
				temp = listSites.get(row).getNameReserving();
				break;
			case 1:
				SimpleDateFormat formatCal = 
					new SimpleDateFormat("M/d/yyyy");
				temp = formatCal.format
						(listSites.get(row).getCheckIn().getTime());
				break;
			case 2:
				temp = listSites.get(row).getDaysStaying() + "";
				break;
			case 3:
				temp = listSites.get(row).getSiteNum() + "";
				break;	
			case 4:
				if(listSites.get(row) instanceof Tent) 
					temp = (((Tent) listSites.get(row)).
							getNumTenters() + " Campers");
				else if(listSites.get(row) instanceof RV)
					temp = (((RV) listSites.get(row)).
							getPow() + " Amps");
				break;
		}
		
		return temp;
	}
	
	/*****************************************************************
	 * Calls fullyReservedSites of listDates to return all dates
	 * where all five sites are reserved
	 * @return ArrayList of strings of fully booked dates
	 * 0(n)
	 ****************************************************************/
	public ArrayList<String> getBookedDays(){
		return listDates.fullyReservedSites();
	}
	
	/*****************************************************************
	 * Saves off the list of Sites to a text file.
	 * @param filename name of file to save text to
	 * 0(n)
	 * @throws IOException 
	 ****************************************************************/
	public void saveText(String filename) throws IOException {
		
		try {
			PrintWriter out = new PrintWriter
					(new BufferedWriter(new FileWriter(filename)));
			out.println(listSites.size());
			
			for (int i = 0; i < listSites.size(); i++) {
				
				// listSites is an ArrayList<Site>
				Site SiteUnit = listSites.get(i);

				// Output the class name.
				out.println(SiteUnit.getClass().getName());

				// Output the Site Name
				out.println(SiteUnit.getNameReserving());
				
				SimpleDateFormat formatCal = new SimpleDateFormat("M/d/yyyy");
				String temp = formatCal.format(listSites.get(i).getCheckIn().getTime());
				out.println(temp);
				
				// Output the days staying
				out.println(SiteUnit.getDaysStaying());
				// Output the site number
				out.println(SiteUnit.getSiteNum());
				
				if(SiteUnit instanceof Tent) 
					out.println(((Tent) SiteUnit).getNumTenters());
				if(SiteUnit instanceof RV) 
					out.println(((RV) SiteUnit).getPow());
			}
			out.close();
		} 
		catch (IOException ex) {
			throw new IOException();
		}
	}

	/******************************************************************
	 * Loads a file named from parameter. Checks for valid file format,
	 * valid numbers, and valid dates. If any errors are found, clear
	 * arrayList and listDates
	 * 
	 * @param filename name of file to load
	 * 0(n)
	 * @throws Exception 
	 *****************************************************************/
	public void loadText(String filename) throws Exception {
		LinkedList<Site> tempList = new LinkedList<Site>();
		Site t;
		File inFile = new File(filename);
		try {
			Scanner sc = new Scanner(inFile);
			// Read first line, which is amount of sites.
			String line = sc.nextLine();
			listDates = new SiteDates();
			countSites = Integer.parseInt(line);
			int i = 0;
			while (i < countSites && sc.hasNext()) {

				String className = sc.nextLine();
				if ("campingsystem.Tent".equals(className)) 
					t = new Tent();
				else if ("campingsystem.RV".equals(className))
					t = new RV();
				else {
					sc.close();
					throw new NumberFormatException();
				}
				t.setNameReserving(sc.nextLine());
				DateFormat df = new SimpleDateFormat("M/d/yyyy");
				java.util.Date date = null;
				try {
					date = df.parse(sc.nextLine());
					GregorianCalendar cal = new GregorianCalendar();
					cal.setTime(date);
					t.setCheckIn(cal);
				}
				catch (Exception e1) {
					sc.close();
					throw new NumberFormatException();	
				}
				t.setDaysStaying(Integer.parseInt(sc.nextLine()));
				t.setSiteNum(Integer.parseInt(sc.nextLine()));
				if (t instanceof Tent)
					((Tent) t).setNumTenters
					(Integer.parseInt(sc.nextLine()));
				else if (t instanceof RV)
					((RV) t).setPow(Integer.parseInt(sc.nextLine()));
				
				if(listDates.isReserved(t)) {
					sc.close();
					throw new InvalidDate();
				}
				if (isValidInput(t)) {
					tempList.add(t);
					listDates.reserve(t);
				}
				else {
					sc.close();
					throw new IllegalArgumentException();
				}
				++i;
			}
			
			listSites = tempList;
			fireTableDataChanged();
			sc.close();
		
		}
		catch(FileNotFoundException e1) {
			throw new FileNotFoundException();
		}
		catch(InvalidDate e2) {
			throw new InvalidDate();
		}
		catch(Exception e3) {
			throw new Exception();
			}
		fireTableDataChanged();
		
	}
	
	/******************************************************************
	 * Saves siteList as serialized file.
	 * @param filename name of file to save to
	 * 0(1)
	 *****************************************************************/
	public void saveAsSerialized(String filename) {
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(listSites);
			oos.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/******************************************************************
	 * Loads from serialized file. Checks for invalid file format.
	 * If error, do not load the file
	 * @param filename name of file to load from.
	 * 0(1)
	 * @throws Exception 
	 *****************************************************************/
	@SuppressWarnings("unchecked")
	public void loadFromSerialized(String filename) throws Exception {
		try {
			FileInputStream fileIn = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			listSites = (LinkedList<Site>) in.readObject();
			in.close();
			fireTableRowsInserted(0, listSites.size());
		}
		catch(IOException e1) {
			throw new IOException();
		}
		catch(ClassNotFoundException e2) {
			throw new ClassNotFoundException();
		}
		catch(Exception e3) {
			throw new Exception();
		}
		
	}
	
	public int checkOutDate(int row, String CheckOutDate) throws Exception {
		try {
        Site newSite = listSites.get(row);
        SimpleDateFormat formatGrego = new SimpleDateFormat("M/d/yyyy");
        DateTimeFormatter formatLocal = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate localDate = LocalDate.parse(CheckOutDate, formatLocal);
        GregorianCalendar date = newSite.getCheckIn();
        
        String dateString = formatGrego.format(date.getTime());;
        
        LocalDate date1 = null;
        date1 = LocalDate.parse(dateString, formatLocal);
        int dates = (int)ChronoUnit.DAYS.between(date1, localDate);
          System.out.println("\n"+dates);
        int ammountDays = dates - newSite.getDaysStaying(); 
        System.out.println(ammountDays);
        if(localDate.isBefore(date1) || dates == 0 || ammountDays ==0)
        	return 0;
        return ammountDays;
		}
		catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
		catch(Exception e1) {
		throw new Exception();
		}
	}
	/********************************************************************
	 * This method checks to see if the row is an RV or Tent
	 * @param row is an integer of the row position in the linked list it is
	 * @return true if the row in the LL is an RV
	 *******************************************************************/
	public boolean isTypeRV(int row) {
		if(listSites.get(row) instanceof RV)
			return true;
		else
			return false;
	}
	
	/*******************************************************************
	 * This calulates the cost for the GUI function for when you check out
	 * 
	 * @param days is an integer of days between check out date
	 * @param type is true if RV and false if Tent
	 * @param row is the row we are dealing with
	 * @return a double of the cost
	 * @throws Exception
	 ******************************************************************/
	public double cost(int days, boolean type, int row) {
		if(type) {
			if(((RV) listSites.get(row)).getPow() == 50 && days >0) {
				return ((15*days) + listSites.get(row).cost());
			}
			else if(days > 0) {
                return ((10 * days) + listSites.get(row).cost());
            }
            else if(days == 0) {
                return listSites.get(row).cost();
            }
		}
		else {
			if(days > 0) {
                return ((5*days) + listSites.get(row).cost());
            }
            else if(days == 0) {
                return listSites.get(row).cost();
            }
           
        }
        return 0;   
		}
			
		
	/******************************************************************
	 * This method sorts by Dates Checked in as a Lambda
	 */
	public void SortByDatesCheckedIn() {
        listSites.sort((Site o1, Site o2)->o1.getCheckIn().
        		compareTo(o2.getCheckIn()));
    }
	
	/******************************************************************
	 * This method sorts using streams into an array of Sites that have
	 * 3 or more days checked in
	 * @return an array of sites that have more than 2 days checked in
	 */
	public Site[] SortStream() {
		Site[] temp = listSites.stream().filter((site) ->
		site.getDaysStaying() >= 3).toArray(Site[]::new);
		
		return temp;
	}
			
	/******************************************************************
	 * This method sorts the amount of days that are being stayed
	 */
	public void SortByDaysStaying() {
    Collections.sort(listSites, new Comparator<Site>() {
      @Override
      public int compare(Site o1, Site o2) {
    	  return (Integer.compare(o1.getDaysStaying(), o2.getDaysStaying()));
      }
  });
	}
	/******************************************************************
	 * This method calls the Sort By name class to sort the linked list
	 * by names.
	 */
	public void SortByName() {
		Collections.sort(listSites,new SortByName());
	}
	}
	
	