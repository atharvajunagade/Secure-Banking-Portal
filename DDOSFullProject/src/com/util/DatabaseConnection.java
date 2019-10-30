package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to create database connection,statement object,prepared statement object and loading Drivers.
 * 
 *
 */
public class DatabaseConnection {
	public static Connection con=null;
	public DatabaseConnection(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e)
		{
 			e.printStackTrace();
		}
	    try 
	    {
			con = DriverManager.getConnection("jdbc:mysql://localhost/ebank?" +
			                                   "user=root&password=root");
		} 
	    catch (SQLException e) 
		{
 			e.printStackTrace();
		}
 	}
	
	public ResultSet selectQuery(String query)
	{
		try
		{
		       PreparedStatement psSelect = con.prepareStatement(query);
		       System.out.println("select query : "+query);
		       return psSelect.executeQuery();    
		}
		catch(SQLException e)
		{
				System.out.println("Here is exception");
			    e.printStackTrace(); 
		}
		return null;
	}
	
	public int updateQuery(String query)
	{
		int result = 0;
		try
		{
		       PreparedStatement psUpdate = con.prepareStatement(query);
		       result = psUpdate.executeUpdate();
		       return result;
		}
		catch(SQLException e)
		{
			result=-1;
		    e.printStackTrace();
		}
		return result;
	}
	public static void closeConnection()
	{
		try 
		{
		  if(con!=null)
		  {
			
				con.close();
		  } 
		}
		catch (SQLException e) 
		  {
				
				e.printStackTrace();
		  }
	}
}
 