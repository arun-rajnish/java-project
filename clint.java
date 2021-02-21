import java.util.*;
import java.net.*;
import java.io.*;

public class clint{
  
  public ststic void main(String[] aegs){
     try
     {
       Scanner scan = new Scanner(System.in);
       InetAddress ip = InetAddress.getByName("localhost");
       Socket s = new Socket(ip,9999);
       
       
       DataInputStream datain = new DataInputStream(s.getInputStream()); 
       DataOutputStream dataout = new DataOutputStream(s.getOutputStream());
       
       
       
       
       
     }
    
  }
  
  
}
