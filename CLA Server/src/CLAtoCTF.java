import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.util.List;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;


public class CLAtoCTF {

	public String serverAddress="localhost";
	public void sendCTF(Voter v){
		try {
			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket s = (SSLSocket) f.createSocket(serverAddress, 9091);
			s.startHandshake();

			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			String fromServer;
			String fromUser;

			out.println(v.getName());
			out.println(v.getAddress());
			out.println(v.getValNumber());

			boolean givingVal=false;
			while ((fromServer = in.readLine()) != null) {
				System.out.println("From CTF Server: " + fromServer);
				if (fromServer.equals("Bye.")||fromServer.equals("denied.")) {
					System.out.println("Client Bye.");
					break;
				}	
			}
			/*
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }*/
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverAddress);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't get I/O for the connection to ");
			System.exit(1);
		}
	}
}
