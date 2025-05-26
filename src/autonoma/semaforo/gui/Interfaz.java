package autonoma.semaforo.gui;

import autonoma.semaforo.models.Carro;
import autonoma.semaforo.models.ListaCarros;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

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
    private ImageIcon image_icon1;
    private ImageIcon image_icon2, image_icon3, image_icon4, image_icon5, image_icon6, image_icon7, image_icon8, image_icon9;
    
     // Posiciones de parada según la ubicacion del semaforo
    private final int SEMAFORO_X_PARADA_IZQ = 400;
    private final int SEMAFORO_X_PARADA_DER = 675;
    private final int SEMAFORO_Y_PARADA_ARRIBA = 270;
    private final int SEMAFORO_Y_PARADA_ABAJO = 520;
    
    private Timer timer;
    private Timer generadorCarros;
    private ListaCarros logicaCarros;
    private List<Carro> carros;
    private int nivelDificultad;
    private Random rand;

    /**
     * Constructor que inicializa la interfaz gráfica, carga las imagenes, 
     * configura los carros y activa el temporizador de actualización.
     */
    
    public Interfaz(int nivelDificultad, ListaCarros logicaCarros) {
        this.nivelDificultad = nivelDificultad;
        this.logicaCarros = logicaCarros;
        this.rand = new Random();
        
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        carros = new ArrayList<>();
        configurarImagenes();
        configurarTemporizadores();
        
        addKeyListener(this);
        requestFocusInWindow();
    }
    // Carga de imágenes
    private void configurarImagenes() {
        image_icon1 = new ImageIcon(getClass().getResource("imagenes/crucero2.jfif"));
        image_icon2 = new ImageIcon(getClass().getResource("imagenes/carrox1.png"));
        image_icon3 = new ImageIcon(getClass().getResource("imagenes/carrox2.png"));
        image_icon4 = new ImageIcon(getClass().getResource("imagenes/carroxi1.png"));
        image_icon5 = new ImageIcon(getClass().getResource("imagenes/carroxi2.png"));
        image_icon6 = new ImageIcon(getClass().getResource("imagenes/carroy1.png"));
        image_icon7 = new ImageIcon(getClass().getResource("imagenes/carroy2.png"));
        image_icon8 = new ImageIcon(getClass().getResource("imagenes/carroya1.png"));
        image_icon9 = new ImageIcon(getClass().getResource("imagenes/carroya2.png"));
    }

    private void configurarTemporizadores() {
        timer = new Timer(10, this);
        timer.start();
        
        int frecuenciaGeneracion = switch(nivelDificultad) {
            case 1 -> 5000; // Fácil
            case 2 -> 3000; // Medio
            case 3 -> 1500; // Difícil
            default -> 3000;
        };
        
        generadorCarros = new Timer(frecuenciaGeneracion, e -> generarNuevoCarro());
        generadorCarros.start();
    }

    private void generarNuevoCarro() {
    int tipoCarro = rand.nextInt(4);
    Carro nuevoCarro = null;
    boolean posicionValida = false;
    int intentos = 0;
    
    while(!posicionValida && intentos < 20) { // Aumentamos intentos
        intentos++;
        
        switch(tipoCarro) {
            case 0: // Horizontal positivo (D)
                nuevoCarro = new Carro(
                    -100 - rand.nextInt(200), // Aleatorizar posición inicial
                    380 + rand.nextInt(50), 
                    Carro.Direccion.HORIZONTAL, 
                    Carro.Sentido.POSITIVO, 
                    rand.nextBoolean() ? image_icon2 : image_icon3, 
                    SEMAFORO_X_PARADA_IZQ, "D");
                break;
                case 1: // Horizontal negativo (C)
                    nuevoCarro = new Carro(1180, 325 + rand.nextInt(50), 
                        Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, 
                        rand.nextBoolean() ? image_icon4 : image_icon5, 
                        SEMAFORO_X_PARADA_DER, "C");
                    break;
                case 2: // Vertical positivo (A)
                    nuevoCarro = new Carro(454 + rand.nextInt(50), -100, 
                        Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, 
                        rand.nextBoolean() ? image_icon6 : image_icon7, 
                        SEMAFORO_Y_PARADA_ARRIBA, "A");
                    break;
                case 3: // Vertical negativo (B)
                    nuevoCarro = new Carro(555 + rand.nextInt(50), 820, 
                        Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, 
                        rand.nextBoolean() ? image_icon8 : image_icon9, 
                        SEMAFORO_Y_PARADA_ABAJO, "B");
                    break;
            }
            
             posicionValida = true;
        for(Carro existente : carros) {
            if(nuevoCarro != null && nuevoCarro.getBounds().intersects(existente.getBounds())) {
                posicionValida = false;
                break;
            }
        }
    }
    
    if(posicionValida && nuevoCarro != null) {
        carros.add(nuevoCarro);
    }
}
        /**
     * Metodo que se ejecuta cada vez que el temporizador se activa.
     * Actualiza la posicion de todos los carros dependiendo del estado de los semaforos.
     * 
     * @param e el evento generado por el temporizador
     */
    @Override
public void actionPerformed(ActionEvent e) {
    // Obtener estados actuales de los semáforos una sola vez
    boolean abVerde = logicaCarros.isSemaforoABVerde();
    boolean cdVerde = logicaCarros.isSemaforoCDVerde();
    
    for (Carro carro : carros) {
        boolean semaforoVerde;
        
        // Asignar correctamente el semáforo correspondiente
        switch(carro.getCarril()) {
            case "A":
            case "B":
                semaforoVerde = abVerde;
                break;
            case "C":
            case "D":
                semaforoVerde = cdVerde;
                break;
            default:
                semaforoVerde = false;
        }
        
        carro.mover(semaforoVerde, carros);
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
    Graphics2D g2d = (Graphics2D)g;
    
    // Dibujar fondo
    g2d.drawImage(image_icon1.getImage(), 0, 0, this);
    
    // Dibujar semáforos
    dibujarSemaforo(g2d, 450, 250, logicaCarros.isSemaforoABVerde()); // AB
    dibujarSemaforo(g2d, 650, 500, logicaCarros.isSemaforoCDVerde()); // CD
    
    // Dibujar autos
    for(Carro carro : carros) {
        g2d.drawImage(carro.getImagen().getImage(), carro.getX(), carro.getY(), this);
    }
    
    // Dibujar información
    g2d.setColor(Color.WHITE);
    g2d.setFont(new Font("Arial", Font.BOLD, 20));
    g2d.drawString("Semaforo AB (Tecla A): " + (logicaCarros.isSemaforoABVerde() ? "VERDE" : "ROJO"), 20, 30);
    g2d.drawString("Semaforo CD (Tecla C): " + (logicaCarros.isSemaforoCDVerde() ? "VERDE" : "ROJO"), 20, 60);
    g2d.drawString("Nivel: " + (nivelDificultad == 1 ? "FÁCIL" : nivelDificultad == 2 ? "MEDIO" : "DIFÍCIL"), 20, 90);
}

private void dibujarSemaforo(Graphics2D g2d, int x, int y, boolean verde) {
    g2d.setColor(Color.BLACK);
    g2d.fillRect(x, y, 30, 60);
    g2d.setColor(verde ? Color.GREEN : Color.RED);
    g2d.fillOval(x+5, y+5, 20, 20);
    g2d.setColor(verde ? Color.RED : Color.GREEN);
    g2d.fillOval(x+5, y+35, 20, 20);
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
    if(e.getKeyCode() == KeyEvent.VK_A) {
        boolean nuevoEstado = !logicaCarros.isSemaforoABVerde();
        logicaCarros.setSemaforoABVerde(nuevoEstado);
        System.out.println("Semaforo AB: " + (nuevoEstado ? "VERDE" : "ROJO"));
    } 
    else if(e.getKeyCode() == KeyEvent.VK_C) {
        boolean nuevoEstado = !logicaCarros.isSemaforoCDVerde();
        logicaCarros.setSemaforoCDVerde(nuevoEstado);
        System.out.println("Semaforo CD: " + (nuevoEstado ? "VERDE" : "ROJO"));
    }
    repaint(); // Forzar redibujado inmediato
}

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}