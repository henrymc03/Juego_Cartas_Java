package Domain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import ADT.EmptyStackException;
import ADT.NodeList;
import ADT.SimpleList;

public class Mano {

	private SimpleList listaMano;
	private int posX;
	private int posY;
	
	public Mano(SimpleList listaMano, int posX, int posY) {
		
		this.listaMano = listaMano;
		this.posX = posX;
		this.posY = posY;
	}

	public SimpleList getListaMano() {
		return listaMano;
	}

	public void setListaMano(SimpleList listaMano) {
		this.listaMano = listaMano;
	}
	
	public Object retiraCartaMano(int xMouse, int yMouse) {
		
		Object salida = null;
			
		if (!this.listaMano.isEmpty()) {
        	
            NodeList carta = this.listaMano.getHead();
            
            while(carta != null){
            	Carta aux = (Carta)carta.getElement();
            	
            	if((xMouse>aux.getPosX() && xMouse<aux.getPosX()+aux.getImagen().getWidth())&&
            		yMouse>aux.getPosY() && yMouse<aux.getPosY()+aux.getImagen().getHeight()) {
            		
            		salida = this.listaMano.remove(carta);
            	}
            	
                carta = carta.getNext();
            }
        }
		
		return salida;
	}
	
	public void dibujar(Graphics g) {
		
		int posImagen = this.posX;
		
		if (!this.listaMano.isEmpty()) {
        	
            NodeList carta = this.listaMano.getHead();
            
            while(carta != null){
            	
            	Carta aux = (Carta)carta.getElement();
            	g.drawImage(aux.getImagen(), posImagen, this.posY, null);
            	
            	aux.setPosX(posImagen);
            	aux.setPosY(this.posY);
                
                carta = carta.getNext();
                posImagen = posImagen + 110;
            }
        }//else
        	//System.out.println("Lista vacia");	
	}
}
