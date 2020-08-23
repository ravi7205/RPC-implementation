import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// Code for getting information of table on which we want to perform operation

public class updatetableinfo extends JFrame
{
	JLabel l1;
	JTextField tf1;
	JButton enter;
	Container c;
	
	public updatetableinfo(Client client)
	{
		l1=new JLabel("Enter Table Name:");
		tf1=new JTextField(20);
		enter=new JButton("Enter");
		
		l1.setBounds(90,10,200,20);
		tf1.setBounds(100,50,100,20);
		
		enter.setBounds(110,80,80,30);
		setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
		c=getContentPane();
		c.setLayout(null);

		c.add(l1);
		c.add(tf1);
		c.add(enter);
		
		enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //client.input.writeUTF("1");
		    //new tableinfo(client);
                    //dispose();
		    try
		   {	
		            String st1=tf1.getText();
			    System.out.println("st1: "+st1);
			    client.out.writeUTF(st1);
			    String table_name=st1;
			    st1=client.input.readUTF();
			    ArrayList<String> header=new ArrayList<String>();
			    ArrayList<JLabel> label=new ArrayList<JLabel>();
			    
			    //ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();
			    	
			    System.out.println("Compiled successfully");		
			    if(st1.equals("Query Executed Successfully"))
			    {
				//int rows=Integer.parseInt(client.input.readUTF());
				int columns=Integer.parseInt(client.input.readUTF());
				System.out.println("Columns:"+columns);
				//System.out.println("Rows:"+rows);
				int[] dType=new int[columns];
				//String[] col_name=new String[columns];
				//Object[][] data =new Object[rows][columns] ;
				for(int i=0;i<columns;i++)
				{
					st1=client.input.readUTF();
				//	System.out.println(st1);
					header.add(st1);
					dType[i]=Integer.parseInt(client.input.readUTF());
					
				}

				for(int i=0;i<columns;i++)
				{
					System.out.println(header.get(i));
					label.add(new JLabel(header.get(i)));	
				}

				System.out.println("Data types:");

				for(int i=0;i<columns;i++)
				{
					System.out.println(dType[i]);
					//label.add(new JLabel(header.get(i)));	
				}				

				new updaterecordinfo(client,table_name,label,columns,dType,header);
				dispose();

				/*for(int i=0;i<columns;i++)
				{
					
					col_name[i]=header.get(i);
					System.out.println(col_name[i]);
				}	
				
				for(int i=0;i<rows;i++)
				{
					//data.add(new ArrayList<String>());					
					for(int j=0;j<columns;j++)
					{
						st1=client.input.readUTF();
						System.out.println(st1);						
						data[i][j]=st1;
			
					}
				}*/		
				
				//new displaytable(data,col_name,client);
			
			    //	dispose();
			    }
			    else
		            {
				System.out.println(st1);
				new message(st1,client);
				dispose();
			    }
			    

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
		setTitle("Table info");
		
	}
	 


}

