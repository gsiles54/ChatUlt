package com.cadena;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;

public class CrearSala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje mensaje) {
		if (mensaje.getComando().equals(Comandos.CrearSala))
		{           
			String nombreSala= new String(mensaje.getInformacion());
			System.out.println("Se solicito la creacion de la sala: "+nombreSala);
		}
		else
		{
			siguiente.manejarPeticion(mensaje);
		}
	}


}