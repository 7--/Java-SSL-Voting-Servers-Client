import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;


public class ValidationNumbers {
	
	public String genRandom(){
		 Random rng = new SecureRandom();
		 byte[] validationNumber = new byte[64]; // 16 bytes = 128 bits
		 rng.nextBytes(validationNumber);
		 System.out.println(validationNumber);
		 return Arrays.toString(validationNumber);
	 }

}
