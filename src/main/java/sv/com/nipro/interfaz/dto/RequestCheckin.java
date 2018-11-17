package sv.com.nipro.interfaz.dto;

import sv.com.nipro.interfaz.utils.Constans;

public class RequestCheckin {

	private String appUser;
	private String password;

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = Constans.ucFirst(appUser);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Constans.ucFirst(password);
	}

	@Override
	public String toString() {
		return "RequestCheckin [appUser=" + appUser + ", password=" + password + "]";
	}

}
