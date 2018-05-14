package com.mensajes;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -5837724430728888410L;
	String comando;
	String tipoInformacion;//Texto,audio,fotos,videos.
	byte informacion[]; // es el dato siendo enviado. En principio, solo texto.
	int size;
	String IDSala;
	

	
	public Mensaje(String comando, String tipoInformacion, byte[] informacion, int sizeInfo,String IDSala) {
		this.comando = comando;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
		size=sizeInfo;
		this.IDSala=IDSala;
	}
	public Mensaje(String comando, String tipoInformacion, byte[] informacion, int sizeInfo) {
		this.comando = comando;
		this.tipoInformacion = tipoInformacion;
		this.informacion = informacion;
		size=sizeInfo;
		this.IDSala = "lobby";
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
	
	public String getIDSala() {
		return IDSala;
	}
	
	@Override
	public String toString() {
		try {
			return new String(informacion,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
