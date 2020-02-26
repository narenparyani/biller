package com.billercrud.billerapi.validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.*;

/**
 * JdbcInsert1.java - Demonstrates how to INSERT data into an SQL
 *                    database using Java JDBC.
 */
class Name1 { 
  
    
    public static void main(String args[]) throws Exception{
    	System.out.println("Enter number of entries: ");
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    	//Class.forName("com.oracle.jdbc.OracleDriver");
    	//Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@10.10.20.3:1781:bduat", "bdtraining", "bdtraining123");
    	
    	try {
            Class.forName("oracle.jdbc.OracleDriver");
     } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
            
     }
     try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@10.10.20.3:1781:bduat", "bdtraining",
                         "bdtraining123");
            String table_name = "SERVICEABLECITY";
        	int number = Integer.parseInt(bufferedReader.readLine());
        	PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO SERVICEABLECITY(BILLERID,CITYID) VALUES(?,?)");
    		//preparedStatement.setString(1, table_name);
    		
    

    	
    	for(int i = 0;i<number;i++) {
    		preparedStatement.setString(1, bufferedReader.readLine());
    		preparedStatement.setString(2, bufferedReader.readLine());
    		//preparedStatement.setString(3, bufferedReader.readLine());
    		

    		
    		int j = preparedStatement.executeUpdate();
    		if(j == 0){
    			return;
    		}
    	
    	}
     }
     catch (SQLException exception) {
         exception.printStackTrace();
        
  }
    }
    
} 