package sv.com.nipro.api.response;

import java.io.Serializable;

public class UsuarioResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	public boolean estado;
	public String mensaje;
	public String token;
	
	public UsuarioResponse(boolean estado, String mensaje, String token) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.token = token;
	}
	
	
}
