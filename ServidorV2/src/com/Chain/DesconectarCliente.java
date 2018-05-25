package com.Chain;

import java.util.ArrayList;
import java.util.HashMap;

import com.cliente.Cliente;
import com.logs.LoggerCliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;



public class DesconectarCliente extends Chain{
	
	ArrayList<Cliente> clientesEnLobby;
	HashMap<Cliente,ArrayList<Integer>> clientesEnSalas;
	
	

	public DesconectarCliente(ArrayList<Cliente> clientesEnLobby,HashMap<Cliente, ArrayList<Integer>> clientesEnSalas) {
		super();
		this.clientesEnLobby = clientesEnLobby;
		this.clientesEnSalas = clientesEnSalas;
	}



	@Override
	public void manejarPeticion(Mensaje mensaje) {
		if(mensaje==null)System.out.println("Mensaje Null en DesconectarCliente");
		if (mensaje.getComando().equals(Comandos.LOGOUT))
		{           
			Cliente cliente = getCliente(mensaje.getInformacion());
			clientesEnSalas.remove(cliente);
			clientesEnLobby.remove(cliente);
			
			aTodos_ClienteDesconectado(cliente);
			LoggerCliente.enviarLog("Cliente: "+mensaje.getInformacion()+" solicito desconectarse del chat");
		}
		else
		{
			siguiente.manejarPeticion(mensaje);
		}
	}
	
	private Cliente getCliente(String nombre) {
		for (Cliente c : clientesEnLobby) {
			if (c.getNombre().equals(nombre))
				return c;
		}
		return null;
	}
	
	private void aTodos_ClienteDesconectado(Cliente saliente) {
		int a=0;
		for(Cliente c: clientesEnLobby) {
			if(!c.equals(saliente))
			{
				c.enviarMensaje(new Mensaje(Comandos.ClienteSaliendo,saliente.getNombre()));
				//Verificar si era el ultimo cliente en la sala tambien!!!
			}
		}
		LoggerCliente.enviarLog("Acaba de informarse a los clientes que "+saliente.getNombre()+" se ha desconectado.");
	}
}
