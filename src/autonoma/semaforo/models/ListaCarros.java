package autonoma.semaforo.models;

/**
 * Clase que simula el comportamiento de los carros en una intersección con semáforo.
 * Se utiliza un hilo que actualiza constantemente el estado de movimiento de los carros
 * según el estado del semáforo.
 * 
 * Los carriles A y B están controlados por el semáforo AB.
 * Los carriles C y D están controlados por el semáforo CD.
 * 
 * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
 */

public class ListaCarros extends Thread {
    private boolean semaforoABVerde = true;
    private boolean semaforoCDVerde = false;
    private boolean carrilAMoviendose = true;
    private boolean carrilBMoviendose = true;
    private boolean carrilCMoviendose = false;
    private boolean carrilDMoviendose = false;
    private final int nivelDificultad;

    public ListaCarros(int nivelDificultad) {
        this.nivelDificultad = nivelDificultad;
    }

    @Override
    public void run() {
        while(true) {
        // Actualizar el estado de movimiento de cada carril
        carrilAMoviendose = semaforoABVerde;
        carrilBMoviendose = semaforoABVerde;
        carrilCMoviendose = semaforoCDVerde;
        carrilDMoviendose = semaforoCDVerde;
        
        try {
            Thread.sleep(10); // Reducir el tiempo de espera para mayor reactividad
        } catch(InterruptedException e) {
            break;
        }
    }
    }

    public boolean isCarrilAMoviendose() { return carrilAMoviendose; }
    public boolean isCarrilBMoviendose() { return carrilBMoviendose; }
    public boolean isCarrilCMoviendose() { return carrilCMoviendose; }
    public boolean isCarrilDMoviendose() { return carrilDMoviendose; }
    public boolean isSemaforoABVerde() { return semaforoABVerde; }
    public boolean isSemaforoCDVerde() { return semaforoCDVerde; }

public void setSemaforoABVerde(boolean estado) {
    this.semaforoABVerde = estado;
    // Eliminamos la línea que forzaba semaforoCDVerde a false
}

public void setSemaforoCDVerde(boolean estado) {
    this.semaforoCDVerde = estado;
    // Eliminamos la línea que forzaba semaforoABVerde a false
}
}
