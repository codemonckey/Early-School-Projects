package project1;

import javax.swing.*;
/***********************************************************************
 * Implements the methods shown in the StopWatch panel and duplicates
 * it into three different panels of a StopWatch.  
 * 
 * @Author Isaac Dessert
 * @version 1.0
 * 
 **********************************************************************/

public class StopWatchPanelMain extends JPanel {

	private StopWatchPanel p1;
	private StopWatchPanel p2;
	private StopWatchPanel p3;
    
    public StopWatchPanelMain(JMenuItem quit,JMenuItem suspend){
    	p1 = new StopWatchPanel(quit, suspend, "1");
    	p2 = new StopWatchPanel(quit, suspend, "2");
    	p3 = new StopWatchPanel(quit, suspend, "3");
    	
     add (p1);
     add (p2);
     add (p3);
    }
    
}
