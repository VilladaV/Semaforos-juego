/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.excepciones;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 26/05/25
// * @version 1.0.0

public class ChoqueException extends Exception {
    private final String carril1;
    private final String carril2;
    private final String horaChoque;

    public ChoqueException(String carril1, String carril2) {
        super("¡CHOQUE DETECTADO! Entre carril " + carril1 + " y " + carril2 + 
              "\nHora: " + java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        this.carril1 = carril1;
        this.carril2 = carril2;
        this.horaChoque = java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public String getCarril1() {
        return carril1;
    }

    public String getCarril2() {
        return carril2;
    }
    
    public String getHoraChoque() {
        return horaChoque;
    }
}