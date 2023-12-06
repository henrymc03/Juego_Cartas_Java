package Domain;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class EscudoMad extends Carta{
	
	public EscudoMad(int valueDefense, int posX, int posY) {
		super(valueDefense, posX, posY);
		
		try {
			this.imagen = ImageIO.read(getClass().getResourceAsStream("/assets/escudomadera2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void dibujar(Graphics g) {
		
		g.drawImage(this.imagen, this.posX, this.posY, null);
	}

}
