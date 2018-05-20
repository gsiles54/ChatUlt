package com.mensajes;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;


public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -5837724430728888410L;
	String comando;
	String informacion; // es el dato siendo enviado. En principio, solo texto.
	Integer IDSala;


	
	public Mensaje(String comando,String informacion,Integer IDSala) {
		this.comando = comando;
		this.informacion = informacion;
		this.IDSala=IDSala;
	}
	
	public Mensaje(String comando, String informacion) {
		this.comando = comando;
		this.informacion = informacion;
		this.IDSala = 0;
	}
	
	public String getComando() {
		return comando;
	}
		
	public String getInformacion() {
		return informacion;
	}
	
	public Integer getIDSala() {
		return IDSala;
	}
	
	@Override
	public String toString() {
		
			return this.informacion;
		
	}
	
	
}
