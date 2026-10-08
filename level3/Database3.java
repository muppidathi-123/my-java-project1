package level3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Database3 {
	public static void main(String[] args) {
		  Scanner scan = new Scanner(System.in);
	      try {
	    	  Class.forName("com.mysql.cj.jdbc.Driver");
	          System.out.println("Driver Accepted");
	          Connection con = DriverManager.getConnection(
	                  "jdbc:mysql://localhost:3306/chettinad",
	                  "root",
	                  "12345"
              );

              System.out.println("Connection success");
              Statement st = con.createStatement();

              System.out.println("Enter rno, student name and mark:");
              int rno = scan.nextInt();
              int result = st.executeUpdate("delete from student where regno = "+rno);
              if(result>0)
            	  System.out.println("successfully deleted your db");
              else
            	  System.out.println("no records found in db");
              st.close(); con.close();
	      }
	      catch(Exception e) {
	    	  System.out.println("Error Reason: " + e);
	      }

	}
}
