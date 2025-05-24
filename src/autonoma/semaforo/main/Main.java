/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.main;

/**
 *
 * @author johan villada yu camila prada 
 * version 1.0.0
 * 3/24/25
 */
import autonoma.semaforo.gui.Interfaz;
import autonoma.semaforo.models.ListaCarros;
import java.awt.BorderLayout;
import javax.swing.*;

public class Main extends JFrame {
    public static ListaCarros LisCarr;

    public Main() {
        Interfaz interfaz = new Interfaz();
        add(interfaz);
        JPanel panel = new JPanel();

        add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setTitle("Practica 1");
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        LisCarr = new ListaCarros();
        new Main();
        LisCarr.start();
    }
}
