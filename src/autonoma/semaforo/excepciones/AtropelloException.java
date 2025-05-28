package autonoma.semaforo.excepciones;

/**
 * Excepción personalizada que se lanza cuando un vehículo atropella a un personaje en el juego.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class AtropelloException extends Exception {

    private final String mensaje;

    /**
     * Constructor de la excepción.
     * 
     * @param personaje El tipo de personaje atropellado.
     * @param carril    El carril donde ocurrió el atropello.
     */
    public AtropelloException(String personaje, String carril) {
        super("¡ATROPELLO DETECTADO!\nPersonaje: " + personaje + "\nCarril: " + carril);
        this.mensaje = "¡ATROPELLO DETECTADO!\nPersonaje: " + personaje + "\nCarril: " + carril;
    }

    /**
     * Obtiene el mensaje de error personalizado.
     * 
     * @return mensaje del atropello.
     */
    public String getMensaje() {
        return mensaje;
    }
}
