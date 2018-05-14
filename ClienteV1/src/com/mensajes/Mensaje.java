package com.mensajes;

import java.io.Serializable;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -5837724430728888410L;
	String comando;
	String tipoInformacion;//Texto,audio,fotos,videos.
	byte informacion[]; // es el dato siendo enviado. En principio, solo texto.
	int size;
	
	public Mensaje(String comando, String tipoInformacion, byte[] informacion, int sizeInfo) {
		this.comando = comando;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
		size=sizeInfo;
	}
	
	public String getComando() {
		return comando;
	}
	public String getTipoInformacion() {
		return tipoInformacion;
	}
	public byte[] getInformacion() {
		return informacion;
	}
	public int getSize() {
		return size;
	}
}
