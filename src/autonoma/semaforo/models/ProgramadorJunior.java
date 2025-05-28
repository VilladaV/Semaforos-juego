package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.util.Random;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

public class ProgramadorJunior extends Personaje {

    public ProgramadorJunior(ImageIcon imagen) {
        super(320 + new Random().nextInt(40), 800, 1, Direccion.VERTICAL, imagen);
    }
}
