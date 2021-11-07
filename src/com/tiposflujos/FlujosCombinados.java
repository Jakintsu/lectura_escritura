package com.tiposflujos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

public class FlujosCombinados {

	public static void main(String[] args) {

		byte[] byteArr1 = {0, 1, 2, 3, 4, 5};
		byte[] byteArr2 = {10, 20, 30, 40, 50};
		
		ByteArrayInputStream flujoArrByte1 = new ByteArrayInputStream(byteArr1);
		ByteArrayInputStream flujoArrByte2 = new ByteArrayInputStream(byteArr2);
		//Se combinan los flujos de entrada de los dos arrays en uno solo
		SequenceInputStream flujoSecundario = new SequenceInputStream(flujoArrByte1, flujoArrByte1);
		
		try {
			byte b = 0;
			while(((b = (byte)flujoSecundario.read()) != -1)) {
				System.out.println(b);
				
			}
			
			flujoArrByte1.close();
			flujoArrByte2.close();
			flujoSecundario.close();
		}catch(IOException ioE) {
			System.out.println(ioE.toString());;
		}
	}

}
