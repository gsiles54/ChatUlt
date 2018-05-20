package com.cadena;

import com.cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;
/*Si el mensaje es un simple texto dirigido a la sala, llama al metodo que 
 * manda el mensaje a la sala que corresponda*/
public class EnviarMsjASala extends Manejador{

	
	@Override
	public void manejarPeticion(Mensaje mensaje,Cliente Cli) {
		
		           
			if (mensaje.getComando().equals(Comandos.MensajeASala)) {
				cs=ControladorServidor.getInstance();
				cs.enviarMensajeASala(mensaje);
		}
		else
		{
			//siguiente.manejarPeticion(mensaje);
			System.out.println("Mensaje era: " + mensaje.toString());
			System.out.println("Agregar mas manejadores");
		}
		// TODO Auto-generated method stub
		
	}

}
