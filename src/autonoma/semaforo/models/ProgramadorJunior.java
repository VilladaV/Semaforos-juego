package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.util.Random;

/**
 * Clase que representa un personaje del tipo Programador Junior.
 * Este personaje se mueve en dirección vertical desde la parte inferior de la pantalla hacia arriba.
 * Hereda de la clase {@link Personaje}.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class ProgramadorJunior extends Personaje {

    /**
     * Constructor que inicializa la posición aleatoria y la imagen del programador junior.
     * 
     * @param imagen Imagen asociada al personaje.
     */
    public ProgramadorJunior(ImageIcon imagen) {
        super(320 + new Random().nextInt(40), 800, 1, Direccion.VERTICAL, imagen);
    }
}
