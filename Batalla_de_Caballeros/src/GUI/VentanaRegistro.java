package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ADT.BinaryTree;
import ADT.NodeTree;
import ADT.Position;
import Business.JugadorBusiness;
import Domain.Jugador;

public class VentanaRegistro extends JFrame implements ActionListener{
	public static   BinaryTree tree=new BinaryTree();
	private JLabel jlblTitle;
	private JLabel jlblUser;
	private JLabel jlblPassword;
	private JLabel jlblCorrect;
	private JLabel jlblFail;
	private JLabel jlblClass;
	private JLabel jlblcError;
	private JTextField jtfUser;
	private JTextField jtfPassword;
	private JButton jbtRegister;
	private JButton jbtBack;
	
	private JComboBox<String> combo1;
	
	public VentanaRegistro() {
		this.setLayout(null);
		this.setSize(500, 450);
		this.setTitle("Registro de Jugadores");
		this.getContentPane().setBackground(new Color(222,184,135));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
	}//constructor
	
	private void init() {
		this.jlblTitle = new JLabel("Registro de Jugadores");
		this.jlblTitle.setBounds(140,20,300,40);
		this.jlblTitle.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlblTitle);
		
		this.jlblUser = new JLabel("Ingrese un nombre de usuario: ");
		this.jlblUser.setBounds(20,60,300,40);
		this.jlblUser.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlblUser);
		
		this.jtfUser = new JTextField();
		this.jtfUser.setBounds(20, 110, 300, 35);
		this.jtfUser.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jtfUser);
		
		this.jlblPassword = new JLabel("Ingrese una contraseña: ");
		this.jlblPassword.setBounds(20,150,300,40);
		this.jlblPassword.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlblPassword);
		
		this.jtfPassword = new JTextField();
		this.jtfPassword.setBounds(20, 200, 300, 35);
		this.jtfPassword.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jtfPassword);
		
		this.jbtRegister = new JButton("Registrar");
		this.jbtRegister.setBounds(320, 350, 150, 30);
		this.jbtRegister.addActionListener(this);
		this.jbtRegister.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jbtRegister);
		
		this.jbtBack = new JButton("Atrás");
		this.jbtBack.setBounds(20, 350, 115, 30);
		this.jbtBack.addActionListener(this);
		this.jbtBack.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jbtBack);
		
		this.jlblcError= new JLabel("¡No ingresaste un usuario,agrega uno por favor!");
		this.jlblcError.setBounds(140,270,400,40);
		this.jlblcError.setFont(new Font("Algerian", Font.BOLD, 12));
		this.jlblcError.setVisible(false);
		this.add(jlblcError);
		
		this.jlblCorrect= new JLabel("¡Registro Completado!");
		this.jlblCorrect.setBounds(140,270,300,40);
		this.jlblCorrect.setFont(new Font("Algerian", Font.BOLD, 20));
		this.jlblCorrect.setVisible(false);
		this.add(jlblCorrect);
		
		this.jlblFail= new JLabel("¡Nombre de Usuario Utilizado!");
		this.jlblFail.setBounds(100,270,350,40);
		this.jlblFail.setFont(new Font("Algerian", Font.BOLD, 20));
		this.jlblFail.setVisible(false);
		this.add(jlblFail);
		
		this.jlblClass = new JLabel("Ingrese su personaje: ");
		this.jlblClass.setBounds(20,250,300,40);
		this.jlblClass.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlblClass);
		
		combo1 = new JComboBox<String>();
		this.combo1.setFont(new Font("Algerian", Font.PLAIN, 18));
		combo1.addItem("Caballero");
		combo1.addItem("Mago");
		combo1.addItem("Rey");
		combo1.addItem("Principe");
		combo1.setBounds(20, 290, 180, 30);
		
		this.add(combo1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.jbtRegister) {
			JugadorBusiness jB = new JugadorBusiness();		
			 try {
				jB.leer();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			    if(jtfUser.getText().length()==0) {
			  jtfPassword.setText("");jtfUser.setText("");
			  			  jlblcError.setVisible(true);
			    }else {
			    	  if(jB.te.contains(jtfUser.getText())==false) {
			    		
			  			try {
			  				boolean bandera = jB.registrarJugador(new Jugador(jtfUser.getText(), jtfPassword.getText(),(String)combo1.getSelectedItem()));
			  				if(bandera == false) {
			  					
			  				}else {
			  					this.jlblCorrect.setVisible(true);
			  					this.jlblFail.setVisible(false);
			  					tree.inOrder();System.out.println("mesirve");
			  				}
			  			} catch (IOException e1) {
			  				// TODO Auto-generated catch block
			  				e1.printStackTrace();
			  			}
			  			  }else {
			  				  this.jlblFail.setVisible(true);
			  				  this.jlblCorrect.setVisible(false);
			  				  System.out.println("fallo es igual");
			  			  }
			  			jtfPassword.setText("");jtfUser.setText("");jlblcError.setVisible(false);
			    }
			    
		}else if(e.getSource()==this.jbtBack){
			this.setVisible(false);
		}
	}
}
