package up;

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
import cp.Client1;
import java.lang.Thread;
//import cls.xp;

//javac u.java && java -cp "C:\Users\hp\Desktop\s\mysql-connector-java-8.0.22.jar;" u
public class u{
	private static String username;
	private static String password;

	public static JFrame f = new JFrame("LOGIN");
	public static Connection corn = null;
	public static PreparedStatement stmt=null;
	public static String code;
	public static String serverip;
	public static int serverport;

	public u(){
		f.setSize(450,400);
		f.getContentPane().setBackground(new Color(157, 150, 180));

		//*******************title******************
		JLabel l = new JLabel("LOGIN");
		l.setBounds(164,10,100,40);
		l.setFont(new Font("Verdona",Font.PLAIN,22));
		l.setForeground(Color.white);
		
		//**********************data label 1*****************
		JLabel l1 = new JLabel("USERNAME");
		l1.setBounds(20,80,130,30);
		l1.setFont(new Font("Verdona",Font.PLAIN,18));
		l1.setForeground(Color.BLACK);

		JTextField t1 = new JTextField();
		t1.setBounds(155,80,260,30);
		t1.setBackground(new Color(205, 249, 244));
		t1.setForeground(Color.RED);
		t1.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 2*****************
		JLabel l2 = new JLabel("PASSWORD");
		l2.setBounds(20,120,130,30);
		l2.setFont(new Font("Verdona",Font.PLAIN,18));
		l2.setForeground(Color.BLACK);

		JPasswordField t2 = new JPasswordField();
		t2.setBounds(155,120,260,30);
		t2.setBackground(new Color(205, 249, 244));
		t2.setForeground(Color.RED);
		t2.setFont(new Font("Verdona",Font.PLAIN,17));

		//**********************data label 3*****************
		JLabel l3 = new JLabel("CODE-LINK");
		l3.setBounds(20,160,130,30);
		l3.setFont(new Font("Verdona",Font.PLAIN,18));
		l3.setForeground(Color.BLACK);

		JTextField t3 = new JTextField();
		t3.setBounds(155,160,260,30);
		t3.setBackground(new Color(205, 249, 244));
		t3.setForeground(Color.RED);
		t3.setFont(new Font("Verdona",Font.PLAIN,17));

		//******************for login*************
		JLabel ll1 = new JLabel("");
		ll1.setBounds(100,300,200,30);
		ll1.setFont(new Font("Verdona",Font.PLAIN,20));
		ll1.setForeground(Color.RED);
		

		//**********************button of register***********
		JButton b1 = new JButton("LOGIN");
		b1.setBounds(150,240,140,30);
		b1.setFont(new Font("Verdona",Font.PLAIN,20));
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.BLUE);
		
		b1.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){ 
			username = t1.getText();
			password = t2.getText();
			code = t3.getText();
			// for local host if no data entered.
			if(!code.equals("")){
			String[] w = code.split(" ");
			String[] w1 = w[0].split(":");
	  		serverip=(w1[1].substring(2));
			serverport=Integer.parseInt(w1[2]);
			}else{
				serverip="localhost";
				serverport = 59001;

			}
		// part of mysql to fedge the data and cheqe the user.
	
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/signup1";
				String usernamesql = "rajnish_488";
				String passwordsql = "Rajnish@123";
				String queary = "SELECT * FROM signup1.register";
				corn = DriverManager.getConnection(url,usernamesql,passwordsql);
				stmt = corn.prepareStatement(queary);
				

				
				ResultSet rs=stmt.executeQuery(queary);
				while(rs.next()){
					//System.out.println("yes");
					//System.out.println(rs.getString(1) + " "+rs.getString(2) + " "+rs.getString(3) + " "+rs.getString(4) + " "+rs.getString(5)); 
					if(username.equals(rs.getString(3)) && password.equals(rs.getString(5))){
	
						System.out.println("match found");
						JOptionPane.showMessageDialog(f,"LOGIN SUCCSESFUL");
						f.setVisible(false);
						Client1 c = new Client1(serverip,serverport,username);
						Thread tt1=new Thread(c);
						tt1.start();
						//c.start();
						//c.run();
						//xp pp = new xp();
						System.out.println("yes thread created");	
						break;
					}
				}
					
				//stmt.executeUpdate();

				}catch(SQLException ev){
					System.out.println("sql exception found");
				}catch(ClassNotFoundException eq){
					System.out.println("sql exception found 2");
					System.out.println(eq);
				}
			
				
				
	
		

		}  
    		});

		f.add(t1);f.add(l1);
		f.add(t2);f.add(l2);
		f.add(t3);f.add(l3);
		f.add(l);f.add(b1);
		f.add(ll1);
		f.setLayout(null);  
        	f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	

	}







	/*public static void main(String[] args){

		u up = new u();

	}*/






}