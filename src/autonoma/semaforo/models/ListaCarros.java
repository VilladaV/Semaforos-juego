/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.models;

/**
 *
 * @author PABLO VI
 */
import java.util.*;

public class ListaCarros extends Thread {

	public int CarrilA,CarrilB,CarrilC,CarrilD;
	public boolean SemAB,SemCD;
	public Random Rand = new Random();
   
	public void run() {
   
	int a,b,c,d;
	boolean iniciar = true;
	
	try {

		Sistema CtrlSem = new Sistema();
		CtrlSem.start();

		while(true) {

			if(iniciar == true) {

				a = ( ( Rand.nextInt(10000) % 13) + 1);
				CarrilA = CarrilA + a;
				if (CarrilA < 0) { CarrilA = 0; }
				b = ( ( Rand.nextInt(10000) % 13) + 1);
				CarrilB = CarrilB + b;
				if (CarrilB < 0) { CarrilB = 0; }
				c = ( ( Rand.nextInt(10000) % 10) + 1);
				CarrilC = CarrilC + c;
				if (CarrilC < 0) { CarrilC = 0; }
				d = ( ( Rand.nextInt(10000) % 10) + 1);
				CarrilD = CarrilD + d;
				if (CarrilD < 0) { CarrilD = 0; }
			}
			else {
			
				if (SemAB == true) {

            	   		    CarrilA=CarrilA-3;
				    CarrilB=CarrilB-2;
				if (CarrilA < 0) { CarrilA = 0; }
				if (CarrilB < 0) { CarrilB = 0; }
				
            	    	            CarrilC=CarrilC+5;
			            CarrilD=CarrilD+5;
				
				} else {

            	    	            CarrilC=CarrilC-2;
				    CarrilD=CarrilD-2;
				
                                    if (CarrilC < 0) { CarrilC = 0; }
				    if (CarrilD < 0) { CarrilD = 0; }

					
            	    	            CarrilA=CarrilA+3;
				    CarrilB=CarrilB+3;		
				}
			}
		
		iniciar = false;
		
		CtrlSem.setCarros(CarrilA,CarrilB,CarrilC,CarrilD);
		CarrilA = 0;
		CarrilB = 0;
		CarrilC = 0;
		CarrilD = 0;

		Thread.sleep(5000);

	
	} //Fin del wile
	
	} catch(InterruptedException e){ 
		System.out.println("Excepcion: " + e.getMessage());
	}

    } //fin del metodo run()

    public void setEstado(boolean SAB,boolean SCD) {
		SemAB = SAB;
		SemCD = SCD;
    }
   
} //Fin de la clase ListaCarros
