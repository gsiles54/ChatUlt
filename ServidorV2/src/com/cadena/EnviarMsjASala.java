package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;

public class EnviarMsjASala extends Manejador{

	
	@Override
	public void manejarPeticion(Mensaje mensaje) {
		
		ControladorServidor cs=null;
		           
			if (mensaje.getComando().equals(Comandos.MensajeASala)) {
				cs=ControladorServidor.getInstance();
				cs.enviarMensaje(mensaje);
		}
		else
		{
			//siguiente.manejarPeticion(mensaje);
			System.out.println("Agregar mas manejadores");
		}
		// TODO Auto-generated method stub
		
	}

}
