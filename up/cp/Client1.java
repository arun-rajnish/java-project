package cp;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.text.BadLocationException;
import java.net.*;
import java.io.*;
import java.util.*;
//import front.frontPage;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.Document;

public class Client1 implements Runnable{
	ArrayList<String> activeClient = new ArrayList<String>();
	String serverAddress;
	Scanner in;
    	public static PrintWriter out;
	public static int port;
	private static String u;//-------username
	private static String code;//----------code
    	JFrame frame = new JFrame("CHAT-UP");
 	JTextField t1 = new JTextField("");
	// --------------------textPane----------------
    	JTextPane textPane = new JTextPane();
	JScrollPane m1= new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);	
	JPanel is2 = new JPanel();
	// buttons
	JPanel is = new JPanel();
	// ---------------textarea--------------
	JTextArea textArea= new JTextArea();
	JScrollPane m2= new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	//----------------atrubute set----------------
	private static SimpleAttributeSet joined;
	private static SimpleAttributeSet me;
	private static SimpleAttributeSet friends;
	private static SimpleAttributeSet leaved;

	//-----------image of face------------
	JPanel is3 = new JPanel();
	JTextPane textPane3 = new JTextPane(); 
	//------------title label-----------------
	JLabel titleLabel = new JLabel("---------------------------------------CHAT - UP----------------------------------------- ");
	JLabel userLabel = new JLabel();
	JLabel user23 = new JLabel("NAME :");

	JLabel serverLabel = new JLabel();
	JLabel server23 = new JLabel("SERVER :");

	JLabel connect = new JLabel("NOT CONNECTED");

	//------------button--------------------------
	JButton ib = new JButton("PROFILE IMAGE");
	JFileChooser fileC = new JFileChooser();

	


	public Client1(String serverAddress,int port,String upqwe){
		System.out.println("clint started");
		this.serverAddress = serverAddress;
		this.port=port;
		this.u = upqwe;
		textPane.setEditable(false);
		frame.setSize(700,700);
		frame.getContentPane().setBackground(new Color(157, 150, 180));
		//sbrText.setBounds(10,10,250,200);

		// **********textpane in panel***************
		//StyledDocument doc2 = (StyledDocument) textPane.getDocument();
		m1.setPreferredSize(new Dimension(390,390));
		is2.setBounds(10,210,400,400);
		is2.setBackground(Color.gray);
		textPane.setBounds(0,0,300,300);
		
		// ********************buttons and textfeild***************
		t1.setBounds(10,630,500,30);
		t1.setFont(new Font("Verdona",Font.PLAIN,20));
		t1.setForeground(Color.RED);
		JButton b1=new JButton("SEND IT"); 
    		b1.setBounds(520,630,150,30);
		b1.setFont(new Font("Verdona",Font.PLAIN,20));
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.BLUE); 
		//JButton b2=new JButton("image"); 
    		//b2.setBounds(300,460,70,30);  

		//**********************name textArea***************
		m2.setBounds(410,240,255,250);
		textArea.setFont(new Font("Verdona",Font.PLAIN,20));
		JLabel lt2 = new JLabel("ACTIVE MEMBERS");
		lt2.setBounds(415,200,200,30);
		lt2.setFont(new Font("Verdona",Font.PLAIN,20));
		lt2.setForeground(Color.RED);

		//**********************face images*******************
		is3.setBounds(10,40,150,150);
		is3.setBackground(Color.gray);
		textPane3.setPreferredSize(new Dimension(150,150));

		//********************title label*********************
		titleLabel.setBounds(0,0,700,30);
		titleLabel.setFont(new Font("Verdona",Font.PLAIN,22));
		titleLabel.setForeground(Color.white);
		
		//*******************user name label *******************
		userLabel.setBounds(250,50,300,30);
		userLabel.setFont(new Font("Verdona",Font.PLAIN,22));
		userLabel.setForeground(Color.white);

		user23.setBounds(170,50,80,30);
		user23.setFont(new Font("Verdona",Font.PLAIN,22));
		user23.setForeground(Color.white);

		// *********************server name *****************
		serverLabel.setBounds(290,90,300,30);
		serverLabel.setFont(new Font("Verdona",Font.PLAIN,22));
		serverLabel.setForeground(Color.white);

		server23.setBounds(170,90,120,30);
		server23.setFont(new Font("Verdona",Font.PLAIN,22));
		server23.setForeground(Color.white);

		connect.setBounds(170,130,250,30);
		connect.setFont(new Font("Verdona",Font.PLAIN,20));
		connect.setForeground(Color.white);
		//******** face button*******************
		ib.setBounds(170,165,200,20);
		ib.setFont(new Font("Verdona",Font.PLAIN,18));
		ib.setForeground(Color.BLACK);
		ib.setBackground(Color.BLUE);
		
		ib.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){
			fileC.setFileFilter(new FileNameExtensionFilter("Open Image","jpg","jpeg","gif"));
			int returnval = fileC.showOpenDialog(null);
			if(returnval == JFileChooser.APPROVE_OPTION)
			{
				File file = fileC.getSelectedFile();	
				ImageIcon iconpb = new ImageIcon(file.getAbsolutePath());
				Image ipb = iconpb.getImage(); 
				Image new_imgpb = ipb.getScaledInstance(150,150,Image.SCALE_SMOOTH);
				iconpb = new ImageIcon(new_imgpb);
				Document doc = textPane.getDocument();
				int cl = doc.getLength();
				textPane3.setSelectionStart(0);
				textPane3.setSelectionEnd(cl);
				textPane3.insertIcon(iconpb);
			}
		}  
    		});

		// ********************* emogis*********************
		JLabel lt1 = new JLabel("EMOGIES");
		lt1.setBounds(415,497,200,30);
		lt1.setFont(new Font("Verdona",Font.PLAIN,20));
		lt1.setForeground(Color.RED);
		is.setBounds(415,532,255,80);
		is.setBackground(Color.blue);

		ImageIcon i1 = new ImageIcon("1.png");
		Image i = i1.getImage();
		Image new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i1 = new ImageIcon(new_img);		
		JButton e1=new JButton(i1); 
    		//e1.setBounds(0,0,185,200);
		ImageIcon i2 = new ImageIcon("2.png");
		i = i2.getImage();
		new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i2 = new ImageIcon(new_img);
		JButton e2=new JButton(i2); 
    		//e2.setBounds(85,0,85,100);
		ImageIcon i3 = new ImageIcon("3.png");
		i = i3.getImage();
		new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i3 = new ImageIcon(new_img);
		JButton e3=new JButton(i3); 
    		//e3.setBounds(170,0,85,100);
		
		ImageIcon i4 = new ImageIcon("4.png");
		i = i4.getImage();
		new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i4 = new ImageIcon(new_img);
		JButton e4=new JButton(i4); 
    		//e4.setBounds(0,100,85,100);

		ImageIcon i5 = new ImageIcon("5.png");
		i = i5.getImage();
		new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i5 = new ImageIcon(new_img);
	  	JButton e5=new JButton(i5); 
    		//e5.setBounds(85,100,85,100);

		ImageIcon i6 = new ImageIcon("6.png");
		i = i6.getImage();
		new_img = i.getScaledInstance(25,25,Image.SCALE_SMOOTH);
		i6 = new ImageIcon(new_img);		
		JButton e6=new JButton(i6); 
    		//e6.setBounds(85,100,85,100);
		/*JButton e7=new JButton("e7"); 
    		//e7.setBounds(85,100,85,100);
		JButton e8=new JButton("e8"); 
    		//e8.setBounds(85,100,85,100);*/

		//*********************button action**************************	

		t1.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println(t1.getText());
                    	  t1.setText("");  
			
        	}  
    		});
		b1.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println(t1.getText());
                	 t1.setText(""); 
			
        	}  
    		});
		e1.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e1");
                	 //t1.setText(""); 
			
        	}  
    		});
		e2.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e2");
                	 //t1.setText(""); 
			
        	}  
    		});
		e3.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e3");
                	 //t1.setText(""); 
			
        	}  
    		});
		e4.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e4");
                	 //t1.setText(""); 
			
        	}  
    		});
		e5.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e5");
                	 //t1.setText(""); 
			
        	}  
    		});
		e6.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
        		 out.println("#e6");
                	 //t1.setText(""); 
			
        	}  
    		});
		
		//***********************setting attribues*****************
		joined= new SimpleAttributeSet();
		me=new SimpleAttributeSet();
		friends=new SimpleAttributeSet();
		leaved=new SimpleAttributeSet();
		
		//joined
		StyleConstants.setItalic(joined,true);
		StyleConstants.setFontSize(joined,20);
		StyleConstants.setFontFamily(joined,"Verdana");
		StyleConstants.setForeground(joined,Color.BLACK);

		//me
		StyleConstants.setBold(me,true);
		StyleConstants.setFontSize(me,16);
		StyleConstants.setFontFamily(me,"Verdana");
		StyleConstants.setForeground(me,Color.GREEN);

		//friends
		StyleConstants.setBold(friends,true);
		StyleConstants.setFontSize(friends,16);
		StyleConstants.setFontFamily(friends,"Verdana");
		StyleConstants.setForeground(friends,Color.BLUE);
	
		//leaved
		StyleConstants.setItalic(leaved,true);
		StyleConstants.setFontSize(leaved,16);
		StyleConstants.setFontFamily(leaved,"Verdana");
		StyleConstants.setForeground(leaved,Color.RED);


			
		//***************************** frame details***********************
		
		frame.add(t1);frame.add(b1);frame.add(lt1);frame.add(lt2);
		is2.add(m1 );
		frame.add(is2);
		is.add(e1);is.add(e2);is.add(e3);is.add(e4);is.add(e5);is.add(e6);
		//is.add(e7);is.add(e8);
		frame.add(m2);
		frame.add(is);
		
		is3.add(textPane3);
		frame.add(is3);
		frame.add(titleLabel);
		frame.add(userLabel);
		frame.add(user23);
		frame.add(serverLabel);
		frame.add(server23);
		frame.add(connect);
		frame.add(ib);
		
		frame.setLayout(null);  
        	frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon iconpb2 = new ImageIcon("face.jpg");
		Image ipb2 = iconpb2.getImage(); 
		Image new_imgpb2 = ipb2.getScaledInstance(150,150,Image.SCALE_SMOOTH);
		iconpb2 = new ImageIcon(new_imgpb2);
		textPane3.insertIcon(iconpb2);
			

	}
	//****************************code for the textPane**********************
	private void setC(){
		Document doc = textPane.getDocument();
		int cl = doc.getLength();
		textPane.setSelectionStart(cl);
		textPane.setSelectionEnd(cl);

	}

	private void insertImage(ImageIcon img){
		setC();
		textPane.insertIcon(img);


	}
	private void insertText(String text,SimpleAttributeSet type){
		setC();
		Document doc = textPane.getDocument();
		try{
			doc.insertString(doc.getLength(),text,type);
		}catch(BadLocationException ev){
			System.out.println("location problem");
		}
	}
	private void resizeEmogi(String path){
		ImageIcon icon = new ImageIcon(path);
		Image i = icon.getImage();
		Image new_img = i.getScaledInstance(30,30,Image.SCALE_SMOOTH);
		icon = new ImageIcon(new_img);
		insertImage(icon);
		insertText("\n",me);
	}
	private void emogi(String path){
		ImageIcon icon = new ImageIcon(path);
		Image i = icon.getImage();
		Image new_img = i.getScaledInstance(10,10,Image.SCALE_SMOOTH);
		icon = new ImageIcon(new_img);
		
		//return icon;
	}
	//*************************code for textArea active clint****************

	public void clintInText(){
	String gpg = "";
	for(String i:activeClient){
		gpg = gpg + i + "\n";
	}
		textArea.setText(gpg);
	}

	

	//**********************************run void functio*****************	
	public void run()  {
        try {
		InetAddress ip=InetAddress.getByName(serverAddress);
            var socket = new Socket(ip, port);
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
	    //System.out.println(code.substring(2));
	    String qwse="";
            while (in.hasNextLine()) {
                var line = in.nextLine();
                if (line.startsWith("SUBMITNAME")) {
			
			out.println(u);
			String[] w = line.split("%");
			activeClient.add(u);
			serverLabel.setText(w[1]);
			for(int i = 0;i<w.length;i++){
			if(i>1){
				activeClient.add(w[i]);	
				System.out.println("clint "+w[i]);
			}
			}

			for(String i:activeClient){
				System.out.println("\nactive clint :" + i);
			
				//textArea.setText(i+"\n");
			}
			clintInText();

                } else if (line.startsWith("NAMEACCEPTED")) {
                    this.frame.setTitle("CHAT UP - " + line.substring(13));
                    t1.setEditable(true);
		    userLabel.setText(u);
		    connect.setText("CONNETED");
                } else if (line.startsWith("MESSAGE")) {
		    if(line.startsWith("MESSAGE "+u)){
			insertText(line.substring(8) + "\n",me);
			}
			else{
                    insertText(line.substring(8) + "\n",friends);
			}
                }
		else if (line.startsWith("JOIN")) {
                    insertText(line.substring(5) + " : has joined\n",joined);
		    String pig = line.substring(5);
		    activeClient.add(pig);
		    for(String i:activeClient){
				System.out.println("\nactive clint :" + i);
				//textArea.setText(i+"\n");
			}
			clintInText();
                }
		else if (line.startsWith("LEAVE")) {
                    insertText(line.substring(5) + " : has leaved\n",leaved);
		    String pig = line.substring(6);
		    activeClient.remove(pig);
			for(String i:activeClient){
				System.out.println("\nactive clint :" + i);
				//textArea.setText(i+"\n");
			}
			clintInText();
                }
		else if(line.startsWith("#e")){
			insertText(line.substring(4) + "\n",friends);
			if(line.startsWith("#e1")){resizeEmogi("1.png");}
			if(line.startsWith("#e2")){resizeEmogi("2.png");}
			if(line.startsWith("#e3")){resizeEmogi("3.png");}
			if(line.startsWith("#e4")){resizeEmogi("4.png");}
			if(line.startsWith("#e5")){resizeEmogi("5.png");}
			if(line.startsWith("#e6")){resizeEmogi("6.png");}
		}
		
            }
        } catch(IOException e){System.out.println(e);}finally {
            frame.setVisible(false);
            frame.dispose();
        }
    }

//****************************** main **********************
	/*public static void main(String[] args){
		frontPage f =new frontPage();
		if(f.frontpage()==0){
		System.out.println("entered");
		u = f.getter();
		code = f.getterp();
		//System.out.println(u);
		//System.out.println(code);
		System.out.println(u.substring(2));
		}
		//System.out.println("out of code");
		int port2 =Integer.parseInt(args[1]); 
		var c=new Client(args[0],port2);
		c.run();
		//System.exit(0);
		Client1 c = new Client1("localhost",59001,"rajnish");
		c.run();

	}*/
	
	



}