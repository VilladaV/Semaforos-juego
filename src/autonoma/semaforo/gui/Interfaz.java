/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;

import autonoma.semaforo.models.ListaCarros; // Importamos nuestra clase de lógica

public class Interfaz extends JPanel implements ActionListener {
    private JButton botonAB, botonCD;

    // ... (tus variables de imágenes y coordenadas)
    private String escenario = "imagenes/crucero.png";
    ImageIcon image_icon1;
    private String carrox1="imagenes/carrox1.png";
    ImageIcon image_icon2;
    private String carrox2="imagenes/carrox2.png";
    ImageIcon image_icon3;
    private String carroxi1="imagenes/carroxi1.png";
    ImageIcon image_icon4;
    private String carroxi2="imagenes/carroxi2.png";
    ImageIcon image_icon5;
    private String carroy1="imagenes/carroy1.png";
    ImageIcon image_icon6;
    private String carroy2="imagenes/carroy2.png";
    ImageIcon image_icon7;
    private String carroya1="imagenes/carroya1.png";
    ImageIcon image_icon8;
    private String carroya2="imagenes/carroya2.png";
    ImageIcon image_icon9;

    public int x1, x2, xi1, xi2;
    public int y1, y2, ya1, ya2;

    private Timer timer;
    private ListaCarros logicaCarros; // Instancia de nuestra lógica de hilos

    // Coordenadas de los semáforos
    private final int SEMAFORO_X_PARADA_IZQ = 100; //dónde parar en X para carros que vienen de izq
    private final int SEMAFORO_X_PARADA_DER = 300; //dónde parar en X para carros que vienen de der
    private final int SEMAFORO_Y_PARADA_ARRIBA = 150; //dónde parar en Y para carros que vienen de arriba
    private final int SEMAFORO_Y_PARADA_ABAJO = 250; //dónde parar en Y para carros que vienen de abajo


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

        // Posiciones iniciales
        x1 = -100; // Empiezan fuera de pantalla a la izquierda
        x2 = -200;
        xi1 = getWidth() + 100; // Empiezan fuera de pantalla a la derecha (si getWidth() está disponible, sino un valor fijo)
        xi2 = getWidth() + 200;
        y1 = -100; // Empiezan fuera de pantalla arriba
        y2 = -200;
        ya1 = getHeight() + 100; // Empiezan fuera de pantalla abajo
        ya2 = getHeight() + 200;


        setLayout(null); // Para posición absoluta de los botones

        logicaCarros = new ListaCarros(); // Instanciamos la lógica
        logicaCarros.start(); // Iniciamos el hilo de la lógica

        botonAB = new JButton("Semáforo A-B Verde");
        botonAB.setBounds(850, 50, 200, 30);
        botonAB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logicaCarros.setSemaforoABVerde(true); // Le decimos a la lógica que A-B está en verde
            }
        });
        add(botonAB);

        botonCD = new JButton("Semáforo C-D Verde");
        botonCD.setBounds(850, 100, 200, 30);
        botonCD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logicaCarros.setSemaforoABVerde(false); // Le decimos a la lógica que C-D está en verde
            }
        });
        add(botonCD);

        timer = new Timer(10, this); // Dispara cada 10 milisegundos
        timer.start();
    }

    // Este es el método que se llama cada vez que el Timer "dispara"
    @Override
    public void actionPerformed(ActionEvent e) {
        // Solo movemos los carros si la lógica nos dice que su carril está en verde
        if (logicaCarros.isCarrilDMoviendose()) { // 
            x1++;
            x2++;
            // Lógica para que el carro reaparezca al salir de la pantalla
            if (x1 > getWidth()) x1 = -image_icon2.getIconWidth();
            if (x2 > getWidth()) x2 = -image_icon3.getIconWidth();
        } else {
            // Si el semáforo está en rojo para D, los carros se detienen en la posición de semáforo
            if (x1 < SEMAFORO_X_PARADA_IZQ) x1 = SEMAFORO_X_PARADA_IZQ;
            if (x2 < SEMAFORO_X_PARADA_IZQ) x2 = SEMAFORO_X_PARADA_IZQ;
        }

        if (logicaCarros.isCarrilCMoviendose()) { // Asumo que carroxi1 y carroxi2 son Carril C (izquierda)
            xi1--;
            xi2--;
            if (xi1 < -image_icon4.getIconWidth()) xi1 = getWidth();
            if (xi2 < -image_icon5.getIconWidth()) xi2 = getWidth();
        } else {
            if (xi1 > SEMAFORO_X_PARADA_DER) xi1 = SEMAFORO_X_PARADA_DER;
            if (xi2 > SEMAFORO_X_PARADA_DER) xi2 = SEMAFORO_X_PARADA_DER;
        }

        if (logicaCarros.isCarrilAMoviendose()) { // Asumo que carroy1 y carroy2 son Carril A (arriba)
            y1++;
            y2++;
            if (y1 > getHeight()) y1 = -image_icon6.getIconHeight();
            if (y2 > getHeight()) y2 = -image_icon7.getIconHeight();
        } else {
            if (y1 < SEMAFORO_Y_PARADA_ARRIBA) y1 = SEMAFORO_Y_PARADA_ARRIBA;
            if (y2 < SEMAFORO_Y_PARADA_ARRIBA) y2 = SEMAFORO_Y_PARADA_ARRIBA;
        }

        if (logicaCarros.isCarrilBMoviendose()) { // Asumo que carroya1 y carroya2 son Carril B (abajo)
            ya1--;
            ya2--;
            if (ya1 < -image_icon8.getIconHeight()) ya1 = getHeight();
            if (ya2 < -image_icon9.getIconHeight()) ya2 = getHeight();
        } else {
            if (ya1 > SEMAFORO_Y_PARADA_ABAJO) ya1 = SEMAFORO_Y_PARADA_ABAJO;
            if (ya2 > SEMAFORO_Y_PARADA_ABAJO) ya2 = SEMAFORO_Y_PARADA_ABAJO;
        }

        repaint(); // Llama a repaint para que se redibujen con las nuevas posiciones
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(image_icon1.getImage(), 0, 0, this);

        // Dibuja los carros con sus coordenadas actuales
        g2d.drawImage(image_icon2.getImage(), x1, 380, this); // carril D ->
        g2d.drawImage(image_icon3.getImage(), x2, 425, this); // carril D ->
        g2d.drawImage(image_icon4.getImage(), xi1, 325, this); // carril C <-
        g2d.drawImage(image_icon5.getImage(), xi2, 365, this); // carril C <-
        g2d.drawImage(image_icon6.getImage(), 454, y1, this); // carril A
        g2d.drawImage(image_icon7.getImage(), 494, y2, this); // carril A
        g2d.drawImage(image_icon8.getImage(), 555, ya1, this); // carril B
        g2d.drawImage(image_icon9.getImage(), 518, ya2, this); // carril B

        Toolkit.getDefaultToolkit().sync();
    }

    // NOTA: Es crucial que el JFrame principal establezca el tamaño de la Interfaz
    // antes de que se use `getWidth()` o `getHeight()` en el constructor de Interfaz
    // para las posiciones iniciales de los carros.
    // Podrías inicializar estas posiciones después de que el panel tenga un tamaño,
    // por ejemplo, en un método `initCarPositions()` que se llame desde el JFrame
    // o después de añadir el panel al frame y hacer `pack()` o `setSize()`.
}