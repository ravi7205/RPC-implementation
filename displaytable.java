import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//code for displaying table

public class displaytable extends JFrame
{
	JTable tab;
	JButton back;
	Container c;
	public displaytable(Object[][] data, String[] col_name,Client client)
	{
		back=new JButton("Go To Options");
                
		
		back.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new menu(client);
		    dispose();
                }
              });
		
	    tab = new JTable(data, col_name);
            tab.setPreferredScrollableViewportSize(new Dimension(400, 400));
            tab.setFillsViewportHeight(true);
	    setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
	    c=getContentPane();
	    c.setLayout(null);
	    c.add(back);
            JScrollPane sp = new JScrollPane(tab,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	    //tab.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	    //sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS); 
            //sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_ALWAYS); 
	    sp.setVisible(true);
            c.add(sp);

            setVisible(true);
            setSize(500,500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Records");
            setLayout(new FlowLayout());
		
	}
}
