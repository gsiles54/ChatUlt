package com.DB;

import java.util.ArrayList;
/*
 * Se encarga de solicitar datos a la base de datos. No valida ni hace nada mas por lo que 
 * hay que sacar validarUsuario de esta clase y meterlo en otro lado mas conveniente.
 * .
 */
public class DAO_BaseDeDatos {
		private static DAO_BaseDeDatos instance=null;
		private ArrayList<Usuario> listaUsuarios;
		
		protected DAO_BaseDeDatos() {
			listaUsuarios=new ArrayList<Usuario>();
			listaUsuarios.add(new Usuario("aa","aa"));
			listaUsuarios.add(new Usuario("ss","ss"));
			listaUsuarios.add(new Usuario("dd","dd"));
		}
		
		public static DAO_BaseDeDatos getInstance() {
			if(instance==null) {
				instance=new DAO_BaseDeDatos();
			}
			return instance;
		}
		// SACAR ESTO DE ACA, Rompe Principio de responsabilidad Unica.
		public boolean validarUsuario(String nombre, String pass) {
			return listaUsuarios.contains(new Usuario(nombre,pass));
		}
		
}
