package autonoma.semaforo.excepciones;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 26/05/25
// * @version 1.0.0

public class AtropelloException extends Exception {
    private final String mensaje;

    public AtropelloException(String personaje, String carril) {
        super("¡ATROPELLO DETECTADO!\nPersonaje: " + personaje + "\nCarril: " + carril);
        this.mensaje = "¡ATROPELLO DETECTADO!\nPersonaje: " + personaje + "\nCarril: " + carril;
    }

    public String getMensaje() {
        return mensaje;
    }
}

