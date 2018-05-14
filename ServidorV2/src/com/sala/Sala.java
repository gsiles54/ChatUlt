package com.sala;

import java.util.ArrayList;

import com.cliente.Cliente;
import com.mensajes.Mensaje;
/**
 * Una sala de chat entre almenos 1 clientes. Puede ser publica o privada.
 * La sala se identifica por un nombre o HashTag.
 * @author Maxi
 *
 */
public class Sala {
	ArrayList<Cliente> clientesEnSala;
	String nombre; //se lo puede usar como hashTag.
	boolean esPrivada=false; //Todas las salas son publicas por defecto
	String salaID;
	Asistente asist;
	
	public Sala(String _nombreSala, boolean _esPrivada) {
		nombre=_nombreSala;
		esPrivada= _esPrivada;
		clientesEnSala = new ArrayList<Cliente>();
	}

	public void meterCliente(Cliente cli) {
		if (clientesEnSala.contains(cli))
			return; // INFORMAR DE USUARIO REPETIDO EN SALA?
		clientesEnSala.add(cli);
	}
	
	public void sacarCliente(Cliente cli) {
		if (!clientesEnSala.contains(cli))
			return; // INFORMAR DE USUARIO REPETIDO EN SALA?
		clientesEnSala.remove(cli);
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean esPrivada() {
		return esPrivada;
	}
	
	public String getSalaID() {
		return salaID;
	}
	
	public void broadcastSala(Mensaje mensaje) {
		for(Cliente c:clientesEnSala) {
			c.enviarMensaje(mensaje);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	
}
