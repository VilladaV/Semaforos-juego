package autonoma.semaforo.gui;

import autonoma.semaforo.models.Carro;
import java.awt.*;
import javax.swing.*;

import autonoma.semaforo.models.ListaCarros;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Interfaz extends JPanel implements ActionListener, KeyListener {

    private String escenario = "imagenes/crucero.png";
    ImageIcon image_icon1;

    private String carrox1 = "imagenes/carrox1.png";
    ImageIcon image_icon2;

    private String carrox2 = "imagenes/carrox2.png";
    ImageIcon image_icon3;

    private String carroxi1 = "imagenes/carroxi1.png";
    ImageIcon image_icon4;

    private String carroxi2 = "imagenes/carroxi2.png";
    ImageIcon image_icon5;

    private String carroy1 = "imagenes/carroy1.png";
    ImageIcon image_icon6;

    private String carroy2 = "imagenes/carroy2.png";
    ImageIcon image_icon7;

    private String carroya1 = "imagenes/carroya1.png";
    ImageIcon image_icon8;

    private String carroya2 = "imagenes/carroya2.png";
    ImageIcon image_icon9;

    public int x1, x2, xi1, xi2;
    public int y1, y2, ya1, ya2;

    private Timer timer;
    private ListaCarros logicaCarros;
    private java.util.List<Carro> carros;


    private final int SEMAFORO_X_PARADA_IZQ = 400;
    private final int SEMAFORO_X_PARADA_DER = 675;
    private final int SEMAFORO_Y_PARADA_ARRIBA = 270;
    private final int SEMAFORO_Y_PARADA_ABAJO = 520;
    
    private boolean x1PasoSemaforo = false;
    private boolean x2PasoSemaforo = false;
    private boolean xi1PasoSemaforo = false;
    private boolean xi2PasoSemaforo = false;
    private boolean y1PasoSemaforo = false;
    private boolean y2PasoSemaforo = false;
    private boolean ya1PasoSemaforo = false;
    private boolean ya2PasoSemaforo = false;


    public Interfaz() {
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);

        image_icon1 = new ImageIcon(this.getClass().getResource(escenario));
        image_icon2 = new ImageIcon(this.getClass().getResource(carrox1));
        image_icon3 = new ImageIcon(this.getClass().getResource(carrox2));
        image_icon4 = new ImageIcon(this.getClass().getResource(carroxi1));
        image_icon5 = new ImageIcon(this.getClass().getResource(carroxi2));
        image_icon6 = new ImageIcon(this.getClass().getResource(carroy1));
        image_icon7 = new ImageIcon(this.getClass().getResource(carroy2));
        image_icon8 = new ImageIcon(this.getClass().getResource(carroya1));
        image_icon9 = new ImageIcon(this.getClass().getResource(carroya2));
        
        carros = new java.util.ArrayList<>();

        // Carril D (izquierda a derecha)
        carros.add(new Carro(-100, 380, Carro.Direccion.HORIZONTAL, Carro.Sentido.POSITIVO, image_icon2, SEMAFORO_X_PARADA_IZQ, "D"));
        carros.add(new Carro(-200, 425, Carro.Direccion.HORIZONTAL, Carro.Sentido.POSITIVO, image_icon3, SEMAFORO_X_PARADA_IZQ, "D"));

        // Carril C (derecha a izquierda)
        carros.add(new Carro(1080 + 100, 325, Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, image_icon4, SEMAFORO_X_PARADA_DER, "C"));
        carros.add(new Carro(1080 + 200, 365, Carro.Direccion.HORIZONTAL, Carro.Sentido.NEGATIVO, image_icon5, SEMAFORO_X_PARADA_DER, "C"));

        // Carril A (arriba hacia abajo)
        carros.add(new Carro(454, -100, Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, image_icon6, SEMAFORO_Y_PARADA_ARRIBA, "A"));
        carros.add(new Carro(494, -200, Carro.Direccion.VERTICAL, Carro.Sentido.POSITIVO, image_icon7, SEMAFORO_Y_PARADA_ARRIBA, "A"));

        // Carril B (abajo hacia arriba)
        carros.add(new Carro(555, 720 + 100, Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, image_icon8, SEMAFORO_Y_PARADA_ABAJO, "B"));
        carros.add(new Carro(518, 720 + 200, Carro.Direccion.VERTICAL, Carro.Sentido.NEGATIVO, image_icon9, SEMAFORO_Y_PARADA_ABAJO, "B"));


        x1 = -100;
        x2 = -200;
        xi1 = 1080 + 100;
        xi2 = 1080 + 200;
        y1 = -100;
        y2 = -200;
        ya1 = 720 + 100;
        ya2 = 720 + 200;

        setLayout(null);

        logicaCarros = new ListaCarros();
        logicaCarros.start();

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        requestFocusInWindow();
    }

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




    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image_icon1.getImage(), 0, 0, this);

        for (Carro carro : carros) {
    g2d.drawImage(carro.getImagen().getImage(), carro.getX(), carro.getY(), this);
}

        // Mostrar texto en pantalla
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String estadoAB = logicaCarros.isSemaforoABVerde() ? "VERDE" : "ROJO";
        String estadoCD = logicaCarros.isSemaforoCDVerde() ? "VERDE" : "ROJO";
        g2d.drawString("Semáforo A-B: " + estadoAB + " | C-D: " + estadoCD, 20, 40);


        Toolkit.getDefaultToolkit().sync();
    }

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
   
