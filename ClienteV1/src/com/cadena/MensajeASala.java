package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.vista.ControladorCliente;

public class MensajeASala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje msj) {
		// TODO Auto-generated method stub
		if(msj.getComando().equals(Comandos.MensajeASala)) {
			cl=ControladorCliente.instance;
			
			cl.imprimirMsjEnSala(msj);
		}
		else {
			System.out.println("agregar manej");
		//	siguiente.manejarPeticion(msj);
		}
	}

}
