import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.lang.ClassNotFoundException;
import up.u;
//import up.cp.Client1;
//import java.color;



public class s{
	private static String fname;
	private static String lname;
	private static String username;
	private static String password;
	private static String emailid;
	private static String conformit;
	public static JFrame f = new JFrame("SIGN-UP");
	public static Connection corn = null;
	public static PreparedStatement stmt=null;




	public s(){
		//Class.forName("com.mysql.cj.jbdc.Driver");
		
		//JFrame f = new JFrame("SIGN-UP");
		f.setSize(450,600);
		f.getContentPane().setBackground(new Color(157, 150, 180));
		

		//*******************title******************
		JLabel l = new JLabel("SIGN-UP");
		l.setBounds(164,10,100,40);
		l.setFont(new Font("Verdona",Font.PLAIN,22));
		l.setForeground(Color.white);
		
		//**********************data label 1*****************
		JLabel l1 = new JLabel("FIRST-NAME");
		l1.setBounds(20,80,130,30);
		l1.setFont(new Font("Verdona",Font.PLAIN,18));
		l1.setForeground(Color.BLACK);

		JTextField t1 = new JTextField();
		t1.setBounds(155,80,260,30);
		t1.setBackground(new Color(205, 249, 244));
		t1.setForeground(Color.RED);
		t1.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 2*****************
		JLabel l2 = new JLabel("LAST-NAME");
		l2.setBounds(20,120,130,30);
		l2.setFont(new Font("Verdona",Font.PLAIN,18));
		l2.setForeground(Color.BLACK);

		JTextField t2 = new JTextField();
		t2.setBounds(155,120,260,30);
		t2.setBackground(new Color(205, 249, 244));
		t2.setForeground(Color.RED);
		t2.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 3*****************
		JLabel l3 = new JLabel("USERNAME");
		l3.setBounds(20,160,130,30);
		l3.setFont(new Font("Verdona",Font.PLAIN,18));
		l3.setForeground(Color.BLACK);

		JTextField t3 = new JTextField();
		t3.setBounds(155,160,260,30);
		t3.setBackground(new Color(205, 249, 244));
		t3.setForeground(Color.RED);
		t3.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 4*****************
		JLabel l4 = new JLabel(" EMAIL ID");
		l4.setBounds(20,200,130,30);
		l4.setFont(new Font("Verdona",Font.PLAIN,18));
		l4.setForeground(Color.BLACK);

		JTextField t4 = new JTextField();
		t4.setBounds(155,200,260,30);
		t4.setBackground(new Color(205, 249, 244));
		t4.setForeground(Color.RED);
		t4.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 5*****************
		JLabel l5 = new JLabel("PASSWORD");
		l5.setBounds(20,240,130,30);
		l5.setFont(new Font("Verdona",Font.PLAIN,18));
		l5.setForeground(Color.BLACK);

		JPasswordField t5 = new JPasswordField();
		t5.setBounds(155,240,260,30);
		t5.setBackground(new Color(205, 249, 244));
		t5.setForeground(Color.RED);
		t5.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 6*****************
		JLabel l6 = new JLabel("CONFORM IT");
		l6.setBounds(20,280,130,30);
		l6.setFont(new Font("Verdona",Font.PLAIN,18));
		l6.setForeground(Color.BLACK);

		JTextField t6 = new JTextField();
		t6.setBounds(155,280,260,30);
		t6.setBackground(new Color(205, 249, 244));
		t6.setForeground(Color.RED);
		t6.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************button of register***********
		JButton b1 = new JButton("Register");
		b1.setBounds(140,340,140,40);
		b1.setFont(new Font("Verdona",Font.PLAIN,22));
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.BLUE);

		//******************for login*************
		JLabel ll1 = new JLabel("HAVE AN ACCOUNT");
		ll1.setBounds(40,450,200,30);
		ll1.setFont(new Font("Verdona",Font.PLAIN,20));
		ll1.setForeground(Color.RED);

		//*******************message label*****************
		JLabel ll2 = new JLabel("");
		ll2.setBounds(100,400,250,30);
		ll2.setFont(new Font("Verdona",Font.PLAIN,18));
		ll2.setForeground(Color.RED);

		//**********************button of register***********
		JButton b2 = new JButton("LOGIN");
		b2.setBounds(250,450,140,30);
		b2.setFont(new Font("Verdona",Font.PLAIN,20));
		b2.setForeground(Color.BLACK);
		b2.setBackground(Color.BLUE);

		//**************action performed by b1************* 
    		b1.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){ 
			fname = t1.getText();
			lname = t2.getText();
			username = t3.getText();
			emailid=t4.getText();
			password =  t5.getText();
			conformit= t6.getText();
			//System.out.println(fname +" " + lname  +" " + username +" " + emailid +" " + password +" " +conformit);
			if(password.equals(conformit)){
//**********************************************************************************************************************************************				
// part to send data to the database of mysql.				
				try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/signup1";
				String usernamesql = "rajnish_488";
				String passwordsql = "Rajnish@123";
				
				String queary="INSERT into register Values(?,?,?,?,?)";
				corn = DriverManager.getConnection(url,usernamesql,passwordsql);
				stmt = corn.prepareStatement(queary);
				stmt.setString(1,fname);
				stmt.setString(2,lname);
				stmt.setString(3,username);
				stmt.setString(4,emailid);
				stmt.setString(5,password);
					
				stmt.executeUpdate();
				ll2.setText("SIGN-UP SUCCSESFUL");
				 JOptionPane.showMessageDialog(f,"SIGNUP SUCCSESFUL");
				f.setVisible(false);
				u pup = new u();


				}catch(SQLException ev){
					System.out.println("sql exception found");
					System.out.println(ev);
				}catch(ClassNotFoundException eq){
					System.out.println("sql exception found 2");
					System.out.println(eq);
				}	
				
				

//**********************************************************************************************************************************************
			}else{
				ll2.setText("PASSWORD NOT MATCH");
			}

        	     
		}  
    		});
// to get the login form visible
		b2.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){ 
			f.setVisible(false);
			u pup = new u();

		}  
    		});

		f.add(t1);f.add(l1);
		f.add(t2);f.add(l2);
		f.add(t3);f.add(l3);
		f.add(t4);f.add(l4);
		f.add(t5);f.add(l5);
		f.add(t6);f.add(l6);
		f.add(ll1);f.add(b2);
		f.add(ll2);
		f.add(b1);f.add(l);		
		f.setLayout(null);  
        	f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	


	public static void main(String[] args){

		s sp = new s();


	}

}