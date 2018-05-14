package com.cliente;


import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.Socket;

import com.logs.LoggerCliente;
import com.mensajes.Mensaje;
import com.servidor.ControladorServidor;

/**
 * Utiliza ObjectInputStream, NO usa DataInputStream.
 * Se encarga de como responder ante las entradas de un cliente. Existe uno para cada Cliente.
 * Esta clase recibe un objeto del tipo IMensaje.
 * @author Maxi
 *
 */
public class ClientInputHandler implements Runnable {

	
	LoggerCliente logger;
	Mensaje mensaje;
	boolean conectado=true;
	ObjectInputStream objectIn;
	Socket socket;
	
	public ClientInputHandler(Socket socket) throws IOException {
	
		logger = new LoggerCliente();
		this.socket=socket;
		objectIn = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		
		ControladorServidor cs = ControladorServidor.getInstance();
		while (conectado) {

				try {
					mensaje= recibirMensaje();
					cs.manejarMensaje(mensaje);
				} catch (ClassNotFoundException | IOException e) {e.printStackTrace();}

		}
	}
	
	public Mensaje recibirMensaje() throws ClassNotFoundException, IOException {
		return (Mensaje) objectIn.readObject();
	}
}
