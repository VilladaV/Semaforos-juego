package autonoma.semaforo.gui;

import autonoma.semaforo.models.Carro;
import autonoma.semaforo.models.ListaCarros;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Clase que representa la interfaz grafica del simulador de semaforo y carros.
 * Extiende {@code JPanel} e implementa {@code ActionListener} y {@code KeyListener}
 * para gestionar el renderizado gráfico, movimiento de los carros y entrada del teclado.
 * 
 * <p>Permite visualizar un cruce de caminos con semáforos, donde los carros avanzan
 * dependiendo del estado de los semáforos controlados por el usuario.</p>
 * 
 * <p>Teclas habilitadas:
 * <ul>
 *   <li>A: Cambia el estado del semáforo A-B</li>
 *   <li>C: Cambia el estado del semáforo C-D</li>
 * </ul></p>
 * 
 * @author: Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
 */
public class Interfaz extends JPanel implements ActionListener, KeyListener {

    // Imágenes de fondo y carros
    private String escenario = "imagenes/crucero.png";
    private ImageIcon image_icon1;

    private String carrox1 = "imagenes/carrox1.png";
    private ImageIcon image_icon2;

    private String carrox2 = "imagenes/carrox2.png";
    private ImageIcon image_icon3;

    private String carroxi1 = "imagenes/carroxi1.png";
    private ImageIcon image_icon4;

    private String carroxi2 = "imagenes/carroxi2.png";
    private ImageIcon image_icon5;

    private String carroy1 = "imagenes/carroy1.png";
    private ImageIcon image_icon6;

    private String carroy2 = "imagenes/carroy2.png";
    private ImageIcon image_icon7;

    private String carroya1 = "imagenes/carroya1.png";
    private ImageIcon image_icon8;

    private String carroya2 = "imagenes/carroya2.png";
    private ImageIcon image_icon9;

    // Posiciones iniciales de los carros
    public int x1, x2, xi1, xi2;
    public int y1, y2, ya1, ya2;

    private Timer timer;
    private ListaCarros logicaCarros;
    private List<Carro> carros;

    // Posiciones de parada según la ubicacion del semaforo
    private final int SEMAFORO_X_PARADA_IZQ = 400;
    private final int SEMAFORO_X_PARADA_DER = 675;
    private final int SEMAFORO_Y_PARADA_ARRIBA = 270;
    private final int SEMAFORO_Y_PARADA_ABAJO = 520;

    // Estados para saber si un carro ya pasó el semáforo (no usados por ahora)
    private boolean x1PasoSemaforo = false;
    private boolean x2PasoSemaforo = false;
    private boolean xi1PasoSemaforo = false;
    private boolean xi2PasoSemaforo = false;
    private boolean y1PasoSemaforo = false;
    private boolean y2PasoSemaforo = false;
    private boolean ya1PasoSemaforo = false;
    private boolean ya2PasoSemaforo = false;

    /**
     * Constructor que inicializa la interfaz gráfica, carga las imagenes, 
     * configura los carros y activa el temporizador de actualización.
     */
    public Interfaz() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        // Carga de imágenes
        image_icon1 = new ImageIcon(this.getClass().getResource(escenario));
        image_icon2 = new ImageIcon(this.getClass().getResource(carrox1));
        image_icon3 = new ImageIcon(this.getClass().getResource(carrox2));
        image_icon4 = new ImageIcon(this.getClass().getResource(carroxi1));
        image_icon5 = new ImageIcon(this.getClass().getResource(carroxi2));
        image_icon6 = new ImageIcon(this.getClass().getResource(carroy1));
        image_icon7 = new ImageIcon(this.getClass().getResource(carroy2));
        image_icon8 = new ImageIcon(this.getClass().getResource(carroya1));
        image_icon9 = new ImageIcon(this.getClass().getResource(carroya2));

        carros = new ArrayList<>();

        // Inicialización de carros en sus respectivos carriles y sentidos
        carros.add(new Carro(-100, 380, Carro.Direccion.HORIZONTAL, Carro.Sentido.POSITIVO, image_icon2, SEMAFORO_X_PARADA_IZQ, "D"));
        carros.add(new Carro(-200, 425, Carro.Direccion.HORIZONTAL, Carro.Sentido.POSITIVO, image_icon3, SEMAFORO_X_PARADA_IZQ, "D"));

        carros.add(new Carro(1180, 325, Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, image_icon4, SEMAFORO_X_PARADA_DER, "C"));
        carros.add(new Carro(1280, 365, Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, image_icon5, SEMAFORO_X_PARADA_DER, "C"));

        carros.add(new Carro(454, -100, Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, image_icon6, SEMAFORO_Y_PARADA_ARRIBA, "A"));
        carros.add(new Carro(494, -200, Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, image_icon7, SEMAFORO_Y_PARADA_ARRIBA, "A"));

        carros.add(new Carro(555, 820, Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, image_icon8, SEMAFORO_Y_PARADA_ABAJO, "B"));
        carros.add(new Carro(518, 920, Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, image_icon9, SEMAFORO_Y_PARADA_ABAJO, "B"));

        // Posiciones iniciales
        x1 = -100; x2 = -200;
        xi1 = 1180; xi2 = 1280;
        y1 = -100; y2 = -200;
        ya1 = 820; ya2 = 920;

        setLayout(null);

        logicaCarros = new ListaCarros();
        logicaCarros.start();

        timer = new Timer(10, this); // actualiza cada 10ms
        timer.start();

        addKeyListener(this);
        requestFocusInWindow();
    }

    /**
     * Metodo que se ejecuta cada vez que el temporizador se activa.
     * Actualiza la posicion de todos los carros dependiendo del estado de los semaforos.
     * 
     * @param e el evento generado por el temporizador
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (Carro carro : carros) {
            boolean semaforoVerde = false;

            switch (carro.getCarril()) {
                case "A" -> semaforoVerde = logicaCarros.isCarrilAMoviendose();
                case "B" -> semaforoVerde = logicaCarros.isCarrilBMoviendose();
                case "C" -> semaforoVerde = logicaCarros.isCarrilCMoviendose();
                case "D" -> semaforoVerde = logicaCarros.isCarrilDMoviendose();
            }

            carro.mover(semaforoVerde);
            carro.reiniciarSiSale(getWidth(), getHeight());
        }

        repaint();
    }

    /**
     * Dibuja los elementos graficos en pantalla: fondo, carros y texto informativo.
     * 
     * @param g el contexto grafico donde se realiza el dibujo
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image_icon1.getImage(), 0, 0, this);

        for (Carro carro : carros) {
            g2d.drawImage(carro.getImagen().getImage(), carro.getX(), carro.getY(), this);
        }

        // Texto de estado de los semáforos
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String estadoAB = logicaCarros.isSemaforoABVerde() ? "VERDE" : "ROJO";
        String estadoCD = logicaCarros.isSemaforoCDVerde() ? "VERDE" : "ROJO";
        g2d.drawString("Semáforo A-B: " + estadoAB + " | C-D: " + estadoCD, 20, 40);

        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Maneja las teclas presionadas. 
     * Tecla A cambia el estado del semáforo A-B.
     * Tecla C cambia el estado del semáforo C-D.
     *
     * @param e evento de teclado
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();
        if (tecla == KeyEvent.VK_A) {
            boolean nuevoEstado = !logicaCarros.isSemaforoABVerde();
            logicaCarros.setSemaforoABVerde(nuevoEstado);
            System.out.println("🔄 Cambiaste Semáforo A-B a " + (nuevoEstado ? "VERDE" : "ROJO"));
        } else if (tecla == KeyEvent.VK_C) {
            boolean nuevoEstado = !logicaCarros.isSemaforoCDVerde();
            logicaCarros.setSemaforoCDVerde(nuevoEstado);
            System.out.println("🔄 Cambiaste Semáforo C-D a " + (nuevoEstado ? "VERDE" : "ROJO"));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}

