package com.vista;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.Cliente.Cliente;
import com.cadena.CrearSala;
import com.cadena.Manejador;
import com.cadena.MensajeASala;
import com.cadena.NuevoClienteConectado;
import com.mensajes.Comandos;
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
	HashMap<Cliente,ArrayList<Integer>> copiaClientesEnSalas;
	
	HashMap<String,Conversacion> conversacionesActivas;
	ArrayList<JFrame> listaGuis;
	DefaultListModel<String> modeloLista;
	
	EntradaSalida entradaSalida; //HACER HILO INPUT, CHAU ENTRADASALIDA
	Lobby_GUI lobbyGui;
	private String userName;
	public static ControladorCliente instance=null;
	Manejador manejador=null;
	public ControladorCliente(EntradaSalida _entradaSalida, Lobby_GUI _lobbyGUI, String _userName) {
		listaGuis = new ArrayList<JFrame>();
		listaGuis.add(_lobbyGUI);
		lobbyGui=_lobbyGUI;
		userName=_userName;
		entradaSalida=_entradaSalida;
		modeloLista= new DefaultListModel<String>();
		copiaClientesEnLobby= new ArrayList<String> ();
		lobbyGui.getListaClientesConectados().setModel(modeloLista);
		copiaSalasDisponibles=new ArrayList<Sala> ();
		copiaSalasDisponibles.add(new Sala ("Lobby",false,-1,_lobbyGUI)); // LOBBY
		copiaClientesEnSalas=new HashMap<>();
		conversacionesActivas=new HashMap<String,Conversacion>();
		
		System.out.println("Username es "+userName);
		//Cliente Nuevo Es para avisar al cliente NO al servidor, de que un cliente se conecto.
		entradaSalida.escribirMensaje(new Mensaje(Comandos.ClienteNuevo, userName));
		manejador = ensamblarChain();
		instance=this;
	}
	
	public ControladorCliente getControlador() { //CONSTRUCTOR DEBE SER PRIVADO/PROTECTED, LOS PARAMETROS NECESITAN IRSE
		
		return instance;
	}

	public synchronized void manejarMensaje(Mensaje mensaje) {
		
		manejador.manejarPeticion(mensaje);
}

	private Manejador ensamblarChain() {
		CrearSala cs = new CrearSala();
		MensajeASala msj = new MensajeASala();
		NuevoClienteConectado ncc= new NuevoClienteConectado(lobbyGui, modeloLista,copiaClientesEnLobby);
		//AGREGAR Comando de Cliente Nuevo Conectado : que agrega al modelList el String del cliente nuevo. Lo mismo para cliente Desconectado

		cs.enlazarSiguiente(msj);
		ncc.enlazarSiguiente(cs);
		return ncc;
	}
	
	@Override
	public void run() {
		
		while(true) {
				
				try {
					Mensaje mensajeRecibido = entradaSalida.recibirMensaje();
					System.out.println(mensajeRecibido.getComando()+" | "+mensajeRecibido.getInformacion());
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
		if(!esParaEsteCliente(mensaje)) {
				StyledDocument sd = lobbyGui.getChatLobby().getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_LEFT);
		try {
			sd.insertString(sd.getLength(), mensaje.getInformacion(), null);
			sd.setParagraphAttributes(sd.getLength()+1, 1, center, false);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	
	}

	private boolean esParaEsteCliente(Mensaje mensaje) {
		String[] array = mensaje.getInformacion().split(" : ");
		
		// TODO Auto-generated method stub
		return array[0].equals('\n'+userName);
	}


}
