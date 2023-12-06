package Domain;

import java.awt.Graphics;

import ADT.ArrayQueue;

public class Enfrentamiento {
	
	private ArrayQueue estrategiaJug1;
	private ArrayQueue estrategiaJug2;
	private int lifeValueJug1;
	private int lifeValueJug2;
	private int attackValueJug1;
	private int attackValueJug2;
	private int posX;
	private int posY;
	private int totalAtqJug1;
	private int totalAtqJug2;

	public Enfrentamiento(ArrayQueue estrategiaJug1, ArrayQueue estrategiaJug2, int posX, int posY) {
		//Construtor
		this.estrategiaJug1 = estrategiaJug1;
		this.estrategiaJug2 = estrategiaJug2;
		this.lifeValueJug1 = 100;
		this.lifeValueJug2 = 100;
		this.posX = posX;
		this.posY = posY;
		
	}

	public ArrayQueue getEstrategiaJug1() {
		return estrategiaJug1;
	}
	
	public void salidaEnfrentamiento() {
		
		Object arregloEstraJug1[] = this.estrategiaJug1.getArrayQueue();
		Object arregloEstraJug2[] = this.estrategiaJug2.getArrayQueue();
		
		//Variables de carta
		int valorCartaJug1 = 0;
		int valorCartaJug2 = 0;
		int seleccionCartaJug1 = 0;
		int seleccionCartaJug2 = 0;
		int valorRecargaEnergia = 5;
		this.totalAtqJug1 = 0;
		this.totalAtqJug2 = 0;
		
		for(int i = this.estrategiaJug1.getF(); i != this.estrategiaJug1.getR();i = (i+1)%arregloEstraJug1.length){
			Carta cartaJugador1 = (Carta)arregloEstraJug1[i];
			Carta cartaJugador2 = (Carta)arregloEstraJug2[i];
			
			//Obtener el valor de la carta del jugador 1
			if(cartaJugador1 instanceof Daga || cartaJugador1 instanceof Espada || cartaJugador1 instanceof Lanza ||
					cartaJugador1 instanceof Hacha || cartaJugador1 instanceof Arco) {
				valorCartaJug1 = cartaJugador1.getValue();
				seleccionCartaJug1 = 1;
			}else if(cartaJugador1 instanceof EscudoMim || cartaJugador1 instanceof EscudoMet || 
					cartaJugador1 instanceof EscudoMad || cartaJugador1 instanceof Armadura) {
				valorCartaJug1 = cartaJugador1.getValue();
				seleccionCartaJug1 = 2;
			}else if(cartaJugador1 instanceof CartaModo || cartaJugador1 instanceof RecargaVida || 
					cartaJugador1 instanceof RecargaEnergia) {
				valorCartaJug1 = cartaJugador1.getValue();
				seleccionCartaJug1 = 3;
			}
			
			//Obtener el valor de la carta del jugador 2
			if(cartaJugador2 instanceof Daga || cartaJugador2 instanceof Espada || cartaJugador2 instanceof Lanza ||
					cartaJugador2 instanceof Hacha || cartaJugador2 instanceof Arco) {
				valorCartaJug2 = cartaJugador2.getValue();
				seleccionCartaJug2 = 1;
			}else if(cartaJugador2 instanceof EscudoMim || cartaJugador2 instanceof EscudoMet || 
					cartaJugador2 instanceof EscudoMad || cartaJugador2 instanceof Armadura) {
				valorCartaJug2 = cartaJugador2.getValue();
				seleccionCartaJug2 = 2;
			}else if(cartaJugador2 instanceof CartaModo || cartaJugador2 instanceof RecargaVida || 
					cartaJugador2 instanceof RecargaEnergia) {
				valorCartaJug2 = cartaJugador2.getValue();
				seleccionCartaJug2 = 3;
			}
			
			//Enfrentamientos de las cartas
			//Listo
			if(seleccionCartaJug1 == 1 && seleccionCartaJug2 == 1) {
				this.lifeValueJug1 = this.lifeValueJug1 - valorCartaJug2;
				this.lifeValueJug2 = this.lifeValueJug2 - valorCartaJug1;
				this.totalAtqJug1 += valorCartaJug1;
				this.totalAtqJug2 += valorCartaJug2;
			}//Listo
			else if(seleccionCartaJug1 == 2 && seleccionCartaJug2 == 2) {
				//Se neutralizan
			}//Listo
			else if(seleccionCartaJug1 == 3 && seleccionCartaJug2 == 3) {
				//Doble ataque aleatorio
				if(valorCartaJug2 == 0 && valorCartaJug1 == 0) {
					int alea1 = (int)(Math.random()*15)+1;
					int alea2 = (int)(Math.random()*15)+1;
					this.lifeValueJug1 = this.lifeValueJug1 - alea2;
					this.lifeValueJug2 = this.lifeValueJug2 - alea1;
					this.totalAtqJug1 += alea2;
					this.totalAtqJug2 += alea1;
				}//Doble recarga de vida
				else if(valorCartaJug2 == 25 && valorCartaJug1 == 25) {
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}//Doble recarga de vida
				else if(valorCartaJug2 == 10 && valorCartaJug1 == 10) {
					this.lifeValueJug1 = this.lifeValueJug1 + valorCartaJug1;
					this.lifeValueJug2 = this.lifeValueJug2 + valorCartaJug2;
					this.totalAtqJug1 -= valorCartaJug1;
					this.totalAtqJug2 -= valorCartaJug2;
				}//Jugador dos recarga vida pero recibe ataque jugador 1
				else if(valorCartaJug2 == 10 && valorCartaJug1 == 0) {
					int alea1 = (int)(Math.random()*15)+1;
					this.lifeValueJug2 = this.lifeValueJug2 - alea1;
					this.lifeValueJug2 = this.lifeValueJug2 + valorCartaJug2;
					this.totalAtqJug1 += alea1;
					this.totalAtqJug2 -= valorCartaJug2;
				}//Jugador uno recarga vida pero recibe ataque jugador 2
				else if(valorCartaJug2 == 0 && valorCartaJug1 == 10) {
					int alea2 =  (int)(Math.random()*15)+1;
					this.lifeValueJug1 = this.lifeValueJug1 + valorCartaJug1;
					this.lifeValueJug1 = this.lifeValueJug1 - alea2;
					this.totalAtqJug1 -= valorCartaJug1;
					this.totalAtqJug2 += alea2;
				}//Jugador uno recibe un ataque aleatorio y dos recarga energia
				else if(valorCartaJug2 == 0 && valorCartaJug1 == 25) {
					int alea2 = (int)(Math.random()*15)+1;
					this.lifeValueJug1 = this.lifeValueJug1 - alea2;
					this.totalAtqJug2 += alea2;
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
				}//Jugador dos recibe un ataque aleatorio y uno recarga energia
				else if(valorCartaJug2 == 25 && valorCartaJug1 == 0) {
					int alea1 = (int)(Math.random()*15)+1;
					this.lifeValueJug2 = this.lifeValueJug2 - alea1;
					this.totalAtqJug1 += alea1;
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}//Jugador dos recarga vida y el otro energia
				else if(valorCartaJug2 == 10 && valorCartaJug1 == 25) {
					this.lifeValueJug2 = this.lifeValueJug2 + valorCartaJug2;
					this.totalAtqJug2 -= valorCartaJug2;
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
				}//Jugador uno recarga vida y el otro energia
				else if(valorCartaJug2 == 25 && valorCartaJug1 == 10) {
					this.lifeValueJug1 = this.lifeValueJug1 + valorCartaJug1;
					this.totalAtqJug1 -= valorCartaJug1;
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}
			}//Listo
			else if(seleccionCartaJug1 == 1 && seleccionCartaJug2 == 2) {
				int ataque = valorCartaJug2 - valorCartaJug1;
				if(ataque < 0) {
					this.lifeValueJug2 = this.lifeValueJug2 + ataque;
					this.totalAtqJug1 += ataque;
				}
			}//Listo
			else if(seleccionCartaJug1 == 1 && seleccionCartaJug2 == 3) {
				if(valorCartaJug2 == 0) {
					//Se neutraliza el ataque
				}else if(valorCartaJug2 == 25) {
					this.lifeValueJug2 = this.lifeValueJug2 - valorCartaJug1;
					this.totalAtqJug1 += valorCartaJug1;
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}else if(valorCartaJug2 == 10) {
					this.lifeValueJug2 = this.lifeValueJug2 + valorCartaJug2;
					this.lifeValueJug2 = this.lifeValueJug2 - valorCartaJug1;
					this.totalAtqJug2 -= valorCartaJug2;
					this.totalAtqJug1 += valorCartaJug1;
				}
				
			}//Listo
			else if(seleccionCartaJug1 == 2 && seleccionCartaJug2 == 1) {
				int ataque = valorCartaJug1 - valorCartaJug2;
				if(ataque < 0) {
					this.lifeValueJug1 = this.lifeValueJug1 + ataque;
					this.totalAtqJug2 += ataque;
				}
			}//Listo
			else if(seleccionCartaJug1 == 2 && seleccionCartaJug2 == 3) {
				if(valorCartaJug2 == 0) {
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}else if(valorCartaJug2 == 25) {
					this.lifeValueJug1 = this.lifeValueJug1 - valorRecargaEnergia;
					this.totalAtqJug2 += valorRecargaEnergia;
				}else if(valorCartaJug2 == 10) {
					this.lifeValueJug2 = this.lifeValueJug2 + valorCartaJug2;
					this.totalAtqJug2 -= valorCartaJug2;
				}
				
			}//Listo
			else if(seleccionCartaJug1 == 3 && seleccionCartaJug2 == 1) {
				if(valorCartaJug1 == 0) {
					//Neutraliza ataque
				}else if(valorCartaJug1 == 25) {
					this.lifeValueJug1 = this.lifeValueJug1 - valorCartaJug2;
					this.totalAtqJug2 += valorCartaJug2;
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
				}else if(valorCartaJug1 == 10) {
					this.lifeValueJug1 = this.lifeValueJug1 + valorCartaJug1;
					this.lifeValueJug1 = this.lifeValueJug1 - valorCartaJug2;
					this.totalAtqJug2 += valorCartaJug2;
					this.totalAtqJug1 -= valorCartaJug1;
				}
			}//Listo
			else if(seleccionCartaJug1 == 3 && seleccionCartaJug2 == 2) {
				if(valorCartaJug1 == 0) {
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
				}else if(valorCartaJug1 == 25) {
					this.lifeValueJug2 = this.lifeValueJug2 - valorRecargaEnergia;
					this.totalAtqJug1 += valorRecargaEnergia;
				}else if(valorCartaJug1 == 10) {
					this.lifeValueJug1 = this.lifeValueJug1 + valorCartaJug1;
					this.totalAtqJug1 -= valorCartaJug1;
				}
			}
		}
	}
	
	public void dibujar(Graphics g) {
		int posImagen = this.posX;
		int posImagen2 = this.posX + 110;
		
		Object arregloEstraJug1[] = this.estrategiaJug1.getArrayQueue();
		Object arregloEstraJug2[] = this.estrategiaJug2.getArrayQueue();
		
		for(int i = this.estrategiaJug1.getF(); i != this.estrategiaJug1.getR();i = (i+1)%arregloEstraJug1.length){
			Carta cartaJugador1 = (Carta)arregloEstraJug1[i];
			g.drawImage(cartaJugador1.getImagen(), posImagen, this.posY, null);
			posImagen = posImagen - 110;
		}
		
		for(int i = this.estrategiaJug2.getF(); i != this.estrategiaJug2.getR();i = (i+1)%arregloEstraJug2.length){
			Carta cartaJugador2 = (Carta)arregloEstraJug2[i];
			g.drawImage(cartaJugador2.getImagen(), posImagen2, this.posY, null);
			posImagen2 = posImagen2 + 110;
		}
	}

	public int getLifeValueJug1() {
		return lifeValueJug1;
	}

	public int getLifeValueJug2() {
		return lifeValueJug2;
	}

	public int getAttackValueJug1() {
		return attackValueJug1;
	}

	public int getAttackValueJug2() {
		return attackValueJug2;
	}

	public ArrayQueue getEstrategiaJug2() {
		return estrategiaJug2;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getTotalAtqJug1() {
		return totalAtqJug1;
	}
	
	public int getTotalAtqJug2() {
		return totalAtqJug2;
	}
}
