package com.lecturaescrituraobjetos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

public class PruebaEscrituraLecturaObjetos {
	
	/**
	 * 
	 * @param puntos
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public static void almacenarPuntosEnArchivo(Collection<Punto> puntos, String nombreArchivo) throws IOException {
		PrintWriter archivoPuntos = new PrintWriter(new BufferedWriter(
				new FileWriter(nombreArchivo)));
		//Obtenemos un iterdaor para recorrer la colección
		Iterator<Punto> puntosIt = puntos.iterator();
		while(puntosIt.hasNext()) {
			Punto p = puntosIt.next();
			//Se escribe en el archivo la información de cada punto
			archivoPuntos.println(p.getCoordX());
			archivoPuntos.println(p.getCoordY());
			archivoPuntos.println(p.getColor());
			
		}
		
		archivoPuntos.close();
	}
	
	/**
	 * 
	 * @param nombreArchivo
	 * @return ArrayList<Punto>
	 * @throws FileNotFoundException
	 */
	public static ArrayList<Punto> obtenerArrayListDeArchivo(String nombreArchivo) throws FileNotFoundException{
		//Se crea un objeto Scanner para leer la infromación del archivo
		Scanner archivoPuntos = new Scanner(new File(nombreArchivo));
		ArrayList<Punto> listaPuntos = new ArrayList<Punto>();
		while(archivoPuntos.hasNextInt()) {
			Punto p = new Punto(archivoPuntos.nextInt(), 
					archivoPuntos.nextInt(),
					archivoPuntos.next());
			listaPuntos.add(p);
		}
		
		archivoPuntos.close();
		return listaPuntos;
	}

	/**
	 * 
	 * @param puntos
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public void almacenarPuntosComoObjetos(Collection<Punto> puntos, String nombreArchivo) throws IOException {
		ObjectOutputStream archivoPuntos = new ObjectOutputStream(new FileOutputStream(nombreArchivo));
		Iterator<Punto> puntosIt = puntos.iterator();
		while(puntosIt.hasNext()) {
			Punto p = puntosIt.next();
			//Los objetos se escriben directamente en el archivo con el método writeObject()
			archivoPuntos.writeObject(p);
		}
		
		archivoPuntos.close();
	}
	
	public ArrayList<Punto> leerArrayListDeArchivo(String nombreArchivo) throws IOException, IOException, ClassNotFoundException{
		ObjectInputStream archivo = new ObjectInputStream(new FileInputStream(nombreArchivo));
		//Lectura del objeto ArrayList con el método readObject()
		ArrayList<Punto> listaPuntos = (ArrayList<Punto>) archivo.readObject();
		archivo.close();
		return listaPuntos;
	}
}
