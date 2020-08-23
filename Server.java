// A Java program for a Server 
import java.net.*; 
import java.sql.*;
import java.io.*; 
import java.util.*;

public class Server 
{ 
	//initialize socket and input stream 
	private Socket		 socket = null; 
	private ServerSocket server = null; 
	private DataInputStream in	 = null; 
	private DataOutputStream out = null;
	private String database;
	private String user;
	private String password;

	// constructor with port 
	public Server(int port) 
	{ 
		// starts server and waits for a connection 
		try
		{ 
			server = new ServerSocket(port); 
			System.out.println("Server started"); 

			System.out.println("Waiting for a client ..."); 

			socket = server.accept(); 
			System.out.println("Client accepted"); 

			// takes input from the client socket 
			in = new DataInputStream(socket.getInputStream());
			out= new DataOutputStream(socket.getOutputStream()); 

			//String line = ""; 

			// reads message from client until "Over" is sent 
			/*while (!line.equals("Over")) 
			{ 
				try
				{ 
					line = in.readUTF(); 
					System.out.println(line); 

				} 
				catch(IOException i) 
				{ 
					System.out.println(i); 
				} 
			} */
			//for(int i=0;i<5;i++)
			//	out.writeUTF(""+i);
			
			//System.out.println("Closing connection"); 

			// close connection 
			//socket.close(); 
			//in.close(); 
		} 
		catch(IOException i) 
		{ 
			System.out.println(i); 
		} 
	} 

	public static void main(String args[]) throws Exception
	{ 
		boolean flag1=true;
		String line="";
		Server server;
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/lab2","root","abcd1234");;
		server = new Server(Integer.valueOf(args[0]));
		
		while(flag1)	// Loop for establishing connection with database
		{	 
			try
			{
				line="";
				
				System.out.println("Waiting for credentials:");
				server.database=server.in.readUTF();
				String url="jdbc:mysql://localhost:3306/";
				url=url+server.database;

				
				
				server.user=server.in.readUTF();
				server.password=server.in.readUTF();
				con=DriverManager.getConnection(url,server.user,server.password);
				System.out.println("Connection with database established");
				server.out.writeUTF("Connection with database established");
				flag1=false;
			}
			catch(Exception h)
			{
				server.out.writeUTF("unable to login");
			}
		}
		
		

		
		while(true && !flag1)   //loop for providing functionalities to the client till he/she is connected
		{
			try
			{	
				
				
				


				//flag=true;
				line=server.in.readUTF();

				Statement st1=con.createStatement();
				Statement st2=con.createStatement();
				Statement st3=con.createStatement();
				ResultSet rs;
				
				String query,query2;

				int columnsNumber;
				ResultSetMetaData rsmd;					
				
				switch(Integer.parseInt(line))
				{
					case 1: // code for collecting information about table in database 
						query="select * from ";
						query2="select count(*) from ";
						line=server.in.readUTF();
						System.out.println("Got table name as "+line);
						query=query+line+";";
						query2=query2+line+";";
						//Class.forName("com.mysql.jdbc.Driver");
						

						for(int i=1;i<=5;i++)
							System.out.println();
					
						System.out.println("Please Ignore the above output");

						for(int i=1;i<=5;i++)
							System.out.println();

						

						String bookID;
						String bookName;
						String authorName;
						String name;
						String issueStatus;
						String rollNo;
						java.sql.Date date;
						
						rs=st1.executeQuery(query2);
						server.out.writeUTF("Query Executed Successfully");
						rs.next();
						int rows=rs.getInt(1);
						System.out.println("Rows: "+rows);
						server.out.writeUTF(""+rows);
						rs=st1.executeQuery(query);
						
						//System.out.println(rs.next());
						//System.out.println(rs.getString(2));
						
						rsmd = rs.getMetaData();

						columnsNumber = rsmd.getColumnCount();
						System.out.println("Columns: "+columnsNumber);
					
						server.out.writeUTF(""+columnsNumber);
						for(int i=1;i<=(columnsNumber);i++)
						{
							server.out.writeUTF(rsmd.getColumnName(i));
						}	
						
						while(rs.next())
						{
							System.out.println("Entered while loop ");	
							
											
							for(int i=1;i<=(columnsNumber);i++)
							{
		        					//System.out.println(rs.getString(i));
								switch(rsmd.getColumnType(i))
								{
									case 4: 
										server.out.writeUTF(""+rs.getInt(i));
							
										break;

									case 7: 
										server.out.writeUTF(""+rs.getFloat(i));
					
										break;
									case 12: if(rs.getString(i)!=null)
										server.out.writeUTF(""+rs.getString(i));
										else
										{
											System.out.println("Found null");
											server.out.writeUTF("null");
										}
										break;
									case 91: if(rs.getDate(i)!=null)
										server.out.writeUTF(""+rs.getDate(i));
										else
										{
											System.out.println("Found null");
											server.out.writeUTF("null");
										}
										break;
								}									
								
								//server.out.writeUTF(server.getDT(rsmd.getColumnType(i)));
								//server.out.writeUTF(rsmd.getColumnName(i));

							}
						}					
						/*while(rs.next())
						{
							System.out.println("Entered while loop ");	
							
											
							for(int i=1;i<=(columnsNumber);i++)
							{
							        System.out.println(rs.getString(i))								
								if(rs.getString(i)!=null)					
									server.out.writeUTF(rs.getString(i));
								else
								{
									System.out.println("Found null");
									server.out.writeUTF("null");
								}
								//server.out.writeUTF(server.getDT(rsmd.getColumnType(i)));
								//server.out.writeUTF(rsmd.getColumnName(i));

							}
								
								
								
						}*/
						
						//server.out.writeUTF("EOF");
						break;
								
					case 2:		// Code for creating table
							System.out.println("Create table Encountered");
							line=server.in.readUTF();
							System.out.println(line);
							st1.executeUpdate(line);
							server.out.writeUTF("Table Created Successfully");
							break;

					case 3:		//Code for insertion
							System.out.println("Insert in table encountered");
							line=server.in.readUTF();
							System.out.println(line);
							query="select * from ";
							query+=line;
							query+=";";

							rs=st1.executeQuery(query);
						
							//System.out.println(rs.next());
							//System.out.println(rs.getString(2));
							
							rsmd = rs.getMetaData();

							columnsNumber = rsmd.getColumnCount();
							System.out.println("Columns: "+columnsNumber);
							server.out.writeUTF("Query Executed Successfully");
						
							server.out.writeUTF(""+columnsNumber);

							for(int i=1;i<=(columnsNumber);i++)
							{
								System.out.println(rsmd.getColumnName(i));								
								server.out.writeUTF(rsmd.getColumnName(i));
							}

							line=server.in.readUTF();
							st1.executeUpdate(line);
							server.out.writeUTF("Query Executed Successfully");
							break;
					
					case 4:		//code for deletion
							System.out.println("Delete Table Record Encountered");

							line=server.in.readUTF();
								
							System.out.println("Delete Table record Name: "+line);

							query="select * from ";
							query+=line;
							query+=";";

							rs=st1.executeQuery(query);
						
							//System.out.println(rs.next());
							//System.out.println(rs.getString(2));
							
							rsmd = rs.getMetaData();

							columnsNumber = rsmd.getColumnCount();
							System.out.println("Columns: "+columnsNumber);
							server.out.writeUTF("Query Executed Successfully");

							server.out.writeUTF(""+columnsNumber);

							for(int i=1;i<=(columnsNumber);i++)
							{
								System.out.println(rsmd.getColumnName(i));								
								server.out.writeUTF(rsmd.getColumnName(i));
								server.out.writeUTF(""+rsmd.getColumnType(i));
							}
							
							query=server.in.readUTF();
							st1.executeUpdate(query);
							server.out.writeUTF("Records deleted successfully");

							break;	

					case 5:		// code for updation
							System.out.println("Update Table Record Encountered");

							line=server.in.readUTF();
								
							System.out.println("Update Table record Name: "+line);

							query="select * from ";
							query+=line;
							query+=";";


							rs=st1.executeQuery(query);
						
							//System.out.println(rs.next());
							//System.out.println(rs.getString(2));
							
							rsmd = rs.getMetaData();

							columnsNumber = rsmd.getColumnCount();
							System.out.println("Columns: "+columnsNumber);
							server.out.writeUTF("Query Executed Successfully");

							server.out.writeUTF(""+columnsNumber);

							for(int i=1;i<=(columnsNumber);i++)
							{
								System.out.println(rsmd.getColumnName(i));								
								server.out.writeUTF(rsmd.getColumnName(i));
								server.out.writeUTF(""+rsmd.getColumnType(i));
							}
							
							query=server.in.readUTF();
							st1.executeUpdate(query);
							server.out.writeUTF("Records updated successfully");
	
							
							
							break;
					case 6: 	// Code for deleting table
							System.out.println("Drop Table Encountered");

							line=server.in.readUTF();
								
							System.out.println("drop Table Name: "+line);
							st1.executeUpdate("drop table "+line+";");
							server.out.writeUTF("Table droped successfully");
							break;

				}

				

				
				if(line.equals("-994658"))
				{	
					break;
				}
				
			}
			catch(Exception i)
			{
				System.out.println("Error Detected");
				server.out.writeUTF(i.getMessage());				
				System.out.println(i.getMessage());
				
			}
		}
		System.out.println("Closing connection");			
		server.socket.close();
		server.in.close();
	} 
	
	public String getDT(int i)
	{
		switch(i)
		{
			case 4: return "Integer";
			case 6: return "Float";
			case 12: return "String";
			case 91: return "Date";
		}
	
		return "unknown";
	}
} 

