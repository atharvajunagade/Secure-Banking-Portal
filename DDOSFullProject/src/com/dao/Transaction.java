package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is use for Database Operations. 
 * 
 *
 */

public class Transaction 
	{
	public static Connection con=null;
	public static ResultSet rs=null;

		public static Connection getConnection() 
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver"); // Register mysql driver by specifying name of driver class
			} 
			catch (ClassNotFoundException e)
			{

				e.printStackTrace();
			}
			try 
			{
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebank","root","root"); // get connection object required to prepare statement object
				return con;
			} 
			catch (SQLException e) 
			{

				e.printStackTrace();
			}
			return null;
		}

		/**
		 * Execute Non-select Query on Database table
		 * @param query
		 * @return
		 */
		public int check1(String query)
		{
			try
			{
				PreparedStatement psUpdate = con.prepareStatement(query);
				return psUpdate.executeUpdate();

			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			return 0;
		}
		
		/**
		 * Close the connection object of database
		 */
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
