package com.accesoalatorio;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class MetodosEscrituraLectura {

	/**
	 * 
	 * @param referencias
	 * @param precios
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public void obtenerArchivoPrecios(int[] referencias, double[] precios, String nombreArchivo) throws IOException {
		RandomAccessFile archivo = new RandomAccessFile(nombreArchivo, "rw");
		for (int i = 0; i < referencias.length && i < precios.length; i++) {
			// se escriben los valores almacenados en el array al archivo
			archivo.writeInt(referencias[i]);
			archivo.writeDouble(precios[i]);
		}
	}

	/**
	 * 
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public void actualizarArchivoPrecios(String nombreArchivo) throws IOException {
		int referencia;
		double precio;
		boolean finDeArchivo = false;
		RandomAccessFile archivo = null;

		archivo = new RandomAccessFile(nombreArchivo, "rw");
		do {
			try {
				referencia = archivo.readInt();
				precio = archivo.readDouble();
				if (precio >= 100D) {
					precio = precio * 1.5;
				} else {
					precio = precio * 0.5;
				}
				// Es necesario retroceder el puntero sobre el último precio leido. Para ello se
				// retroceden 8 posiciones,
				// correspondiente al número de bytes que ocupa un valor double
				archivo.seek(archivo.getFilePointer() - 8);
				archivo.writeDouble(precio);

			} catch (EOFException e) {
				finDeArchivo = true;
				archivo.close();
				e.printStackTrace();
			}

		} while (!finDeArchivo);
	}

	/**
	 * 
	 * @param nombreArchivo
	 * @throws IOException
	 */
	public void mostrarArchivoPrecios(String nombreArchivo) throws IOException {
		int referencia;
		double precio;
		boolean finArchivo = false;
		RandomAccessFile archivo = null;
		do {
			try {
				referencia = archivo.readInt();
				System.out.println("Referencia: " + referencia);
				precio = archivo.readDouble();
				System.out.println("Precio: " + precio);
			} catch (EOFException e) {
				finArchivo = true;
				archivo.close();
			}
		} while (!finArchivo);
	}
}
