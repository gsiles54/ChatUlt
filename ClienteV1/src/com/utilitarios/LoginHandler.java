package com.utilitarios;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mensajes.Comandos;
import com.mensajes.Formato;
import com.mensajes.Mensaje;
import com.vista.Lobby_GUI;
import com.vista.Login_GUI;
/*
 * Esta clase solo se dedica a loguearse e informar si salio bien o mal. Si sale bien, pasa el socket a otro lado.
 * Ver de enviar esta info por canal encriptado ?
 */
public class LoginHandler implements Runnable {
	
	boolean running;
	EntradaSalida entradaSalida;
	String userName;
	String password;
	Login_GUI loginGui;
	
	//ESTE SOCKET NO LO TENGO Q PERDER!
	public  LoginHandler(Socket _socket, String name, String pass, Login_GUI _loginGui) {
			userName=name;
			password=pass;
			entradaSalida=new EntradaSalida(_socket);
			loginGui=_loginGui;
	}
	
	public void enviarUserPass() {
		try {
			byte userPass[]= (userName+" "+password).getBytes("UTF-8");
			Mensaje credenciales= new Mensaje(Comandos.LOGIN,Formato.TEXTO,userPass,userPass.length);
			entradaSalida.escribirMensaje(credenciales);
		} catch (IOException e) {e.printStackTrace();} //INFORMAR GUI/LOG del cliente ?
	}

	@Override
	public void run() {
		System.out.println("Login Handler Thread Running");
		enviarUserPass();
		try {
			Mensaje resultadoLogin=entradaSalida.recibirMensaje();
			
			int fueExitoso= Integer.parseInt(new String(resultadoLogin.getInformacion()));
			if(fueExitoso==0) {
				System.out.println("Credenciales incorrectas, intentelo nuevamente");
			}else {
				System.out.println("Credeciales verificadas exitosamente.. Logueandose al sistema.");
				loginGui.setVisible(false);
				
				Lobby_GUI lobbyGui= new Lobby_GUI(entradaSalida,userName);
				lobbyGui.setVisible(true);
			}
		} catch (IOException | ClassNotFoundException e) {e.printStackTrace();} //INFORMAR GUI/LOG
		System.out.println("Login Handler Thread Saliendo sin novedades.");
	}

}