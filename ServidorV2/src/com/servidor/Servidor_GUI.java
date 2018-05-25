package com.servidor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.logs.LoggerServidor;
import com.logs.LoggerCliente;

public class Servidor_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonIniciarServidor;
	private static JTextArea jTextAreaLogs;
	private Thread hilo_ServerLogger;

	static Servidor servidor; // Porque es estatico eso?.

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				Servidor_GUI frame = new Servidor_GUI();
				frame.setVisible(true);
			}
		});
	}

	public Servidor_GUI() {
		crearUI();

		// -------------BOTON INICIAR SERVIDOR-------
		botonIniciarServidor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					iniciarServidorChat();
					iniciarServidorDeLogs();
					
					LoggerCliente.enviarLog("Servidor Iniciado.");
					LoggerCliente.enviarLog("Esperando por nuevos Clientes.");
					
					botonIniciarServidor.setEnabled(false);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			private void iniciarServidorChat() throws IOException {
				servidor = new Servidor(1234, jTextAreaLogs); // Separado en 3 lineas para mejor entendimiento
				Thread tServidor = new Thread(servidor);
				tServidor.start();
			}
		});
		// ---------FIN BOTON INICIARSERVIDOR-------------
	}

	private void iniciarServidorDeLogs() {
		hilo_ServerLogger = new Thread(new LoggerServidor(jTextAreaLogs));
		hilo_ServerLogger.start();
	}

	private void crearUI() {
		setTitle("Server Status Broccoli Chat UNLAM");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 525);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnMenu.add(mntmSalir);

		JMenu mnConfiguracion = new JMenu("Configuracion");
		menuBar.add(mnConfiguracion);

		JMenuItem mntmCambiarIp = new JMenuItem("Cambiar IP & Puerto");
		mnConfiguracion.add(mntmCambiarIp);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		botonIniciarServidor = new JButton("Iniciar Servidor");

		botonIniciarServidor.setForeground(Color.BLUE);
		botonIniciarServidor.setBackground(SystemColor.activeCaption);
		botonIniciarServidor.setFont(new Font("Tahoma", Font.BOLD, 16));
		botonIniciarServidor.setBounds(103, 163, 277, 90);
		contentPane.add(botonIniciarServidor);

		jTextAreaLogs = new JTextArea();
		jTextAreaLogs.setBackground(Color.PINK);
		jTextAreaLogs.setEditable(false);
		jTextAreaLogs.setBounds(10, 299, 453, 163);
		contentPane.add(jTextAreaLogs);

		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogs.setBounds(10, 248, 51, 40);
		contentPane.add(lblLogs);

		JLabel lblEstadoDelServidor = new JLabel("Estado del Servidor:");
		lblEstadoDelServidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoDelServidor.setForeground(Color.BLUE);
		lblEstadoDelServidor.setBounds(150, 62, 177, 14);
		contentPane.add(lblEstadoDelServidor);

		JLabel lblClientesConectados = new JLabel("Clientes Conectados");
		lblClientesConectados.setForeground(Color.BLUE);
		lblClientesConectados.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblClientesConectados.setBounds(150, 109, 177, 14);
		contentPane.add(lblClientesConectados);

		JLabel lblSalasCreadas = new JLabel("Salas Creadas");
		lblSalasCreadas.setForeground(Color.BLUE);
		lblSalasCreadas.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSalasCreadas.setBounds(150, 134, 177, 14);
		contentPane.add(lblSalasCreadas);

		JLabel lblEstadoBroccoliBot = new JLabel("Estado Broccoli Bot");
		lblEstadoBroccoliBot.setForeground(Color.BLUE);
		lblEstadoBroccoliBot.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstadoBroccoliBot.setBounds(150, 84, 177, 14);
		contentPane.add(lblEstadoBroccoliBot);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
	}
}
