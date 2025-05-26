/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.main;

import autonoma.semaforo.gui.Interfaz;
import autonoma.semaforo.models.ListaCarros;
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static ListaCarros LisCarr;
    private static int nivelDificultad = 1;

    public Main() {
        Object[] opciones = {"Fácil", "Medio", "Difícil"};
        int seleccion = JOptionPane.showOptionDialog(
            null,
            "Seleccione el nivel de dificultad:",
            "Configuración de juego",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if(seleccion == JOptionPane.CLOSED_OPTION) {
            System.exit(0);
        }
        
        nivelDificultad = seleccion + 1;
        LisCarr = new ListaCarros(nivelDificultad);
        
        Interfaz interfaz = new Interfaz(nivelDificultad, LisCarr);
        add(interfaz);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setLocationRelativeTo(null);
        setTitle("Simulador de Tráfico - Nivel " + (nivelDificultad == 1 ? "Fácil" : nivelDificultad == 2 ? "Medio" : "Difícil"));
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
    
    public static int getNivelDificultad() {
        return nivelDificultad;
    }
}