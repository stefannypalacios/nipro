package sv.com.nipro.api.response;

public class CheckoutResponse {
	public boolean Estado;
	public String Mensaje;
	
	public CheckoutResponse(boolean estado, String mensaje) {
		super();
		Estado = estado;
		Mensaje = mensaje;
	}
	
	
}
