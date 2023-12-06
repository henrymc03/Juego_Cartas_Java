package Domain;

import java.awt.Graphics;

import ADT.ArrayQueue;
import ADT.EmptyQueueException;
import ADT.NodeList;

public class ZonaFormAtaque {

	private ArrayQueue colaFormAtaque;
	private int posX;
	private int posY;

	public ZonaFormAtaque(ArrayQueue colaFormAtaque, int posX, int posY) {
		this.colaFormAtaque = colaFormAtaque;
		this.posX = posX;
		this.posY = posY;
	}

	public ArrayQueue getColaFormAtaque() {
		return colaFormAtaque;
	}

	public void setColaFormAtaque(ArrayQueue colaFormAtaque) {
		this.colaFormAtaque = colaFormAtaque;
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
	
	public void dibujar(Graphics g) {
		int posImagen = this.posX;
		
		if(!this.colaFormAtaque.isEmpty()) {
				
			Object[] arregloAux = this.colaFormAtaque.getArrayQueue();
			
			for(int i = this.colaFormAtaque.getF(); i != this.colaFormAtaque.getR();
					i = (i+1)%this.colaFormAtaque.getArrayQueue().length){
				
				Carta cartaNueva = (Carta) arregloAux[i];
				g.drawImage(cartaNueva.getImagen(), posImagen, this.posY, null);
				posImagen = posImagen + 110;
			}
		}
	}
}
