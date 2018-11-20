package sv.com.nipro.interfaz.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

public class RequestAcceptMessage {

	private String token;
	private String mensaje;
	private String checksum;

	public String getToken() {
		return token;
	}

	@JsonSetter("Token")
	public void setToken(String token) {
		this.token = token;
	}

	public String getMensaje() {
		return mensaje;
	}

	@JsonSetter("Mensaje")
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getChecksum() {
		return checksum;
	}

	@JsonSetter("Checksum")
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	@Override
	public String toString() {
		return "RequestAcceptMessage [token=" + token + ", mensaje=" + mensaje + ", checksum=" + checksum + "]";
	}
	
	

}
