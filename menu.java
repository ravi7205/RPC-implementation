import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
// Code for displaying various functionalities

public class menu extends JFrame
{	

	JButton show;
	JButton create;
	JButton insert;
	JButton delete;
	JButton update;
	JButton drop;
	JLabel title;
	Container c;
	public menu(Client client)
	{
		title=new JLabel("Select a Functionality:");
		show=new JButton("Show Table");
		create=new JButton("Create Table");
		insert=new JButton("Insert record");
		delete=new JButton("Delete record");
		update=new JButton("Update record");
		drop=new JButton("Drop a table");
		
		title.setBounds(10,10,200,20);
		show.setBounds(10,40,200,20);
		create.setBounds(10,70,200,20);
		insert.setBounds(10,100,200,20);
		delete.setBounds(10,130,200,20);
		update.setBounds(10,160,200,20);
		drop.setBounds(10,190,200,20);

		setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
		
		c=getContentPane();
		c.setLayout(null);
		//Ok.setBounds(150,40,100,40);
		
		c.add(title);
		c.add(show);
		c.add(create);
		c.add(insert);
		c.add(delete);
		c.add(update);
		c.add(drop);

		show.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		            client.out.writeUTF("1");
			    new tableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });

		create.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		    	    	        
			    client.out.writeUTF("2");
			    new newtableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });
	       
		insert.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		    	    	        
			    client.out.writeUTF("3");
			    new inserttableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });
		
		delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		    	    	        
			    client.out.writeUTF("4");
			    new deletetableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });
		update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		    	    	        
			    client.out.writeUTF("5");
			    new updatetableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });
		drop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		   try
		   {	
		    	    	        
			    client.out.writeUTF("6");
			    new droptableinfo(client);
		            dispose();
		   }
		   catch(Exception ee)
		   {
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		   }
		
                }
            });
	       
	        setVisible(true);
		setLocation(100,100);
		//setLayout(new FlowLayout());
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Main Menu");
		
	}
}
		
	
