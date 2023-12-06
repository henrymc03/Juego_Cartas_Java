package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import Business.JugadorBusiness;

public class VentanaRanking extends JFrame implements ActionListener{
	private JLabel jlblrankin;
	private JLabel jlblTitle;
	private JButton jbtBack;
	
	public VentanaRanking() throws IOException {
		this.setLayout(null);
		this.setSize(500, 450);
		this.setTitle("Ranking");
		this.getContentPane().setBackground(new Color(222,184,135));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}
	
	private void init() throws IOException {
		this.jlblTitle = new JLabel("Los mejores guerreros de Fight of Champions: ");
		this.jlblTitle.setBounds(20,30,500,40);
		this.jlblTitle.setFont(new Font("Algerian", Font.PLAIN, 18));;
		this.add(jlblTitle);
		
		
		this.jbtBack = new JButton("Atrás");
		this.jbtBack.setBounds(20, 350, 115, 30);
		this.jbtBack.addActionListener(this);
		this.jbtBack.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jbtBack);
		
		JugadorBusiness jb=new JugadorBusiness();
	    jb.ranking();
		
		
			JTextArea text=new JTextArea();
			text.setText(jb.si.printList()+"\n");
			text.setBackground(new Color(222,184,135));
			text.setBounds(20,100,280,300);
			text.setEditable(false);
			text.setFont(new Font("Algerian", Font.PLAIN, 16));
			this.add(text);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.jbtBack){
			this.setVisible(false);
	}
  }

}

