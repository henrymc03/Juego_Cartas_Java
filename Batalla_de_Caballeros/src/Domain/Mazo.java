package Domain;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import ADT.ArrayStack;
import ADT.EmptyStackException;
import ADT.FullStackException;

public class Mazo {
	
	private ArrayStack pilaMazo;
	private BufferedImage imagenActual;
	private int posX;
	private int posY;

	public Mazo(ArrayStack pilaMazo, int posX, int posY) {
		this.pilaMazo = pilaMazo;
		this.posX = posX;
		this.posY = posY;
		
		try {
			this.imagenActual = ImageIO.read(getClass().getResourceAsStream("/assets/mazo2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayStack getPilaMazo() {
		return pilaMazo;
	}

	public void setPilaMazo(ArrayStack pilaMazo) {
		this.pilaMazo = pilaMazo;
	}
	
	public Object retiraCartaMazo(int xMouse, int yMouse) {
		
		Object salida = null;
		
		if((xMouse>this.posX && xMouse<this.posX+this.imagenActual.getWidth())&&
			yMouse>this.posY && yMouse<this.posX+this.imagenActual.getHeight()) {
			try {
				salida =  this.pilaMazo.pop();
			} catch (EmptyStackException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return salida;
	}
	
	public void generarMazo(int posX, int posY) {
		
		Object[] arrayAux = new Object[30];
		//Crear cartas de ataque
		Carta espada1 = new Espada(10, posX, posY);
		arrayAux[0] = espada1;
		Carta espada2 = new Espada(10, posX, posY);
		arrayAux[1] = espada2;
		Carta arco1 = new Arco(8, posX, posY);
		arrayAux[2] = arco1;
		Carta arco2 = new Arco(8, posX, posY);
		arrayAux[3] = arco2;
		Carta hacha1 = new Hacha(15, posX, posY);
		arrayAux[4] = hacha1;
		Carta hacha2 = new Hacha(15, posX, posY);
		arrayAux[5] = hacha2;
		Carta daga1 = new Daga(5, posX, posY);
		arrayAux[6] = daga1;
		Carta daga2 = new Daga(5, posX, posY);
		arrayAux[7] = daga2;
		Carta lanza1 = new Lanza(12, posX, posY);
		arrayAux[8] = lanza1;
		Carta lanza2 = new Lanza(12, posX, posY);
		arrayAux[9] = lanza2;
		
		//Crear cartas de defensa
		Carta escMad1 = new EscudoMad(10, posX, posY);
		arrayAux[10] = escMad1;
		Carta escMad2 = new EscudoMad(10, posX, posY);
		arrayAux[11] = escMad2;
		Carta escMad3 = new EscudoMad(10, posX, posY);
		arrayAux[12] = escMad3;
		Carta escMet1 = new EscudoMet(15, posX, posY);
		arrayAux[13] = escMet1;
		Carta escMet2 = new EscudoMet(15, posX, posY);
		arrayAux[14] = escMet2;
		Carta escMet3 = new EscudoMet(15, posX, posY);
		arrayAux[15] = escMet3;
		Carta escMim1 = new EscudoMim(8, posX, posY);
		arrayAux[16] = escMim1;
		Carta escMim2 = new EscudoMim(8, posX, posY);
		arrayAux[17] = escMim2;
		Carta armadura1 = new Armadura(5, posX, posY);
		arrayAux[18] = armadura1;
		Carta armadura2 = new Armadura(5, posX, posY);
		arrayAux[19] = armadura2;
		
		//Cartas especiales
		Carta recEnrgia1 = new RecargaEnergia(25, posX, posY);
		arrayAux[20] = recEnrgia1;
		Carta recEnrgia2 = new RecargaEnergia(25, posX, posY);
		arrayAux[21] = recEnrgia2;
		Carta recEnrgia3 = new RecargaEnergia(25, posX, posY);
		arrayAux[22] = recEnrgia3;
		Carta recEnrgia4 = new RecargaEnergia(25, posX, posY);
		arrayAux[23] = recEnrgia4;
		Carta recEnrgia5 = new RecargaEnergia(25, posX, posY);
		arrayAux[24] = recEnrgia5;
		Carta recVida1 = new RecargaVida(10, posX, posY);
		arrayAux[25] = recVida1;
		Carta recVida2 = new RecargaVida(10, posX, posY);
		arrayAux[26] = recVida2;
		Carta cartaModo1 = new CartaModo(0, posX, posY);
		arrayAux[27] = cartaModo1;
		Carta cartaModo2 = new CartaModo(0, posX, posY);
		arrayAux[28] = cartaModo2;
		Carta cartaModo3 = new CartaModo(0, posX, posY);
		arrayAux[29] = cartaModo3;
		
		//Lista de los numeros a salir
		List<Integer> numbers = new ArrayList<>(30);
		for (int i=0;i<30;i++){
		   numbers.add(i);
		}
		
		Random random = new Random();

		//Metodo que escoge un aleatorio sin repetirlo
		while (numbers.size()>0){
			
		   int randomIndex = random.nextInt(numbers.size());

		    try {
				
			   this.pilaMazo.push(arrayAux[numbers.get(randomIndex)]);
			   
			} catch (FullStackException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		   
		   numbers.remove(randomIndex);
		}
	}
	
	public void dibujar(Graphics g) {
		
		g.drawImage(this.imagenActual, this.posX, this.posY, null);
	}
	
}
