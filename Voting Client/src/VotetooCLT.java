import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class VotetooCLT {

	public VotetooCLT(Voter you) {
		System.out.println("Vote to CTF:  vote,selfID,Val#  " + you.getVote()+you.getSelfID()+you.getValNumber() );
		String serverAddress="localhost";
		
			try {
				SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
				SSLSocket s = (SSLSocket) f.createSocket(serverAddress, 9092);
				s.startHandshake();

				PrintWriter out = new PrintWriter(s.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

				out.println(you.getValNumber());
				out.println(you.getSelfID());
				out.println(you.getVote());
				
				//See if it was accepted
			    String fromCTF=in.readLine();
			    System.out.println("From CTF. Your vote was: " + fromCTF);

				s.close();	
						
			}
				
			 catch (UnknownHostException e) {
				System.err.println("Don't know about host " + serverAddress);
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Couldn't get I/O for the connection to ");
				System.exit(1);
			}
	}
}


