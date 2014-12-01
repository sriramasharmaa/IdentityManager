package crypt;
import io.FileOperations;

import java.io.File;
import java.security.*;

import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * This program generates a Blowfish key, retrieves its raw bytes, and
 * then reinstantiates a Blowfish key from the key bytes.
 * The reinstantiated key is used to initialize a Blowfish cipher for
 * encryption.
 */
 interface GenericCrypt{
	public void encrypt(String a) throws Exception;
	public void encrypt(File a) throws Exception;
	public void decrypt(String a,byte[] key) throws Exception;
	public void decrypt(File a)  throws Exception;
	public byte[] encode(String a) throws Exception;
	public byte[] encode(byte[] a) throws Exception;
	public byte[] decode(String a) throws Exception;	
}
 
public class BlowfishKey implements GenericCrypt{
	byte[] passPhrase;
	public byte[] cipherPhrase;
	byte[] keyPhrase;
	public byte[] plainText;
	
	public BlowfishKey(String keyPhrase) throws Exception{
		this.keyPhrase= keyPhrase.getBytes();
		if(keyPhrase == null){
	        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
	        SecretKey skey = kgen.generateKey();
	        byte[] raw = skey.getEncoded();
	        this.keyPhrase = raw;
		}
	}
	
	public void SetPassPhrase(String pass){
		keyPhrase = pass.getBytes();
	}
	
	public String getPassPhrase(){
		return new String(keyPhrase);
	}
    public static void main(String[] args) throws Exception {
    	BlowfishKey obj = new BlowfishKey("ubutzukubutzu");
    	//obj.encrypt(new File("C:\\Users\\CIBLR620\\Documents\\cbay\\password\\hdfc-1.txt"));
    	obj.decrypt(new File("C:\\Users\\CIBLR620\\Documents\\cbay\\password\\hdfc-1.txt"));
    	//obj.decrypt(new String(obj.cipherPhrase), obj.keyPhrase);
    	//System.out.println(obj.byte2hex(obj.encode("hi")));
        //System.out.println(new String(obj.plainText));
    }

    public String byte2hex(byte[] digest){
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<digest.length;i++) {
    		String hex=Integer.toHexString(0xff & digest[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
    }
    
	@Override
	public void encrypt(String plainText) throws Exception{
		//System.out.println(new String(keyPhrase));
        SecretKeySpec skeySpec = new SecretKeySpec(keyPhrase, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        cipherPhrase = cipher.doFinal(plainText.getBytes());  	
	}

	public void encrypt(byte[] plainText) throws Exception{
        SecretKeySpec skeySpec = new SecretKeySpec(keyPhrase, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        cipherPhrase = cipher.doFinal(plainText);  	
	}
	
	@Override
	public void decrypt(String cipherText,byte[] key)  throws Exception{
        SecretKeySpec skeySpec = new SecretKeySpec(key, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        plainText = cipher.doFinal(cipherText.getBytes());		
	}
	public void decrypt(byte[] cipherText,byte[] key)  throws Exception{
        SecretKeySpec skeySpec = new SecretKeySpec(key, "Blowfish");
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);
        plainText = cipher.doFinal(cipherText);		
	}
	@Override
	public byte[] encode(String a) throws Exception {
		byte[] bytesOfMessage = a.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		return thedigest;
	}

	@Override
	public byte[] decode(String cipher) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] encode(byte[] a) throws Exception {
		byte[] bytesOfMessage = a;
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(bytesOfMessage);
		return thedigest;
	}

	@Override
	public void encrypt(File a) throws Exception {
		encrypt(FileOperations.read(a));
		//System.out.println(new String(cipherPhrase));
        FileOperations.write(a,cipherPhrase);		
	}
	
	public void decrypt(File a)  throws Exception{
		decrypt(FileOperations.read(a),keyPhrase);
		//System.out.println(new String(plainText));
	}	//
	
}