////name variable to store name
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.Executors;

public class Server{
	private static int p;
	public static ArrayList<String> ac = new ArrayList<String>();
	public static Set<String> names = new HashSet<>();
		
	public static Set<PrintWriter> writers = new HashSet<>();
	public static String u;
	public static String n; 
	public static int qu;
	public static JFrame f;

	public Server(){
		f=new JFrame("CREATE SERVER");
		f.setSize(400,260);
		f.getContentPane().setBackground(new Color(157, 150, 180));
		JLabel l1 = new JLabel("    SERVER CODE");
		l1.setBounds(10,20,200,30);
		l1.setFont(new Font("Verdona",Font.PLAIN,18));
		l1.setForeground(Color.BLACK);
		JLabel l2 = new JLabel("NUMBER OF CLIENT");
		l2.setBounds(10,100,200,30);
		l2.setFont(new Font("Verdona",Font.PLAIN,18));
		l2.setForeground(Color.BLACK);
		JTextField t1 = new JTextField("");
		t1.setBounds(200,20,150,30);
		t1.setBackground(new Color(205, 249, 244));
		t1.setForeground(Color.RED);
		t1.setFont(new Font("Verdona",Font.PLAIN,17));
		JTextField t2 = new JTextField("");
		t2.setBounds(200,100,150,30);
		t2.setBackground(new Color(205, 249, 244));
		t2.setForeground(Color.RED);
		t2.setFont(new Font("Verdona",Font.PLAIN,17));

		JButton b1=new JButton("CREATE"); 
    		b1.setBounds(80,160,200,30); 
		b1.setFont(new Font("Verdona",Font.PLAIN,20));
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.BLUE); 
    	b1.addActionListener(new ActionListener(){  
	public void actionPerformed(ActionEvent e){  
        	    u=(t1.getText());
		    n=(t2.getText());
		// need a boolean for yes true 
	}  
    	}); 
	f.add(l1);f.add(l2);f.add(t1);f.add(t2);f.add(b1);
	f.setLayout(null);  
        f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
		


		public static void main(String[] args) throws IOException {
			u="0";n="0";
			Server s = new Server();
			while(true){
				if(u !="0" && n != "0"){break;}
				System.out.print("");
			}
			f.setVisible(false);
			System.out.println(u+ " "+ n);
			int nu = Integer.parseInt(n);
			System.out.println(nu);
			p=0;
        		System.out.println("THE SERVER IS READY TO GO.......\n");
			
       			var pool = Executors.newFixedThreadPool(nu);
        		try (var listener = new ServerSocket(59001)) {
            		while (true) {
				//var po = listener.accept();
                		pool.execute(new ClientHandler(listener.accept()));
				p=p+1;
				System.out.println("Client "+Integer.toString(p)+" Connected...");
			}
           	 }
	}


private static class ClientHandler implements Runnable{
	private String name;
	private String password;
	private Socket socket;
        private Scanner in;
        private PrintWriter out;
	private String namedata(){
		String p = "";
		for(String i:ac){
			p =p+"%" + i ;
		}
		return p;

	}


	public ClientHandler(Socket S){
		this.socket = S;
	}


	public void run() {
            try {
                in = new Scanner(socket.getInputStream());
                out = new PrintWriter(socket.getOutputStream(), true);
		String gp = namedata();
		String sendf = "SUBMITNAME" + "%" + u + gp; // u is server code
                // Keep requesting a name until we get a unique one.
                while (true) {
			
                    out.println(sendf);     //"SUBMITNAME");
                    name = in.nextLine();
                    if (name == null) {
                        return;
			//continue;
                    }
                    synchronized (names) {
                        if (!name.isBlank() && !names.contains(name)) {
                            names.add(name);
			    ac.add(name);
                            break;
                        }
                    }
                }

                
                out.println("NAMEACCEPTED " + name);
                for (PrintWriter writer : writers) {
                    writer.println("JOIN " + name );//+ " has joined");
                }
                writers.add(out);

                // Accept messages from this client and broadcast them.
                while (true) {
                    String input = in.nextLine();
                    if (input.toLowerCase().startsWith("/quit")) {
                        return;
                    }
		    else if(input.startsWith("#")){
			for (PrintWriter writer : writers) {
                        writer.println(input +" " + name + ": ");
			}}
		    else{
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
		}
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (out != null) {
                    writers.remove(out);
                }
                if (name != null) {
                    System.out.println(name + " is leaving");
                    names.remove(name);
                    for (PrintWriter writer : writers) {
                        writer.println("LEAVE " + name );// " has left");
			ac.remove(name);
                    }
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}