package Domain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Dado {
	private BufferedImage imagenActual;
	private int posX;
	private int posY;
	private int dado1 = (int)(Math.random()*6)+1;
	public Dado(int posX, int posY) {
		this.posX=posX;
		this.posY=posY;
		try {
			if(dado1==1) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/1.png"));;
			}
			else if(dado1==2) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/2.png"));
			}else if(dado1==3) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/3.png"));
			}else if(dado1==4) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/4.png"));
			}
			else if(dado1==5) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/5.png"));
			}
			else if(dado1==6) {
				imagenActual=ImageIO.read(getClass().getResourceAsStream("/assets/6.png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // constructor
	
	public void dibujar(Graphics g) {
		g.drawImage(this.imagenActual, this.posX, this.posY, 
				this.imagenActual.getWidth(), this.imagenActual.getHeight(), null);
	}//dibujar
	
	public int salio() {
		return dado1;
	}//retorna el numero que salio del dado para comparar
	
}
