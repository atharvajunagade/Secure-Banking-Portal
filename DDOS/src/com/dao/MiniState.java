package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.model.MiniStatement;
import com.util.DatabaseConnection;

/**
 * This class is use for Mini Statement. 
 * @author Renuka Gore
 *
 */

public class MiniState {
	
	public ArrayList<MiniStatement> getDetails(String accountnumber)
	{
	ArrayList<MiniStatement> list = new ArrayList<MiniStatement>(); // Created Array List.
	DatabaseConnection db=new DatabaseConnection();
	
	ResultSet rs=db.selectQuery("select * from (select *,'Debited By' as type"+ 
								" from transactions,user"+ 
								" where transactions.tuid=user.uid and sender_account="+accountnumber+""+
										" union all"+
								" select *,'Credited By' as type "+
								" from transactions,user "+ 
								" where transactions.tuid=user.uid and receiver_account="+accountnumber+") a "+
								" order by datetime desc");
	try
	{
	while(rs.next())
	{
		MiniStatement ms=new MiniStatement(); 		// Created the object of MiniStatement.
		ms.setTransAmount(rs.getDouble("amount")); 	//Setting the values.
		ms.setTransType(rs.getString("type"));
		ms.setTransId(rs.getInt("tid"));
		ms.setTransTime(rs.getString("datetime"));
		ms.setUserBalance(rs.getDouble("balance"));	
		list.add(ms); 								//object is added to list.
	}
	}
	catch(Exception e)
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
	return list;
}
}
