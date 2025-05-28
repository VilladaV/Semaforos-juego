package autonoma.semaforo.models;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

public class Sonido {
    private Clip clip;

    public Sonido(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (Exception e) {
            System.err.println("Error al cargar sonido: " + ruta + " -> " + e.getMessage());
        }
    }

    public void reproducirUnaVez() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void reproducirEnBucle() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }
}

