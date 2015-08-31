import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.*;
import javax.net.ssl.*;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//-Djavax.net.debug=ssl:handshake //TODO print CLA val numbers and recipients, Clean up, security check.
public class VotingClient {

	public static void main(String[] args) throws IOException {
		String serverAddress = "localhost";/*JOptionPane.showInputDialog(
			"Enter IP Address of a machine that is\n" +
			"running the service on port 9090:");*/
		Voter you =  prompt();
		
		boolean gotVal=false;
		try {
			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();
			SSLSocket s = (SSLSocket) f.createSocket(serverAddress, 9090);
			s.startHandshake();

			PrintWriter out = new PrintWriter(s.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

			String fromServer;
			String fromUser;

			out.println("Request Val");
			out.println(you.getName());
			out.println(you.getAddress());

			boolean givingVal=false;
			while ((fromServer = in .readLine()) != null) {
				System.out.println("From CLA: " + fromServer);
				if (gotVal=fromServer.equals("Bye.")) {
					System.out.println("Client Bye.");
					break;
				}
				if(fromServer.equals("denied")){
					break;
				}
				if(givingVal==true)	
					System.out.println(you.setvalNumber(fromServer));
				if(fromServer.equals("What's your address?"))
					givingVal=true;
				
					
			}
			/*
                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }*/
			s.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + serverAddress);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't get I/O for the connection to ");
			System.exit(1);
		}
		
		//Got val# so can contact CTF
		if(gotVal){
			VotetooCLT v2c= new VotetooCLT(you);
		}
		
	}

	public static Voter prompt() {

		JTextField nameField = new JTextField(5);
		JTextField addressField = new JTextField(5);
		JTextField voteField = new JTextField(5);

		Voter you = new Voter();

		JPanel myPanel = new JPanel();
		myPanel.add(new JLabel("Name:"));
		myPanel.add(nameField);
		myPanel.add(Box.createHorizontalStrut(15)); // a spacer
		myPanel.add(new JLabel("Address:"));
		myPanel.add(addressField);
		myPanel.add(new JLabel("Vote (A,B,or C):"));
		myPanel.add(voteField);

		int result = JOptionPane.showConfirmDialog(null, myPanel,
			"Enter your name, address, and vote", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			//System.out.println("name: " + nameField.getText());
			//System.out.println("address: " + addressField.getText());
			you.setName(nameField.getText());
			you.setAddress(addressField.getText());
			you.setVote(voteField.getText());
			
		} else {
			System.out.println("No name and address read.");
		}
		return you;
	}
	
}