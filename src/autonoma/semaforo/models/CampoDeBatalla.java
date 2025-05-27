package autonoma.semaforo.models;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CampoDeBatalla {
    private final List<Personaje> personajes;

    public CampoDeBatalla() {
        personajes = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje p) {
        personajes.add(p);
    }

    public void actualizar() {
        Iterator<Personaje> it = personajes.iterator();
        while (it.hasNext()) {
            Personaje p = it.next();
            p.mover();
            if (p.getY() < -50 || p.getX() > 1200) { // Sale de pantalla
                it.remove();
            }
        }
    }

    public void dibujar(Graphics g) {
        for (Personaje p : personajes) {
            p.dibujar(g);
        }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}

