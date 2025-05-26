// Archivo: MenuPrincipal.java
package autonoma.semaforo.main;

import autonoma.semaforo.gui.Interfaz;
import autonoma.semaforo.models.ListaCarros;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {
    private int nivelSeleccionado = 0;
    private JButton btnIniciar;

    public MenuPrincipal() {
        setTitle("Simulador de Tráfico - Menú");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        ImageIcon fondoGif = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/fondo_menu.gif"));
        int ancho = fondoGif.getIconWidth();
        int alto = fondoGif.getIconHeight();

        setSize(ancho, alto);
        setLocationRelativeTo(null);

        JLabel fondoLabel = new JLabel(fondoGif);
        fondoLabel.setLayout(null);
        setContentPane(fondoLabel);

        JButton btnDificultad = new JButton("⚙️ Elegir Dificultad");
        btnDificultad.setBounds(ancho / 2 - 100, alto - 180, 200, 40);
        fondoLabel.add(btnDificultad);

        btnIniciar = new JButton("🚦 Iniciar Juego");
        btnIniciar.setBounds(ancho / 2 - 100, alto - 130, 200, 40);
        btnIniciar.setEnabled(false);
        fondoLabel.add(btnIniciar);

        JButton btnSalir = new JButton("❌ Salir");
        btnSalir.setBounds(ancho / 2 - 100, alto - 80, 200, 40);
        fondoLabel.add(btnSalir);

        btnDificultad.addActionListener(e -> {
            Object[] opciones = {"Fácil", "Media", "Difícil"};
            int seleccion = JOptionPane.showOptionDialog(
                this, "Selecciona el nivel de dificultad:",
                "Nivel de dificultad", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null,
                opciones, opciones[0]
            );

            if (seleccion != JOptionPane.CLOSED_OPTION) {
                nivelSeleccionado = seleccion + 1;
                btnIniciar.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Dificultad seleccionada: " + opciones[seleccion]);
            }
        });

        btnIniciar.addActionListener(e -> {
            if (nivelSeleccionado == 0) {
                JOptionPane.showMessageDialog(this, "Por favor, elige una dificultad antes de iniciar.",
                    "Falta dificultad", JOptionPane.ERROR_MESSAGE);
            } else {
                ListaCarros lisCarr = new ListaCarros(nivelSeleccionado);
                lisCarr.start();
                JFrame juego = new JFrame("Simulador de Tráfico");
                juego.add(new autonoma.semaforo.gui.Interfaz(nivelSeleccionado, lisCarr) {
                    // implementamos la clase abstracta como anónima
                });
                juego.setSize(1080, 720);
                juego.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                juego.setLocationRelativeTo(null);
                juego.setVisible(true);
                dispose();
            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}
