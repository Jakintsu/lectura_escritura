package com.lecturaescrituraobjetos;

import java.io.Serializable;

public class Punto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	private int coordX;
	private int coordY;
	private double distOrigen;
	private String color;
	
	public Punto(int coordX, int coordY, String color) {
		super();
		this.coordX = coordX;
		this.coordY = coordY;
		this.distOrigen = Math.sqrt(coordX*coordX + coordY*coordY);
		this.color = color;
	}

	public int getCoordX() {
		return coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public double getDistOrigen() {
		return distOrigen;
	}

	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "Punto [coordX=" + coordX + ", coordY=" + coordY + ", distOrigen=" + distOrigen + ", color=" + color
				+ "]";
	}
	
	
	
	
}
