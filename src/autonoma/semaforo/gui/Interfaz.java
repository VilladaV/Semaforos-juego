// Archivo: Interfaz.java
package autonoma.semaforo.gui;

import autonoma.semaforo.excepciones.AtropelloException;
import autonoma.semaforo.excepciones.ChoqueException;
import autonoma.semaforo.models.Arquitecto;
import autonoma.semaforo.models.CampoDeBatalla;
import autonoma.semaforo.models.Carro;
import autonoma.semaforo.models.ListaCarros;
import autonoma.semaforo.models.Personaje;
import autonoma.semaforo.models.ProgramadorJunior;
import autonoma.semaforo.models.Sonido;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Interfaz extends JPanel implements ActionListener, KeyListener {
    private ImageIcon image_icon1;
    private ImageIcon image_icon2, image_icon3, image_icon4, image_icon5, image_icon6, image_icon7, image_icon8, image_icon9;

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
    private int puntaje;
    private int mejorPuntaje;
    private Timer timerPuntaje;

    private CampoDeBatalla campoPersonajes;
    private Timer generadorPeatones;
    private ImageIcon imgJunior, imgArquitecto;

    private Sonido sonidoJuego;
    private Sonido sonidoChoque;

    private static final String ARCHIVO_PUNTAJE = "mejor_puntaje.txt";

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

        this.puntaje = 0;
        this.mejorPuntaje = cargarMejorPuntaje();
        iniciarTimerPuntaje();

        campoPersonajes = new CampoDeBatalla();

        imgJunior = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/Programador1.png"));
        imgArquitecto = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/Arquitecto.png"));

        generadorPeatones = new Timer(9000, e -> {
            if (rand.nextBoolean()) {
                campoPersonajes.agregarPersonaje(new ProgramadorJunior(imgJunior));
            } else {
                campoPersonajes.agregarPersonaje(new Arquitecto(imgArquitecto));
            }
        });
        generadorPeatones.start();

        sonidoJuego = new Sonido("/autonoma/semaforo/gui/sonidos/trafico.wav");
        sonidoJuego.reproducirEnBucle();

        sonidoChoque = new Sonido("/autonoma/semaforo/gui/sonidos/choque.wav");
    }

    private void configurarImagenes() {
        image_icon1 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/crucero.png"));
        image_icon2 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carrox1.png"));
        image_icon3 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carrox2.png"));
        image_icon4 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroxi1.png"));
        image_icon5 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroxi2.png"));
        image_icon6 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroy1.png"));
        image_icon7 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroy2.png"));
        image_icon8 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroya1.png"));
        image_icon9 = new ImageIcon(getClass().getResource("/autonoma/semaforo/gui/imagenes/carroya2.png"));
    }

    private void configurarTemporizadores() {
        timer = new Timer(10, this);
        timer.start();

        int frecuenciaGeneracion = switch (nivelDificultad) {
            case 1 -> 5000;
            case 2 -> 3000;
            case 3 -> 1500;
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

    while (!posicionValida && intentos < 20) {
        intentos++;

        switch (tipoCarro) {
            case 0 -> nuevoCarro = new Carro(-100 - rand.nextInt(200), 380 + rand.nextInt(50), 
                Carro.Direccion.HORIZONTAL, Carro.Sentido.POSITIVO, 
                rand.nextBoolean() ? image_icon2 : image_icon3, 
                SEMAFORO_X_PARADA_IZQ, "D", nivelDificultad);
            case 1 -> nuevoCarro = new Carro(1180, 325 + rand.nextInt(50), 
                Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, 
                rand.nextBoolean() ? image_icon4 : image_icon5, 
                SEMAFORO_X_PARADA_DER, "C", nivelDificultad);
            case 2 -> nuevoCarro = new Carro(454 + rand.nextInt(50), -100, 
                Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, 
                rand.nextBoolean() ? image_icon6 : image_icon7, 
                SEMAFORO_Y_PARADA_ARRIBA, "A", nivelDificultad);
            case 3 -> nuevoCarro = new Carro(505 + rand.nextInt(50), 820, 
                Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, 
                rand.nextBoolean() ? image_icon8 : image_icon9, 
                SEMAFORO_Y_PARADA_ABAJO, "B", nivelDificultad);
        }

        posicionValida = true;
        for (Carro existente : carros) {
            if (nuevoCarro != null && nuevoCarro.getBounds().intersects(existente.getBounds())) {
                posicionValida = false;
                break;
            }
        }
    }

    if (posicionValida && nuevoCarro != null) {
        carros.add(nuevoCarro);
    }
}
    

    private void verificarAtropellos() throws AtropelloException {
        for (Carro carro : carros) {
            Rectangle rCarro = carro.getBounds();
            for (Personaje p : campoPersonajes.getPersonajes()) {
                if (rCarro.intersects(p.getBounds())) {
                    throw new AtropelloException(p.getClass().getSimpleName(), carro.getCarril());
                }
            }
        }
    }

    private boolean verificarColisionesEnCruce() throws ChoqueException {
        Rectangle areaCruce = new Rectangle(400, 270, 275, 250);

        for (int i = 0; i < carros.size(); i++) {
            for (int j = i + 1; j < carros.size(); j++) {
                Carro carro1 = carros.get(i);
                Carro carro2 = carros.get(j);

                if (carro1.getDireccion() != carro2.getDireccion()) {
                    if (areaCruce.contains(carro1.getBounds()) &&
                        areaCruce.contains(carro2.getBounds()) &&
                        carro1.getBounds().intersects(carro2.getBounds())) {
                        throw new ChoqueException(carro1.getCarril(), carro2.getCarril());
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean abVerde = logicaCarros.isSemaforoABVerde();
        boolean cdVerde = logicaCarros.isSemaforoCDVerde();

        try {
            for (Carro carro : carros) {
                boolean semaforoVerde;

                switch (carro.getCarril()) {
                    case "A", "B" -> semaforoVerde = abVerde;
                    case "C", "D" -> semaforoVerde = cdVerde;
                    default -> semaforoVerde = false;
                }

                carro.mover(semaforoVerde, carros);
                carro.reiniciarSiSale(getWidth(), getHeight());
            }

            verificarColisionesEnCruce();
            verificarAtropellos();

        } catch (ChoqueException | AtropelloException ex) {
            terminarJuegoConExcepcion(ex.getMessage());
        }

        campoPersonajes.actualizar();
        repaint();
    }

    private void iniciarTimerPuntaje() {
        timerPuntaje = new Timer(1000, e -> {
            puntaje += 2;
            repaint();
        });
        timerPuntaje.start();
    }

    private int cargarMejorPuntaje() {
        try (Scanner s = new Scanner(new File(ARCHIVO_PUNTAJE))) {
            return s.hasNextInt() ? s.nextInt() : 0;
        } catch (Exception e) {
            return 0;
        }
    }

    private void guardarMejorPuntaje() {
        try (PrintWriter w = new PrintWriter(ARCHIVO_PUNTAJE)) {
            w.print(mejorPuntaje);
        } catch (Exception ignored) {}
    }

    private void terminarJuego() {
        timer.stop();
        generadorCarros.stop();
        timerPuntaje.stop();
        sonidoJuego.detener();
        sonidoChoque.reproducirUnaVez();

        if (puntaje > mejorPuntaje) {
            mejorPuntaje = puntaje;
            guardarMejorPuntaje();
        }

        JOptionPane.showMessageDialog(this, "¡Colisión!\nPuntaje: " + puntaje +
                "\nMejor puntaje: " + mejorPuntaje, "Fin del juego", JOptionPane.ERROR_MESSAGE);

        reiniciarJuego();
    }

    private void terminarJuegoConExcepcion(String mensaje) {
        timer.stop();
        generadorCarros.stop();
        timerPuntaje.stop();
        generadorPeatones.stop();
        sonidoJuego.detener();
        sonidoChoque.reproducirUnaVez();

        if (puntaje > mejorPuntaje) {
            mejorPuntaje = puntaje;
            guardarMejorPuntaje();
        }

        JOptionPane.showMessageDialog(this,
                mensaje + "\nPuntaje: " + puntaje +
                "\nMejor puntaje: " + mejorPuntaje,
                "Fin del juego",
                JOptionPane.ERROR_MESSAGE);

        reiniciarJuego();
    }

    private void reiniciarJuego() {
        carros.clear();
        puntaje = 0;
        timer.start();
        generadorCarros.start();
        timerPuntaje.start();
        requestFocusInWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image_icon1.getImage(), 0, 0, this);
        dibujarSemaforo(g2d, 400, 250, logicaCarros.isSemaforoABVerde());
        dibujarSemaforo(g2d, 650, 500, logicaCarros.isSemaforoCDVerde());

        for (Carro carro : carros) {
            g2d.drawImage(carro.getImagen().getImage(), carro.getX(), carro.getY(), this);
        }

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Puntaje: " + puntaje, 20, 120);
        g2d.drawString("Mejor: " + mejorPuntaje, 20, 150);
        g2d.drawString("Semáforo AB (A): " + (logicaCarros.isSemaforoABVerde() ? "Verde" : "Rojo"), 20, 30);
        g2d.drawString("Semáforo CD (C): " + (logicaCarros.isSemaforoCDVerde() ? "Verde" : "Rojo"), 20, 60);
        g2d.drawString("Nivel: " + (nivelDificultad == 3 ? "😈 Caos" : nivelDificultad == 2 ? "Medio" : "Fácil"), 20, 90);

        campoPersonajes.dibujar(g);
    }

    private void dibujarSemaforo(Graphics2D g2d, int x, int y, boolean verde) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, 30, 60);
        g2d.setColor(verde ? Color.GREEN : Color.RED);
        g2d.fillOval(x + 5, y + 5, 20, 20);
        g2d.setColor(verde ? Color.RED : Color.GREEN);
        g2d.fillOval(x + 5, y + 35, 20, 20);
    }

    @Override public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A -> logicaCarros.setSemaforoABVerde(!logicaCarros.isSemaforoABVerde());
            case KeyEvent.VK_C -> logicaCarros.setSemaforoCDVerde(!logicaCarros.isSemaforoCDVerde());
            case KeyEvent.VK_R -> reiniciarJuego();
        }
        repaint();
    }

    @Override public void keyTyped(KeyEvent e) {}
    @Override public void keyReleased(KeyEvent e) {}
}
