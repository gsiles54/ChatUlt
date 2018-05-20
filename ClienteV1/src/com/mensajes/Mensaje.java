package com.mensajes;

import java.io.Serializable;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -5837724430728888410L;
	String comando;
	String tipoInformacion;//Texto,audio,fotos,videos.
	String informacion; // es el dato siendo enviado. En principio, solo texto.
	
	
	public Mensaje(String comando, String tipoInformacion, String informacion) {
		this.comando = comando;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
	
	}
	
	public String getComando() {
		return comando;
	}
	public String getTipoInformacion() {
		return tipoInformacion;
	}
	public String getInformacion() {
		return informacion;
	}
	
}
