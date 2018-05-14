package com.cadena;

import com.mensajes.Mensaje;

public abstract class Manejador {
		protected Manejador siguiente;
		
		
		public void enlazarSiguiente(Manejador sig) {
			siguiente=sig;
		}
		
		public abstract void manejarPeticion(Mensaje p);
				
}
