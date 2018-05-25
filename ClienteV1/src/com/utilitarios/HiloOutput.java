package com.utilitarios;

import java.awt.ComponentOrientation;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.Cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.vista.Lobby_GUI;

public class HiloOutput implements Runnable{
	
	Cliente cliente;
	Lobby_GUI lobbyGui;
	StringBuilder texto;
	EntradaSalida entradaSalida;
	
	public HiloOutput(Cliente cliente, Lobby_GUI lobby_Gui, EntradaSalida entradaSalida){
		this.cliente=cliente;
		this.lobbyGui=lobby_Gui;
		this.entradaSalida=entradaSalida;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean flag = true;
		StyledDocument sd ;
		SimpleAttributeSet attribute = new SimpleAttributeSet();
		StyleConstants.setAlignment(attribute, StyleConstants.ALIGN_RIGHT);
		
		while(flag) {
			if(lobbyGui.isChatBox()) { // tengo en cuenta todos los GUI? o hago hilos separados?
				lobbyGui.setChatBox(false);
				texto = new StringBuilder();
				texto.append('\n');
				texto.append(cliente.getNombre() + " : " );
				texto.append(lobbyGui.getChatTextBoxLobby().getText());
				sd=lobbyGui.getChatLobby().getStyledDocument();
				try {
					sd.insertString(sd.getLength(), texto.toString(), null);
					sd.setParagraphAttributes(sd.getLength()+1, 1, attribute, false);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//lobbyGui.getChatLobby().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				entradaSalida.escribirMensaje(new Mensaje(Comandos.MensajeASala,texto.toString()));
				lobbyGui.getChatTextBoxLobby().setText("");
			}
		}
	}

}
