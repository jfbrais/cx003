import java.io.FileInputStream;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class Decryption 
{
	private static void readKey(String input, String algorithm) 
    throws Exception {
    String fl = input+".key";
    FileInputStream fis = new FileInputStream(fl);
    int kl = fis.available();
    byte[] kb = new byte[kl];
    fis.read(kb);
    fis.close();
    KeySpec ks = null;
    SecretKey ky = null;
    SecretKeyFactory kf = null;
    if (algorithm.equalsIgnoreCase("DES")) {
    	 ks = new DESKeySpec(kb);
       kf = SecretKeyFactory.getInstance("DES");
       ky = kf.generateSecret(ks);
    } else if (algorithm.equalsIgnoreCase("DESede")) {
    	 ks = new DESedeKeySpec(kb);
       kf = SecretKeyFactory.getInstance("DESede");
       ky = kf.generateSecret(ks);
    } else {
       ks = new SecretKeySpec(kb,algorithm);
       ky = new SecretKeySpec(kb,algorithm);
    }

    System.out.println();
    System.out.println("KeySpec Object Info: ");
    System.out.println("Saved File = "+fl);
    System.out.println("Length = "+kb.length);
    System.out.println("toString = "+ks.toString());

    System.out.println();
    System.out.println("SecretKey Object Info: ");
    System.out.println("Algorithm = "+ky.getAlgorithm());
    System.out.println("toString = "+ky.toString());
 }
}
