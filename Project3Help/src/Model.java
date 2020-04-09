import java.io.*;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Model extends AbstractTableModel {

	private ArrayList<Book> books;  

	public Model() {
		books = new ArrayList<Book>();
		books.add(new Book(1000, "Book1"));
		books.add(new Dictionary(1000, "Dict1", "French"));
		books.add(new Dictionary(2000, "Dict2", "English"));
	}

	public int getRowCount() {
		return books.size();
	}

	public int getColumnCount() {
		return 3; 
	}
	
	public void add () {
		
		books.add(new Dictionary(1000, "Dict3", "Swahili"));
		books.add(new Dictionary(2000, "Dict4", "Arabic"));
		fireTableRowsInserted(1, 5);
	}

	public Object getValueAt(int row, int col) {
	
		
		String temp="";
		switch (col) {
		case 0: {
			temp = books.get(row).name;
			return temp;
		}
		case 1: {
			temp = books.get(row).pages +"";
			return temp;
		}
		case 2: {
			if (books.get(row) instanceof Dictionary) 
				temp += (" " + (((Dictionary) books.get(row)).getType()));
			else
				temp = "";
				return temp;
		}
		}
		return "";
			
		
	}

	public void saveAsSerialized(String filename) {
		for(int x = 0; x<getRowCount(); x++) {
		String temp = books.get(x).name + ":" + books.get(x).pages;
		if (books.get(x) instanceof Dictionary) 
			temp += (":" + (((Dictionary) books.get(x)).getType()));
		try(  PrintWriter out = new PrintWriter( filename )  ){
		    out.println(temp);
		}
		catch(FileNotFoundException e) {}
	}
	}

	public void loadFromSerialized(String filename) {
		
	}
}
