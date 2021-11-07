package com.tiposflujos;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class FlujosLecturaEscritura {
	
	public static void main(String[] args) {
		byte[] byteArr1 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		
		ByteArrayInputStream flujoArrByte1 = new ByteArrayInputStream(byteArr1);
		
		while(flujoArrByte1.available() != 0) {
			byte leido = (byte) flujoArrByte1.read();
			System.out.println(leido);
			flujoArrByte1.skip(1);
		}
		
		try {
			flujoArrByte1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
