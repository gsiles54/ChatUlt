package com.cadena;

import com.cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;

public class CrearSala extends Manejador{

	@Override
	public void manejarPeticion(Mensaje mensaje,Cliente Cli) {
		if (mensaje.getComando().equals(Comandos.CrearSala))
		{           
			Integer ID=-1;
			String[] info = mensaje.getInformacion().split(" ");
			boolean esPrivada = conseguirEstado(info[0]);
			String nombreSala = info[1];
			
			ID=cs.crearSala( nombreSala, esPrivada);
			Cli.enviarMensaje(new Mensaje(Comandos.SalaCreadaExitosamente,ID.toString()));
			
			System.out.println("Se solicito la creacion de la sala: "+nombreSala);
		}
		else
		{
			siguiente.manejarPeticion(mensaje,Cli);
		}
	}

	private boolean conseguirEstado(String string) {
		// TODO Auto-generated method stub
		
		return string.equals("1");
	}


}