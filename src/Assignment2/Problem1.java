package Assignment2;

import java.sql.*;

public class Problem1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn_local = null;
		Connection conn_remote = null;
		Statement statement_local = null;
		Statement statement_remote = null;
		ResultSet result_local = null;
		ResultSet result_remote = null;

	       try
	       {

	    	   //LocalHost
	           String url_local = "jdbc:mysql://localhost:3306/A2_distB00912580";
	           Class.forName ("com.mysql.cj.jdbc.Driver");
	           conn_local = DriverManager.getConnection (url_local,"root","Alliance@1");
	           System.out.println ("Database connection established");
	           
	           //Remote
	           String url_remote = "jdbc:mysql://34.130.230.139/A2_distB00912580";
	           Class.forName ("com.mysql.cj.jdbc.Driver");
	           conn_remote = DriverManager.getConnection (url_remote,"root","Alliance@1");
	           System.out.println ("Database connection established");
	           
	           
	           conn_local.setAutoCommit(false);
	           conn_remote.setAutoCommit(false);
	           
	           statement_local = conn_local.createStatement();
	           statement_remote = conn_remote.createStatement();
	           
	           statement_local.execute("set profiling = 1;");
	           statement_remote.execute("set profiling = 1;");
	           
	           statement_local.executeUpdate("UPDATE Amenities\n"
		           		+ "SET Special_Request = \"Contact person on Duty\"\n"
		           		+ "WHERE Amenities_Type = \"Day Use\";");
	           
	           statement_remote.executeUpdate("UPDATE Provincial_Park SET Activities_Done = \"Camping\" WHERE Park_Name = \"Jerry Lawrence\";");
	           
	           result_local = statement_local.executeQuery("SELECT * FROM Amenities");
	           result_remote = statement_remote.executeQuery("select * from Provincial_Park");

	           
	           while (result_local.next()) {
	        	   System.out.println(result_local.getString("Amenities_Type"));
	        	   System.out.println(result_local.getString("Park_Name"));
	        	   System.out.println(result_local.getString("Special_Request"));
	        	   System.out.println(result_local.getString("Amenities_ID"));
	        	   System.out.println(result_local.getString("Provincial_Park_Park_Name")); 
	           }
	           while (result_remote.next()) {
	        	   System.out.println(result_remote.getString("Park_Name"));
	        	   System.out.println(result_remote.getString("Region"));
	        	   System.out.println(result_remote.getString("Activities_Done"));
	        	   System.out.println(result_remote.getString("Description"));
	        	   System.out.println(result_remote.getString("Images")); 	        	   
	           }
	           
	           result_local = statement_local.executeQuery("show profiles;");
	           result_remote = statement_remote.executeQuery("show profiles;");
	           while (result_remote.next()) {
	        	   System.out.println(result_remote.getString("Query_ID"));
	        	   System.out.println(result_remote.getString("Duration"));
	        	   System.out.println(result_remote.getString("Query"));
	           }
	           
	           while (result_local.next()) {
	        	   System.out.println(result_local.getString("Query_ID"));
	        	   System.out.println(result_local.getString("Duration"));
	        	   System.out.println(result_local.getString("Query"));
	           }
	           
	           conn_local.commit();
	           conn_remote.commit();
	       }
	     
	       catch (Exception e)
	       {
	           e.printStackTrace();

	       }
	       finally
	       {

	    	    if (statement_local != null) {
	    	        try {
	    	        	statement_local.close();
	    	        } catch (SQLException sqlEx) { } // ignore

	    	        statement_local = null;
	    	    }
	       }
	}

}
