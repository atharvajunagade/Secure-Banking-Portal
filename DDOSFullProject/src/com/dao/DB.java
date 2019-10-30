package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.model.Account;
import com.model.User;
import com.util.DatabaseConnection;

/**
 * This class is use for Database operations.  
 * 
 *
 */
public class DB {

	public String validateUser(String username, String password, String table)   // This method is used to check whether user login details are correct or not based on Email and Password
	{
		Connection con = Transaction.getConnection();
		
		String query = "select * from "+table+" where username=? and password=?";  // This is the Parameterized Query
		System.out.println("Here is : "+query);
		ResultSet rs = null; 	 // This is object object declaration of Resultset and assigning it to null
		try
		{			
			PreparedStatement ps=con.prepareStatement(query);  // This is Object creation by using PreparedStatement to avoid SQL injection
			ps.setString(1,username); 
			ps.setString(2,password); 
			rs = ps.executeQuery() ;  //Executes the SQL query in this PreparedStatement object and returns the ResultSet object generated by the query.

			if(rs.next())
			{
				return "success";
			}
			else
			{
				return "failure";
			}
		}
		
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(rs!=null)
				{
					rs.close();
					Transaction.closeConnection();
				}
			} 
			catch(SQLException e)
			{

				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<User> getData()
	{
		ArrayList<User> list = new ArrayList<User>();
		Connection con1 = Transaction.getConnection();
		String query1 = "select * from user";
		ResultSet rs1 = null;
		
		try
		{
			Statement stmt1 = con1.createStatement();
			rs1 = stmt1.executeQuery(query1) ;
			while(rs1.next())
			{
				User user=new User();
				user.setFname(rs1.getString("first_name"));
				user.setLname(rs1.getString("last_name"));
				list.add(user);
				
			}
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	
		finally
		{
			try 
			{
				if(rs1!=null)
				{
					rs1.close();
					Transaction.closeConnection();
				}
			} 
			catch(SQLException e)
			{

				e.printStackTrace();
			}
		}
		return null;
	}
		public ArrayList<Account> getData1()
		{
			ArrayList<Account> list = new ArrayList<Account>();
			DatabaseConnection db = new DatabaseConnection();
			ResultSet rs = db.selectQuery("select accountno,balance+credit-debit,date from transactions,user where transactions.tuid=user.uid");
			
			try
			{
				while(rs.next())
				{
					Account acc=new Account();
					acc.setTitle(rs.getString("accountno").toString());
					acc.setBalance(rs.getString("balance").toString());
					acc.setLastTrans(rs.getString("date").toString());
					list.add(acc);
				}
				return list;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		
			finally
			{
				try 
				{
					if(rs!=null)
					{
						rs.close();
						DatabaseConnection.closeConnection();
					}
				} 
				catch(SQLException e)
				{

					e.printStackTrace();
				}
			}
			return null;
		}

	}
	
	


             