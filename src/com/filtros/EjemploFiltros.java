package com.filtros;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploFiltros {

	/**
	 * 
	 * @param matrizCar
	 * @return
	 * @throws IOException
	 */
	public String[] obtenerFilasMatrizCar(char[][] matrizCar) throws IOException {
		String[] arrCadenas = new String[matrizCar.length];
		for (int i = 0; i < matrizCar.length; i++) {
			BufferedReader flujoLectura = new BufferedReader(new CharArrayReader(matrizCar[i]));
			arrCadenas[i] = flujoLectura.readLine();
		}

		return arrCadenas;
	}

	/**
	 * 
	 * @param mes
	 * @param letraDiaPrimero
	 * @throws FileNotFoundException
	 */
	public void escribirMes(int mes, char letraDiaPrimero) throws FileNotFoundException {
		if (mes < 1 || mes > 12) {
			throw new IllegalArgumentException("Mes no válido");

		}

		String letraDia1 = "" + letraDiaPrimero;
		String letrasDiasSemana = "LMXJVSD";

		if (letrasDiasSemana.indexOf(letraDia1) != -1) {
			throw new IllegalArgumentException("Letra dia 1 no válida");
		}

		DataOutputStream archivoMes = new DataOutputStream(
				new BufferedOutputStream(new FileOutputStream("C:/misArchivos/mes" + mes + ".txt")));

		int numDiasMes = 0;

		switch (mes) {
		case 2:
			numDiasMes = 28;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			numDiasMes = 30;
			break;
		default:
			numDiasMes = 31;
		}

		for (int i = 0; i < numDiasMes; i++) {
			char letra = letrasDiasSemana.charAt((letrasDiasSemana.indexOf((letraDiaPrimero) + i)) % 7);
			try {
				archivoMes.writeInt(i + 1);
				archivoMes.writeChar((char) letra);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		try {
			archivoMes.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param mes
	 * @param dia
	 * @return
	 * @throws IOException
	 */
	public String consultarDiaMes(int mes, int dia) throws IOException {
		DataInputStream archivoMes = new DataInputStream(
				new BufferedInputStream(new FileInputStream("C:/misArchivos/mes" + mes + ".txt")));

		String[] meses = { "Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic" };
		String[] diasSemana = { "Lun", "Mar", "Mie", "Jue", "Vie", "Sab", "Dom" };

		int diaLeido = 0;
		char letraDia = 0;
		boolean encontrado = false;

		while (!encontrado) {
			// Los datos del archivo se leen con los métodos apropiados
			try {
				diaLeido = archivoMes.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
			letraDia = archivoMes.readChar();
			System.out.println("Dia leído: " + diaLeido);
			System.out.println("Letra: " + letraDia);

			if (diaLeido == dia) {
				encontrado = true;
			}

			if (encontrado) {
				if (letraDia == 'X') {
					return "El dia: " + dia + " del mes " + meses[mes - 1] + " es " + diasSemana[2];
				} else {
					// SE busca en el array de diasSemana el nombre del día cuya primera letra
					// coincida con la
					// correspondiente al resultado
					for (String cadena : diasSemana) {
						if (letraDia == cadena.charAt(0)) {
							return "El día " + dia + " del mes " + meses[mes - 1] + " es " + cadena;
						}
					}
				}
			}

		}
		return "El dia " + dia + " no corresponde al mes " + meses[mes - 1];
	}
}
