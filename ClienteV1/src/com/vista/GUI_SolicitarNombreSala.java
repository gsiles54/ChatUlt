package com.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.Cliente.EntradaSalida;
import com.mensajes.Comandos;
import com.mensajes.Mensaje;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class GUI_SolicitarNombreSala extends JFrame {

	private JPanel contentPane;
	private JTextField tfNombreSala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_SolicitarNombreSala frame = new GUI_SolicitarNombreSala();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_SolicitarNombreSala() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 294, 209);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfNombreSala = new JTextField();
		tfNombreSala.setFont(new Font("Source Sans Pro Semibold", Font.BOLD, 16));
		tfNombreSala.setBounds(44, 53, 210, 30);
		contentPane.add(tfNombreSala);
		tfNombreSala.setColumns(10);
		
		JLabel lblIngreseElNombre = new JLabel("Ingrese el nombre de la sala que desa crear.");
		lblIngreseElNombre.setBounds(42, 27, 259, 14);
		contentPane.add(lblIngreseElNombre);
		
		
		JCheckBox chckbxHacerlaPrivada = new JCheckBox("Hacerla Privada");
		chckbxHacerlaPrivada.setBounds(44, 90, 130, 23);
		contentPane.add(chckbxHacerlaPrivada);
		
		JLabel labelWarning = new JLabel("");
		labelWarning.setForeground(Color.RED);
		labelWarning.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		labelWarning.setBounds(44, 154, 210, 14);
		contentPane.add(labelWarning);
		
		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(165, 120, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Crear");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreSala=tfNombreSala.getText();
				boolean esPrivada=chckbxHacerlaPrivada.isSelected();
				EntradaSalida entradaSalida = EntradaSalida.getInstance();
				ControladorCliente cl = ControladorCliente.getInstance();
				if(nombreSala==null || nombreSala.isEmpty()) {
					labelWarning.setText("");
					labelWarning.setText("Ingrese un nombre a la sala.");
				}else {
					Mensaje mensajeCrearSala;
					if(esPrivada) {
						 mensajeCrearSala= new Mensaje(Comandos.CrearSalaPrivada,nombreSala,cl.cliente);
					
					}else {
					 mensajeCrearSala= new Mensaje(Comandos.CrearSalaPublica,nombreSala,cl.cliente);
						
					}
					entradaSalida.escribirMensaje(mensajeCrearSala);
				}
				dispose();
			}
		});
		btnNewButton_1.setBounds(44, 120, 89, 23);
		contentPane.add(btnNewButton_1);

	}
}
