package com.Chain;

import com.cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;
/*Si el mensaje es un simple texto dirigido a la sala, llama al metodo que 
 * manda el mensaje a la sala que corresponda*/
public class EnviarMsjASala extends Chain{

	
	@Override
	public void manejarPeticion(Mensaje mensaje) {
		
		           
			if (mensaje.getComando().equals(Comandos.MensajeASala)) {
				cs=ControladorServidor.getInstance();
				cs.enviarMensajeASala(mensaje);
		}
		else
		{	if(mensaje==null) System.out.println("MENSAJE ES NULL EN ENVIARMSJASALA");
			System.out.println("EnviarMsjASala: La informacion del mensaje era: " + mensaje.toString());
			System.out.println("Agregar mas manejadores");
		}
		
	}

}
