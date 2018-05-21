package com.salas;

import com.vista.Lobby_GUI;

public class Sala {

	String nombre; //se lo puede usar como hashTag.
	boolean esPrivada=false; //Todas las salas son publicas por defecto
	Integer salaID;
	Sala_GUI SalaGUI;
	Lobby_GUI lobby;
	
	public Sala(String nombre, boolean esPrivada, Integer salaID, Sala_GUI salaGUI){
		this.nombre=nombre;
		this.esPrivada=esPrivada;
		this.salaID=salaID;
		this.SalaGUI=salaGUI;
	}

	public Sala(String nombre, boolean esPrivada, Integer salaID, Lobby_GUI lobbyGui){
		this.nombre=nombre;
		this.esPrivada=esPrivada;
		this.salaID=salaID;
		this.lobby=lobbyGui;
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
