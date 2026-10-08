package level3;

import java.sql.*;
import java.util.Scanner;

public class Database4 {

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

            System.out.print("Enter rno to update: ");
            int rno = scan.nextInt();

            scan.nextLine();

            System.out.print("Enter new student name: ");
            String sname = scan.nextLine();

            System.out.print("Enter new mark: ");
            float mark = scan.nextFloat();

            int result = st.executeUpdate(
                "update student set sname = '" + sname +
                "', mark = " + mark +
                " where regno = " + rno
            );

            if (result > 0) {
                System.out.println("Successfully updated");
            }
            else {
                System.out.println("No record found");
            }

            st.close();
            con.close();
            scan.close();

        }
        catch (Exception e) {
            System.out.println("Error Reason: " + e);
        }
    }
}