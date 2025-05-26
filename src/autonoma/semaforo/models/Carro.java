/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

import javax.swing.ImageIcon;

public class Carro {
    public enum Direccion { HORIZONTAL, VERTICAL }
    public enum Sentido { POSITIVO, NEGATIVO }

    private int x, y;
    private final Direccion direccion;
    private final Sentido sentido;
    private final ImageIcon imagen;
    private final int puntoParada;
    private boolean haPasadoSemaforo;
    private final String carril; // A, B, C o D

    public Carro(int x, int y, Direccion direccion, Sentido sentido, ImageIcon imagen, int puntoParada, String carril) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.sentido = sentido;
        this.imagen = imagen;
        this.puntoParada = puntoParada;
        this.haPasadoSemaforo = false;
        this.carril = carril;
    }

   public void mover(boolean semaforoEnVerde) {
    // Determinar la posición del "frente" del carro
    int frente;
    int tamanoCarro;

    if (direccion == Direccion.HORIZONTAL) {
        tamanoCarro = imagen.getIconWidth();
        frente = (sentido == Sentido.POSITIVO) ? x + tamanoCarro : x;
    } else { // Direccion.VERTICAL
        tamanoCarro = imagen.getIconHeight();
        frente = (sentido == Sentido.POSITIVO) ? y + tamanoCarro : y;
    }

    boolean estaCercaSemaforo = false;
    // Un margen para considerar que el carro está "cerca" del semáforo
    // y debe empezar a considerar su estado.
    // Podría ser un poco antes del punto de parada y hasta un poco después de él.
    int margenSemaforo = 10; // Por ejemplo, 10 píxeles antes y después del punto de parada

    if (direccion == Direccion.HORIZONTAL) {
        if (sentido == Sentido.POSITIVO) { // Carril D (izquierda a derecha)
            estaCercaSemaforo = (frente >= puntoParada - margenSemaforo && x < puntoParada + tamanoCarro + margenSemaforo);
        } else { // Carril C (derecha a izquierda)
            estaCercaSemaforo = (frente <= puntoParada + margenSemaforo && x > puntoParada - tamanoCarro - margenSemaforo);
        }
    } else { // Direccion.VERTICAL
        if (sentido == Sentido.POSITIVO) { // Carril A (arriba a abajo)
            estaCercaSemaforo = (frente >= puntoParada - margenSemaforo && y < puntoParada + tamanoCarro + margenSemaforo);
        } else { // Carril B (abajo a arriba)
            estaCercaSemaforo = (frente <= puntoParada + margenSemaforo && y > puntoParada - tamanoCarro - margenSemaforo);
        }
    }

    // Lógica para decidir si el carro se mueve
    // 1. Si el semáforo está en verde, ¡adelante!
    // 2. Si ya pasó el semáforo (haPasadoSemaforo es true), también ¡adelante!
    // 3. Si no está cerca del semáforo (ni ha llegado ni lo ha pasado completamente), también ¡adelante!
    // El carro solo para si NO está en verde Y NO ha pasado el semáforo Y SÍ está cerca del semáforo.
    boolean debeMover = false;

    if (semaforoEnVerde) {
        debeMover = true;
        // Si el semáforo está en verde y el carro está en la zona de parada,
        // marcamos que ya lo ha pasado para que siga sin problemas
        // incluso si el semáforo cambia a rojo después de que lo cruzó.
        if (estaCercaSemaforo) { // Podría ser solo (frente > puntoParada) para ser más preciso.
            haPasadoSemaforo = true;
        }
    } else { // Semáforo en rojo
        if (haPasadoSemaforo) {
            // Si el semáforo está en rojo, pero el carro ya lo había pasado, ¡sigue!
            debeMover = true;
        } else {
            // Si el semáforo está en rojo y NO ha pasado el semáforo
            // (es decir, está antes o justo en el punto de parada), entonces debe parar.
            // Para moverse, debe no estar cerca del semáforo (es decir, aún no ha llegado).
            if (!estaCercaSemaforo) {
                 debeMover = true;
            }
        }
    }

    if (debeMover) {
        if (direccion == Direccion.HORIZONTAL) {
            x += (sentido == Sentido.POSITIVO) ? 1 : -1;
        } else { // Direccion.VERTICAL
            y += (sentido == Sentido.POSITIVO) ? 1 : -1;
        }
    }

    // Aquí reseteamos haPasadoSemaforo cuando el carro sale de la vista,
    // para que la próxima vez que aparezca, se comporte como si no hubiera pasado el semáforo aún.
    // Esto ya lo tenías y es correcto.
    // La lógica de reiniciarSiSale() ya se encarga de esto.
}



    public void reiniciarSiSale(int ancho, int alto) {
        if (direccion == Direccion.HORIZONTAL) {
            if (x > ancho || x + imagen.getIconWidth() < 0) {
                x = (sentido == Sentido.POSITIVO) ? -imagen.getIconWidth() : ancho;
                haPasadoSemaforo = false;
            }
        } else {
            if (y > alto || y + imagen.getIconHeight() < 0) {
                y = (sentido == Sentido.POSITIVO) ? -imagen.getIconHeight() : alto;
                haPasadoSemaforo = false;
            }
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getImagen() { return imagen; }
    public String getCarril() { return carril; }
}
