package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Personaje {
    protected int x, y;
    protected int velocidad;
    protected ImageIcon imagen;
    protected Direccion direccion;

    public enum Direccion {
        VERTICAL, HORIZONTAL
    }

    public Personaje(int x, int y, int velocidad, Direccion direccion, ImageIcon imagen) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.direccion = direccion;
        this.imagen = imagen;
    }

    public void mover() {
        if (direccion == Direccion.VERTICAL) {
            y += velocidad;
        } else {
            x += velocidad;
        }
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen.getImage(), x, y, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, imagen.getIconWidth(), imagen.getIconHeight());
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getImagen() { return imagen; }
}
