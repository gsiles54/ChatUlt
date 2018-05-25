package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;

public class CrearSala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje p) {
		// TODO Auto-generated method stub
		if(p.getComando().equals(Comandos.CrearSala)) {
			
		}
		else {
		
			siguiente.manejarPeticion(p);
		}
	}

}
