package com.cadena;

import com.cliente.Cliente;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;
/* Aca se implementa la cadena de responsabilidad para manejar los mjs*/
public abstract class Manejador {
		protected Manejador siguiente;

		ControladorServidor cs=null;
		
		public void enlazarSiguiente(Manejador sig) {
			siguiente=sig;
		}
		
		public abstract void manejarPeticion(Mensaje p,Cliente Cli);
				
}
