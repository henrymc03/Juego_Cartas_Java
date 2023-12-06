package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ADT.ArrayQueue;
import ADT.ArrayStack;
import ADT.EmptyQueueException;
import ADT.EmptyStackException;
import ADT.FullQueueException;
import ADT.NodeList;
import ADT.SimpleList;
import Data.JugadorData;
import Domain.Carta;
import Domain.Enfrentamiento;
import Domain.Jugador;
import Domain.JugadorPartida;
import Domain.Mano;
import Domain.Mazo;
import Domain.ZonaFormAtaque;


public class EscenaPrincipal extends JPanel implements ActionListener, MouseListener{

	//Domains
	private Mazo mazoPrimerJugador;
	private Mazo mazoSegundoJugador;
	
	private Mano manoPrimerJugador;
	private Mano manoSegundoJugador;
	
	private ZonaFormAtaque zonaAtqPrimerJugador;
	private ZonaFormAtaque zonaAtqSegundoJugador;
	
	private Enfrentamiento enfrentaJugadores;
	
	//ADTs
	private ArrayStack pilaMazo1;
	private ArrayStack pilaMazo2;
	
	private SimpleList listaMano1;
	private SimpleList listaMano2;
	
	private ArrayQueue colaZonaAtq1;
	private ArrayQueue colaZonaAtq2;
	
	//Componentes del panel
	//Labels titulos
	private JLabel jlbTitleMano1;
	private JLabel jlbTitleMano2;
	private JLabel jlbTitleZA1;
	private JLabel jlbTitleZA2;
	private JLabel jlbTitleEnf;
	private JLabel jlbTitleWin;
	//Labels excepciones
	private JLabel jlbExcpMaxMano1;
	private JLabel jlbExcpMaxMano2;
	private JLabel jlbExcpMinMano1;
	private JLabel jlbExcpMinMano2;
	private JLabel jlbExcpMaxZA1;
	private JLabel jlbExcpMaxZA2;
	private JLabel jlbExcpMinZA1;
	private JLabel jlbExcpMinZA2;
	//Labels dataJugadores
	private JLabel jlbUsername1;
	private JLabel jlbUsername2;
	private JLabel jlbCharacter1;
	private JLabel jlbCharacter2;
	private JLabel jlbLifePoints1;
	private JLabel jlbLifePoints2;
	//Botones
	private JButton jbtGeneraMazo1;
	private JButton jbtGeneraMazo2;
	private JButton jbtLimpiaZA1;
	private JButton jbtLimpiaZA2;
	private JButton jbtInicioAtq1;
	private JButton jbtInicioAtq2;
	private JButton jbtdqueCola1;
	private JButton jbtdqueCola2;
	private JButton jbtStartBattle;
	private JButton jbtBack;
	
	//Variables del otro panel
	// 
	private boolean banderaBotones = JPAreaDibujo.iniciaPartida;
	private String personajeJug1 = JPAreaDibujo.jugador1.getJugador().getPersonajes();
	private String personajeJug2 = JPAreaDibujo.jugador2.getJugador().getPersonajes();
	private String nombreJug1 = JPAreaDibujo.jugador1.getJugador().getUsername();
	private String nombreJug2 = JPAreaDibujo.jugador2.getJugador().getUsername();
	
	//Variables dentro del juego
	private boolean conBtnGenerarMazo1;
	private boolean conBtnGenerarMazo2;
	private boolean confBattle1;
	private boolean confBattle2;
	
	//Constructor
	public EscenaPrincipal() {
		//Constructor
		this.setPreferredSize(new Dimension(1500, 750));
		this.setFocusable(true);
		this.setLayout(null);
		this.setRequestFocusEnabled(true);
		this.addMouseListener(this);
		
		//Inicializar variables internas
		//Contador del boton generar mazo
		this.conBtnGenerarMazo1 = true;
		this.conBtnGenerarMazo2 = true;
		//Se espera a que los dos jugadores den listo
		this.confBattle1 = false;
		this.confBattle2 = false;
		
		//Mazo
		this.pilaMazo1 = new ArrayStack(30);
		this.pilaMazo2 = new ArrayStack(30);
		this.mazoPrimerJugador = new Mazo(this.pilaMazo1, 600, 580);
		this.mazoSegundoJugador = new Mazo(this.pilaMazo2, 1350, 580);
		
		//Mano jugador
		this.listaMano1 = new SimpleList();
		this.listaMano2 = new SimpleList();
		this.manoPrimerJugador = new Mano(this.listaMano1, 20, 580);
		this.manoSegundoJugador = new Mano(this.listaMano2, 750, 580);
		
		//Zona de ataque
		this.colaZonaAtq1 = new ArrayQueue(4);
		this.colaZonaAtq2 = new ArrayQueue(4);
		this.zonaAtqPrimerJugador = new ZonaFormAtaque(colaZonaAtq1, 20, 380);
		this.zonaAtqSegundoJugador = new ZonaFormAtaque(colaZonaAtq2, 750, 380);
		
		//Enfrentamiento
		this.enfrentaJugadores = new Enfrentamiento(this.colaZonaAtq1, this.colaZonaAtq2, 600, 100);
		
		//Agregar componentes
		init();
	}
	
	public void init() {
		
		//Titulos
		this.jlbTitleMano1 = new JLabel("Mano del Jugador 1:");
		this.jlbTitleMano1.setForeground(Color.WHITE);;
		this.jlbTitleMano1.setBounds(20,540,200,30);
		this.jlbTitleMano1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbTitleMano1);
		
		this.jlbTitleMano2 = new JLabel("Mano del Jugador 2:");
		this.jlbTitleMano2.setForeground(Color.WHITE);;
		this.jlbTitleMano2.setBounds(750,540,200,30);
		this.jlbTitleMano2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbTitleMano2);
		
		this.jlbTitleZA1 = new JLabel("Zona de Ataque del Jugador 1:");
		this.jlbTitleZA1.setForeground(Color.WHITE);;
		this.jlbTitleZA1.setBounds(20,350,300,30);
		this.jlbTitleZA1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbTitleZA1);
		
		this.jlbTitleZA2 = new JLabel("Zona de Ataque del Jugador 2:");
		this.jlbTitleZA2.setForeground(Color.WHITE);;
		this.jlbTitleZA2.setBounds(750,350,300,30);
		this.jlbTitleZA2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbTitleZA2);
		
		this.jlbTitleEnf = new JLabel("ENFRENTAMIENTO");
		this.jlbTitleEnf.setForeground(Color.WHITE);;
		this.jlbTitleEnf.setBounds(600,50,300,30);
		this.jlbTitleEnf.setVisible(false);
		this.jlbTitleEnf.setFont(new Font("Algerian", Font.PLAIN, 25));
		this.add(jlbTitleEnf);
		
		this.jlbTitleWin = new JLabel();
		this.jlbTitleWin.setForeground(Color.GREEN);;
		this.jlbTitleWin.setBounds(430,120,600,200);
		this.jlbTitleWin.setVisible(false);
		this.jlbTitleWin.setFont(new Font("Algerian", Font.PLAIN, 30));
		this.add(jlbTitleWin);
		
		//Informacion de los jugadores
		//Jugador 1
		this.jlbLifePoints1 = new JLabel("PUNTOS DE VIDA: "+this.enfrentaJugadores.getLifeValueJug1());
		this.jlbLifePoints1.setForeground(Color.RED);;
		this.jlbLifePoints1.setBounds(20,50,350,30);
		this.jlbLifePoints1.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbLifePoints1);
		
		this.jlbUsername1 = new JLabel("Jugador 1: "+this.nombreJug1);//
		this.jlbUsername1.setForeground(Color.WHITE);;
		this.jlbUsername1.setBounds(20,100,300,30);
		this.jlbUsername1.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbUsername1);
		
		this.jlbCharacter1 = new JLabel("Personaje: "+this.personajeJug1);//
		this.jlbCharacter1.setForeground(Color.WHITE);;
		this.jlbCharacter1.setBounds(20,150,300,30);
		this.jlbCharacter1.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbCharacter1);
		
		//Jugador 2
		this.jlbLifePoints2 = new JLabel("PUNTOS DE VIDA: "+this.enfrentaJugadores.getLifeValueJug2());
		this.jlbLifePoints2.setForeground(Color.RED);;
		this.jlbLifePoints2.setBounds(1150,50,350,30);
		this.jlbLifePoints2.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbLifePoints2);
		
		this.jlbUsername2 = new JLabel("Jugador 2: "+this.nombreJug2);//
		this.jlbUsername2.setForeground(Color.WHITE);;
		this.jlbUsername2.setBounds(1150,100,300,30);
		this.jlbUsername2.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbUsername2);
		
		this.jlbCharacter2 = new JLabel("Personaje: "+this.personajeJug2);//
		this.jlbCharacter2.setForeground(Color.WHITE);;
		this.jlbCharacter2.setBounds(1150,150,300,30);
		this.jlbCharacter2.setFont(new Font("Algerian", Font.BOLD, 20));
		this.add(jlbCharacter2);
		
		//Excepciones
		//Cuando la mano esta llena
		this.jlbExcpMaxMano1 = new JLabel("Sólo 5 Cartas por Turno");
		this.jlbExcpMaxMano1.setForeground(Color.RED);;
		this.jlbExcpMaxMano1.setBounds(245,540,250,30);
		this.jlbExcpMaxMano1.setVisible(false);
		this.jlbExcpMaxMano1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMaxMano1);
		
		this.jlbExcpMaxMano2 = new JLabel("Sólo 5 Cartas por Turno");
		this.jlbExcpMaxMano2.setForeground(Color.RED);;
		this.jlbExcpMaxMano2.setBounds(975,540,250,30);
		this.jlbExcpMaxMano2.setVisible(false);
		this.jlbExcpMaxMano2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMaxMano2);
		
		//Pedir que se llene la mano
		this.jlbExcpMinMano1 = new JLabel("Llenar la Mano con 5 Cartas");
		this.jlbExcpMinMano1.setForeground(Color.RED);;
		this.jlbExcpMinMano1.setBounds(245,540,300,30);
		this.jlbExcpMinMano1.setVisible(false);
		this.jlbExcpMinMano1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMinMano1);
		
		this.jlbExcpMinMano2 = new JLabel("Llenar la Mano con 5 Cartas");
		this.jlbExcpMinMano2.setForeground(Color.RED);;
		this.jlbExcpMinMano2.setBounds(975,540,300,30);
		this.jlbExcpMinMano2.setVisible(false);
		this.jlbExcpMinMano2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMinMano2);
		
		//Cuando la zona de ataque esta llena
		this.jlbExcpMaxZA1 = new JLabel("Sólo 3 Cartas por Zona");
		this.jlbExcpMaxZA1.setForeground(Color.RED);;
		this.jlbExcpMaxZA1.setBounds(330,350,250,30);
		this.jlbExcpMaxZA1.setVisible(false);
		this.jlbExcpMaxZA1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMaxZA1);
		
		this.jlbExcpMaxZA2 = new JLabel("Sólo 3 Cartas por Zona");
		this.jlbExcpMaxZA2.setForeground(Color.RED);
		this.jlbExcpMaxZA2.setBounds(1060,350,250,30);
		this.jlbExcpMaxZA2.setVisible(false);
		this.jlbExcpMaxZA2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMaxZA2);
		
		//Cuando la zona de ataque esta vacia
		this.jlbExcpMinZA1 = new JLabel("Sin Cartas en la Zona");
		this.jlbExcpMinZA1.setForeground(Color.RED);;
		this.jlbExcpMinZA1.setBounds(330,350,250,30);
		this.jlbExcpMinZA1.setVisible(false);
		this.jlbExcpMinZA1.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMinZA1);
		
		this.jlbExcpMinZA2 = new JLabel("Sin Cartas en la Zona");
		this.jlbExcpMinZA2.setForeground(Color.RED);
		this.jlbExcpMinZA2.setBounds(1060,350,250,30);
		this.jlbExcpMinZA2.setVisible(false);
		this.jlbExcpMinZA2.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.add(jlbExcpMinZA2);
		
		//Botones
		//Botones que generan los 2 mazos
		this.jbtGeneraMazo1 = new JButton("Generar Mazo");
		this.jbtGeneraMazo1.setBounds(580,530,130,30);
		this.jbtGeneraMazo1.setBackground(new Color(184,134,11));
		this.jbtGeneraMazo1.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtGeneraMazo1.addActionListener(this);
		this.add(jbtGeneraMazo1);
		
		this.jbtGeneraMazo2 = new JButton("Generar Mazo");
		this.jbtGeneraMazo2.setBounds(1330,530,130,30);
		this.jbtGeneraMazo2.setBackground(new Color(184,134,11));
		this.jbtGeneraMazo2.addActionListener(this);
		this.jbtGeneraMazo2.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.add(jbtGeneraMazo2);
		
		//Limpia zona de ataque
		this.jbtLimpiaZA1 = new JButton("Limpiar Zona de Ataque");
		this.jbtLimpiaZA1.setBounds(540,480,190,30);
		this.jbtLimpiaZA1.setBackground(new Color(184,134,11));
		this.jbtLimpiaZA1.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtLimpiaZA1.addActionListener(this);
		this.add(jbtLimpiaZA1);
		
		this.jbtLimpiaZA2 = new JButton("Limpiar Zona de Ataque");
		this.jbtLimpiaZA2.setBounds(1290,480,190,30);
		this.jbtLimpiaZA2.setBackground(new Color(184,134,11));
		this.jbtLimpiaZA2.addActionListener(this);
		this.jbtLimpiaZA2.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.add(jbtLimpiaZA2);
		
		//Confirmar ataque
		this.jbtInicioAtq1 = new JButton("Confirmar Ataque");
		this.jbtInicioAtq1.setBounds(570,430,150,30);
		this.jbtInicioAtq1.setBackground(new Color(184,134,11));
		this.jbtInicioAtq1.setEnabled(false);
		this.jbtInicioAtq1.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtInicioAtq1.addActionListener(this);
		this.add(jbtInicioAtq1);
		
		this.jbtInicioAtq2 = new JButton("Confirmar Ataque");
		this.jbtInicioAtq2.setBounds(1320,430,150,30);
		this.jbtInicioAtq2.setBackground(new Color(184,134,11));
		this.jbtInicioAtq2.setEnabled(false);
		this.jbtInicioAtq2.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtInicioAtq2.addActionListener(this);
		this.add(jbtInicioAtq2);
		
		//Devueleve una carta de la zona de ataque a la mano
		this.jbtdqueCola1 = new JButton("Devolver Carta");
		this.jbtdqueCola1.setBounds(570,380,140,30);
		this.jbtdqueCola1.setBackground(new Color(184,134,11));
		this.jbtdqueCola1.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtdqueCola1.addActionListener(this);
		this.add(jbtdqueCola1);
		
		this.jbtdqueCola2 = new JButton("Devolver Carta");
		this.jbtdqueCola2.setBounds(1320,380,140,30);
		this.jbtdqueCola2.setBackground(new Color(184,134,11));
		this.jbtdqueCola2.setFont(new Font("Algerian", Font.PLAIN, 12));
		this.jbtdqueCola2.addActionListener(this);
		this.add(jbtdqueCola2);
		
		//Boton que envia de la zona de ataque al enfrentamiento
		this.jbtStartBattle = new JButton("¡Iniciar Batalla!");
		this.jbtStartBattle.setBounds(600,280,200,30);
		this.jbtStartBattle.setBackground(new Color(184,134,11));
		this.jbtStartBattle.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.jbtStartBattle.setVisible(false);
		this.jbtStartBattle.addActionListener(this);
		this.add(jbtStartBattle);
		
		//Condicion que inicializa los botones al inicio
		if(this.banderaBotones == true) {
			//Botones jugador 1
			this.jbtdqueCola1.setEnabled(true);
			this.jbtLimpiaZA1.setEnabled(true);
			this.jbtGeneraMazo1.setEnabled(true);
			//Botones jugador 2
			this.jbtdqueCola2.setEnabled(false);
			this.jbtLimpiaZA2.setEnabled(false);
			this.jbtGeneraMazo2.setEnabled(false);
			
		}else {
			//Botones jugador 1
			this.jbtdqueCola2.setEnabled(true);
			this.jbtLimpiaZA2.setEnabled(true);
			this.jbtGeneraMazo2.setEnabled(true);
			//Botones jugador 2 
			this.jbtdqueCola1.setEnabled(false);
			this.jbtLimpiaZA1.setEnabled(false);
			this.jbtGeneraMazo1.setEnabled(false);
		}
		
		//Boton devuelve al menu
		this.jbtBack = new JButton("¡Ver Resultados!");
		this.jbtBack.setBounds(600,280,200,30);
		this.jbtBack.setBackground(new Color(184,134,11));
		this.jbtBack.setFont(new Font("Algerian", Font.PLAIN, 18));
		this.jbtBack.setVisible(false);
		this.jbtBack.addActionListener(this);
		this.add(jbtBack);
	}
	
	public void paintComponent(Graphics g) {
		
		//Fondo de la escena
	    ImageIcon fondo=new ImageIcon(getClass().getResource("/assets/principal.jpg"));
	    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(),this);
        this.setOpaque(false);
        super.paintComponent(g);
        
        //Pinta mazo
        this.mazoPrimerJugador.dibujar(g);
        this.mazoSegundoJugador.dibujar(g);
        
        //Pinta mano
        this.manoPrimerJugador.dibujar(g);
        this.manoSegundoJugador.dibujar(g);
        
        //Pinta zona de ataque
        this.zonaAtqPrimerJugador.dibujar(g);
        this.zonaAtqSegundoJugador.dibujar(g);
        
        //Pinta enfrentamiento
        //if(this.confBattle1 == true && this.confBattle2 == true) {
        	this.enfrentaJugadores.dibujar(g);
        //}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Condicion del generarMazo1
		if(e.getSource()==this.jbtGeneraMazo1) {
			
			this.mazoPrimerJugador.generarMazo(0, 0);
			this.jbtGeneraMazo1.setEnabled(false);
		}//Condicion del generarMazo2
		else if(e.getSource()==this.jbtGeneraMazo2) {
			
			this.mazoSegundoJugador.generarMazo(0, 0);
			this.jbtGeneraMazo2.setEnabled(false);
		}//Condicion del boton devolverCarta1
		else if(e.getSource()==this.jbtdqueCola1) {
			if(!this.colaZonaAtq1.isEmpty()) {
				try {
					Object salidaCartaZona = this.colaZonaAtq1.dequeue();
					if(this.listaMano1.getSize()<5) {
						NodeList nLNuevaCarta = new NodeList(salidaCartaZona);
						this.listaMano1.addFirst(nLNuevaCarta);
						repaint();
						this.jlbExcpMaxZA1.setVisible(false);
					}else
						this.jlbExcpMaxMano1.setVisible(true);
					//Desactiva el boton de confirmar ataque al jugador 1
					if(this.colaZonaAtq1.size() < 3 ) {
						this.jbtInicioAtq1.setEnabled(false);
					}
				} catch (EmptyQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}else
				this.jlbExcpMinZA1.setVisible(true);
			
		}//Condicion del boton devolverCarta2
		else if(e.getSource()==this.jbtdqueCola2) {
			if(!this.colaZonaAtq2.isEmpty()) {
				try {
					Object salidaCartaZona = this.colaZonaAtq2.dequeue();
					
					if(this.listaMano2.getSize()<5) {
						NodeList nLNuevaCarta = new NodeList(salidaCartaZona);
						this.listaMano2.addFirst(nLNuevaCarta);
						repaint();
						this.jlbExcpMaxZA2.setVisible(false);
					}else
						this.jlbExcpMaxMano2.setVisible(true);
					//Desactiva el boton de confirmar ataque al jugador 2
					if(this.colaZonaAtq2.size() < 3 ) {
						this.jbtInicioAtq2.setEnabled(false);
					}
				} catch (EmptyQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}else
				this.jlbExcpMinZA2.setVisible(true);
		}//Condicion del boton que limpia toda la zona de Ataque 1
		else if(e.getSource()==this.jbtLimpiaZA1) {
			if(!this.colaZonaAtq1.isEmpty()) {
				while(this.colaZonaAtq1.size() != 0) {
					try {
						Object salidaCartaZona = this.colaZonaAtq1.dequeue();
						NodeList nLNuevaCarta = new NodeList(salidaCartaZona);
						this.listaMano1.addFirst(nLNuevaCarta);
						repaint();
					} catch (EmptyQueueException e1) {
						System.err.println(e1.getMessage());
					}
				}
				this.jlbExcpMaxZA1.setVisible(false);
				
				//Desactiva el boton de confirmar ataque al jugador 1
				if(this.colaZonaAtq1.size() < 3 ) {
					this.jbtInicioAtq1.setEnabled(false);
				}
			}else
				this.jlbExcpMinZA1.setVisible(true);
		}//Condicion del boton que limpia toda la zona de Ataque 2 
		else if(e.getSource()==this.jbtLimpiaZA2) {
			if(!this.colaZonaAtq2.isEmpty()) {
				while(this.colaZonaAtq2.size() != 0) {
					try {
						Object salidaCartaZona = this.colaZonaAtq2.dequeue();
						NodeList nLNuevaCarta = new NodeList(salidaCartaZona);
						this.listaMano2.addFirst(nLNuevaCarta);
						repaint();
					} catch (EmptyQueueException e1) {
						System.err.println(e1.getMessage());;
					}
				}
				this.jlbExcpMaxZA2.setVisible(false);
				
				//Desactiva el boton de confirmar ataque al jugador 2
				if(this.colaZonaAtq2.size() < 3 ) {
					this.jbtInicioAtq2.setEnabled(false);
				}
			}else
				this.jlbExcpMinZA2.setVisible(true);
		}//Condicion que confirma el ataque jugador 1
		else if(e.getSource()==this.jbtInicioAtq1) {
			this.banderaBotones = false;
			
			if(this.banderaBotones == false) {
				//Botones jugador 1
				this.jbtdqueCola2.setEnabled(true);
				this.jbtLimpiaZA2.setEnabled(true);
				if(this.conBtnGenerarMazo2 == true)
					this.jbtGeneraMazo2.setEnabled(true);
				//Botones jugador 2 
				this.jbtdqueCola1.setEnabled(false);
				this.jbtLimpiaZA1.setEnabled(false);
				this.jbtGeneraMazo1.setEnabled(false);
				this.jbtInicioAtq1.setEnabled(false);
			}
			//Evita que se vuelva a activar el boton de generar mazo
			this.conBtnGenerarMazo1 = false;
			//Confirma estrategia del jugador
			this.confBattle1 = true;
			
		}//Condicion que confirma el ataque jugador 2
		else if(e.getSource()==this.jbtInicioAtq2) {
			this.banderaBotones = true;
			
			if(this.banderaBotones == true) {
				//Botones jugador 1
				this.jbtdqueCola1.setEnabled(true);
				this.jbtLimpiaZA1.setEnabled(true);
				if(this.conBtnGenerarMazo1 == true)
					this.jbtGeneraMazo1.setEnabled(true);
				//Botones jugador 2
				this.jbtdqueCola2.setEnabled(false);
				this.jbtLimpiaZA2.setEnabled(false);
				this.jbtGeneraMazo2.setEnabled(false);
				this.jbtInicioAtq2.setEnabled(false);
			}
			//Evita que se vuelva a activar el boton de generar mazo
			this.conBtnGenerarMazo2 = false;
			//Confirma estrategia del jugador
			this.confBattle2 = true;
		}
		
		//Condicion del boton batalla
		if(e.getSource()==this.jbtStartBattle) {
			this.enfrentaJugadores.salidaEnfrentamiento();
			//Vacea la zona de ataque del jugador 1
			while(this.colaZonaAtq1.size() != 0) {
				try {
					this.colaZonaAtq1.dequeue();
					repaint();
				} catch (EmptyQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}
			//Vacea la zona de ataque del jugador 2
			while(this.colaZonaAtq2.size() != 0) {
				try {
					this.colaZonaAtq2.dequeue();
					repaint();
				} catch (EmptyQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}
			this.confBattle1 = false;
			this.confBattle2 = false;
			
			//Actualizar valor del label
			this.jlbLifePoints1.setText("PUNTOS DE VIDA: "+this.enfrentaJugadores.getLifeValueJug1());
			this.jlbLifePoints2.setText("PUNTOS DE VIDA: "+this.enfrentaJugadores.getLifeValueJug2());
			
			//Activa el label que explica ataque
			this.jlbTitleWin.setVisible(true);
			if(this.enfrentaJugadores.getLifeValueJug1() <= 0 || this.enfrentaJugadores.getLifeValueJug2() <= 0) {
				if(this.enfrentaJugadores.getLifeValueJug1() <= 0) {
					this.jlbTitleWin.setText("¡GANA EL JUGADOR 2!");
					this.jlbTitleWin.setBounds(600,150,400,30);
					this.jbtBack.setVisible(true);
					
					//Ranking
					Jugador jugGana = new Jugador(nombreJug2, 100);
					JugadorPartida jugPartGana = new JugadorPartida(jugGana, 0);
					JugadorData jugDataGna = new JugadorData();
					try {
						jugDataGna.jugadoresRanking(jugPartGana);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else {
					this.jlbTitleWin.setText("¡GANA EL JUGADOR 1!");
					this.jlbTitleWin.setBounds(600,150,400,30);
					this.jbtBack.setVisible(true);
					
					//Ranking
					Jugador jugGana = new Jugador(nombreJug1, 100);
					JugadorPartida jugPartGana = new JugadorPartida(jugGana, 0);
					JugadorData jugDataGna = new JugadorData();
					try {
						jugDataGna.jugadoresRanking(jugPartGana);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				this.jlbTitleWin.setText("<html>Ataque del Jugador "+this.nombreJug1+": "+this.enfrentaJugadores.getTotalAtqJug1()+
									" PUNTOS <p>Ataque del Jugador "+this.nombreJug2+": "+this.enfrentaJugadores.getTotalAtqJug2() 
									+" PUNTOS<html>");
			}
		}
		
		//Condicion que activa el boton de batalla
		if(this.confBattle1 == true && this.confBattle2 == true) {
			this.jbtStartBattle.setVisible(true);
			this.jlbTitleEnf.setVisible(true);
		} else {
			this.jbtStartBattle.setVisible(false);
			this.jlbTitleEnf.setVisible(false);
		}
		
		if(e.getSource()==this.jbtBack) {
			this.setVisible(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Remueve carta del mazo y la envia a mano del jugador 1, solo si hay 5 cartas en juego
		if(this.listaMano1.getSize()<5 && this.listaMano1.getSize()+this.colaZonaAtq1.size()<5) {
			Object cartaSalidaMazo1 = this.mazoPrimerJugador.retiraCartaMazo(e.getX(), e.getY());
			
			if(cartaSalidaMazo1 != null) {
				NodeList nLNuevaCarta = new NodeList(cartaSalidaMazo1);
				this.listaMano1.addFirst(nLNuevaCarta);
				repaint();
			}
			//Desactiva label de Gane
			this.jlbTitleWin.setVisible(false);
		}else {
			//Excepcion
			if(this.listaMano1.getSize()==5) {
				this.jlbExcpMaxMano1.setVisible(true);
				this.jlbExcpMinMano1.setVisible(false);
			}
		}
		
		// Remueve carta del mazo y la envia a mano de jugador 2, solo si hay 5 cartas en juego
		if(this.listaMano2.getSize()<5 && this.listaMano2.getSize()+this.colaZonaAtq2.size()<5) {
			Object cartaSalidaMazo2 = this.mazoSegundoJugador.retiraCartaMazo(e.getX(), e.getY());
			if(cartaSalidaMazo2 != null) {
				NodeList nLNuevaCarta = new NodeList(cartaSalidaMazo2);
				this.listaMano2.addFirst(nLNuevaCarta);
				repaint();
			}
			//Desactiva label de Gane
			this.jlbTitleWin.setVisible(false);
		}else {
			//Excepcion
			if(this.listaMano2.getSize()==5) {
				this.jlbExcpMaxMano2.setVisible(true);
				this.jlbExcpMinMano2.setVisible(false);
			}
		}
		
		
		//Remueve carta de la mano(lista) y lo envia a la zona de ataque si la cola es menor a 3 del jugador 1
		if(this.colaZonaAtq1.size()<3 && this.listaMano1.getSize()+this.colaZonaAtq1.size() == 5) {
			Object cartaSalidaMano1 = this.manoPrimerJugador.retiraCartaMano(e.getX(), e.getY());
			
			if(cartaSalidaMano1 != null) {
				try {
					this.colaZonaAtq1.enqueue(cartaSalidaMano1);
					repaint();
				} catch (FullQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}
			//Activa el boton de confirmar ataque
			if(this.colaZonaAtq1.size() == 3 ) {
				this.jbtInicioAtq1.setEnabled(true);
			}
		}else {
			//Labels excepciones
			if(this.listaMano1.getSize()+this.colaZonaAtq1.size()<5) {
				this.jlbExcpMaxZA1.setVisible(false);
				this.jlbExcpMinMano1.setVisible(true);
				this.jlbExcpMaxMano1.setVisible(false);
			}else if(this.listaMano1.getSize()==5) {
				this.jlbExcpMaxMano1.setVisible(true);
				this.jlbExcpMaxZA1.setVisible(false);
				this.jlbExcpMinMano1.setVisible(false);
			}else if(this.colaZonaAtq1.size()==3) {
				this.jlbExcpMaxMano1.setVisible(false);
				this.jlbExcpMaxZA1.setVisible(true);
				this.jlbExcpMinMano1.setVisible(false);
			}
		}
		
		//Remueve carta de la mano(lista) y lo envia a la zona de ataque si la cola es menor a 3 del jugador 2
		if(this.colaZonaAtq2.size()<3 && this.listaMano2.getSize()+this.colaZonaAtq2.size() == 5) {
			Object cartaSalidaMano2 = this.manoSegundoJugador.retiraCartaMano(e.getX(), e.getY());
			
			if(cartaSalidaMano2 != null) {
				try {
					this.colaZonaAtq2.enqueue(cartaSalidaMano2);
					repaint();
				} catch (FullQueueException e1) {
					System.err.println(e1.getMessage());
				}
			}
			//Activa el boton de confirmar ataque
			if(this.colaZonaAtq2.size() == 3 ) {
				this.jbtInicioAtq2.setEnabled(true);
			}
		}else {
			//Labels excepciones
			if(this.listaMano2.getSize()+this.colaZonaAtq2.size()<5) {
				this.jlbExcpMaxZA2.setVisible(false);
				this.jlbExcpMinMano2.setVisible(true);
				this.jlbExcpMaxMano2.setVisible(false);
			}else if(this.listaMano2.getSize()==5) {
				this.jlbExcpMaxMano2.setVisible(true);
				this.jlbExcpMaxZA2.setVisible(false);
				this.jlbExcpMinMano2.setVisible(false);
			}else if(this.colaZonaAtq2.size()==3) {
				this.jlbExcpMaxMano2.setVisible(false);
				this.jlbExcpMaxZA2.setVisible(true);
				this.jlbExcpMinMano2.setVisible(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
