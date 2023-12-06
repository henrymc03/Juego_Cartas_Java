package Domain;

import java.awt.Graphics;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RecargaVida extends Carta{

	public RecargaVida(int valueAttack, int posX, int posY) {
		super(valueAttack, posX, posY);
		
		try {
			this.imagen = ImageIO.read(getClass().getResourceAsStream("/assets/recuperavida2.jpg"));
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
