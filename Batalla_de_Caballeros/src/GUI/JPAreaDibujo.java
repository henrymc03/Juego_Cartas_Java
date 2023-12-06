package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;

import Business.JugadorBusiness;
import Domain.Dado;
import Domain.Jugador;
import Domain.JugadorPartida;
import Util.Utility;

public class JPAreaDibujo extends JPanel implements ActionListener{
	public static int vida1=100;public static int vida2=100;
	private Dado dado1;
	private Dado dado2;
	private int b;
	private Image fondo;
	private JButton btnEnviar;
	public static JugadorPartida jugador1;
	public static JugadorPartida jugador2;
	public static boolean iniciaPartida;
	Utility u=new Utility();
	public JPAreaDibujo() {
		
		this.dado1=new Dado(150, 300);
		this.dado2=new Dado(550, 300);
		this.setPreferredSize(new Dimension(800, 600));
		this.setFocusable(true);
		this.setLayout(null);
		init();
		
		
	} // fin clase
	
	private void init () {
		
		
		 u.musica();
		
		JLabel eti = new JLabel();
	    eti.setText("Usuario: ");
	    eti.setBounds(25, 75, 500, 40);
	    eti.setForeground(Color.white);
	    eti.setFont(new Font("Algerian", Font.BOLD, 20));
	    
	    JLabel eti2 = new JLabel();
	    eti2.setText("Contraseña: ");
	    eti2.setBounds(25, 125, 300, 40);
	    eti2.setForeground(Color.white);
	    eti2.setFont(new Font("Algerian", Font.BOLD, 20));
	    
	    JLabel eti5 = new JLabel();
	    eti5.setText("Contraseña: ");
	    eti5.setBounds(450, 125, 300, 40);
	    eti5.setForeground(Color.white);
	    eti5.setFont(new Font("Algerian", Font.BOLD, 20));
	    this.add(eti5);
	    
	    JLabel eti3 = new JLabel();
	    eti3.setText("Usuario: ");
	    eti3.setBounds(450, 75, 500, 40);
	    eti3.setFont(new Font("Algerian", Font.BOLD, 20));
	    eti3.setForeground(Color.white);
	    
	    JTextField contra1=new JTextField();
	    contra1.setBounds(180, 80, 150, 25);
	    contra1.setFont(new Font("Algerian", Font.PLAIN, 18));
        this.add(contra1);
      
        
        JPasswordField textfield1=new JPasswordField();      
        textfield1.setBounds(180, 130, 150, 25);
        this.add(textfield1);
        
        JTextField ter=new JTextField();
        ter.setBounds(600,80,150,25);
        ter.setFont(new Font("Algerian", Font.PLAIN, 18));
        this.add(ter);
        
        JPasswordField contra2=new JPasswordField();
        contra2.setBounds(600,130,150,25);
        this.add(contra2);
        
  
        JButton bi = new JButton("Ingresar");
        bi.setFont(new Font("Algerian", Font.BOLD, 18));
        
        this.btnEnviar = new JButton("INICIAR BATALLA");
        btnEnviar.setBounds(300, 300, 200, 40);
        btnEnviar.setFont(new Font("Algerian", Font.PLAIN, 18));
        btnEnviar.setVisible(false);
        btnEnviar.addActionListener(this);
        btnEnviar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFramePrincipal jfp = new JFramePrincipal();
				jfp.setVisible(true);
			}
		});
        
        
	    JLabel eti221 = new JLabel();
	    eti221.setText("Algún jugador ingreso mal su usuario o contraseña valide sus datos ingresados, por favor");
	    eti221.setBounds(25, 450, 2000, 40);
	    eti221.setForeground(Color.white);
	    eti221.setFont(new Font("Algerian", Font.BOLD, 16));
	    this.add(eti221);
        eti221.setVisible(false);
        
        bi.setBounds(315, 175, 150, 40);
        bi.setFont(new Font("Algerian", Font.PLAIN, 18));
        bi.addActionListener(new ActionListener() {
       
			@Override
			public void actionPerformed(ActionEvent e) {
			//tiroDados();
				boolean next1 = false,next2 = false;
				JugadorBusiness jB = new JugadorBusiness();
				
				try {
					boolean bandera = jB.ingresar(new Jugador(ter.getText(), contra2.getText(),""));
					if(bandera == true) {
						next2=true;
					}else {
						eti221.setVisible(true);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				try {
					boolean bandera2 = jB.ingresar(new Jugador(contra1.getText(), textfield1.getText(),""));
					if(bandera2 == true) {
						next1=true;
					}else {
					repaint();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(next1==true&&next2==true) {
					btnEnviar.setVisible(true);
					eti221.setVisible(false);
					b=2;repaint();
					JugadorBusiness jb=new JugadorBusiness();
					try {
						jugador1 =new JugadorPartida(new Jugador(contra1.getText(), "", jb.recogerPersonaje(contra1.getText())), vida1);
						jugador2 =new JugadorPartida(new Jugador(ter.getText(), "",jb.recogerPersonaje(ter.getText())), vida2);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
		});
        
        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(590, 520, 170, 40);
        btnRegistrar.setFont(new Font("Algerian", Font.PLAIN, 18));
        btnRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRegistro vR= new VentanaRegistro();
				vR.setVisible(true);
			}
		});
        
        JButton btnRanking = new JButton("Ver ranking");
        btnRanking.setBounds(430, 520, 150, 40);
        btnRanking.setFont(new Font("Algerian", Font.PLAIN, 18));
        btnRanking.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaRanking vRk = null;
				try {
					vRk = new VentanaRanking();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				vRk.setVisible(true);
			}
		});

this.add(btnRanking);
		
        this.add(bi);
	    this.add(eti);
	    this.add(eti2);
	    this.add(eti3);
	
	    this.add(btnRegistrar);
	    this.add(btnEnviar);
	

	}
	
	public void paintComponent(Graphics g) {
	    ImageIcon imagem=new ImageIcon(getClass().getResource("/assets/a3.jpg"));
	    g.drawImage(imagem.getImage(), 0, 0, getWidth(), getHeight(),this);
        setOpaque(false);
        super.paintComponent(g);
		g.setColor(Color.white);
		
		if(b==2) {
			
			if(dado1.salio() != dado2.salio()) {
				this.dado1.dibujar(g);
				this.dado2.dibujar(g);
				
				if(dado1.salio()<dado2.salio()) {
					iniciaPartida=false;
				}
				if(dado2.salio()<dado1.salio()) {
					iniciaPartida=true;
				}
			}
		
		}
		b=0;
		//g.fillRect(0, 0, 800, 600);	
		//init();
	
	} // paintComponent

	@Override
	public void actionPerformed(ActionEvent e) {
	
		repaint();
	} // actionPerformed
	


	
} // fin clase

