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

        // Panel de información con comandos del juego
        JTextArea info = new JTextArea();
        info.setText("🚦 INSTRUCCIONES DE JUEGO 🚦\n" +
                     "▶ A: Cambiar semáforo A/B\n" +
                     "▶ C: Cambiar semáforo C/D\n" +
                     "▶ R: Reiniciar juego\n" +
                     "▶ Dificultad Difícil = Modo Caos\n" +
                     "¡Evita colisiones y alcanza el mejor puntaje!");
        info.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        info.setOpaque(false);
        info.setEditable(false);
        info.setForeground(new Color(255, 255, 255));
        info.setBounds(20, 20, 450, 150);
        fondoLabel.add(info);

        // Botón de dificultad
        JButton btnDificultad = crearBoton("⚙️ Elegir Dificultad");
        btnDificultad.setBounds(ancho / 2 - 100, alto - 180, 200, 40);
        fondoLabel.add(btnDificultad);

        // Botón de iniciar
        btnIniciar = crearBoton("🚦 Iniciar Juego");
        btnIniciar.setBounds(ancho / 2 - 100, alto - 130, 200, 40);
        btnIniciar.setEnabled(false);
        fondoLabel.add(btnIniciar);

        // Botón de salir
        JButton btnSalir = crearBoton("❌ Salir");
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
                    private int tiempoTranscurrido = 0;
                    private Timer dificultadTimer = new Timer(10000, e2 -> {
                        tiempoTranscurrido += 10;
                        if (nivelSeleccionado < 3 && tiempoTranscurrido >= 30) {
                            lisCarr.setSemaforoABVerde(!lisCarr.isSemaforoABVerde());
                        }
                        if (nivelSeleccionado == 3) {
                            lisCarr.setSemaforoABVerde(Math.random() > 0.5);
                            lisCarr.setSemaforoCDVerde(Math.random() > 0.5);
                        }
                    });
                    {
                        dificultadTimer.start();
                    }
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

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        boton.setForeground(Color.WHITE);
        boton.setBackground(new Color(180, 130, 255));
        boton.setOpaque(true);
        boton.setBorderPainted(false);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(200, 150, 255));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(new Color(180, 130, 255));
            }
        });

        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}



