package com.test.security;

public class Modulo26Crypto {

	public static String encrypt(String password, String secretKey) {
		StringBuffer encryptedString = new StringBuffer();
		int encryptedInt;
		if(password != null && password.trim().length() > 0 ){
		for (int i = 0; i < password.length(); i++) {
			int plainTextInt = (int) (password.charAt(i) - 'A');
			int secretKeyInt = (int) (secretKey.charAt(0) - 'A');
			encryptedInt = (plainTextInt + secretKeyInt) % 26;
			encryptedString.append((char) ((encryptedInt) + (int) 'A'));
		}
		}
		return encryptedString.toString();
	}

	public static String decrypt(String password, String secretKey) {
		StringBuffer decryptedString = new StringBuffer();
		int decryptedInt;
		if(password != null && password.trim().length() > 0 ){
		for (int i = 0; i < password.length(); i++) {
			int decryptedTextInt = (int) (password.charAt(i) - 'A');
			int secretKeyInt = (int) (secretKey.charAt(0) - 'A');
			decryptedInt = decryptedTextInt - secretKeyInt;
			if (decryptedInt < 1)
				decryptedInt += 26;
			decryptedString.append((char) ((decryptedInt) + (int) 'A'));
		}
		}
		return decryptedString.toString();
	}
}
