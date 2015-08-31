import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;


public class VotingProtocal {

    private static final int WAITING = 0;
    private static int NAME=1;
    private static int ADDRESS=2;
    private static int FINISHED=10;
    private int state = WAITING;
    
    List<Voter> list = new ArrayList<Voter>();
    Voter lastVoter;
	private String curName;
    
    public VotingProtocal(List<Voter> list){
    	this.list=list;
    }
    public List<Voter> getList(){
    	return list;
    }
    public Voter getLastVoter(){
    	return lastVoter;
    }
    public int getState(){
    	return state;
    }
    
	public String processInput(String input) {
        String out = null;
        
        if(state!=FINISHED){
		    if(input.equals("Request Val")){
		    	out="What's your name?";
		       	state=NAME; 
		    }
		    else if(state==NAME){
		    	Iterator<Voter> iterator = list.iterator();
		    	while(iterator.hasNext()){
		    		Voter aVoter = iterator.next();
		    		if(aVoter.getName().equalsIgnoreCase(input)){
		    			out="What's your address?";
		    			System.out.println(out);
		    			curName=input;
		    			state=ADDRESS;
		    			return out;
		    		}
		    	}
		    	out="denied";
		    }
		    else if(state==ADDRESS){
		    	ListIterator<Voter> iterator = list.listIterator();
		    	while(iterator.hasNext()){
		    		Voter aVoter = iterator.next();
		    		if(aVoter.getAddress().equalsIgnoreCase(input)&&aVoter.getValNumber()==null){
		    			String valNum=genRandom();
		    			out=valNum;
		    			aVoter.setvalNumber(valNum);
		    			iterator.set(aVoter);
		    			lastVoter=aVoter;
		    			//System.out.println(out+aVoter.toString());
		    			state=FINISHED;
		    			return out;
		    		}
		    	}
		    	out="Already issued valID";
		    }
        }
	    return out;
	}
	
	public String genRandom(){
		 Random rng = new SecureRandom();
		 byte[] validationNumber = new byte[64]; // 16 bytes = 128 bits
		 rng.nextBytes(validationNumber);
		 System.out.println(validationNumber);
		 return Arrays.toString(validationNumber);
	 }
}
