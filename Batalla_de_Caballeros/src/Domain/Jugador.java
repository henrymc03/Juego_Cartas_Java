package Domain;

public class Jugador {

	private String username;
	private String password;
	private String Personajes;
	private int monedas;
	
	public Jugador(String username, String password,String Personajes) {
		this.username = username;
		this.password = password;
		this.Personajes = Personajes;
		this.monedas=0;
	}
	
	public Jugador(String username,int monedas) {
		this.username = username;
		this.monedas=monedas;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPersonajes() {
		return Personajes;
	}

	public void setPersonajes(String personajes) {
		Personajes = personajes;
	}

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

	@Override
	public String toString() {
		return "Usuario=" + username+", monedas="
				+ monedas;
	}
	
	
}
