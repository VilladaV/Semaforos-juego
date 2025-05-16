/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.main;

/**
 *
 * @author PABLO VI
 */
import autonoma.semaforo.gui.Interfaz;
import autonoma.semaforo.models.ListaCarros;
import javax.swing.*;

public class Main extends JFrame {

	public Main() {
 		add(new Interfaz());
 		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		setSize(400,300);
 		setLocationRelativeTo(null);
 		setTitle("Practica 1");
 		setResizable(false);
 		setVisible(true);
 	}

 	public static void main(String[] args) {
 		new Main();
		ListaCarros LisCarr = new ListaCarros();
		LisCarr.start();
 	}
}