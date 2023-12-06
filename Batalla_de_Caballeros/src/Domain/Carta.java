package Domain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Carta {

	protected int value;
	protected BufferedImage imagen;
	protected int posX;
	protected int posY;
	
	public Carta(int value, int posX, int posY) {
		this.value = value;
		this.posX = posX;
		this.posY = posY;
	}
	
	
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}



	public BufferedImage getImagen() {
		return imagen;
	}



	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}



	public int getPosX() {
		return posX;
	}



	public void setPosX(int posX) {
		this.posX = posX;
	}



	public int getPosY() {
		return posY;
	}



	public void setPosY(int posY) {
		this.posY = posY;
	}



	public abstract void dibujar(Graphics g);
	
	
}
