package autonoma.semaforo.models;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que representa el campo donde se mueven los personajes.
 * Se encarga de gestionar su actualización, dibujo y control de límites de pantalla.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class CampoDeBatalla {
    
    private final List<Personaje> personajes;

    /**
     * Constructor que inicializa la lista de personajes.
     */
    public CampoDeBatalla() {
        personajes = new ArrayList<>();
    }

    /**
     * Agrega un personaje al campo de batalla.
     * 
     * @param p Personaje a agregar.
     */
    public void agregarPersonaje(Personaje p) {
        personajes.add(p);
    }

    /**
     * Actualiza el estado de todos los personajes.
     * Mueve los personajes y elimina aquellos que salen de la pantalla.
     */
    public void actualizar() {
        Iterator<Personaje> it = personajes.iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            p.mover();
            if (p.getY() < -50 || p.getX() > 1200) {
                it.remove();
            }
        }
    }

    /**
     * Dibuja todos los personajes en el gráfico recibido.
     * 
     * @param g Objeto gráfico para dibujar.
     */
    public void dibujar(Graphics g) {
        for (Personaje p : personajes) {
            p.dibujar(g);
        }
    }

    /**
     * Obtiene la lista de personajes actualmente activos en el campo.
     * 
     * @return Lista de personajes.
     */
    public List<Personaje> getPersonajes() {
        return personajes;
    }
}
