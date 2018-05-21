package com.utilitarios;

import com.Cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.vista.Lobby_GUI;

public class HiloOutput implements Runnable{
	
	Cliente cliente;
	Lobby_GUI lobbyGui;
	String texto;
	EntradaSalida entradaSalida;
	
	public HiloOutput(Cliente cliente, Lobby_GUI lobby_Gui, EntradaSalida entradaSalida){
		this.cliente=cliente;
		this.lobbyGui=lobby_Gui;
		this.entradaSalida=entradaSalida;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean flag = true;
		while(flag) {
			if(lobbyGui.isChatBox()) { // tengo en cuenta todos los GUI? o hago hilos separados?
				lobbyGui.setChatBox(false);
				texto=lobbyGui.getChatTextBoxLobby().getText();
				entradaSalida.escribirMensaje(new Mensaje(Comandos.MensajeASala,texto));
				lobbyGui.getChatTextBoxLobby().setText("");
			}
		}
	}

}
