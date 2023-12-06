package GUI;

import javax.swing.JFrame;

public class JFrameInicio extends JFrame{

	private JPAreaDibujo aD;
	
	public JFrameInicio() {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Batalla de Caballeros");
		this.aD = new JPAreaDibujo();
		this.add(this.aD);
		this.pack();
	}
}
