/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.com.nipro.interfaz.dto;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 *
 * @author karina.palacios
 */
public class Response {

	private Boolean estado;
	private String mensaje;

	public Boolean getEstado() {
		return estado;
	}

	@JsonSetter("Estado")
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getMensaje() {
		return mensaje;
	}

	@JsonSetter("Mensaje")
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "Response [estado=" + estado + ", mensaje=" + mensaje + "]";
	}

}
