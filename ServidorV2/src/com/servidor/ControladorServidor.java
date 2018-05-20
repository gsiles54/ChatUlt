package com.servidor;

import java.util.ArrayList;
import java.util.HashMap;

import com.cadena.CrearSala;
import com.cadena.DesconectarCliente;
import com.cadena.EnviarMsjASala;
import com.cadena.Manejador;
import com.cliente.Cliente;
import com.logs.LoggerCliente;
import com.mensajes.Comandos;
import com.mensajes.Formato;
import com.mensajes.Mensaje;
import com.sala.Sala;

/**
 * Singleton. Controlador general del chat. Desde aca se deberia poder hacer
 * todo lo necesario en el funcionamiento publico del chat, como enviar mensajes
 * o logouts. Sera accedida desde multiples hilos al mismo tiempo.
 * <b>Sincronizar!</b>
 * 
 * @author Maxi
 *
 */
public class ControladorServidor {

	private static ControladorServidor instance = null;
	ArrayList<Cliente> clientesEnLobby; // Todos los clientes conectados al chat estan aca. esten o no chateando.
	ArrayList<Sala> salas;
	HashMap<Cliente, Integer> clientesEnSalas;// (Cliente ; ID_Sala) informacion de las salas en las que un cliente
												// esta.
	LoggerCliente logger;

	// TO-DO muchos metodos aca no son publicos, revisar.
	protected ControladorServidor() {
		logger = new LoggerCliente();
		clientesEnLobby = new ArrayList<Cliente>();
		salas = new ArrayList<Sala>();
		clientesEnSalas = new HashMap<Cliente, Integer>();
	}

	public synchronized static ControladorServidor getInstance() {
		if (instance == null) {
			instance = new ControladorServidor();
		}
		return instance;
	}

	public synchronized void decodificar(Mensaje mensaje) {
		// El gran y amado switch va a aca
		switch (mensaje.getComando()) {
		case (Comandos.MensajeASala):
			break;
		case ("B"):
			break;
		case ("c"):
			break;
		}
	}
	
	//Recibe el mensaje del input y se fija que garompa hacer con él
	public synchronized void manejarMensaje(Mensaje mensaje,Cliente Cli) {
				Manejador manejador = ensamblarChain();
				manejador.manejarPeticion(mensaje,Cli);
	}
	
	//Emsambla la cadena de manejadores
	private Manejador ensamblarChain() {
		CrearSala cs = new CrearSala();
		DesconectarCliente dc = new DesconectarCliente();
		EnviarMsjASala msj = new EnviarMsjASala();
		
		cs.enlazarSiguiente(dc);
		dc.enlazarSiguiente(msj);
		return cs;
	}

	/** Los mensajes se envian a una SALA, no a un cliente */
	/** Si es un mensaje de txt, busca que exista esa sala y realiza broadcast*/
	public synchronized void enviarMensajeASala(Mensaje mensaje) {
		
		Integer idSala = mensaje.getIDSala();
		boolean flag = true;
		for(Sala s:salas) {
			if(s.getSalaID().equals(idSala)) {
					s.broadcastSala(mensaje);
					flag=false;
					break;
			}									
		}
		
		if(!flag)
			System.out.println("No existe la sala");
			}
	
	public synchronized void logout(Cliente cliente) {
		aTodos_ClienteDesconectado(cliente);
	}

	public synchronized Integer crearSala(String nombreSala, boolean esPrivada) {
		Sala salaNueva =new Sala(nombreSala, esPrivada);
		salas.add(salaNueva);
		if (!esPrivada) 
			aTodos_SalaCreada();
		
		return salaNueva.getSalaID();
			
	}

	public synchronized void entrarASala(Cliente entrante, String nombreSala) {
		aSala_ClienteEntro(entrante, nombreSala);
	}

	public synchronized void cerrarSala(String nombreSala) {
		aTodos_SalaCerrada(nombreSala);
	}

	public synchronized void entrarAlLobby(Cliente entrante) {
		if (clientesEnLobby.contains(entrante)) {
			logger.enviarLog("FALLO: Cliente " + entrante.getNombre() + " intenta loguearse con usuario ya logueado.");
			return;
		}
		logger.enviarLog("Cliente " + entrante.getNombre() + " acaba de entrar al chat.");
		clientesEnLobby.add(entrante);
		entrante.iniciarEscucha();
		entrante.iniciarRespuesta();
		
		entrante.getEntrada().setCliente(entrante);
		entrante.getSalida().setCliente(entrante);
		// salaManager.entrarClienteAlLobby(entrante);
		aTodos_ClienteConectado(entrante);
		logger.enviarLog("Lista de clientes actualizada. Clientes Actuales: " + clientesEnLobby.size());
		
	}

	public synchronized void salirDelLobby(Cliente saliente) {

		if (!clientesEnLobby.contains(saliente))
			return; // INFORMAR DE USUARIO REPETIDO EN SALA?
		logger.enviarLog("Cliente " + saliente.getNombre() + " acaba de salir del chat.");
		clientesEnLobby.remove(saliente);
		aTodos_ClienteDesconectado(saliente);
	}

	private synchronized void entrarASalaPublica(Cliente entrante, String nombreSala) {
		aSala_ClienteEntro(entrante, nombreSala);
	}

	// ----------------- EVENTOS A TODOS----------------------
	private void aTodos_ClienteConectado(Cliente entrante) {

		Mensaje mensaje = new Mensaje(Comandos.ClienteNuevo, entrante.getNombre());

		for (Cliente c : clientesEnLobby) {
			
			if (!entrante.equals(c)) {
				entrante.enviarMensaje(new Mensaje(Comandos.ClienteNuevo, c.getNombre()));
			}//c.enviarMensaje(mensaje);
		}
		logger.enviarLog("Se envio a todos el nuevo usuario.");
	}

	private void aTodos_SalaCreada() {
		logger.enviarLog("IMPLEMENTAR Controlador.aTodos_SalaCreada");
	}

	private void aTodos_SalaCerrada(String nombreSala) {
		logger.enviarLog("IMPLEMENTAR Controlador.aTodos_SalaCerrada");
	}

	private void aTodos_ClienteDesconectado(Cliente saliente) {
		logger.enviarLog("IMPLEMENTAR Controlador.aTodos_ClienteDesconectado");
	}

	private synchronized void aTodos_enviarMensaje(Mensaje mensaje, Cliente emisor) {
		for (Cliente c : clientesEnLobby) {
			c.enviarMensaje(mensaje);
		}
	}

	// --------------EVENTOS A SALA------------------------------
	private void aSala_ClienteEntro(Cliente entrante, String sala) {
		logger.enviarLog("IMPLEMENTAR Controlador.aSala_ClienteEntro");
	}

}
