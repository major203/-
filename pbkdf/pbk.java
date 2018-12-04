package com.pbkdf;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class pbk {
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException 
	{
	    String  originalPassword = "password";//要加密的密码
	    System.out.println("Password："+originalPassword);
	    String generatedSecuredPasswordHash = generateStorngPasswordHash(originalPassword);//加密运算
	    System.out.println(generatedSecuredPasswordHash);
	}
	private static String generateStorngPasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
	    int iterations = 1000;
	    char[] chars = password.toCharArray();
	    byte[] salt = getSalt().getBytes();
	     
	    PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
	    SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");//生产key
	    byte[] hash = skf.generateSecret(spec).getEncoded();
	    System.out.println("迭代次数:"+iterations);
	    System.out.println("key："+skf);
	    System.out.println("盐:"+toHex(salt)+"("+getSalt()+")");
	    
	    return toHex(hash);
	}
	 
	private static String getSalt() throws NoSuchAlgorithmException
	{
	    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");//生产伪随机数
	    byte[] salt = new byte[16];
	    sr.nextBytes(salt);
	    return salt.toString();
	}
	 
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
	    BigInteger bi = new BigInteger(1, array);
	    String hex = bi.toString(16);
	    int paddingLength = (array.length * 2) - hex.length();
	    if(paddingLength > 0)
	    {
	        return String.format("%0"  +paddingLength + "d", 0) + hex;
	    }else{
	        return hex;
	    }
	}
	 
	
}
