package com.utilitarios;


import java.io.IOException;

import java.net.Socket;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import com.mensajes.Comandos;
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
	public  LoginHandler(Socket _socket, Login_GUI _loginGui) {

			entradaSalida=new EntradaSalida(_socket);
			loginGui=_loginGui;
	}
	
	public void enviarUserPass() {
		
		String userPass= loginGui.getUsername()+" "+loginGui.getPassword();
		Mensaje credenciales= new Mensaje(Comandos.LOGIN,userPass);
		entradaSalida.escribirMensaje(credenciales);
	}

	@Override
	public void run() {
		System.out.println("Login Handler Thread Running");
		boolean flag=true;
		System.out.println("entrando al bucle");
		StyledDocument sd;
			while(flag) {
				//System.out.println(""); //VAYA A SABER UNO, SI SACO ESTO NO ANDA
				if(loginGui.isBoton()) {
					loginGui.setBoton(false);
					enviarUserPass();
					try {
						
						Mensaje resultadoLogin=entradaSalida.recibirMensaje();
						
						int fueExitoso= Integer.parseInt(new String(resultadoLogin.getInformacion()));
						if(fueExitoso==0) {
							
							System.out.println("Credenciales incorrectas, intentelo nuevamente");
						}else {
							System.out.println("Credeciales verificadas exitosamente.. Logueandose al sistema.");
							loginGui.setVisible(false);
							
							
							//******
							
							userName=loginGui.getUsername();
							Lobby_GUI lobbyGui= new Lobby_GUI(entradaSalida,userName);
							lobbyGui.setVisible(true);
							sd=lobbyGui.getChatLobby().getStyledDocument();
							sd.insertString(sd.getLength(), "Credeciales verificadas exitosamente.. Logueandose al sistema.", null);
							flag=false;
						}
					} catch (IOException | ClassNotFoundException | BadLocationException e) {e.printStackTrace();} //INFORMAR GUI/LOG
				
					}
				}
			
		
		
		System.out.println("Login Handler Thread Saliendo sin novedades.");
	}

}
