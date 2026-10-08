package level3;

import java.sql.*;
import java.util.Scanner;

public class Database1 {
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

            System.out.println("Enter rno, student name and mark:");
            int rno = scan.nextInt();
            scan.nextLine(); // consume newline
            String sname = scan.nextLine();
            float mark = scan.nextFloat();
            String checkQuery = "SELECT COUNT(*) FROM student WHERE rno = ?";
            PreparedStatement checkStmt = con.prepareStatement(checkQuery);
            checkStmt.setInt(1, rno);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Error: Student with roll number " + rno + " already exists!");
            } else {
                String insertQuery = "INSERT INTO student VALUES (?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(insertQuery);
                pstmt.setInt(1, rno);
                pstmt.setString(2, sname);
                pstmt.setFloat(3, mark);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Student record inserted successfully!");
                }
                pstmt.close();
            }

            rs.close();
            checkStmt.close();
            con.close();
            scan.close();

        } catch (Exception e) {
            System.out.println("Error Reason: " + e);
        }
    }
}
