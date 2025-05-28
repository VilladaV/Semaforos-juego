/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

// * @author : Jhoan Andres Villada - Juan Esteban Giraldo Betancur - Isabela Quintero Murillo
// * @since 27/05/25
// * @version 1.0.0

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

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
    private int velocidadBase;
    private int velocidadActual;
    private Random rand;

    public Carro(int x, int y, Direccion direccion, Sentido sentido, 
                ImageIcon imagen, int puntoParada, String carril, int nivelDificultad) {
        this.x = x;
        this.y = y;
        this.direccion = direccion;
        this.sentido = sentido;
        this.imagen = imagen;
        this.puntoParada = puntoParada;
        this.haPasadoSemaforo = false;
        this.carril = carril;
        this.rand = new Random();
        this.velocidadBase = determinarVelocidadBase(nivelDificultad);
        this.velocidadActual = calcularVelocidadActual();
    }
    
    private int determinarVelocidadBase(int nivelDificultad) {
        switch(nivelDificultad) {
            case 1: return 1;  // Fácil
            case 2: return 2;  // Medio
            case 3: return 3;  // Difícil
            default: return 1;
        }
    }

    private int calcularVelocidadActual() {
        // Variación aleatoria de ±1 respecto a la velocidad base
        return Math.max(1, velocidadBase + rand.nextInt(3) - 1);
    }

    public void mover(boolean semaforoEnVerde, List<Carro> todosLosCarros) {
    // 1. Verificar si hay carro delante
    boolean carroDelante = false;
        int distanciaMinima = 50;
        
        for(Carro otro : todosLosCarros) {
            if(otro != this && mismoCarril(otro) && estaAdelante(otro)) {
                int distancia = calcularDistancia(otro);
                if(distancia < distanciaMinima) {
                    carroDelante = true;
                    break;
                }
            }
        }
        
        
    
    // 2. Solo mover si no hay carro delante cerca
            if(!carroDelante) {
            boolean debeMover = false;
            
            if(semaforoEnVerde) {
                debeMover = true;
                if(estaEnSemaforo()) {
                    haPasadoSemaforo = true;
                }
            } else {
                if(haPasadoSemaforo) {
                    debeMover = true;
                } else {
                    debeMover = !estaEnSemaforo();
                }
            }
            
            if(debeMover) {
                if(direccion == Direccion.HORIZONTAL) {
                    x += (sentido == Sentido.POSITIVO) ? velocidadActual : -velocidadActual;
                } else {
                    y += (sentido == Sentido.POSITIVO) ? velocidadActual : -velocidadActual;
                }
            }
        }
        
        // Pequeña probabilidad de cambiar la velocidad aleatoriamente
        if(rand.nextInt(100) == 0) {
            velocidadActual = calcularVelocidadActual();
        }
    }
    
    private boolean mismoCarril(Carro otro) {
    return this.carril.equals(otro.getCarril());
}

private boolean estaAdelante(Carro otro) {
    if(direccion == Direccion.HORIZONTAL) {
        if(sentido == Sentido.POSITIVO) {
            return otro.getX() > this.x;
        } else {
            return otro.getX() < this.x;
        }
    } else {
        if(sentido == Sentido.POSITIVO) {
            return otro.getY() > this.y;
        } else {
            return otro.getY() < this.y;
        }
    }
}

private boolean estaEnSemaforo() {
    if(direccion == Direccion.HORIZONTAL) {
        if(sentido == Sentido.POSITIVO) {
            return x + imagen.getIconWidth() >= puntoParada - 10 && x <= puntoParada + 10;
        } else {
            return x <= puntoParada + 10 && x >= puntoParada - 10;
        }
    } else {
        if(sentido == Sentido.POSITIVO) {
            return y + imagen.getIconHeight() >= puntoParada - 10 && y <= puntoParada + 10;
        } else {
            return y <= puntoParada + 10 && y >= puntoParada - 10;
        }
    }
}
    
//private boolean mismoCarrilYDireccion(Carro otro) {
//    return this.carril.equals(otro.getCarril()) && 
//           this.direccion == otro.direccion && 
//           this.sentido == otro.sentido;
//}
//
//private int calcularDistanciaCon(Carro otro) {
//    if(direccion == Direccion.HORIZONTAL) {
//        if(sentido == Sentido.POSITIVO) {
//            return otro.getX() - (this.x + this.imagen.getIconWidth());
//        } else {
//            return this.x - (otro.getX() + otro.getImagen().getIconWidth());
//        }
//    } else {
//        if(sentido == Sentido.POSITIVO) {
//            return otro.getY() - (this.y + this.imagen.getIconHeight());
//        } else {
//            return this.y - (otro.getY() + otro.getImagen().getIconHeight());
//        }
//    }
//}

    private int calcularDistancia(Carro otro) {
        if(direccion == Direccion.HORIZONTAL) {
            if(sentido == Sentido.POSITIVO && otro.getX() > this.x) {
                return otro.getX() - (this.x + this.imagen.getIconWidth());
            } else if(sentido == Sentido.NEGATIVO && otro.getX() < this.x) {
                return (this.x) - (otro.getX() + otro.getImagen().getIconWidth());
            }
        } else {
            if(sentido == Sentido.POSITIVO && otro.getY() > this.y) {
                return otro.getY() - (this.y + this.imagen.getIconHeight());
            } else if(sentido == Sentido.NEGATIVO && otro.getY() < this.y) {
                return (this.y) - (otro.getY() + otro.getImagen().getIconHeight());
            }
        }
        return Integer.MAX_VALUE;
    }

//    private boolean determinarMovimiento(boolean semaforoEnVerde) {
//        int frente = (direccion == Direccion.HORIZONTAL) ? 
//            (sentido == Sentido.POSITIVO ? x + imagen.getIconWidth() : x) :
//            (sentido == Sentido.POSITIVO ? y + imagen.getIconHeight() : y);
//
//        boolean estaCercaSemaforo = (direccion == Direccion.HORIZONTAL) ?
//            (sentido == Sentido.POSITIVO ? frente >= puntoParada - 10 && x < puntoParada + imagen.getIconWidth() + 10 :
//             frente <= puntoParada + 10 && x > puntoParada - imagen.getIconWidth() - 10) :
//            (sentido == Sentido.POSITIVO ? frente >= puntoParada - 10 && y < puntoParada + imagen.getIconHeight() + 10 :
//             frente <= puntoParada + 10 && y > puntoParada - imagen.getIconHeight() - 10);
//
//        if(semaforoEnVerde) {
//            if(estaCercaSemaforo) {
//                haPasadoSemaforo = true;
//            }
//            return true;
//        } else {
//            if(haPasadoSemaforo) {
//                return true;
//            } else {
//                return !estaCercaSemaforo;
//            }
//        }
//    }

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
    public Direccion getDireccion() {
    return direccion;
}

    public int getX() { return x; }
    public int getY() { return y; }
    public ImageIcon getImagen() { return imagen; }
    public String getCarril() { return carril; }
}