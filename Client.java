// A Java program for a Client 
import java.net.*; 
import java.io.*; 
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.applet.*;

public class Client 
{ 
	// initialize socket and input output streams 
	public Socket socket		 = null; 
	public DataInputStream input = null; 
	public DataOutputStream out	 = null; 
	public String s;
	public Scanner scanner = null;
	public boolean success;

	// constructor to put ip address and port 
	public Client(String address, int port) 
	{ 
		// establish a connection 
		try
		{ 
			success=false;				
			socket = new Socket(address, port); 
			System.out.println("Connected");
			scanner=new Scanner(System.in); 

			// takes input from terminal 
			//input = new DataInputStream(System.in);
			 input = new DataInputStream(socket.getInputStream());

			// sends output to the socket 
			out = new DataOutputStream(socket.getOutputStream()); 
			success=true;
		} 
	//	catch(UnknownHostException u) 
	//	{ 
	//		System.out.println(u); 
	//	} 
		catch(Exception i) 
		{ 
			System.out.println(i); 
			new message(i.getMessage(),this);
		} 

		// string to read message from input
		s=""; 
		 
	} 

	public static void main(String args[]) 
	{ 
				
		Client client = new Client(args[0], Integer.parseInt(args[1])); 
		if(client.success)
		{
			//int i=0;
			frame1 obj=new frame1(client);
		}
		

		// keep reading until "Over" is input 
		/*while (!line.equals("5")) 
		{ 
			try
			{ 
				//line = input.readLine();
				line=input.readUTF(); 
				//out.writeUTF(line);
				System.out.println(line);		
				s=scanner.next();		 
			} 
			catch(IOException i) 
			{ 
				System.out.println(i); 
			} 
		} */
		//System.out.println("Starting to read from server");
		
	
		

		// close the connection 
		
	} 
} 

class frame1 extends JFrame
{
    JButton enter;
    JTextField tf1,tf3;
    JPasswordField tf2;	
    JLabel l1,l2,l3,backgrd;
    Container c;
    Image bg;
    ImageIcon BG;
    String line;
    int columns;

    public frame1(Client client)
    {
	line = "";
	columns=0;
	ArrayList<String> header=new ArrayList<String>();
        enter=new JButton("Enter");
	tf1=new JTextField(20);
        tf2=new JPasswordField(20);
	tf3=new JTextField(20);

	//JLabel background=new JLabel("",new ImageIcon("wallpaper.jpg"), JLabel.CENTER);
	
	//background.setBounds(0,0,500,500);
	setContentPane(new JLabel(new ImageIcon("wallpaper.jpg")));
	c=getContentPane();

	//add(background);
	 

	c.setLayout(null);
	tf1.setBounds(100,50,100,20);
	tf2.setBounds(100,90,100,20);
	tf3.setBounds(100,130,100,20);
	//tf2.setEchoCharacter('*');

	
        //update=new JButton("UPDATE ENTRY");
        //delete=new JButton("DELETE ENTRY");
        //show=new JButton("SHOW");
	l1=new JLabel("Username");
	l2=new JLabel("Password");
	l3=new JLabel("Database");

	l1.setBounds(10,10,100,100);
	l2.setBounds(10,50,100,100);
	l3.setBounds(10,90,100,100);
	enter.setBounds(60,160,80,30);

        //add(enter);
	
	//setOpaque(true);
	c.add(tf1);
	c.add(tf2);
	c.add(tf3);
	c.add(l1);
	c.add(l2);
	c.add(l3);
		
	c.add(enter);
        //add(update);
        //add(delete);
        //add(show);
	
        

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //new frame2();
		try
		{		
			String st1=tf1.getText();
			String st2=tf2.getText();
			String st3=tf3.getText();
			System.out.println(st1);
			System.out.println(st2);
			client.out.writeUTF(st3);
			client.out.writeUTF(st1);
			client.out.writeUTF(st2);
			
			line="";
			line=client.input.readUTF();
			if(line.equals("Connection with database established"))
			{
								
				
				//new tableinfo(client);
				new menu(client);
				dispose();
				
				/*while(!line.equals("quit"))
				{
					System.out.println("Type \"quit\" to terminate");
					line=client.scanner.next();
					client.out.writeUTF(line);
				}*/

				/*System.out.println("Connection Terminated!!");
				client.input.close(); 
				client.out.close(); 
				client.socket.close();*/

			}
			else
			{
				System.out.println(line);
				new message(line,client);
				dispose();
			}
			 
			
			
		}
		catch(Exception ee)
		{
			//System.out.println("This is the one");			
			System.out.println(ee.getMessage());
			new message(ee.getMessage(),client);
			dispose();
		}
                //dispose();
            }
        });

        

        setVisible(true);
	setLocation(100,100);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Enter Credentials");
        //setLayout();
    }
}

