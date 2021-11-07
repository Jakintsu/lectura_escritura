package com.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MetodosArchivos {
	/**
	 * 
	 * @param rutaOrigen
	 * @param rutaDestino
	 * @param reemplazar
	 * @throws DestinoProtegidoException
	 * @throws IOException
	 */
	public void copiarArchivo(String rutaOrigen, String rutaDestino, boolean reemplazar ) throws DestinoProtegidoException, IOException {
		if(rutaOrigen == null || rutaDestino == null) {
			throw new IllegalArgumentException("Argumentos no válidos");
		}
		
		File origen = new File(rutaOrigen);
		File copia = new File(rutaDestino);
		FileInputStream entrada;
		FileOutputStream salida;
		
		if(copia.isFile() && !reemplazar) {
			throw new DestinoProtegidoException("Error al copiar: el archivo ya existe y no se desea reemplazar");
		}
		if(copia.isDirectory()) {
			//Si el archivo de copia es un directorio, el archivo copia tendrá el mismo nombre que el original
			copia = new File(copia.getAbsolutePath()+"/"+origen.getName());
			//Se comprueba que el archivo copia exista tanto si la ruta de copia es un archivo como un directorio
			if(copia.isFile() && !reemplazar) {
				throw new DestinoProtegidoException("Error al copiar: el archivo ya existe y no se desea reemplazar");
			}
		}
		
		entrada = new FileInputStream(origen);
		salida = new FileOutputStream(copia);
		
		int leido = 0;
		while((leido = entrada.read()) !=-1) {
			salida.write(leido);
		}
		
		salida.close();
		entrada.close();
	}
	/**
	 * 
	 * @param rutaOrigen
	 * @param rutaDestino
	 * @param reemplazar
	 */
	public void moverArchivo(String rutaOrigen, String rutaDestino, boolean reemplazar ) {
		if(rutaOrigen == null || rutaDestino == null) {
			throw new IllegalArgumentException("Argumentos no válidos");
		}
		
		File origen = new File(rutaOrigen);
		File destino = new File(rutaDestino);
		
		try {
			//Si la copia tiene éxito, el archivo origen se elimina
			copiarArchivo(origen.getAbsolutePath(), destino.getAbsolutePath(), reemplazar);
			origen.delete();
		} catch (DestinoProtegidoException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param rutaOrigen
	 * @param rutaDestino
	 * @throws Exception
	 */
	public void copiarDirectorio(String rutaOrigen, String rutaDestino) throws Exception {
		if(rutaOrigen == null || rutaDestino == null) {
			throw new IllegalArgumentException("Argumentos no válidos");
		}
		
		File origen = new File(rutaOrigen);
		File destino = new File(rutaDestino);
		if(!origen.isDirectory()) {
			throw new Exception("No se pudo realizar la copia. El directorio de origen no existe.");
		}
		if(destino.isDirectory()) {
			throw new Exception("No se pudo realizar la copia. El directorio de destino ya existe.");

		}
		
		String[] listaOrigen = origen.list();
		destino.mkdirs();
		
		for(String cadena: listaOrigen) {
			File f = new File(origen.getAbsolutePath()+"/"+cadena);
			if(f.isFile()) {
				copiarArchivo(f.getAbsolutePath(), destino.getAbsolutePath(), true);
			}else {
				File des = new File(destino.getAbsolutePath()+"/"+f.getName());
				//Llamada recursiva para la copia de subdirectorios
				copiarDirectorio(f.getAbsolutePath(), des.getAbsolutePath());
			}
		}
	}
}
