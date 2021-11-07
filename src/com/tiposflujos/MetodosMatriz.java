package com.tiposflujos;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class MetodosMatriz {

	public byte[][] obtenerMatrizIdentidad(int dimension) {
		if (dimension <= 0) {
			throw new IllegalArgumentException("Argumento no válido");
		}

		byte[][] matriz = new byte[dimension][];
		for (int i = 0; i < matriz.length; i++) {
			// Se crea un flujo de escritura para escribir los elementos correspondientes a
			// cada fila
			ByteArrayOutputStream fila = new ByteArrayOutputStream(dimension);
			for (int j = 0; j < dimension; j++) {
				fila.write(i == j ? 1 : 0);
			}
			try {
				fila.close();
			} catch (IOException ioE) {
				System.out.println(ioE.toString());
			}
			// El método toByteArray convierte el flujo en un array que se asigna al
			// correspondiente elemento de la matriz
			matriz[i] = fila.toByteArray();
		}

		return matriz;
	}

	public void mostrarMatrizConFlujos(byte[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			// SE crea un flujo de lectura para recorrer los elementos a lo largo de la
			// segunda dimensión de la matriz
			ByteArrayInputStream fila = new ByteArrayInputStream(matriz[i]);
			byte elemento;
			while ((elemento = (byte) fila.read()) != -1) {
				System.out.print(elemento);
			}
			System.out.println();
		}
	}

	public String[] clasificadorMayMin(String cadena) throws IOException {

		StringReader flujoLecturaCadena = new StringReader(cadena);
		StringWriter flujoEscrituraMin = new StringWriter();
		StringWriter flujoEscMayus = new StringWriter();

		int leido = 0;
		// Se añade cada tipo de letra a la cadena correspondiente mediante los flujos
		// de escritura
		while ((leido = flujoLecturaCadena.read()) != -1) {
			if (Character.isLowerCase((char)leido)) {
				flujoEscrituraMin.append((char) leido);
			} else if (Character.isUpperCase((char)leido)) {
				flujoEscMayus.append((char) leido);
			}

		}
		// Añade a la cadena cualquier caracter almacenado en el buffer del flujo
		flujoEscMayus.flush();
		flujoEscrituraMin.flush();

		String[] arrCad = new String[2];
		arrCad[0] = flujoEscrituraMin.toString();
		arrCad[1] = flujoEscMayus.toString();

		return arrCad;
	}

	public char[] conversorMinMay(char[] cadena) throws IOException {
		CharArrayReader lecturaCar = new CharArrayReader(cadena);
		CharArrayWriter escrituraCar = new CharArrayWriter();

		int c;

		while ((c = lecturaCar.read()) != -1) {
			if (Character.isLetter((char) c)) {
				c = Character.toUpperCase((char) c);

			}
			escrituraCar.write(c);
		}

		lecturaCar.close();
		escrituraCar.close();

		return escrituraCar.toCharArray();
	}

}
