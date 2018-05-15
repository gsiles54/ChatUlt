package com.cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.mensajes.Mensaje;

public class ClientOutputHandler implements Runnable{

	ObjectOutputStream objectOut;
	Socket socket;
	List<Mensaje> lMensaje = new ArrayList<Mensaje>();
	
	public ClientOutputHandler(Socket socket) throws IOException {
		this.socket=socket;
		objectOut=new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void sigueVivo() {
		//SE DEBERIA MANDAR UN MENSAJE SI EL CLIENTE ESTA AFK PARA SABER SI LA CONEXION SIGUE VIVA
	}
	
	private synchronized Mensaje siguienteMensaje() throws InterruptedException {
		while(lMensaje.isEmpty())
			wait();
		
		Mensaje mensaje = lMensaje.get(0);
		lMensaje.remove(0);
		return mensaje;
	}
	
	public synchronized void enviarMensaje(Mensaje mensaje) {
		lMensaje.add(mensaje);
		notify();
	}
	
	private synchronized void enviarMensajeAlCliente(Mensaje mensaje) {
		try {
			objectOut.writeObject(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		boolean flag=true;
		
		while(flag) {
			try {
				Mensaje msj = siguienteMensaje();
				enviarMensajeAlCliente(msj);
				System.out.println("Se envio el msj");
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
	}
}
