package org.openmrs.module.limsemrops.authentication;

import java.math.BigInteger;
import java.security.MessageDigest;

public class AuthModuleUtils {
	
	public static String hash(String input) {
		MessageDigest msgDigest;
		try {
			msgDigest = MessageDigest.getInstance("SHA-256");
		}
		catch (Exception e) {
			return null;
		}
		
		byte[] inputDigest = msgDigest.digest(input.getBytes());
		
		BigInteger inputDigestBigInt = new BigInteger(1, inputDigest);
		
		// Convert the input digest into hex value
		String hashtext = inputDigestBigInt.toString(16);
		
		//Add preceding 0's to pad the hashtext to make it 32 bit
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
		return hashtext;
	}
	
	public static String extractKey(String key, String response) {
		int startIndex = response.indexOf(String.format("%s\":\"", key), 0);
		int keyLength = key.length();
		int endIndex = response.indexOf("\"", startIndex + keyLength + 3);
		String actualToken = response.substring(startIndex + keyLength + 3, endIndex);
		return actualToken.trim();
	}
	
	public static String extractJWT(String response) {
		return extractKey("jwt", response);
		// int startIndex = response.indexOf("jwt\":\"",0);
		// int endIndex = response.indexOf("\"", startIndex + 6);
		// String actualToken = response.substring(startIndex + 6, endIndex);
		// return actualToken.trim();
	}
}
