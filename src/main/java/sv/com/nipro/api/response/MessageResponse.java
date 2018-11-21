package sv.com.nipro.api.response;

import java.io.Serializable;

public class MessageResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public boolean Estado;
	public String Mensaje;
	
	public MessageResponse(boolean estado, String mensaje) {
		super();
		Estado = estado;
		Mensaje = mensaje;
	}
	
	
}
