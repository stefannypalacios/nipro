package sv.com.nipro.interfaz.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class RequestCheckin {

	private String AppUser;
	private String Password;

	public String getAppUser() {
		return AppUser;
	}

	@JsonSetter("AppUser")
	public void setAppUser(String appUser) {
		AppUser = appUser;
	}

	public String getPassword() {
		return Password;
	}

	@JsonSetter("Password")
	public void setPassword(String password) {
		Password = password;
	}

	@Override
	public String toString() {
		return "RequestCheckin [AppUser=" + AppUser + ", Password=" + Password + "]";
	}

}
