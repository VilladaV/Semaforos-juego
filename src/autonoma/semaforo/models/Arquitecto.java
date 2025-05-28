package autonoma.semaforo.models;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

import javax.swing.ImageIcon;
import java.util.Random;
/**
 * Clase que representa un personaje del tipo Arquitecto.
 * Este personaje se mueve de forma horizontal sobre la pantalla.
 * Extiende de la clase base {@link Personaje}.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class Arquitecto extends Personaje {
 /**
     * Constructor que inicializa la posición y la imagen del arquitecto.
     * 
     * @param imagen Imagen asociada al personaje arquitecto.
     */
    public Arquitecto(ImageIcon imagen) {
        super(320, 410 + new Random().nextInt(20), 1, Direccion.HORIZONTAL, imagen);
    }
}
   
