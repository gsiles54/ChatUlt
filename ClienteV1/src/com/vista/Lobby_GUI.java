package com.vista;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;

import com.utilitarios.EntradaSalida;

public class Lobby_GUI extends JFrame {

	private static final long serialVersionUID = 7194023903108242857L;
	private JPanel contentPane;
	private ControladorCliente controladorCliente;
	private Thread hiloControladorCliente;
	JList<String> listaClientesConectados;
	JTree tree;//Describe en teoria Subchats y clientes dentro de esos chats.
	private String userName;
	EntradaSalida entradaSalida;

	public Lobby_GUI(EntradaSalida _entradaSalida, String _userName) {
		
		configurarGUI();
		userName=_userName;
		controladorCliente= new ControladorCliente(_entradaSalida, this,_userName);
		hiloControladorCliente = new Thread(controladorCliente);
		hiloControladorCliente.start();
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
		lblJlist.setBounds(10, 601, 46, 14);
		contentPane.add(lblJlist);
		
		JLabel lblJtree = new JLabel("JTree");
		lblJtree.setBounds(388, 601, 75, 14);
		contentPane.add(lblJtree);
		
		JLabel lblParaVerEn = new JLabel("Para ver en que sala estan cada usuario");
		lblParaVerEn.setBounds(388, 61, 226, 14);
		contentPane.add(lblParaVerEn);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 105, 294, 466);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 294, 466);
		panel.add(scrollPane_1);
		
		listaClientesConectados = new JList<String>();
		scrollPane_1.setViewportView(listaClientesConectados);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(388, 105, 294, 459);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 294, 459);
		panel_1.add(scrollPane);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
	}
}
