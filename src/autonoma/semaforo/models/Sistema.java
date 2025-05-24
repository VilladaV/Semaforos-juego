///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package autonoma.semaforo.models;
//
///**
// *
// * @author PABLO VI
// */
//public class Sistema extends Thread {
//
//	public boolean SemAB,SemCD; //Variables que controlan el estado booleano de los Semaforos AB y CD
//	public boolean CA,CB,CC,CD; //Variables que controlan el Congestionamiento en cada carril.
//	public int CarrilA,CarrilB,CarrilC,CarrilD; //Num. de carros en cada carril (obtenidas de ListaCarros)
//	public String EstadoAB = "";
//	public String EstadoCD = "";
//
//        @Override
//    public void run() {
//
//	try { 
//
//            ListaCarros LisCarr = new ListaCarros();
//
//		while(true) {
//			
//		    if (CarrilA > 10) { CA = true; }
//
//		    if (CarrilB > 10) { CB = true; }
//
//		    if (CarrilC > 5 ) { CC = true; }
//
//		    if (CarrilD > 5 ) { CD = true; }
//						
//		    SemAB = (CA | CB | !CD ) & (CA | !CC | !CD ) & (CA | CB | !CC) & (CB | !CC | !CD);
//		    SemCD = !SemAB;
//				
//		LisCarr.setEstado(SemAB,SemCD);
//				
//		if (SemAB == true) {
//			EstadoAB = "Verde";
//			EstadoCD = "Rojo";
//
//		} else {
//			EstadoAB = "Rojo";
//			EstadoCD = "Verde";
//		}	
//				
//		for(int r=0;r<20;r++){System.out.println("\n");}
//		System.out.println("\t\t----------------------------------");
//		System.out.println("\t\t~Sistema de Control de Semaforos~");				
//		System.out.println("\t\t----------------------------------");
//		System.out.println("\n\n\t\t~Congestionamiento~");
//		System.out.println("\n\tHay "+CarrilA+" carros en A");
//		System.out.println("\n\tHay "+CarrilB+" carros en B");
//		System.out.println("\n\tHay "+CarrilC+" carros en C");
//		System.out.println("\n\tHay "+CarrilD+" carros en D");
//			
//		System.out.println("\n\n\t\t~Estado Semaforos~");
//		System.out.println("\n\tA y B -> "+EstadoAB );
//		System.out.println("\n\tC y D -> "+EstadoCD );
//	
//	Thread.sleep(5000);
//	} //Fin del while
//
//        } catch(InterruptedException e) {
//		System.out.println("Excepcion: " + e.getMessage());
//	}
//		
//} //Termina el run()
//
//public void setCarros(int CaA, int CaB, int CaC, int CaD) {
//	CarrilA = CaA;
//	CarrilB = CaB;
//	CarrilC = CaC;
//	CarrilD = CaD;
//}
//
//}//Termina la clase Sistema
