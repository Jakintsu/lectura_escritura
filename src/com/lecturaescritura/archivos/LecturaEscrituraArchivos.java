package com.lecturaescritura.archivos;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class LecturaEscrituraArchivos {

	/**
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public int obtenerSumaNumerosArchivo(String nombreArchivo) throws IOException, FileNotFoundException {
		FileInputStream archivoNumeros = new FileInputStream(nombreArchivo);
		int numero, suma = 0;
		
		while((numero = archivoNumeros.read()) != -1) {
			suma += numero;
		}
		archivoNumeros.close();
		
		return suma;
	}
	/**
	 * 
	 * @param matriz
	 * @param nombreArchivo
	 * @throws FileNotFoundException
	 */
	public void matrizAArchivo(byte[][] matriz, String nombreArchivo) throws FileNotFoundException {
		
		FileOutputStream archivo = new FileOutputStream(nombreArchivo);
		for(int i = 0; i < matriz.length; i++) {
			ByteArrayInputStream fila = new ByteArrayInputStream(matriz[i]);
			byte elemento;
			while((elemento = (byte)fila.read()) != -1) {
				try {
					//Cada valor que se lee de la matriz se escribe en el archivo
					archivo.write(elemento);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		try {
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param nombreArchivo
	 * @param dim1
	 * @param dim2
	 * @return
	 * @throws FileNotFoundException
	 */
	public byte[][] obtenerMatrizDeArchivo(String nombreArchivo, int dim1, int dim2) throws FileNotFoundException{
		FileInputStream archivo = new FileInputStream(nombreArchivo);
		try {
			if(archivo.available() != dim1 * dim2) {
				throw new IllegalArgumentException("Argumentos no correctos");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[][] matriz = new byte[dim1][dim2];
		int elemento, contFila = 0, contColumna = 0;
		
		try {
			while((elemento = archivo.read()) != -1) {
				matriz[contFila][contColumna++] = (byte) elemento;
				if(contColumna == dim2) {
					contColumna = 0;
					contFila++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return matriz;
	}
	/**
	 * 
	 * @param nombreArchivo
	 * @param cadenas
	 * @throws IOException
	 */
	public void escribirCadenasEnArchivo(String nombreArchivo, String[] cadenas ) throws IOException {
		FileWriter escritura = new FileWriter(nombreArchivo);
		for(String cadena:cadenas) {
			for(int i = 0; i < cadena.length(); i++) {
				//Los caracteres de todas las cadenas se escriben en el archivo
				escritura.write(cadena.charAt(i));
			}
			
			escritura.write('*');
		}
		 escritura.close();
	}
	/**
	 * 
	 * @param nombreArchivo
	 * @return
	 * @throws IOException
	 */
	public String[] leerCadenasDeArchivo(String nombreArchivo) throws IOException {
		FileReader archivo = new FileReader(nombreArchivo);
		ArrayList<String> arregloCadenas = new ArrayList<String>();
		int lectura = 0;
		//Flujo de escritura para almacenar los caracteres de cada cadena
		CharArrayWriter cadenaCar = new CharArrayWriter();
		
		while( (lectura = archivo.read()) != -1) {
			char caracter = (char) lectura;
			//Cuando encuentra un asterisco el flujo de escritura se convierte a una cadena
			//de caracteres que se añade al objeto ArrayList
			if(caracter == '*') {
				cadenaCar.close();
				arregloCadenas.add(cadenaCar.toString());
				cadenaCar.reset();
			}else {
				cadenaCar.write(caracter);
			}
		}
		
		return (String[]) arregloCadenas.toArray(new String[arregloCadenas.size()]);
	}
	
}
