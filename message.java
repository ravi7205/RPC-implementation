import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class message extends JFrame
{	

	JButton Ok;
	JLabel mes;
	Container c;
	public message(String s,Client client)
	{
		mes=new JLabel(s);
		Ok=new JButton("OK");
		
		mes.setBounds(100,10,300,20);
		
		c=getContentPane();
		c.setLayout(null);
		Ok.setBounds(150,40,100,40);
		
		c.add(mes);
		c.add(Ok);

		Ok.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
		    if(s.equals("unable to login"))
		    {
			    new frame1(client);
		            
		    }
		    else
		    {	
			new menu(client);
			
		    }
		    dispose();
                }
            });

	        setVisible(true);
		setLocation(100,100);
		setSize(500,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Message");
		
	}
}
		
	
