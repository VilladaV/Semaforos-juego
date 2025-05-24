/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

import java.util.Random;

public class ListaCarros extends Thread {

    public boolean semaforoABVerde = true; // true = AB verde, CD rojo; false = AB rojo, CD verde
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
            // Lógica para decidir qué carriles se mueven
            if (semaforoABVerde) {
                carrilAMoviendose = true;
                carrilBMoviendose = true;
                carrilCMoviendose = false; // Detenidos
                carrilDMoviendose = false; // Detenidos
            } else {
                carrilAMoviendose = false; // Detenidos
                carrilBMoviendose = false; // Detenidos
                carrilCMoviendose = true;
                carrilDMoviendose = true;
            }

            // Aquí agregar lógica para la densidad de carros,
            // por ejemplo, generando nuevos carros o quitándolos
            // CarrilA += rand.nextInt(5);
            // ...

            try {
                // Hacemos que este hilo "duerma" un poco para no consumir CPU inútilmente
                // y permitir que el Timer de la GUI tenga tiempo de reaccionar.
                Thread.sleep(100); //
            } catch (InterruptedException e) {
                System.out.println("Excepción en ListaCarros: " + e.getMessage());
                break; // Salir del bucle si el hilo es interrumpido
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
}