import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Encryption 
{
	public void encrypt()
	{
		KeyGenerator keygen;
		Cipher aesCipher;

		try 
		{
			// Fichier à chiffrer
			File f = new File("C:\\traffic.log");
			byte[] buffer = new byte[(int)f.length()];
			DataInputStream in = new DataInputStream(new FileInputStream(f));
			in.readFully(buffer);
			in.close();

			// Choix de l'iv
			byte[] iv = { (byte) 0xc9, (byte) 0x36, (byte) 0x78, (byte) 0x99,
						  (byte) 0x52, (byte) 0x3e, (byte) 0xea, (byte) 0xf2,
						  (byte) 0xd9, (byte) 0x63, (byte) 0x68, (byte) 0x09,
						  (byte) 0xf2, (byte) 0xff, (byte) 0xae, (byte) 0x2f};

			IvParameterSpec salt = new IvParameterSpec(iv);
			
			keygen = KeyGenerator.getInstance("AES");
			SecretKey aesKey = keygen.generateKey();

			// Chiffrement du fichier
			// Initialize the cipher for encryption
			aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");    
			aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, salt);
			
			byte[] buf_crypt = aesCipher.doFinal(buffer);

			FileOutputStream envfos = new FileOutputStream("chaine_chiffree");
			envfos.write(buf_crypt);
			envfos.close();

			// Déchiffrement du fichier
			aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");    
			aesCipher.init(Cipher.DECRYPT_MODE, aesKey, salt);
			byte[] buf_decrypt = aesCipher.doFinal(buf_crypt);

			envfos = new FileOutputStream("C:\\test.log");
			envfos.write(buf_decrypt);
			envfos.close();
		} 
		catch (NoSuchPaddingException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) 
		{
			e.printStackTrace();
		} 
		catch (BadPaddingException e)
		{
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchProviderException e) 
		{
			e.printStackTrace();
		}
	}
}
		
//		try
//		{
//			// Fichier à chiffrer
//			File f = new File(file);
//			byte[] buffer = new byte[(int)f.length()];
//			DataInputStream in = new DataInputStream(new FileInputStream(f));
//			in.readFully(buffer);
//			in.close();
//
//			// Choix de l'iv
//			byte[] iv = { (byte) 0xc9, (byte) 0x36, (byte) 0x78, (byte) 0x99,
//						  (byte) 0x52, (byte) 0x3e, (byte) 0xea, (byte) 0xf2 };
//
//			IvParameterSpec salt = new IvParameterSpec(iv);
//			// Clé secrète choisie
//			byte[] raw = "DSFsdkfjsadkFJSADKfjSAkfd".getBytes();
//			SecretKey skeySpec = new SecretKeySpec(raw, "AES");
//
//			// Chiffrement du fichier
//			Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");    
//		    c.init(Cipher.ENCRYPT_MODE, skeySpec, salt);
//			byte[] buf_crypt = c.doFinal(buffer);
//
//			FileOutputStream envfos = new FileOutputStream("chaine_chiffree");
//			envfos.write(buf_crypt);
//			envfos.close();
//
//			// Déchiffrement du fichier
//			c = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");    
//		    c.init(Cipher.DECRYPT_MODE, skeySpec, salt);
//			byte[] buf_decrypt = c.doFinal(buf_crypt);
//
//			envfos = new FileOutputStream("fichier_dechiffre");
//			envfos.write(buf_decrypt);
//			envfos.close();
////		} 
////		catch (Exception e) 
////		{
////			e.printStackTrace();
////		}
//	}
//	
	
