package com.cadena;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.vista.Lobby_GUI;

public class NuevoClienteConectado extends Manejador {
	
	Lobby_GUI lobbyGui;
	DefaultListModel<String> modeloLista;
	ArrayList<String> copiaClientesEnLobby; 


	public NuevoClienteConectado(Lobby_GUI lobbyGui, DefaultListModel<String> modeloLista, ArrayList<String> _copiaClientesEnLobby) {
		super();
		this.lobbyGui = lobbyGui;
		copiaClientesEnLobby=_copiaClientesEnLobby;
		this.modeloLista = modeloLista;
	}


	@Override
	public void manejarPeticion(Mensaje mensaje) {
		// TODO Auto-generated method stub
		if(mensaje.getComando().equals(Comandos.ClienteNuevo)) {
			String usuarioEntrante= new String(mensaje.getInformacion());
			//if(!modeloLista.contains(usuarioEntrante)) {
			copiaClientesEnLobby.add(usuarioEntrante);
			modeloLista.addElement(usuarioEntrante);
			lobbyGui.getListaClientesConectados().setModel(modeloLista);
			//}
		}
		else {
			siguiente.manejarPeticion(mensaje);
		}
	}
}
