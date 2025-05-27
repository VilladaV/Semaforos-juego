package autonoma.semaforo.excepciones;

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

