package com.Chain;

import com.cliente.Cliente;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;
/* Aca se implementa la cadena de responsabilidad para manejar los mjs*/
public abstract class Chain {
		protected Chain siguiente;

		ControladorServidor cs=null;
		
		public void enlazarSiguiente(Chain sig) {
			siguiente=sig;
		}
		
				
		public abstract void manejarPeticion(Mensaje p);
}
