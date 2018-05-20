package com.salas;



public class Sala {

	String nombre; //se lo puede usar como hashTag.
	boolean esPrivada=false; //Todas las salas son publicas por defecto
	Integer salaID;

	Sala(String nombre, boolean esPrivada, Integer salaID){
		this.nombre=nombre;
		this.esPrivada=esPrivada;
		this.salaID=salaID;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isEsPrivada() {
		return esPrivada;
	}

	public void setEsPrivada(boolean esPrivada) {
		this.esPrivada = esPrivada;
	}

	public Integer getSalaID() {
		return salaID;
	}


	
	
}
