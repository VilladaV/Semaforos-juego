package autonoma.semaforo.models;

/**
 * Clase que simula el comportamiento de los carros en una intersección con semáforo.
 * Se utiliza un hilo que actualiza constantemente el estado de movimiento de los carros
 * según el estado del semáforo.
 * 
 * Los carriles A y B están controlados por el semáforo AB.
 * Los carriles C y D están controlados por el semáforo CD.
 * 
 * @author 
 *     Juan Esteban - Isabela - Jhojan
 */
public class ListaCarros extends Thread {

    private boolean semaforoABVerde = true;
    private boolean semaforoCDVerde = false;
    private boolean carrilAMoviendose = true;
    private boolean carrilBMoviendose = true;
    private boolean carrilCMoviendose = false;
    private boolean carrilDMoviendose = false;
    private final int nivelDificultad;

    /**
     * Constructor que recibe el nivel de dificultad seleccionado.
     * 
     * @param nivelDificultad Nivel de dificultad del juego.
     */
    public ListaCarros(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    /**
     * Método que ejecuta el hilo. Actualiza constantemente los estados de movimiento
     * de los carriles según el color del semáforo.
     */
    @Override
    public void run() {
        while (true) {
            carrilAMoviendose = semaforoABVerde;
            carrilBMoviendose = semaforoABVerde;
            carrilCMoviendose = semaforoCDVerde;
            carrilDMoviendose = semaforoCDVerde;

            try {
                Thread.sleep(10); // Tiempo de espera entre actualizaciones
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    /**
     * Indica si el carril A está en movimiento.
     * @return true si está en movimiento, false si no.
     */
    public boolean isCarrilAMoviendose() { return carrilAMoviendose; }

    /**
     * Indica si el carril B está en movimiento.
     * @return true si está en movimiento, false si no.
     */
    public boolean isCarrilBMoviendose() { return carrilBMoviendose; }

    /**
     * Indica si el carril C está en movimiento.
     * @return true si está en movimiento, false si no.
     */
    public boolean isCarrilCMoviendose() { return carrilCMoviendose; }

    /**
     * Indica si el carril D está en movimiento.
     * @return true si está en movimiento, false si no.
     */
    public boolean isCarrilDMoviendose() { return carrilDMoviendose; }

    /**
     * Devuelve el estado actual del semáforo AB.
     * @return true si está en verde, false si está en rojo.
     */
    public boolean isSemaforoABVerde() { return semaforoABVerde; }

    /**
     * Devuelve el estado actual del semáforo CD.
     * @return true si está en verde, false si está en rojo.
     */
    public boolean isSemaforoCDVerde() { return semaforoCDVerde; }

    /**
     * Cambia el estado del semáforo AB.
     * 
     * @param estado Nuevo estado (true para verde, false para rojo).
     */
    public void setSemaforoABVerde(boolean estado) {
        this.semaforoABVerde = estado;
    }

    /**
     * Cambia el estado del semáforo CD.
     * 
     * @param estado Nuevo estado (true para verde, false para rojo).
     */
    public void setSemaforoCDVerde(boolean estado) {
        this.semaforoCDVerde = estado;
    }
}
