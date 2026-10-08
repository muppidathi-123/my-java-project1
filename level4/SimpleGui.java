package level4;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleGui extends JFrame implements ActionListener {

    JLabel l1, l2, l3;
    JTextField t1, t2, t3;
    JButton b1, b2, b3, b4, b5;

    SimpleGui() {

        setLayout(null);
        setTitle("Muppidathi Application");

        l1 = new JLabel("Enter RegisterNumber : ");
        l2 = new JLabel("Enter Student Name : ");
        l3 = new JLabel("Enter Mark : ");

        t1 = new JTextField(20);
        t2 = new JTextField(20);
        t3 = new JTextField(20);

        b1 = new JButton("Search/Find");
        b2 = new JButton("Insert/Save");
        b3 = new JButton("Delete/Remove");
        b4 = new JButton("Update/Edit");
        b5 = new JButton("Clear");

        l1.setBounds(100, 100, 200, 30);
        l2.setBounds(100, 150, 200, 30);
        l3.setBounds(100, 200, 200, 30);

        t1.setBounds(300, 100, 200, 30);
        t2.setBounds(300, 150, 200, 30);
        t3.setBounds(300, 200, 200, 30);

        b1.setBounds(40, 270, 130, 40);
        b2.setBounds(190, 270, 130, 40);
        b3.setBounds(340, 270, 130, 40);
        b4.setBounds(490, 270, 130, 40);
        b5.setBounds(640, 270, 130, 40);

        add(l1);
        add(l2);
        add(l3);

        add(t1);
        add(t2);
        add(t3);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            if(t1.getText().length()>0) {
                int rno = Integer.parseInt(t1.getText());
                search(rno);
            }else {
                JOptionPane.showMessageDialog(null, "Must fill the Register Number");
            }
        }

        if (e.getSource() == b2) {
            if(t1.getText().length() > 0 && t2.getText().length() > 0 && t3.getText().length() > 0) {
                insert();
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all inputs to insert");
            }
        }

        if (e.getSource() == b3) {
            if(t1.getText().length() > 0) {
                delete();
            } else {
                JOptionPane.showMessageDialog(null, "Must fill the Register Number to delete");
            }
        }

        if (e.getSource() == b4) {
            if(t1.getText().length() > 0 && t2.getText().length() > 0 && t3.getText().length() > 0) {
                update(); 
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all inputs to update");
            }
        }
        
        if (e.getSource() == b5) {
            clear();
        }
    }

    void search(int rno) {
        try {
            Statement st = dbconnection();
            if (st == null) {
                JOptionPane.showMessageDialog(this, "Database Connection Failed!");
                return;
            }

            // Updated columns to match your 'Database4' console baseline table design
            ResultSet rs = st.executeQuery("select * from student where regno=" + rno);
            if (rs.next()) {
                t2.setText(rs.getString("sname"));
                t3.setText(rs.getString("mark"));
            }
            else {
                JOptionPane.showMessageDialog(this, rno +" is not found");
            }

            rs.close();
            st.getConnection().close(); 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    void insert() {
        try {
            Statement st = dbconnection();
            if (st == null) {
                JOptionPane.showMessageDialog(this, "Database Connection Failed!");
                return;
            }

            int rno = Integer.parseInt(t1.getText());
            String sname = t2.getText();
            float mark = Float.parseFloat(t3.getText());

            // Updated values mapping structure to target your base column arrangement setup
            int result = st.executeUpdate("insert into student values(" + rno + ",'" + sname + "'," + mark + ")");

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Successfully inserted");
            }
            else {
                JOptionPane.showMessageDialog(this, "No records inserted");
            }

            st.getConnection().close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    void delete() {
        try {
            Statement st = dbconnection();
            if (st == null) {
                JOptionPane.showMessageDialog(this, "Database Connection Failed!");
                return;
            }

            int rno = Integer.parseInt(t1.getText());

            int result = st.executeUpdate("delete from student where regno=" + rno);

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Student deleted successfully");
                clear(); 
            }
            else {
                JOptionPane.showMessageDialog(this, rno + " is not found to delete");
            }

            st.getConnection().close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.toString());
        }
    }

    void update() {
        try {
            Statement st = dbconnection();
            if (st == null) {
                JOptionPane.showMessageDialog(this, "Database Connection Failed!");
                return;
            }

            int rno = Integer.parseInt(t1.getText());
            String sname = t2.getText();
            float mark = Float.parseFloat(t3.getText());

            // FIXED: Executes your exact baseline query to target correct schema properties
            int result = st.executeUpdate(
                "update student set sname = '" + sname +
                "', mark = " + mark +
                " where regno = " + rno
            );

            if (result > 0) {
                JOptionPane.showMessageDialog(this, "Successfully updated");
            }
            else {
                JOptionPane.showMessageDialog(this, "No record found");
            }

            st.getConnection().close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Reason: " + e.toString());
        }
    }
    
    void clear() {
        t1.setText("");
        t2.setText("");
        t3.setText("");
    }

    Statement dbconnection() {
        try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             System.out.println("Driver Accepted");

             Connection con = DriverManager.getConnection(
                 "jdbc:mysql://localhost:3306/chettinad",
                 "root",
                 "12345"
             );
             return con.createStatement();
        }
        catch(Exception e){
             System.out.println("Connection Error: " + e.getMessage());
             return null;
        }
    }

    public static void main(String[] args) {
        SimpleGui f1 = new SimpleGui();
        f1.setSize(800, 500);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.setLocationRelativeTo(null);
        f1.setVisible(true);
    }
}
