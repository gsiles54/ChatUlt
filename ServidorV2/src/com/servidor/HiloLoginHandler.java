package com.servidor;

import java.io.IOException;

import java.net.Socket;

import com.DB.Comandos;
import com.DB.DAO_BaseDeDatos;
import com.cliente.ClientInputHandler;
import com.cliente.ClientOutputHandler;
import com.cliente.Cliente;
import com.logs.LoggerCliente;
import com.mensajes.Formato;
import com.mensajes.Mensaje;

/**
 * Clase para acceder a informacion en la base de datos. DAO = Data Access
 * Object. Se crea Uno por cada cliente que se conecta, se crea, hace lo que
 * hace y se destruye.
 * 
 * @author Maxi
 *
 */
public class HiloLoginHandler implements Runnable {
	boolean running;
	ClientOutputHandler salida;
	ClientInputHandler entrada;
	Socket socket;
	String userName;
	String password;
	LoggerCliente logger;
	ControladorServidor controlador;

	public HiloLoginHandler(Socket _socket) throws IOException {
		salida = new ClientOutputHandler(_socket);
		entrada = new ClientInputHandler(_socket);
		this.socket=_socket;
		logger = new LoggerCliente();
		controlador = ControladorServidor.getInstance();
	}

	@Override
	public void run() {
		boolean usuarioValido = false;
		Cliente c=null;
		do {
			String usuarioYPassword[] = getDatosDeUsuario();
			String usuarioRecibido = usuarioYPassword[0];
			String passwordRecibido = usuarioYPassword[1];
			usuarioValido = validarUsuario(usuarioRecibido, passwordRecibido);
			
			contestarUsuario(usuarioValido);
			if(usuarioValido) {
				
				logger.enviarLog("Usuario "+ usuarioRecibido +" ha entrado al chat.");
				c = new Cliente(usuarioRecibido, salida,entrada);
				try {
					controlador.entrarAlLobby(c);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			


		} while (usuarioValido == false);
	}

	private String[] getDatosDeUsuario() {
		
		try {
			
			Mensaje mensajeRecibido = entrada.recibirMensaje();
			int cantidadCaracteres = mensajeRecibido.getSize();
			byte bytesDeUsuario[] = mensajeRecibido.getInformacion();

			System.out.println("Cantidad Caracteres recibidos " + cantidadCaracteres);
			String userPassword = new String(bytesDeUsuario, "UTF-8");
			System.out.println(userPassword);
			return userPassword.split(" ");

		} catch (ClassNotFoundException | IOException e1) {
			logger.enviarLog("No se pudo recibir los datos de usuario.");
			e1.printStackTrace();
		}

		return null;

	}

	private boolean validarUsuario(String usuario, String password) {
		System.out.println(usuario + " " + password);
		return DAO_BaseDeDatos.getInstance().validarUsuario(usuario, password);
	}

	public void contestarUsuario(boolean usuarioValido) { // REVISAR PARTE CLIENTE DEBE TENER IMPLEMENTADO SWITCH/CADENA DE RESPNB

			if (usuarioValido) {
				byte[] respuesta = (new String("1")).getBytes();
				Mensaje contestacionLogin = new Mensaje(Comandos.LOGIN, Formato.ENTERO, respuesta, 1);
				salida.enviarMensaje(contestacionLogin);

			} else {
				byte[] respuesta = new String("0").getBytes();
				Mensaje contestacionLogin = new Mensaje(Comandos.LOGIN, Formato.ENTERO, respuesta, 1);
				salida.enviarMensaje(contestacionLogin);
			}


	}

}
