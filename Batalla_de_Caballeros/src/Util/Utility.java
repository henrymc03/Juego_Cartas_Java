package Util;

//Librerias para Escritura y Lectura de Archivos
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

public class Utility {

	// Crea el archivo y la ubicación a escribir
	private FileWriter file;
	// Objeto que escribe en el archivo creado
	private PrintWriter pw;

	// Es la ubicación con el archivo a leer
	File fileAddress;
	// Objeto que permite leer el archivo seleccionado
	FileReader fr;
	// Objeto que almacena el archivo leido
	BufferedReader br;

	public void writeFile(String address, String data) {

		try {
			// Se crea el archivo y se la dan las propiedades de escritura (boolean)
			file = new FileWriter(address, true);
			// Se crea el objeto que tiene la propiedad de escribir en el archivo
			pw = new PrintWriter(file);

			pw.println(data);// guarda

		} catch (IOException ioe) {

			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		} finally {
			try {
				file.close();// Cerrar el archivo de texto
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
				e.printStackTrace();
			}
		}
	}

	public String readFile(String address) {

		String information = "";
		String line = "";

		try {
			fileAddress = new File(address);
			fr = new FileReader(fileAddress);
			br = new BufferedReader(fr);

			// Leo una línea y si tiene un valor, la almaceno
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				information += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fr) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return information;
	}
	
	public void musica() {
		 try{
		        File f = new File("/assets/ab.wav");
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/assets/ab.wav"));
		        clip.open(ais);
		        clip.start();
		    }catch(Exception exception){System.out.println("Failed To Play The WAV File!");}
		
	}
}
