package com.cadena;

public class Test {
	
	public static Manejador construirChain() {
		Manejador m1 = new CrearSala();
		Manejador m2 = new ManejadorB();
		
		m1.enlazarSiguiente(m2);
		return m1;
	}

	public static void main(String[] args) {
		Manejador m1= construirChain();
		//m1.manejarPeticion(new MensajeP("Negativo ",-1));
		//m1.manejarPeticion(new MensajeP("Positivo ",0));
		//m1.manejarPeticion(new MensajeP("Positivo ",5));

	}

}
