package sv.com.nipro.interfaz.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

	protected static SecureRandom random = new SecureRandom();

	public static String generateToken(String username) {
		long longToken = Math.abs(random.nextLong());
		String random = Long.toString(longToken, 5);
		return (Base64.getEncoder().encodeToString(username.getBytes()) + ":"
				+ Base64.getEncoder().encodeToString(random.getBytes()));
	}
}