package autonoma.semaforo.models;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

import javax.swing.ImageIcon;
import java.util.Random;

public class Arquitecto extends Personaje {

    public Arquitecto(ImageIcon imagen) {
        super(320, 410 + new Random().nextInt(20), 1, Direccion.HORIZONTAL, imagen);
    }
}
   
