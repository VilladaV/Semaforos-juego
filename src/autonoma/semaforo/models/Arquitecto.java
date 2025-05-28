package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.util.Random;

public class Arquitecto extends Personaje {

    public Arquitecto(ImageIcon imagen) {
        super(320, 410 + new Random().nextInt(20), 1, Direccion.HORIZONTAL, imagen);
    }
}
   
