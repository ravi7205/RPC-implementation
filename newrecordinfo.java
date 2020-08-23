import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


// Code for inserting a new record

class radio 
{
	public JRadioButton r1,r2,r3,r4;

	public radio()
	{
		r1=new JRadioButton("Int");
		r2=new JRadioButton("Float");
		r3=new JRadioButton("String");
		r4=new JRadioButton("Date");
	}
}

public class newrecordinfo extends JFrame
{
	JButton enter;
	
	ArrayList<JTextField> textfield;
	//ArrayList<radio> radiobutton;
	ArrayList<ButtonGroup> buttongroup;

	JLabel l1,l2;

	Container c;

	public newrecordinfo(Client client, String st1, ArrayList<JLabel> label,int col)
	{
		enter=new JButton("Enter");
		//radiobutton=new ArrayList<radio>();
		//buttongroup=new ArrayList<ButtonGroup>();
		//label=new ArrayList<JLabel>();
		textfield=new ArrayList<JTextField>();
		String info="Column ";
		//System.out.println("Found Database name: "+ name);
		System.out.println("Found number of columns:" + col);
		System.out.println("Found table name: "+st1);
		setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
		c=getContentPane();
		c.setLayout(null);

		l1=new JLabel("Column Value (if date then enter in YYYY-MM-DD format)");
		l1.setBounds(110,0,450,20);
		c.add(l1);

		/*l2=new JLabel("Data Type");
		l2.setBounds(310,0,100,20);
		c.add(l2);*/
		
		int lWide=10,lHei=40,tWide=110,tHei=40;
		for(int i=0;i<col;i++)
		{
			//int number=i+1;		
			//label.add(new JLabel(info+number));
			label.get(i).setBounds(lWide,lHei,100,20);
			c.add(label.get(i));
			lHei+=40;

			/*radiobutton.add(new radio());
			buttongroup.add(new ButtonGroup());
			
			radiobutton.get(i).r1.setBounds(230,lHei,50,20);
			radiobutton.get(i).r2.setBounds(290,lHei,60,20);
			radiobutton.get(i).r3.setBounds(360,lHei,80,20);
			radiobutton.get(i).r4.setBounds(440,lHei,80,20);
			//for(int j=0;j<4;j++)
			
			buttongroup.get(i).add(radiobutton.get(i).r1);
			buttongroup.get(i).add(radiobutton.get(i).r2);
			buttongroup.get(i).add(radiobutton.get(i).r3);
			buttongroup.get(i).add(radiobutton.get(i).r4);

			

			c.add(radiobutton.get(i).r1);
			c.add(radiobutton.get(i).r2);
			c.add(radiobutton.get(i).r3);
			c.add(radiobutton.get(i).r4);*/

		}

		for(int i=0;i<col;i++)
		{
			textfield.add(new JTextField(20));
			textfield.get(i).setBounds(tWide,tHei,100,20);
			c.add(textfield.get(i));
			tHei+=40;
		}

		enter.setBounds(160,tHei,80,30);
		c.add(enter);
		/*
		l1=new JLabel("Table Name:");
		l2=new JLabel("No. of Columns:");
		tf1=new JTextField(20);
		tf2=new JTextField(20);
		c=getContentPane();
		c.setLayout(null);

		//l1.setBounds(10,10,100,100);
		//l2.setBounds(10,50,200,100);

		tf1.setBounds(130,50,100,20);
		tf2.setBounds(130,90,100,20);*/

		//enter.setBounds(60,120,80,30);
		
		enter.addActionListener(new ActionListener() {
			//@override
			public void actionPerformed(ActionEvent e){
			
				try
				{
					System.out.println(textfield.get(0).getText());
					String cmd="insert into "+st1+" values(";
					for(int i=0;i<col;i++)
					{	
						if(i!=0)
							cmd+=",";	
						cmd+="'";					
						cmd+=textfield.get(i).getText();
						cmd+="'";
						
						/*cmd+=" ";
						if(radiobutton.get(i).r1.isSelected())
						{
							cmd+="int";
						}
						else if(radiobutton.get(i).r2.isSelected())
						{
							cmd+="float";
						}
						else if(radiobutton.get(i).r3.isSelected())
						{
							cmd+="varchar(100)";
						}
						else if(radiobutton.get(i).r4.isSelected())
						{
							cmd+="date";
						}
						else
						{
							System.out.println("Data type not Selected");							
							new message("Data type not selected!!",client);
							dispose();
							break;

						}*/

	
					}
					
					cmd+=");";

					System.out.println(cmd);

					client.out.writeUTF(cmd);

					String line=client.input.readUTF();
					if(line.equals("Query Executed Successfully"))
					{
						new message("record inserted successfully",client);
						dispose();
					}
					else
					{
						new message(line,client);
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
		/*c.add(l1);
		c.add(l2);
		c.add(tf1);
		c.add(tf2);*/
		//c.add(enter);

		setVisible(true);
		setLocation(100,100);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("New Record Details");
	}

	
}


