package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Clase base que representa un personaje en el simulador.
 * Un personaje tiene una posición, velocidad, dirección y una imagen asociada.
 * Puede moverse y dibujarse sobre la interfaz gráfica.
 * Es extendida por clases como {@link ProgramadorJunior} y {@link Arquitecto}.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class Personaje {

    protected int x, y;
    protected int velocidad;
    protected ImageIcon imagen;
    protected Direccion direccion;

    /**
     * Enumeración que define la dirección de movimiento del personaje.
     */
    public enum Direccion {
        VERTICAL, HORIZONTAL
    }

    /**
     * Constructor de la clase Personaje.
     * 
     * @param x         Posición horizontal inicial.
     * @param y         Posición vertical inicial.
     * @param velocidad Velocidad de movimiento.
     * @param direccion Dirección del movimiento (vertical u horizontal).
     * @param imagen    Imagen gráfica del personaje.
     */
    public Personaje(int x, int y, int velocidad, Direccion direccion, ImageIcon imagen) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    /**
     * Mueve el personaje una unidad en su dirección asignada.
     */
    public void mover() {
        if (direccion == Direccion.VERTICAL) {
            y += velocidad;
        } else {
            x += velocidad;
        }
    }

    /**
     * Dibuja el personaje en la pantalla usando el objeto gráfico.
     * 
     * @param g Objeto gráfico donde se dibuja la imagen.
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen.getImage(), x, y, null);
    }

    /**
     * Retorna los límites del personaje como un rectángulo.
     * Usado para detectar colisiones.
     * 
     * @return Objeto Rectangle con el área ocupada por el personaje.
     */
    public Rectangle getBounds() {
        return new Rectangle(x, y, imagen.getIconWidth(), imagen.getIconHeight());
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public ImageIcon getImagen() { return imagen; }
}
