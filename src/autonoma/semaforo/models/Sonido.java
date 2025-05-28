package autonoma.semaforo.models;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * Clase encargada de reproducir sonidos dentro del simulador de tráfico.
 * Permite reproducir sonidos una sola vez o en bucle, así como detenerlos.
 * Utiliza la API de sonido de Java (javax.sound.sampled).
 * 
 * Esta clase se utiliza para efectos como el sonido de ciudad o de colisión.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class Sonido {

    private Clip clip;

    /**
     * Constructor que carga el sonido desde la ruta proporcionada.
     * 
     * @param ruta Ruta relativa del archivo de sonido dentro del proyecto.
     */
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

    /**
     * Reproduce el sonido una sola vez desde el inicio.
     */
    public void reproducirUnaVez() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    /**
     * Reproduce el sonido en bucle indefinidamente.
     */
    public void reproducirEnBucle() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * Detiene la reproducción del sonido actual.
     */
    public void detener() {
        if (clip != null) {
            clip.stop();
        }
    }
}
