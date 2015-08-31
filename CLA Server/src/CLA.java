import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.net.ssl.*;
//-Djavax.net.debug=ssl:handshake 
public class CLA {

	public static void main(String[] args) throws IOException {
		//List of registered voters
	    List<Voter> list = new ArrayList<Voter>();
	    list.add(new Voter("John", "100"));
	    list.add(new Voter("Jane", "200"));
	    list.add(new Voter("Jim", "300"));
	    Voter lastVoter = null;
		try {
			SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(9090);
			while(true){
				SSLSocket s = (SSLSocket)sslServerSocket.accept();
	
			    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			    BufferedReader in =
			        new BufferedReader(new InputStreamReader(s.getInputStream())); 

			    VotingProtocal vp = new VotingProtocal(list);
		        String response = vp.processInput(in.readLine());
		        System.out.println(response);
		        out.println(response);
	
		        String inputLine;
		        String outputLine;
		        while ((inputLine = in.readLine()) != null) {
		        	System.out.println(inputLine);
		            outputLine = vp.processInput(inputLine);
		            out.println(outputLine);
		            if(outputLine.equals("Already issued valID")){
		            	break;
		            }
		            if(vp.getState()==10){
		            	lastVoter=vp.getLastVoter();
		          
		            	out.println("Bye.");
		            	System.out.println("CLA Bye.");
		            	break;
		            }	            
		        }
		        
		        s.close();
		        printResults(list);
		        CLAtoCTF c2c= new CLAtoCTF();
		        if(lastVoter!=null)
		        	c2c.sendCTF(lastVoter);
			}
	    }catch (Exception e) {
	        System.err.println("Server Error: " + e.getMessage());
	        System.err.println("Localized: " + e.getLocalizedMessage());
	        System.err.println("Stack Trace: " + e.getStackTrace());
	        System.err.println("To String: " + e.toString());
	        e.printStackTrace();
	    }
	}
	private static void printResults(List<Voter> list) {
		System.out.println("\nRESULTS");
		ListIterator<Voter> iterator = list.listIterator();
    	while(iterator.hasNext()){
    		Voter aVoter = iterator.next();
    		System.out.println("Voter Name: " +aVoter.getName()+" Address: "+ aVoter.getAddress()+ " Val#: " + aVoter.getValNumber());
    		
    	}
	}
	
}