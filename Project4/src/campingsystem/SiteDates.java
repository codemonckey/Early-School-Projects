package campingsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;

/**********************************************************************
 * SiteDates class that reserves the dates. Uses SiteInfo for
 * array of booleans, setting false if they're unreserved, true if
 * reserved.
 *  
 * @version November 1 2017
 *********************************************************************/
public class SiteDates {
	
	/** Constant for number of years to add to HashMap of 
		reservations */
	private final int NUMBER_OF_YEARS = 20;

	/** HashMap using date Strings as Keys, and SiteInfo.
		Used for reserving sites.*/
	private HashMap<String,SiteInfo> map;
	
	/** Formatter for date Strings*/
	private SimpleDateFormat formatCal;
	

	/******************************************************************
	 * Instantiates a hashmap of Strings and SiteInfo, using Strings
	 * of valid dates from the current date to number of years 
	 * constant. 
	 *****************************************************************/
	public SiteDates() {
		
		formatCal = new SimpleDateFormat("M/d/yyyy");
		map = new HashMap<String, SiteInfo>();
		int leap_year_included = NUMBER_OF_YEARS * 365;
		for(int x = 0; x<=NUMBER_OF_YEARS; x++) 
			if(isLeapYear(x+2018))
				leap_year_included++;
				
		
		for(int i = 0; i < leap_year_included; i++) {
			SiteInfo site = new SiteInfo();
			GregorianCalendar cal = new GregorianCalendar(2018,0,0);
			cal.add(Calendar.DATE, i);
			String date = formatCal.format(cal.getTime());
			System.out.print(date);
			map.put(date, site);
		}
	}
	/*****************************************************************
	 * This tests to see if it's a lep year
	 * @param year is the year testing
	 * @return true if it is a leap year
	 */
	public boolean isLeapYear(int year) {
		return (year%4 == 0 && (year%100 != 0 || year%400 == 0));
	}
	
	/******************************************************************
	 * Getter method of hashmap
	 * @return hashmap of date strings and SiteInfo
	 *****************************************************************/
	public HashMap<String,SiteInfo> getHashMap(){
		return map;
	}

	
	/******************************************************************
	 * Checks if there is already a site reserved in the dates and
	 * site that site parameter wishes to reserve in.
	 * @param pSite	Site to check availability in hashmap
	 * @return	true if already reserved, false if open.
	 * @throws ParseException	Shouldn't hit. Error check with parse.
	 * @throws InvalidDate		Invalid date
	 *****************************************************************/
	public boolean isReserved(Site pSite)throws ParseException,InvalidDate{

		String date = formatCal.format(pSite.getCheckIn().getTime());
		int daysStaying = pSite.getDaysStaying();
		int site = pSite.getSiteNum();

		for (int i = 0; i < daysStaying; i++) {
			try {
				if (map.get(date).getAt(site))
					return true;
				Calendar cal = Calendar.getInstance();
				cal.setTime(formatCal.parse(date));
				cal.add(Calendar.DATE, 1);
				date = formatCal.format(cal.getTime());

			} catch (NullPointerException e) {
				throw new InvalidDate();
			}
		}
		return false;
	}
	
	/******************************************************************
	 * Reserves a site on the requested days for parameter t
	 * @param pSite	Site to reserve spots.
	 * @throws ParseException		Error during parsing
	 *****************************************************************/
	public void reserve(Site pSite) throws ParseException {
		
		String date = formatCal.format
				(pSite.getCheckIn().getTime());
		
		int daysStaying = pSite.getDaysStaying();
		int site = pSite.getSiteNum();
		
		for(int i = 0; i < daysStaying; i++) {
			
			//Set current date as taken (true)
			map.get(date).setAt(site, true);
			
			//Convert date to calendar to add 1 day
			Calendar cal = Calendar.getInstance();
			cal.setTime(formatCal.parse(date));
			cal.add(Calendar.DATE, 1);
			date = formatCal.format(cal.getTime());
		}
	}
	
	/******************************************************************
	 * Removes the reservation for the request site. Used in deletion
	 * or undo methods.
	 * @param pSite	Site to remove reservation
	 *****************************************************************/
	public void removeReservation(Site pSite){
		try {
			String date = formatCal.format(pSite.getCheckIn().getTime());

			int daysStaying = pSite.getDaysStaying();
			int site = pSite.getSiteNum();

			for (int i = 0; i < daysStaying; i++) {

				// Set current date as taken (true)
				map.get(date).setAt(site, false);

				// Convert date to calendar to add 1 day
				Calendar cal = Calendar.getInstance();
				cal.setTime(formatCal.parse(date));
				cal.add(Calendar.DATE, 1);
				date = formatCal.format(cal.getTime());
			}
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "ERROR");
		}

	}
	
	/******************************************************************
	 * Returns array of booleans to see the sites that are reserved
	 * or open for the parameter date
	 * @param date	date to check site's reservations.
	 * @return	returns array of booleans for each site
	 *****************************************************************/
	public boolean[] reservedForDay(String date) {
		return map.get(date).getAvailable();
	}
	
	/******************************************************************
	 * Returns all dates where all sites are reserved.
	 * @return	ArrayList of Strings of dates that are reserved
	 *****************************************************************/
	public ArrayList<String> fullyReservedSites(){
		ArrayList<String> bookedDays = new ArrayList<String>();
		for(String keyDate : map.keySet()) {
			if(map.get(keyDate).allReserved()) {
				bookedDays.add(keyDate);
			}
		}
		return bookedDays;
	}

}