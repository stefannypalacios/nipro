package sv.com.nipro.interfaz.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class RequestCheckout {

	private String token;

	public String getToken() {
		return token;
	}

	@JsonSetter("Token")
	public void setToken(String token) {
		this.token = token;
	}

}
