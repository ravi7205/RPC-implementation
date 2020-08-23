import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Code for getting table information on which we want to perform operation

public class newtableinfo extends JFrame
{
	JButton enter;
	JLabel l1,l2;
	JTextField tf1,tf2;
	Container c;

	public newtableinfo(Client client)
	{
		enter=new JButton("Enter");
		l1=new JLabel("Table Name:");
		l2=new JLabel("No. of Columns:");
		tf1=new JTextField(20);
		tf2=new JTextField(20);
		setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
		c=getContentPane();
		c.setLayout(null);

		l1.setBounds(10,10,100,100);
		l2.setBounds(10,50,200,100);

		tf1.setBounds(130,50,100,20);
		tf2.setBounds(130,90,100,20);

		enter.setBounds(60,120,80,30);
		
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e){
			
				try
				{
					String st1=tf1.getText();
					String st2=tf2.getText();

					new columnsinfo(client,st1,Integer.parseInt(st2));
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
		c.add(l1);
		c.add(l2);
		c.add(tf1);
		c.add(tf2);
		c.add(enter);

		setVisible(true);
		setLocation(100,100);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("New Table Details");
	}

	
}


