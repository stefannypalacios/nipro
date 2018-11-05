package sv.com.nipro.interfaz.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.springframework.beans.factory.annotation.Autowired;

import sv.com.nipro.interfaz.entities.Token;
import sv.com.nipro.interfaz.repository.TokenRepository;

@ManagedBean(name = "BaseBean")
public class BaseBean {
	@Autowired
	public TokenRepository tokenService;

	public BaseBean() {
	}

	public boolean isTokenActive(String token, int validMinutes) {

		Token t;
		if (tokenService != null) {
			try {
				t = tokenService.findByToken(token);

				if (t != null) {
					DateTime startDate = new DateTime(t.getLastUsage().getTime());
					DateTime endDate = new DateTime();

					Minutes minutes = Minutes.minutesBetween(startDate, endDate);
					int numberOfMinutes = minutes.getMinutes();

					System.out.println("**************" + numberOfMinutes + "**************");
					if (numberOfMinutes >= validMinutes) {
						t.setStatus(false);
						tokenService.save(t);
					}

					return (numberOfMinutes < validMinutes);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean inactiveToken(String token) {
		Token t;
		if (tokenService != null && token != null) {
			try {
				t = tokenService.findByTokenActive(token, true);
				if (t != null) {
					t.setStatus(false);
					tokenService.save(t);
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean matchingMessage(String msgOrig, String msgCompare) {
		String md5 = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(msgCompare.getBytes());
			byte[] digest = md.digest();
			md5 = new BigInteger(1, digest).toString(16);

			return md5.equals(msgOrig);

		} catch (NoSuchAlgorithmException e) {
			return false;
		}
	}
}
