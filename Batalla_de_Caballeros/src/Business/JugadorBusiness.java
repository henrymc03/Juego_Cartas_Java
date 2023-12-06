package Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import ADT.BinaryTree;
import ADT.NodeTree;
import ADT.SimpleList;
import Data.JugadorData;
import Domain.Jugador;

public class JugadorBusiness {

private JugadorData jugadorData;
	//
	public JugadorBusiness() {
		this.jugadorData = new JugadorData();
	}//constructor
	
	public boolean registrarJugador(Jugador player) throws IOException {
		
	    File fileAddress = new File("usuarios.txt");
	    FileReader fr = new FileReader (fileAddress);
	    BufferedReader br = new BufferedReader(fr);
	    
	    //Valida que no haya algun usuario con el mismo nombre
	    String registerFile = br.readLine();   
	    
	    while(registerFile!=null){      
	    	
	    	StringTokenizer sT = new StringTokenizer(registerFile, "*");
	    	
	    	if(player.getUsername().equals(sT.nextToken()))
	    		return false;
	    	
	    	registerFile = br.readLine();
	    }  
		br.close();
		
	    return this.jugadorData.registrarJugador(player);
	}
	
public boolean ingresar(Jugador player) throws IOException {
		

    File fileAddress = new File("usuarios.txt");
    FileReader fr = new FileReader (fileAddress);
    BufferedReader br = new BufferedReader(fr);
    
    //Valida que no haya algun usuario con el mismo nombre
    String registerFile = br.readLine();   
    
    while(registerFile!=null){      
    	
    	StringTokenizer sT = new StringTokenizer(registerFile, "*");
    	
    	if(player.getUsername().equals(sT.nextToken())&&player.getPassword().equals(sT.nextToken())) 
    		return true;
    	
    	registerFile = br.readLine();
    }  
	br.close();
	
    return false;

	}
public BinaryTree te=new BinaryTree();
public BinaryTree leer() throws IOException {
	te.clear();

    File fileAddress = new File("usuarios.txt");
    FileReader fr = new FileReader (fileAddress);
    BufferedReader br = new BufferedReader(fr);
    
    //Valida que no haya algun usuario con el mismo nombre
    String registerFile = br.readLine();   
    
    while(registerFile!=null){      
    	
    	StringTokenizer sT = new StringTokenizer(registerFile, "*");
    	
    	te.insert(sT.nextToken());
    	
    	registerFile = br.readLine();
    }  
	br.close();
	
    return te;

	}

public String recogerPersonaje(String player) throws IOException {
	
    String salida;
    File fileAddress = new File("usuarios.txt");
    FileReader fr = new FileReader (fileAddress);
    BufferedReader br = new BufferedReader(fr);
    
    //Valida que no haya algun usuario con el mismo nombre
    String registerFile = br.readLine();   
    String personajes = "";
    while(registerFile!=null){      
    	
    	StringTokenizer sT = new StringTokenizer(registerFile, "*");
    	
    	
    	if(player.equals(sT.nextToken())) {
    		      sT.nextToken();
    				personajes =(String) sT.nextElement(); 
    	}
    	registerFile = br.readLine();
    }  
	br.close();
	
    return personajes;

	}
public SimpleList si=new SimpleList();

public SimpleList ranking() throws IOException {
	si.clear();

    File fileAddress = new File("Ranking.txt");
    FileReader fr = new FileReader (fileAddress);
    BufferedReader br = new BufferedReader(fr);
    
    //Valida que no haya algun usuario con el mismo nombre
    String registerFile = br.readLine();   
    String user;String monedas;
    while(registerFile!=null){      
    	
    	StringTokenizer sT = new StringTokenizer(registerFile, "*");
    	
    	user=sT.nextToken();
    	monedas=sT.nextToken();
    	si.addInSortedList(new Jugador(user,Integer.parseInt(monedas)));
    	
    	registerFile = br.readLine();
    }  
	br.close();
	
	
    return si;

	}//End readRegisterFile()
   
   
}//End class
	

