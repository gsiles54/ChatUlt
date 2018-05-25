package com.mensajes;

import java.io.Serializable;



public class Mensaje implements Serializable {
	
	private static final long serialVersionUID = -5837724430728888410L;
	String comando;
	String informacion; // es el dato siendo enviado. En principio, solo texto.
	Integer IDSalaDestinatario;
	String clienteEmisor;

	/**Usado para: Enviar chat a sala  */
	public Mensaje(String comando,String informacion,Integer IDSala, String emisor) {
		if(informacion==null) {System.out.println(comando.toString());}
		this.comando = comando;
		this.informacion = informacion;
		this.IDSalaDestinatario=IDSala;
	}
	/**Usado para: logout */
	public Mensaje(String comando, String informacion) {
		if(informacion==null) {System.out.println(comando.toString());}
		this.comando = comando;
		this.informacion = informacion;
		this.IDSalaDestinatario = -1; //-1 es el lobby por defecto
	}
	
	/**Usado para:  */
	public Mensaje(String comando, String informacion, String emisor) {
		if(informacion==null) {System.out.println(comando.toString());}
		this.comando = comando;
		this.informacion = informacion;
		this.IDSalaDestinatario = -1; //-1 es el lobby por defecto
		this.clienteEmisor=emisor;
	}
	
	public String getEmisor() {return clienteEmisor;}
	
	public String getComando() {
		return comando;
	}
		
	public String getInformacion() {
		return informacion;
	}
	
	public Integer getIDSala() {
		return IDSalaDestinatario;
	}
	
	@Override
	public String toString() {
		
			return this.informacion;
		
	}
	
	
}
