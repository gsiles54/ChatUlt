package com.vista;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;

import com.cadena.CrearSala;

import com.cadena.Manejador;
import com.cadena.MensajeASala;
import com.mensajes.Comandos;
import com.mensajes.Formato;
import com.mensajes.Mensaje;
import com.salas.Sala;
import com.utilitarios.EntradaSalida;

/**
 * PORqué tengo que meter esta clase en com.vista si es un controlador? solo para tener acceso directo a los componentes
 * GUI ??? otra forma ?
 * @author Maxi
 *
 */
public class ControladorCliente implements Runnable{
	ArrayList<String> copiaClientesEnLobby; 
	ArrayList<Sala> copiaSalasDisponibles; //El cliente solo necesita saber el nombre identificativo.
	HashMap<String,String> copiaClientesEnSalas; // (String:nombre del Cliente ; String:Sala)
	HashMap<String,Conversacion> conversacionesActivas;
	ArrayList<JFrame> listaGuis;
	DefaultListModel<String> modeloLista;
	EntradaSalida entradaSalida;
	Lobby_GUI lobbyGui;
	private String userName;
	private static ControladorCliente instance=null;
	
	public ControladorCliente(EntradaSalida _entradaSalida, Lobby_GUI _lobbyGUI, String _userName) {
		listaGuis = new ArrayList<JFrame>();
		listaGuis.add(_lobbyGUI);
		lobbyGui=_lobbyGUI;
		userName=_userName;
		entradaSalida=_entradaSalida;
		modeloLista= new DefaultListModel<String>();
		copiaClientesEnLobby= new ArrayList<String> ();
		lobbyGui.listaClientesConectados.setModel(modeloLista);
		copiaSalasDisponibles=new ArrayList<Sala> ();
		copiaSalasDisponibles.add(new Sala ("Lobby",false,-1,_lobbyGUI)); // LOBBY
		copiaClientesEnSalas=new HashMap<String,String>();
		conversacionesActivas=new HashMap<String,Conversacion>();
		instance=this;
		entradaSalida.escribirMensaje(new Mensaje(Comandos.ClienteNuevo,
											userName));
	}
	
	public ControladorCliente getControlador() {
		
		return instance;
	}

	public synchronized void manejarMensaje(Mensaje mensaje) {
		Manejador manejador = ensamblarChain();
		manejador.manejarPeticion(mensaje);
}

//Emsambla la cadena de manejadores
	private Manejador ensamblarChain() {
		CrearSala cs = new CrearSala();
		MensajeASala msj = new MensajeASala();

		cs.enlazarSiguiente(msj);
		return cs;
	}
	
	@Override
	public void run() {
		
		while(true) {
				
				try {
					Mensaje mensajeRecibido = entradaSalida.recibirMensaje();
					manejarMensaje(mensajeRecibido);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
	}
	
/*	private void procesarMensaje(Mensaje mensaje) {
		
		switch(mensaje.getComando()) {
		
		case(Comandos.ClienteNuevo):
			String usuarioEntrante= new String(mensaje.getInformacion());
			copiaClientesEnLobby.add(usuarioEntrante);
			modeloLista.addElement(usuarioEntrante);
			lobbyGUI.listaClientesConectados.setModel(modeloLista);
			System.out.println("asdasdasd");
			break;
		}
	}*/
	
	public void imprimirMsjEnSala(Mensaje mensaje) {
		if(mensaje.getIDSala().equals(-1)) {
			imprimirEnLobby(mensaje);
		}
		for(Sala s: copiaSalasDisponibles) {
			if(s.getSalaID().equals(mensaje.getIDSala())) {
				imprimirMsj(mensaje);
			}
		}
	}

	private void imprimirMsj(Mensaje mensaje) {
		// TODO Auto-generated method stub
		
	}
	
	private void imprimirEnLobby(Mensaje mensaje) {
		lobbyGui.getChatLobby().setText(mensaje.getInformacion());
	}


}
