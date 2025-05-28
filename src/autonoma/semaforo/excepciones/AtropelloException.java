package autonoma.semaforo.excepciones;

<<<<<<< HEAD
/**
 * Excepción personalizada que se lanza cuando un vehículo atropella a un personaje en el juego.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
=======
// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 26/05/25
// * @version 1.0.0

>>>>>>> e12b8a039c733e9f49b762de030ac063a373f33d
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


