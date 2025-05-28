package autonoma.semaforo.main;

import javax.swing.*;

/**
 * Clase principal que lanza el menú principal del simulador de tráfico.
 * Inicia la interfaz gráfica usando `SwingUtilities`.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class Main {

    /**
     * Método principal de la aplicación.
     * Lanza el menú principal en el hilo de eventos de Swing.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPrincipal());
    }
}

