// Archivo: MenuPrincipal.java
package autonoma.semaforo.main;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

import autonoma.semaforo.gui.Interfaz;
import autonoma.semaforo.models.ListaCarros;
import autonoma.semaforo.models.Sonido;

import javax.swing.*;
import java.awt.*;
  /**
 * Clase que representa el menú principal del simulador de tráfico.
 * Permite seleccionar el nivel de dificultad, iniciar el juego o salir.
 * También reproduce un sonido de fondo y muestra instrucciones básicas.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */

public class MenuPrincipal extends JFrame {
    private int nivelSeleccionado = 0;
    private JButton btnIniciar;
    private Sonido sonidoMenu; 
    


 /**
     * Constructor que inicializa todos los elementos gráficos del menú.
     */
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
        sonidoMenu = new Sonido("/autonoma/semaforo/gui/sonidos/ciudad.wav");

        sonidoMenu.reproducirEnBucle();


        // Panel de información con comandos del juego
        JTextArea info = new JTextArea();
        info.setText(" INSTRUCCIONES JUEGO \n" +
                     " A: Cambiar semáforo A/B\n" +
                     " C: Cambiar semáforo C/D\n" +
                     " R: Reiniciar juego\n" +
                     "¡Evita colisiones y alcanza el mejor puntaje!");
        info.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        info.setOpaque(false);
        info.setEditable(false);
        info.setForeground(new Color(255, 255, 255));
        info.setBounds(20, 20, 450, 150);
        fondoLabel.add(info);

        // Botón de dificultad
        JButton btnDificultad = crearBoton("Elegir Dificultad");
        btnDificultad.setBounds(ancho / 2 - 100, alto - 180, 200, 40);
        fondoLabel.add(btnDificultad);

        // Botón de iniciar
        btnIniciar = crearBoton("Iniciar Juego");
        btnIniciar.setBounds(ancho / 2 - 100, alto - 130, 200, 40);
        btnIniciar.setEnabled(false);
        fondoLabel.add(btnIniciar);

        // Botón de salir
        JButton btnSalir = crearBoton("Salir");
        btnSalir.setBounds(ancho / 2 - 100, alto - 80, 200, 40);
        fondoLabel.add(btnSalir);

        btnDificultad.addActionListener(e -> {
            Object[] opciones = {"Facil", "Media", "Difícil"};
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
                JFrame juego = new JFrame("Simulador de Trafico");
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
                sonidoMenu.detener();

            }
        });

        btnSalir.addActionListener(e -> System.exit(0));

        setVisible(true);
    }
/**
     * Crea un botón personalizado con estilo.
     * 
     * @param texto Texto del botón.
     * @return JButton estilizado.
     */
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
 /**
     * Método main alternativo para ejecutar directamente el menú.
     * 
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPrincipal::new);
    }
}



