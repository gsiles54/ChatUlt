package com.vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class Conversacion extends JFrame {

	private static final long serialVersionUID = 6553990511100608674L;
	private JPanel contentPane;
	 JTextField textField;
	 JList<String> list;
	 JTextPane txtpnafsf;
	 JButton btnNewButton;
	
	
	public Conversacion() {
		configurarGUI();
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void configurarGUI() {
		setTitle("Conversacion %Privada/Publica% con %destinatario%");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 373, 370, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("Enviar");
		btnNewButton.setBounds(390, 373, 82, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.setBounds(482, 373, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblClientesEnEste = new JLabel("Clientes en este Chat");
		lblClientesEnEste.setBounds(410, 46, 161, 14);
		contentPane.add(lblClientesEnEste);
		
		JLabel lblTipoDeChat = new JLabel("Tipo de Chat publico/Privado");
		lblTipoDeChat.setBounds(10, 46, 256, 14);
		contentPane.add(lblTipoDeChat);
		
		JPanel panel = new JPanel();
		panel.setBounds(390, 73, 181, 291);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 181, 291);
		panel.add(scrollPane_1);
		
		list = new JList<String>();
		scrollPane_1.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 73, 370, 285);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 370, 285);
		panel_1.add(scrollPane);
		
		txtpnafsf = new JTextPane();
		scrollPane.setViewportView(txtpnafsf);
	}
}
