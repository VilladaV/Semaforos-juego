package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.util.Random;

public class ProgramadorJunior extends Personaje {

    public ProgramadorJunior(ImageIcon imagen) {
        super(320 + new Random().nextInt(40), 800, 1, Direccion.VERTICAL, imagen);
    }
}
