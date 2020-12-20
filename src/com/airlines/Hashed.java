package com.airlines;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashed {
	String password;
	public static final String SALT = "my-salt-text";
	public Hashed(String password) {
		super();
		this.password = password;
	}
		public String getHash() {
		String saltedPassword = SALT  + password;
		String hashedPassword = generateHash(saltedPassword);
		return hashedPassword;
	}

	public Boolean login(String enteredPass, String realPassHash) {
		Boolean isAuthenticated = false;

		// remember to use the same SALT value use used while storing password
		// for the first time.
		String saltedPassword = SALT  + enteredPass;
		String hashedPassword = generateHash(saltedPassword);

		if(hashedPassword.equals(realPassHash)){
			isAuthenticated = true;
		}else{
			isAuthenticated = false;
		}
		return isAuthenticated;
	}

	public static String generateHash(String input) {
		String hash = "";

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
					'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash+=digits[((b & 0xf0) >> 4)];
				hash+=digits[b & 0x0f];
			}
		} catch (NoSuchAlgorithmException e) {
			// handle error here.
		}

		return hash.toString();
	}
}