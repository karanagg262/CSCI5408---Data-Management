package Assignment2;

import java.sql.*;
import java.util.ArrayList;

public class Problem2 implements Runnable{
	
    public void run() {
    	
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
		Connection conn_local = null;
		Statement statement_transaction1 = null;
		//ResultSet result_remote = null;
		
		try
		{
			//LocalHost
	        String url_local = "jdbc:mysql://localhost:3306/mydb";
	        Class.forName ("com.mysql.cj.jdbc.Driver");
	        conn_local = DriverManager.getConnection (url_local,"root","Alliance@1");
	        System.out.println ("Database connection established");
	           
	        conn_local.setAutoCommit(false);
	           
	        statement_transaction1 = conn_local.createStatement();
	        
	        ArrayList<String> querylist1 = new ArrayList<String>();
	        ArrayList<String> querylist2 = new ArrayList<String>();
	        
	        querylist1.add("UPDATE Academic_Program SET Faculty_Name = \" Ahmed \" WHERE Course_Name = \" Computer Science \";");
	        querylist1.add("SELECT * FROM Academic_Program;");
	        querylist1.add("UPDATE Academic_Timetable SET Schedule = \" 10am-1pm \" WHERE Course_Name = \" Computer Science \";");
	        
	        querylist2.add("SELECT * FROM Academic_Timetable;");
	        querylist2.add("UPDATE Academic_Program SET Faculty_Name = \" F. Ahmed \" WHERE Course_Name = \" Computer Science \";");
	        querylist2.add("SELECT * FROM Academic_Program;");
	        
	        
	        MultiThreading transaction1 = new MultiThreading(querylist1, statement_transaction1, "Transaction 1");
	        MultiThreading transaction2 = new MultiThreading(querylist2, statement_transaction1, "Transaction 2");
	        
	        transaction1.start();
	        transaction2.start();
	        
		}
	    catch (Exception e)
	    {
	           e.printStackTrace();
	    }
	}

}
