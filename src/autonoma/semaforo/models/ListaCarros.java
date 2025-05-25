/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

import java.util.Random;

public class ListaCarros extends Thread {

    private boolean semaforoABVerde = true;
    private boolean semaforoCDVerde = false;
 // true = AB verde, CD rojo; false = AB rojo, CD verde
    private Random rand = new Random();

    // Variables que indican si los carros de un carril se están moviendo
    private boolean carrilAMoviendose = true;
    private boolean carrilBMoviendose = true;
    private boolean carrilCMoviendose = false;
    private boolean carrilDMoviendose = false;

    // Puedes agregar más variables para controlar la "velocidad" o "densidad" si quieres simular eso
    // ...

   @Override
public void run() {
    while (true) {
        carrilAMoviendose = semaforoABVerde;
        carrilBMoviendose = semaforoABVerde;
        carrilCMoviendose = semaforoCDVerde;
        carrilDMoviendose = semaforoCDVerde;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            break;
        }
    }
}


    // Métodos para que la Interfaz pregunte el estado de movimiento
    public boolean isCarrilAMoviendose() {
        return carrilAMoviendose;
    }

    public boolean isCarrilBMoviendose() {
        return carrilBMoviendose;
    }

    public boolean isCarrilCMoviendose() {
        return carrilCMoviendose;
    }

    public boolean isCarrilDMoviendose() {
        return carrilDMoviendose;
    }

    // Método para cambiar el estado del semáforo desde la GUI
    public void setSemaforoABVerde(boolean estado) {
        this.semaforoABVerde = estado;
    }
    
    public boolean isSemaforoABVerde() {
    return semaforoABVerde;
}

    public boolean isSemaforoCDVerde() {
    return semaforoCDVerde;
}


    public void setSemaforoCDVerde(boolean estado) {
    this.semaforoCDVerde = estado;
}

}