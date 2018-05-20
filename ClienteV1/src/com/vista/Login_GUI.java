package com.vista;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.utilitarios.LoginHandler;

public class Login_GUI extends JFrame {

	private static final long serialVersionUID = 5818745717722164373L;
	private JPanel contentPane;
	private JTextField usuario_textField;
	private JPasswordField password_textField;
	private JButton btnNewButton;
	private Checkbox checkbox_2;
	private Checkbox checkbox_1;
	private Checkbox checkbox;
	private JLabel lblEstado;
	private Socket socket;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_GUI frame = new Login_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Login_GUI()  {
		Login_GUI thisGui=this;
		crearRecursosGUI();
		actualizarLabelEstadoConexion();//ESTO ESTA MAL, SI SE PIERDE LA CONEXION, SE PINCHA TODO, por el socket
		
		//Meter esto en un thread y fijarse que si el server esta offline, el cliente no se puede loguear mas.
		//poner esto en un while infinito y agregar un boton para reconectar en el caso que se pierda la conexion.
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TO-DO VALIDACIONES BASICAS.
				String nombre=usuario_textField.getText().trim();
				String pass=String.valueOf(password_textField.getPassword());
				
				LoginHandler loginHandler= new LoginHandler(socket,nombre,pass, thisGui);
				Thread tLoginHandler= new Thread(loginHandler);
				tLoginHandler.start();
			}
		});
		
		
	}

	
	private void actualizarLabelEstadoConexion() {
		try {
			socket = new Socket("localhost",1234); //PARAMETRIZAR ESTO			
			if(socket.isConnected()) {
				lblEstado.setText("");
				lblEstado.setText("Estado: Servidor Online");
			}
		} catch (IOException e) {
			lblEstado.setText("");
			lblEstado.setText("Estado: Servidor Offline");
		}
	}
	
	private void crearRecursosGUI() {
		setResizable(false);
		setTitle("Broccoli Chat UNLAM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 598);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmConfiguracionServidor = new JMenuItem("Configuracion Servidor");
		mnMenu.add(mntmConfiguracionServidor);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnMenu.add(mntmSalir);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(43, 377, 202, 34);
		contentPane.add(btnNewButton);
		
		usuario_textField = new JTextField();
		usuario_textField.setText("");
		usuario_textField.setBounds(58, 179, 131, 20);
		contentPane.add(usuario_textField);
		usuario_textField.setColumns(10);
		
		password_textField = new JPasswordField();
		password_textField.setBounds(58, 234, 131, 20);
		contentPane.add(password_textField);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(58, 154, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Contraseña:");
		lblPassword.setBounds(58, 210, 142, 14);
		contentPane.add(lblPassword);
		
		checkbox = new Checkbox("Recordar Usuario");
		checkbox.setBounds(58, 273, 160, 22);
		contentPane.add(checkbox);
		
		checkbox_1 = new Checkbox("Recordar Contraseña");
		checkbox_1.setBounds(58, 301, 160, 22);
		contentPane.add(checkbox_1);
		
		checkbox_2 = new Checkbox("Iniciar Sesion Automaticamente");
		checkbox_2.setBounds(58, 329, 202, 22);
		contentPane.add(checkbox_2);
		
		JLabel lblObtenerUnaCuenta = new JLabel("Obtener Una Cuenta Nueva");
		lblObtenerUnaCuenta.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblObtenerUnaCuenta.setForeground(Color.BLUE);
		lblObtenerUnaCuenta.setBounds(151, 483, 160, 14);
		contentPane.add(lblObtenerUnaCuenta);
		
		JLabel lblEstadoDelServicio = new JLabel("Estado Del Servicio");
		lblEstadoDelServicio.setForeground(Color.BLUE);
		lblEstadoDelServicio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoDelServicio.setBounds(10, 483, 131, 14);
		contentPane.add(lblEstadoDelServicio);
		
		lblEstado = new JLabel("Estado:");
		lblEstado.setBounds(43, 438, 202, 14);
		contentPane.add(lblEstado);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	}
}
