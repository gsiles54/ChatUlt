package com.vista;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import com.Cliente.Cliente;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;
import com.utilitarios.EntradaSalida;
import com.utilitarios.HiloOutput;
import javax.swing.JTextPane;

public class Lobby_GUI extends JFrame {

	private static final long serialVersionUID = 7194023903108242857L;
	private JPanel contentPane;
	private ControladorCliente controladorCliente;
	private Thread hiloControladorCliente;
	private Thread hiloOutput;
	private JList<String> listaClientesConectados;
	JTree tree;//Describe en teoria Subchats y clientes dentro de esos chats.
	private Cliente cliente;
	EntradaSalida entradaSalida;
	private JTextPane chatLobby=null;
	private JTextField chatTextBoxLobby;
	private boolean chatBox=false;
	private HiloOutput output;
	
	public Lobby_GUI(EntradaSalida _entradaSalida, String _userName) {

		
		configurarGUI();
		cliente = new Cliente(_userName);
		entradaSalida=_entradaSalida;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// HACER QUE CONFIRME SI DESEA SALIR O NO, en vez de cerrar de una.
				int confirma=JOptionPane.showConfirmDialog(null,
				        "Realmente desea Salir?", "Realmente desea Salir?", JOptionPane.YES_NO_OPTION);
				if(confirma==0) {
				entradaSalida.escribirMensaje(new Mensaje(Comandos.LOGOUT, _userName));
				System.out.println("SALIIIIDA");
				dispose();
				}
				
			}
		});
		
		
		System.out.println("En lovbby gui nombre es "+_userName);
		controladorCliente= new ControladorCliente(_entradaSalida, this,_userName);
		hiloControladorCliente = new Thread(controladorCliente);
		hiloControladorCliente.start();
		output= new HiloOutput(cliente,this,_entradaSalida);
		hiloOutput = new Thread(output);
		hiloOutput.start();
	}

	void ponerClienteNuevoEnLista(String entrante) {
		
	}
	void sacarClienteDeLista(String saliente) {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	//------CONFIGURACIONES GUI---------------------
	private void configurarGUI() {
		setTitle("Broccoli Chat Lobby");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuariosConectados = new JLabel("Usuarios Conectados (Para Enviar Chats Privados)");
		lblUsuariosConectados.setBounds(10, 61, 278, 14);
		contentPane.add(lblUsuariosConectados);
		
		JLabel lblJlist = new JLabel("JList");
		lblJlist.setBounds(10, 482, 46, 14);
		contentPane.add(lblJlist);
		
		JLabel lblJtree = new JLabel("JTree");
		lblJtree.setBounds(388, 482, 75, 14);
		contentPane.add(lblJtree);
		
		JLabel lblParaVerEn = new JLabel("Para ver en que sala estan cada usuario");
		lblParaVerEn.setBounds(388, 61, 226, 14);
		contentPane.add(lblParaVerEn);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 294, 366);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(new Rectangle(0, 0, 500, 500));
		scrollPane_1.setBounds(0, 0, 294, 366);
		panel.add(scrollPane_1);
		
		setListaClientesConectados(new JList<String>());
		scrollPane_1.setViewportView(getListaClientesConectados());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(388, 105, 294, 359);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 294, 359);
		panel_1.add(scrollPane);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 507, 469, 172);
		contentPane.add(scrollPane_2);
		
		chatLobby = new JTextPane();
		
		scrollPane_2.setColumnHeaderView(chatLobby);
		
		chatTextBoxLobby = new JTextField();
		chatTextBoxLobby.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setChatBox(true);
			}
		});

		chatTextBoxLobby.setBounds(10, 680, 469, 20);
		contentPane.add(chatTextBoxLobby);
		chatTextBoxLobby.setColumns(10);
	}

	public synchronized JTextPane getChatLobby() {
		return chatLobby;
	}


	public synchronized JTextField getChatTextBoxLobby() {
		return chatTextBoxLobby;
	}



	public synchronized boolean isChatBox() {
		return chatBox;
	}

	public void setChatBox(boolean set) {
		this.chatBox=set;
	}

	public JList<String> getListaClientesConectados() {
		return listaClientesConectados;
	}

	public void setListaClientesConectados(JList<String> listaClientesConectados) {
		this.listaClientesConectados = listaClientesConectados;
	}
}
