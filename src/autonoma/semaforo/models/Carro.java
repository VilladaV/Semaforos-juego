/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.util.List;

public class Carro {
    public enum Direccion { HORIZONTAL, VERTICAL }
    public enum Sentido { POSITIVO, NEGATIVO }

    private int x, y;
    private final Direccion direccion;
    private final Sentido sentido;
    private final ImageIcon imagen;
    private final int puntoParada;
    private boolean haPasadoSemaforo;
    private final String carril;

    public Carro(int x, int y, Direccion direccion, Sentido sentido, 
                ImageIcon imagen, int puntoParada, String carril) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.sentido = sentido;
        this.imagen = imagen;
        this.puntoParada = puntoParada;
        this.haPasadoSemaforo = false;
        this.carril = carril;
    }

    public void mover(boolean semaforoEnVerde, List<Carro> todosLosCarros) {
    // 1. Verificar si hay carro delante muy cerca (para evitar colisiones)
    boolean carroDelanteCerca = false;
    int distanciaSeguridad = 30; // Pixeles de separación mínima
    
    for(Carro otro : todosLosCarros) {
        if(otro != this && mismoCarrilYDireccion(otro)) {
            int distancia = calcularDistanciaCon(otro);
            
            if(distancia < distanciaSeguridad && distancia > 0) {
                carroDelanteCerca = true;
                break;
            }
        }
    }
    
    // 2. Solo mover si no hay carro muy cerca delante
    if(!carroDelanteCerca) {
        boolean debeMover = determinarMovimiento(semaforoEnVerde);
        if(debeMover) {
            if(direccion == Direccion.HORIZONTAL) {
                x += (sentido == Sentido.POSITIVO) ? 1 : -1;
            } else {
                y += (sentido == Sentido.POSITIVO) ? 1 : -1;
            }
        }
    }
}
    
    private boolean mismoCarrilYDireccion(Carro otro) {
    return this.carril.equals(otro.getCarril()) && 
           this.direccion == otro.direccion && 
           this.sentido == otro.sentido;
}

private int calcularDistanciaCon(Carro otro) {
    if(direccion == Direccion.HORIZONTAL) {
        if(sentido == Sentido.POSITIVO) {
            return otro.getX() - (this.x + this.imagen.getIconWidth());
        } else {
            return this.x - (otro.getX() + otro.getImagen().getIconWidth());
        }
    } else {
        if(sentido == Sentido.POSITIVO) {
            return otro.getY() - (this.y + this.imagen.getIconHeight());
        } else {
            return this.y - (otro.getY() + otro.getImagen().getIconHeight());
        }
    }
}

//    private int calcularDistancia(Carro otro) {
//        if(direccion == Direccion.HORIZONTAL) {
//            if(sentido == Sentido.POSITIVO && otro.getX() > this.x) {
//                return otro.getX() - (this.x + this.imagen.getIconWidth());
//            } else if(sentido == Sentido.NEGATIVO && otro.getX() < this.x) {
//                return (this.x) - (otro.getX() + otro.getImagen().getIconWidth());
//            }
//        } else {
//            if(sentido == Sentido.POSITIVO && otro.getY() > this.y) {
//                return otro.getY() - (this.y + this.imagen.getIconHeight());
//            } else if(sentido == Sentido.NEGATIVO && otro.getY() < this.y) {
//                return (this.y) - (otro.getY() + otro.getImagen().getIconHeight());
//            }
//        }
//        return Integer.MAX_VALUE;
//    }

    private boolean determinarMovimiento(boolean semaforoEnVerde) {
        int frente = (direccion == Direccion.HORIZONTAL) ? 
            (sentido == Sentido.POSITIVO ? x + imagen.getIconWidth() : x) :
            (sentido == Sentido.POSITIVO ? y + imagen.getIconHeight() : y);

        boolean estaCercaSemaforo = (direccion == Direccion.HORIZONTAL) ?
            (sentido == Sentido.POSITIVO ? frente >= puntoParada - 10 && x < puntoParada + imagen.getIconWidth() + 10 :
             frente <= puntoParada + 10 && x > puntoParada - imagen.getIconWidth() - 10) :
            (sentido == Sentido.POSITIVO ? frente >= puntoParada - 10 && y < puntoParada + imagen.getIconHeight() + 10 :
             frente <= puntoParada + 10 && y > puntoParada - imagen.getIconHeight() - 10);

        if(semaforoEnVerde) {
            if(estaCercaSemaforo) {
                haPasadoSemaforo = true;
            }
            return true;
        } else {
            if(haPasadoSemaforo) {
                return true;
            } else {
                return !estaCercaSemaforo;
            }
        }
    }

    public boolean estaDetenido() {
        int frente = (direccion == Direccion.HORIZONTAL) ? 
            (sentido == Sentido.POSITIVO ? x + imagen.getIconWidth() : x) :
            (sentido == Sentido.POSITIVO ? y + imagen.getIconHeight() : y);

        return !haPasadoSemaforo && 
               ((direccion == Direccion.HORIZONTAL && 
                 ((sentido == Sentido.POSITIVO && frente >= puntoParada - 5) ||
                  (sentido == Sentido.NEGATIVO && frente <= puntoParada + 5))) ||
               (direccion == Direccion.VERTICAL && 
                 ((sentido == Sentido.POSITIVO && frente >= puntoParada - 5) ||
                  (sentido == Sentido.NEGATIVO && frente <= puntoParada + 5))));
    }

    public void reiniciarSiSale(int ancho, int alto) {
        if(direccion == Direccion.HORIZONTAL) {
            if(x > ancho || x + imagen.getIconWidth() < 0) {
                x = (sentido == Sentido.POSITIVO) ? -imagen.getIconWidth() : ancho;
                haPasadoSemaforo = false;
            }
        } else {
            if(y > alto || y + imagen.getIconHeight() < 0) {
                y = (sentido == Sentido.POSITIVO) ? -imagen.getIconHeight() : alto;
                haPasadoSemaforo = false;
            }
        } 
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, imagen.getIconWidth(), imagen.getIconHeight());
    }

    public boolean colisionaCon(Carro otro) {
        return this.getBounds().intersects(otro.getBounds());
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getImagen() { return imagen; }
    public String getCarril() { return carril; }
}