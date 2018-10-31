package sv.com.nipro.interfaz.dto;

public class RequestCheckin {

	private String appUser;
	private String password;

	public String getAppUser() {
		return appUser;
	}

	public void setAppUser(String appUser) {
		this.appUser = appUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RequestCheckin [appUser=" + appUser + ", password=" + password + "]";
	}

}
