package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.vista.ControladorCliente;

public class MensajeASala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje msj) {
		// TODO Auto-generated method stub
		if(msj.getComando().equals(Comandos.MensajeASala)) {
			cl=ControladorCliente.instance; // Hice publica la variable , porque sino es privada y se quiere hacer cl.getInstance() ( cl es nulo
											// aca porque su constructor es publico y no es un singleton y bueno nada explota re fiero. ( REVISAR)
			
			cl.imprimirMsjEnSala(msj);
		}
		else {
			System.out.println("agregar manej");
		//	siguiente.manejarPeticion(msj);
		}
	}

}
