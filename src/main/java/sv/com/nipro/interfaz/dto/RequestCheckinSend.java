/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.dto;

/**
 *
 * @author karina.palacios
 */
public class RequestCheckinSend {

	private Boolean Status;
	private String Message;
	private String Token;

	public Boolean getStatus() {
		return Status;
	}

	public void setStatus(Boolean Status) {
		this.Status = Status;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String Message) {
		this.Message = Message;
	}

	public String getToken() {
		return Token;
	}

	public void setToken(String Token) {
		this.Token = Token;
	}

	@Override
	public String toString() {
		return "Response [Status=" + Status + ", Message=" + Message + ", Token=" + Token + "]";
	}

}
