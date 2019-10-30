package com.dao;

public class SQLinjection {

	public static boolean check(String input) {
 
		 if(input.contains("&") || input.contains("||")){
			 return true;
		 }
		 else if(input.contains("where") || input.contains("update") || input.contains("insert") || input.contains("delete") || input.contains("or"))
			 return true;
 	     	return false;
	}
	
  public static void main(String args[]){
	  System.out.println(check("asd abc"));
  }
	
}
