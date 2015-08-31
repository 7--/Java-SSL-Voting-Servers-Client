import java.io.Serializable;


public class Voter {
	private String name;
	private String address;
	private String valNumber=null;
	
	public Voter(String name, String address){
		this.name=name;
		this.address=address;
	}
	public String toString(){
		return "name: "+" address: "+address+" valNumber: "+valNumber;
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
	
}
