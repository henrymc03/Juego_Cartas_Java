	package Domain;
	
	public class JugadorPartida {
	//
	private Jugador jugador;
	private int vida;
	
	public JugadorPartida(Jugador jugador, int vida) {
		this.jugador = jugador;
		this.vida = vida;
	}

	public Jugador getJugador() {
		return jugador;
	}



	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public int getVida() {
		return vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}

	@Override
	public String toString() {
		return "jugadorPartida [jugador=" + jugador.getUsername() + ", vida=" + vida  ;
	}




}
