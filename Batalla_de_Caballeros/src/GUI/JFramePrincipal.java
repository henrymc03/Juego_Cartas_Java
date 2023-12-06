package GUI;

import javax.swing.JFrame;

public class JFramePrincipal extends JFrame{
	
	private EscenaPrincipal eP;
	
	public JFramePrincipal() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Batalla de Caballeros");
		this.eP = new EscenaPrincipal();
		this.add(this.eP);
		this.pack();
	}
	
}
