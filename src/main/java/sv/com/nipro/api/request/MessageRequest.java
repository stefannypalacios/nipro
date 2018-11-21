package sv.com.nipro.api.request;

public class MessageRequest {
	private String token;
	private String Mensaje;
	private String Checksum;
	
	public MessageRequest(String token, String mensaje, String checksum) {
		super();
		this.token = token;
		Mensaje = mensaje;
		Checksum = checksum;
	}
	
	
}
