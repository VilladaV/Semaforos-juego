package autonoma.semaforo.gui;

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

    private final int SEMAFORO_X_PARADA_IZQ = 100;
    private final int SEMAFORO_X_PARADA_DER = 300;
    private final int SEMAFORO_Y_PARADA_ARRIBA = 150;
    private final int SEMAFORO_Y_PARADA_ABAJO = 250;

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
    // CARRIL D (izquierda a derecha)
    if (logicaCarros.isCarrilDMoviendose() || x1 + image_icon2.getIconWidth() < SEMAFORO_X_PARADA_IZQ) {
        x1++;
    }
    if (logicaCarros.isCarrilDMoviendose() || x2 + image_icon3.getIconWidth() < SEMAFORO_X_PARADA_IZQ) {
        x2++;
    }
    if (x1 > getWidth()) x1 = -image_icon2.getIconWidth();
    if (x2 > getWidth()) x2 = -image_icon3.getIconWidth();

    // CARRIL C (derecha a izquierda)
    if (logicaCarros.isCarrilCMoviendose() || xi1 > SEMAFORO_X_PARADA_DER) {
        xi1--;
    }
    if (logicaCarros.isCarrilCMoviendose() || xi2 > SEMAFORO_X_PARADA_DER) {
        xi2--;
    }
    if (xi1 < -image_icon4.getIconWidth()) xi1 = getWidth();
    if (xi2 < -image_icon5.getIconWidth()) xi2 = getWidth();

    // CARRIL A (arriba hacia abajo)
    if (logicaCarros.isCarrilAMoviendose() || y1 + image_icon6.getIconHeight() < SEMAFORO_Y_PARADA_ARRIBA) {
        y1++;
    }
    if (logicaCarros.isCarrilAMoviendose() || y2 + image_icon7.getIconHeight() < SEMAFORO_Y_PARADA_ARRIBA) {
        y2++;
    }
    if (y1 > getHeight()) y1 = -image_icon6.getIconHeight();
    if (y2 > getHeight()) y2 = -image_icon7.getIconHeight();

    // CARRIL B (abajo hacia arriba)
    if (logicaCarros.isCarrilBMoviendose() || ya1 > SEMAFORO_Y_PARADA_ABAJO) {
        ya1--;
    }
    if (logicaCarros.isCarrilBMoviendose() || ya2 > SEMAFORO_Y_PARADA_ABAJO) {
        ya2--;
    }
    if (ya1 < -image_icon8.getIconHeight()) ya1 = getHeight();
    if (ya2 < -image_icon9.getIconHeight()) ya2 = getHeight();

    repaint();
}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image_icon1.getImage(), 0, 0, this);

        g2d.drawImage(image_icon2.getImage(), x1, 380, this);
        g2d.drawImage(image_icon3.getImage(), x2, 425, this);
        g2d.drawImage(image_icon4.getImage(), xi1, 325, this);
        g2d.drawImage(image_icon5.getImage(), xi2, 365, this);
        g2d.drawImage(image_icon6.getImage(), 454, y1, this);
        g2d.drawImage(image_icon7.getImage(), 494, y2, this);
        g2d.drawImage(image_icon8.getImage(), 555, ya1, this);
        g2d.drawImage(image_icon9.getImage(), 518, ya2, this);

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
   
