package com.cadena;

public class MensajeP {
	
	private int value;
	private String descripcion;
	
	public MensajeP (String descripcion , int value){
		this.value = value;
		this.descripcion = descripcion;
	}

	public int getValue() {
		return value;
	}

	public String getDescripcion() {
		return descripcion;
	}
}