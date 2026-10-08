package level4;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener {
	JLabel tit, l1, l2;
	JTextField t1;
	JPasswordField p1;
	JButton b1, b2;
	Font myfont = new Font("arial", Font.BOLD, 35);
	
	LoginForm() {
		setTitle("Komal Application Login Form");
		setLayout(null);

		tit = new JLabel("Login Form");
		l1 = new JLabel("Enter User Name/Id: ");
		l2 = new JLabel("Enter Password: ");
		
		t1 = new JTextField();
		p1 = new JPasswordField();
		
		b1 = new JButton("Login/SignIn");
		b2 = new JButton("Reset");

		tit.setBounds(320, 50, 250, 50);
		tit.setFont(myfont);
		
		l1.setBounds(250, 120, 150, 30);
		t1.setBounds(420, 120, 200, 30);
		
		l2.setBounds(250, 180, 150, 30);
		p1.setBounds(420, 180, 200, 30);
		
		b1.setBounds(290, 260, 130, 35);
		b2.setBounds(450, 260, 130, 35);
		b1.addActionListener(this);
		b2.addActionListener(this);
		

		add(tit);
		add(l1);
		add(l2);
		add(t1);
		add(p1);
		add(b1);
		add(b2);
	}
	
	public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            String password = new String(p1.getPassword());
            
            if (t1.getText().equals("komal") && password.equals("komal@1212")) {
                //JOptionPane.showMessageDialog(null, "valid user!!");
            	this.setVisible(false);
            	JFrame obj = new SimpleGui();
            	obj.setSize(1000,700);
            	obj.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid User!!!!!");
            }
        } else {
        	t1.setText("");
        	p1.setText("");
        	t1.requestFocus();
        }
    }

	public static void main(String[] args) {
		JFrame f1 = new LoginForm();
		f1.setSize(800, 450); 
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f1.setLocationRelativeTo(null);
		f1.setVisible(true);
	}
}
