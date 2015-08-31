import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;


public class CTF {

	public static void main(String[] args) {
		List<Voter> list = new ArrayList<Voter>();
	    
		try {
			SSLServerSocketFactory sslServerSocketfactory = (SSLServerSocketFactory)SSLServerSocketFactory.getDefault();
			SSLServerSocket sslServerSocket = (SSLServerSocket)sslServerSocketfactory.createServerSocket(9091);
			SSLServerSocket sslServerSocket2 = (SSLServerSocket)sslServerSocketfactory.createServerSocket(9092);

			while(true){
				SSLSocket s = (SSLSocket)sslServerSocket.accept();
	
			    PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			    BufferedReader in =
			        new BufferedReader(new InputStreamReader(s.getInputStream())); 

			    //Just read in the name, address, and valNumber
			    Voter newVoter= new Voter(in.readLine(),in.readLine(),in.readLine());
			    System.out.println(newVoter.toString());
			    list.add(newVoter);
		        /*
		        String inputLine;
		        String outputLine;
		        while ((inputLine = in.readLine()) != null) {
		        	System.out.println(inputLine);
		            //out.println(outputLine);
		            if(inputLine.equals("Bye.")){
		            	//out.println("Bye.");
		            	System.out.println("CTF Bye.");
		            	break;
		            }	            
		        }
		        */
		        s.close();
				SSLSocket s2 = (SSLSocket)sslServerSocket2.accept();
				PrintWriter out2 = new PrintWriter(s2.getOutputStream(), true);
			    BufferedReader in2 =
			        new BufferedReader(new InputStreamReader(s2.getInputStream())); 
			    String valID= in2.readLine();
			    String selfID= in2.readLine();
			    String vote= in2.readLine();
			    System.out.println("Read vote: " +vote+valID+selfID);
			    
			    //TODO check if val# is in list, save vote, hasVoted, and self ID in Voter, make print results method
			    boolean foundVoter =false;
			    ListIterator<Voter> iterator = list.listIterator();
		    	while(iterator.hasNext()){
		    		Voter aVoter = iterator.next();
		    		if(aVoter.getValNumber().equalsIgnoreCase(valID)&&aVoter.getHasVoted()){
		    			out2.println("Already voted");
		    			foundVoter=true;
		    		}
		    		if(aVoter.getValNumber().equalsIgnoreCase(valID)&& !aVoter.getHasVoted()){
		    			out2.println("Vote accepted");
		    			aVoter.setSelfID(selfID);
		    			aVoter.setVote(vote);
		    			aVoter.setHasVoted(true);
		    			iterator.set(aVoter);
		    			foundVoter=true;
		    		}
		    	}
			    if(!foundVoter){
	    			out2.println("Bad validation ID");
			    }
			    
			    
			    s.close();
		        printResults(list); 
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
		System.out.println("\nList of IDs and Votes");
		ListIterator<Voter> iterator = list.listIterator();
		int voteA=0;
		int voteB=0;
		int voteC=0;
    	while(iterator.hasNext()){
    		Voter aVoter = iterator.next();
    		System.out.println("Vote "+(voteA+voteB+voteC+1) +" Vote: "+ aVoter.getVote()+" Self ID: " +aVoter.getSelfID());
    		if(aVoter.getVote().equals("A"))
    			voteA++;
    		if(aVoter.getVote().equals("B"))
    			voteB++;
    		if(aVoter.getVote().equals("C"))
    			voteC++;
    	}
    	System.out.println("Votes for A: " +voteA+" B: "+voteB+" C: "+voteC +"\n");
	}

}
