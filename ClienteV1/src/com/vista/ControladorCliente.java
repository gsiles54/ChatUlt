package com.vista;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;

import com.mensajes.Comandos;
import com.mensajes.Formato;
import com.mensajes.Mensaje;
import com.utilitarios.EntradaSalida;

/**
 * PORqué tengo que meter esta clase en com.vista si es un controlador? solo para tener acceso directo a los componentes
 * GUI ??? otra forma ?
 * @author Maxi
 *
 */
public class ControladorCliente implements Runnable{
	ArrayList<String> copiaClientesEnLobby; 
	ArrayList<String> copiaSalasDisponibles; //El cliente solo necesita saber el nombre identificativo.
	HashMap<String,String> copiaClientesEnSalas; // (String:nombre del Cliente ; String:Sala)
	HashMap<String,Conversacion> conversacionesActivas;
	DefaultListModel<String> modeloLista;
	EntradaSalida entradaSalida;
	Lobby_GUI lobbyGUI;
	private String userName;
	
	public ControladorCliente(EntradaSalida _entradaSalida, Lobby_GUI _lobbyGUI, String _userName) {
		lobbyGUI=_lobbyGUI;
		userName=_userName;
		entradaSalida=_entradaSalida;
		modeloLista= new DefaultListModel<String>();
		copiaClientesEnLobby= new ArrayList<String> ();
		lobbyGUI.listaClientesConectados.setModel(modeloLista);
		copiaSalasDisponibles=new ArrayList<String> ();
		copiaClientesEnSalas=new HashMap<String,String>();
		conversacionesActivas=new HashMap<String,Conversacion>();
		
		entradaSalida.escribirMensaje(new Mensaje(Comandos.ClienteNuevo,Formato.TEXTO,
											userName.getBytes(),userName.length()));
	}

	@Override
	public void run() {
		while(true) {
				
				try {
					Mensaje mensajeRecibido = entradaSalida.recibirMensaje();
					procesarMensaje(mensajeRecibido);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


		}
		
	}
	
	private void procesarMensaje(Mensaje mensaje) {
		
		switch(mensaje.getComando()) {
		
		case(Comandos.ClienteNuevo):
			String usuarioEntrante= new String(mensaje.getInformacion());
			copiaClientesEnLobby.add(usuarioEntrante);
			modeloLista.addElement(usuarioEntrante);
			lobbyGUI.listaClientesConectados.setModel(modeloLista);
			System.out.println("asdasdasd");
			break;
		}
	}
	


}
