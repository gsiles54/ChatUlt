package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;

public class MensajeASala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje msj) {
		// TODO Auto-generated method stub
		if(msj.getComando().equals(Comandos.MensajeASala)) {
			cl=cl.getControlador();
			cl.imprimirMsjEnSala(msj);
		}
		else {
			siguiente.manejarPeticion(msj);
		}
	}

}
