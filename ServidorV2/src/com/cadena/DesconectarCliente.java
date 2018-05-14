package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;

public class DesconectarCliente extends Manejador{

	@Override
	public void manejarPeticion(Mensaje mensaje) {
		if (mensaje.getComando().equals(Comandos.LOGOUT))
		{           
			//DESDE EL CLIENTE EN LA INFORMACION DEL MENSAJE SE DEBERIA EXPLICITAR EL NICK/USER
			System.out.println("Cliente: "+mensaje.toString()+" solicito desconectarse del chat");
		}
		else
		{
			siguiente.manejarPeticion(mensaje);
		}
	}
}
