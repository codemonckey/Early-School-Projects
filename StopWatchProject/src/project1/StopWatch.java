package project1;

import java.io.*;
import java.util.Scanner;


/***********************************************************************
 * 
 * Implement the following methods and properties in StopWatch class. 
 * For properties, you will need three instance variables: minutes 
 * (integer), seconds (integer), milliseconds (integer). For methods,
 * you will need to implement the following (include getters and 
 * setters as needed). At this point, assume all values of parameters 
 * are valid (i.e., within range) numbers.
 * 
 * @author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/

public class StopWatch {

	public static void suspend(boolean flag) {
		suspend = flag;

	}

	/** Used to suspend mutator methods from working */
	private static boolean suspend = false;
	/** used to store minutes in the StopWatch object */
	private int minutes;

	/** used to store seconds in the StopWatch object */
	private int seconds;

	/** used to store milliseconds in the StopWatch object */
	private int milliseconds;
	

	/*****************************************************************
	 * A get method that retrieves minutes as an integer
	 * 
	 * @return the minutes as an integer
	 ****************************************************************/
	public int getMinutes() {
		return minutes;
	}

	/*****************************************************************
	 * A set method that sets minutes of "this" in minutes
	 * 
	 * @param minutes
	 *            represents an integer of minutes
	 ****************************************************************/

	public void setMinutes(int minutes) {
		if (minutes < 0)
			throw new IllegalArgumentException();
		else
			this.minutes = minutes;
	}

	/*****************************************************************
	 * a get method that retrieves seconds
	 * 
	 * @return seconds of "this" as an int
	 ****************************************************************/

	public int getSeconds() {
		return seconds;
	}

	/*****************************************************************
	 * a set method that sets your program with minutes
	 * 
	 * @param seconds
	 *            represent an integer of seconds
	 ****************************************************************/
	public void setSeconds(int seconds) {
		if (seconds < 0)
			throw new IllegalArgumentException();
		else if (seconds > 59) {
			this.minutes = seconds / 60;
			this.seconds = seconds % 60;
		} else
			this.seconds = seconds;
	}

	/*****************************************************************
	 * a get method that retrieves milliseconds of "this"
	 * 
	 * @return milliseconds of "this" as an int
	 ****************************************************************/
	public int getMilliseconds() {
		return milliseconds;
	}

	/*****************************************************************
	 * a set method that sets your program with milliseconds
	 * 
	 * @param milliseconds represents an integer of milliseconds
	 ****************************************************************/
	public void setMilliseconds(int milliseconds) {
		if (milliseconds < 0)
			throw new IllegalArgumentException();
		else if (milliseconds > 60000) {
			this.minutes = milliseconds / 60000;
			this.seconds = milliseconds % 60000 / 1000;
			this.milliseconds = milliseconds % 60000 % 1000;
		} else if (milliseconds > 1000) {
			this.seconds = milliseconds / 1000;
			this.milliseconds = milliseconds % 1000;
		} else
			this.milliseconds = milliseconds;
	}

	/******************************************************************
	 * A constructor that initializes the instance variables with the
	 * provided values
	 * 
	 * @param minutes
	 *            represents the number of minutes
	 * @param seconds
	 *            represents the number of seconds
	 * @param milliseconds
	 *            represents the number of milliseconds
	 *****************************************************************/
	public StopWatch(int minutes, int seconds, int milliseconds) {
		if (minutes < 0 || (seconds > 59) || (seconds < 0) || 
				(milliseconds < 0) || (milliseconds > 999))
			throw new IllegalArgumentException();

		this.minutes = minutes;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/******************************************************************
	 * A constructor that initializes the instance variables with the
	 * provided values
	 * 
	 * @param seconds
	 *            represents the number of seconds
	 * @param milliseconds
	 *            represents the number of milliseconds
	 *****************************************************************/

	public StopWatch(int seconds, int milliseconds) {
		if ((seconds > 59) || (seconds < 0) || (milliseconds < 0) 
				|| milliseconds > 999)
			throw new IllegalArgumentException();
		this.minutes = 0;
		this.seconds = seconds;
		this.milliseconds = milliseconds;
	}

	/*******************************************************************
	 * A constructor that initializes the instance variables with the
	 * provided values
	 * 
	 * @param milliseconds
	 *            represents the number of milliseconds
	 ******************************************************************/
	public StopWatch(int milliseconds) {
		if (milliseconds > 0 || milliseconds < 999) {
			this.milliseconds = milliseconds;
		this.seconds = 0;
		this.minutes = 0;
		}
	}

	/*******************************************************************
	 * Default constructor that sets the StopWatch to zero
	 ******************************************************************/
	public StopWatch() {
		super();
	}

	/********************************************************************
	 * A constructor that accepts a string as a parameter with the 
	 * following format: “1:21:300” where 1 indicates minutes, 21 
	 * indicates seconds, and 300 indicates milliseconds. OR the format 
	 * “15:200” where the 15 indicates seconds, and 200 indicates 
	 * milliseconds, OR the format “300” where 300 indicates 
	 * milliseconds. If a value is not specified, then it is set to
	 *  zero. You can assume the input has no errors (i.e., a valid 
	 *  set of numbers) contained with in.
	 * 
	 * @param startTime represents a String containing milliseconds,
	 *  seconds and minutes all separated by colons.
	 *******************************************************************/
	public StopWatch(String startTime) {

		String[] s = startTime.split(":");

		if (s.length == 3) {
			if ((Integer.parseInt(s[0]) < 0) || 
					(Integer.parseInt(s[1]) > 59) ||
					(Integer.parseInt(s[1]) < 0) || 
					(Integer.parseInt(s[2]) < 0) || 
					Integer.parseInt(s[2]) > 999)
				throw new IllegalArgumentException();

			this.minutes = Integer.parseInt(s[0]);
			this.seconds = Integer.parseInt(s[1]);
			this.milliseconds = Integer.parseInt(s[2]);
		}

		else if (s.length == 2) {
			if ((Integer.parseInt(s[0]) > 59) || 
					(Integer.parseInt(s[0]) < 0) ||
					(Integer.parseInt(s[1]) < 0) || 
					Integer.parseInt(s[1]) > 999)
				throw new IllegalArgumentException();
			this.seconds = Integer.parseInt(s[0]);
			this.milliseconds = Integer.parseInt(s[1]);
		}

		else if (s.length == 1) {
			if ((Integer.parseInt(s[0]) < 0) ||
					Integer.parseInt(s[0]) > 999)
				throw new IllegalArgumentException();
			this.milliseconds = Integer.parseInt(s[0]);
		} else

			throw new IllegalArgumentException();

	}

	/************************************************************************
	 * A method that returns true if “this” StopWatch object is exactly the
	 * same as the other StopWatch object; this.minutes equals ((StopWatch)
	 * other).minutes and so on.
	 *
	 * @param other is an object that holds a StropWatch numeric
	 * @return boolean of whether two StopWatches equal each other
	 ***********************************************************************/
	public boolean equals(Object other) {

		if (other instanceof StopWatch) {
			StopWatch temp = (StopWatch) other;

			if ((this.minutes == temp.minutes) && 
					(this.seconds == temp.seconds)
					&& (this.milliseconds == temp.milliseconds))
				return true;
			else
				return false;
		}
		return false;
	}

	/*************************************************************************
	 * A method that returns true if “this” StopWatch object is exactly the 
	 * same as the other StopWatch object; this.minutes equals other.minutes 
	 * and this.seconds equals other.seconds and so on.
	 * 
	 * @param other represents a StopWatch object
	 * @return boolean of whether two StopWatches equal each other
	 ************************************************************************/
	public boolean equals(StopWatch other) {

		if ((this.minutes == other.minutes) && (this.seconds == other.seconds)
				&& (this.milliseconds == other.milliseconds))
			return true;
		else
			return false;

	}

	/***********************************************************************
	 * A static method that returns true if StopWatch object s1 is exactly 
	 * the same as StopWatch object s2.
	 * 
	 * @param s1 represents the first parameter being compared
	 * @param s2 represents the second parameter being compared
	 * @return true if s1 and s2 are the same
	 **********************************************************************/
	public boolean equals(StopWatch s1, StopWatch s2) {

		if (s1.toString().equals(s2.toString()))
			return true;
		else
			return false;

	}
    
	/***********************************************************************
	 * A method that saves the “this” StopWatch to a file
	 * 
	 * @param fileName holds the value and places it in a file
	 **********************************************************************/
	public void save(String fileName) {
        PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter
					(new FileWriter(fileName)));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(this.minutes);
		out.println(this.seconds);
		out.println(this.milliseconds);
		out.close();
	}

	/***********************************************************************
	 * A method that loads the “this” StopWatch from a file
	 * 
	 * @param fileName represents a scanner that will read the file of this
	 **********************************************************************/
	public void load(String fileName) {
        
		try {
			// open the data file
			Scanner fileReader = new Scanner(new File(fileName));

			this.setMinutes(fileReader.nextInt());
			this.setSeconds(fileReader.nextInt());
			this.setMilliseconds(fileReader.nextInt());
			fileReader.close();
		}

		// problem reading the file
		catch (Exception error) {
			System.out.println("Oops!  Something went wrong.");
		}
	
	}

	/**********************************************************************
	 * A method that returns 1 if “this” StopWatch object is greater than 
	 * the other StopWatch object; returns -1 if the “this” StopWatch 
	 * object is less than the other StopWatch; returns 0 if the “this” 
	 * StopWatch object is equal to the other StopWatch object
	 * 
	 * @param other represents an object of Stopwatch that is compared.
	 * @return a number that equals which StopWatch is greater.
	 *********************************************************************/
	public int compareTo(StopWatch other) {
		if ((minutes < 0) || (seconds > 59) || (seconds < 0) || 
				(milliseconds < 0))
			throw new IllegalArgumentException();

		int temp1 = this.milliseconds + this.seconds * 1000 + 
				this.minutes * 60000;
		int temp2 = other.milliseconds + other.seconds * 1000 + 
				other.minutes * 60000;

		if (temp1 > temp2)
			return 1;
		else if (temp1 < temp2)
			return -1;
		else
			return 0;
	}

	/***********************************************************************
	 * A method that adds the number of milliseconds to “this” StopWatch
	 * object. You may assume in this step the parameter “milliseconds” 
	 * is positive. Suggestion: call the inc method within a loop.
	 * 
	 * @param milliseconds
	 *            represents an integer of milliseconds
	 **********************************************************************/

	public void add(int milliseconds) {
		if (suspend == false) {
			if (milliseconds < 0)
				throw new IllegalArgumentException();
			else {
				int temp1 = this.milliseconds + this.seconds * 1000 + 
						this.minutes * 60000;
				temp1 += milliseconds;
				this.setMinutes(temp1 / 60000);
				this.setSeconds((temp1 % 60000) / 1000);
				this.setMilliseconds((temp1 % 60000) % 1000);
			}

		}
	}

	/************************************************************************
	 * A method that adds StopWatch other to the “this” StopWatch.
	 * Suggestion: convert “other” to milliseconds and call the 
	 * add(int milliseconds) method
	 * 
	 * @param other represents an object of type StopWatch
	 **********************************************************************/
	public void add(StopWatch other) {
		if (suspend == false) {
			if (other.milliseconds < 0 || other.seconds < 0 || 
					other.minutes < 0)
				throw new IllegalArgumentException();
			else {
				int temp1 = this.milliseconds + this.seconds * 1000 
						+ this.minutes * 60000;
				int temp2 = other.milliseconds + other.seconds * 1000 
						+ other.minutes * 60000;

				temp1 = temp1 + temp2;
				this.setMinutes(temp1 / 60000);
				this.setSeconds((temp1 % 60000) / 1000);
				this.setMilliseconds((temp1 % 60000) % 1000);
			}
		}
	}

	/************************************************************************
	 * A method that increments the “this” StopWatch by 1 millisecond
	 ***********************************************************************/
	public void inc() {
		if (suspend == false) {
			// adds 1 to the total then sets all methods if suspend is false
			int temp1 = this.milliseconds + this.seconds * 1000 +
					this.minutes * 60000 + 1;
			this.setMinutes(temp1 / 60000);
			this.setSeconds((temp1 % 60000) / 1000);
			this.setMilliseconds((temp1 % 60000) % 1000);
		}
	}

	/************************************************************************
	 * A method that subtracts the number of milliseconds from “this” 
	 * StopWatch object. You may assume in this step the parameter 
	 * “milliseconds” is positive.  Suggestion: call the inc method 
	 * within a loop.
	 * 
	 * @param milliseconds represents an integer of milliseconds
	 ***********************************************************************/
	public void sub(int millisecond) {
		if (suspend == false) {
			int temp1 = this.milliseconds + this.seconds * 1000 + 
					this.minutes * 60000;
			if (temp1 < millisecond) {
				throw new IllegalArgumentException();
			} else {
				temp1 -= millisecond;
				this.setMinutes(temp1 / 60000);
				this.setSeconds((temp1 % 60000) / 1000);
				this.setMilliseconds((temp1 % 60000) % 1000);
			}
		}
	}

	/************************************************************************
	 * A method that subtracts StopWatch other from the “this” StopWatch.
	 * Suggestion: convert “other” to milliseconds and call the sub(int
	 * milliseconds) method
	 * 
	 * @param other represents a object of type StopWatch
	 ***********************************************************************/
	public void sub(StopWatch other) {
		if (suspend == false) {
			int temp1 = this.milliseconds + this.seconds * 1000 + 
					this.minutes * 60000;
			int temp2 = other.milliseconds + other.seconds * 1000 + 
					other.minutes * 60000;
			if (temp1 < temp2) {
				throw new IllegalArgumentException();
			} else {
				temp1 = temp1 - temp2;
				this.setMinutes(temp1 / 60000);
				this.setSeconds((temp1 % 60000) / 1000);
				this.setMilliseconds((temp1 % 60000) % 1000);
			}
		}
	}

	/************************************************************************
	 * A method that decrements the “this” StopWatch by 1 millisecond
	 ***********************************************************************/
	public void dec() {
		if (suspend == false) {
			if (this.milliseconds != 0 || this.seconds != 0 || 
					this.minutes != 0) {
				int temp1 = this.milliseconds + this.seconds * 1000 + 
						this.minutes * 60000;
				temp1 -= 1;

				setMinutes(temp1 / 60000);
				setSeconds((temp1 % 60000) / 1000);
				setMilliseconds((temp1 % 60000) % 1000);
			}
		}
	}

	/************************************************************************
	 * Method that returns a string that represents a StopWatch with
	 * the following format: “1:06:010”. Display the minutes as is; if 
	 * seconds < 10 then display with a leading “0”, and always display 
	 * milliseconds with 3 digits
	 ***********************************************************************/
	public String toString() {
		if (seconds > 9 && milliseconds > 99)
			return minutes + ":" + seconds + ":" + milliseconds;
		else if (seconds > 9 && milliseconds > 9)
			return minutes + ":" + seconds + ":0" + milliseconds;
		else if (seconds > 9)
			return minutes + ":" + seconds + ":00" + milliseconds;
		else if (milliseconds > 99)
			return minutes + ":0" + seconds + ":" + milliseconds;
		else if (milliseconds > 9)
			return minutes + ":0" + seconds + ":0" + milliseconds;
		else
			return minutes + ":0" + seconds + ":00" + milliseconds;
	}

}
