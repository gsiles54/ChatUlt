package com.cadena;

import com.mensajes.Mensaje;
import com.vista.ControladorCliente;

public abstract class Manejador {
	protected Manejador siguiente;
	ControladorCliente cl = null;
	
	public void enlazarSiguiente(Manejador sig) {
		siguiente=sig;
	}
	
	public abstract void manejarPeticion(Mensaje msj);
			
}
