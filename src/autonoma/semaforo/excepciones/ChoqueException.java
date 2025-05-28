package autonoma.semaforo.excepciones;

/**
 * Excepción personalizada que se lanza cuando dos vehículos colisionan en la intersección.
 * Guarda información sobre los carriles involucrados y la hora del choque.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class ChoqueException extends Exception {

    private final String carril1;
    private final String carril2;
    private final String horaChoque;

    /**
     * Constructor de la excepción.
     * 
     * @param carril1 Carril del primer vehículo involucrado.
     * @param carril2 Carril del segundo vehículo involucrado.
     */
    public ChoqueException(String carril1, String carril2) {
        super("¡CHOQUE DETECTADO! Entre carril " + carril1 + " y " + carril2 +
              "\nHora: " +
              java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        this.carril1 = carril1;
        this.carril2 = carril2;
        this.horaChoque =
            java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * Obtiene el carril del primer vehículo.
     * 
     * @return carril1
     */
    public String getCarril1() {
        return carril1;
    }

    /**
     * Obtiene el carril del segundo vehículo.
     * 
     * @return carril2
     */
    public String getCarril2() {
        return carril2;
    }

    /**
     * Obtiene la hora en que ocurrió el choque.
     * 
     * @return hora del choque.
     */
    public String getHoraChoque() {
        return horaChoque;
    }
}
