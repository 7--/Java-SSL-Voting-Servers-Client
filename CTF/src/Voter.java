

public class Voter {
	private String name;
	private String address;
	private String valNumber;
	
	private Boolean hasVoted;
	private String vote;
	private String selfID;
	
	public Voter(String name, String address,String valNumber){
		this.name=name;
		this.address=address;
		this.valNumber=valNumber;
		hasVoted=false;
	}
	public String toString(){
		return "From CLA: name: " +name+" address: "+address+" valNumber: "+valNumber+"\n";
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
	public void setvalNumber(String valNumber){
		this.valNumber=valNumber;
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
	public String getSelfID() {
		return selfID;
	}
	public void setSelfID(String selfID) {
		this.selfID = selfID;
	}
	public String getVote() {
		return vote;
	}
	public void setVote(String vote) {
		this.vote = vote;
	}
	public Boolean getHasVoted() {
		return hasVoted;
	}
	public void setHasVoted(Boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
	
}
