import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;


public class Voter  {
	private String name;
	private String address;
	private String valNumber;
	private String selfID=genRandom();
	private String vote;
	
	private String genRandom(){
		 Random rng = new SecureRandom();
		 byte[] validationNumber = new byte[16]; 
		 rng.nextBytes(validationNumber);
		 //System.out.println(validationNumber);
		 return Arrays.toString(validationNumber);
	 }
	
	
	public Voter(String name, String address, String vote){
		this.name=name;
		this.address=address;
		this.setVote(vote);
	
	}
	
	public Voter() {
	}

	public String getSelfID(){
		return selfID;
	}
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public String getValNumber(){
		return valNumber;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public void setAddress(String address){
		this.address=address;
	}
	public String setvalNumber(String valNumber){
		return this.valNumber=valNumber;
	}
	
	public boolean equals(Object o){
		if(o instanceof Voter){
			Voter voter = (Voter) o;
			if(voter.name==this.name&&voter.address==voter.address&&voter.valNumber==this.valNumber)
				return true;
			return false;
		}
		else
			return false;
	}


	public String getVote() {
		return vote;
	}


	public void setVote(String vote) {
		this.vote = vote;
	}
	
}
