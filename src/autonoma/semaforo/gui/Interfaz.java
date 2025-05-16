/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.semaforo.gui;

/**
 *
 * @author PABLO VI
 */
import java.awt.*;
import javax.swing.*;

public class Interfaz extends JPanel  {

    private String escenario = "Crucero.png";
    ImageIcon image_icon1;

    private String carrox1="carrox1.png";
    ImageIcon image_icon2;
    private String carrox2="carrox2.png";
    ImageIcon image_icon3;
    private String carroxi1="carroxi1.png";
    ImageIcon image_icon4;
    private String carroxi2="carroxi2.png";
    ImageIcon image_icon5;
    private String carroy1="carroy1.png";
    ImageIcon image_icon6;
    private String carroy2="carroy2.png";
    ImageIcon image_icon7;
    private String carroya1="carroya1.png";
    ImageIcon image_icon8;
    private String carroya2="carroya2.png";
    ImageIcon image_icon9;

    public int x1,x2,xi1,xi2;
    public int y1,y2,ya1,ya2;
    public int A;


public Interfaz() {

 setFocusable(true);
 setBackground(Color.BLACK);
 setDoubleBuffered(true);

 image_icon1 = new ImageIcon(this.getClass().getResource(escenario));
 image_icon2 = new ImageIcon(this.getClass().getResource(carrox1));
 image_icon3 = new ImageIcon(this.getClass().getResource(carrox2));
 image_icon4 = new ImageIcon(this.getClass().getResource(carroxi1));
 image_icon5 = new ImageIcon(this.getClass().getResource(carroxi2));
 image_icon6 = new ImageIcon(this.getClass().getResource(carroy1));
 image_icon7 = new ImageIcon(this.getClass().getResource(carroy2));
 image_icon8 = new ImageIcon(this.getClass().getResource(carroya1));
 image_icon9 = new ImageIcon(this.getClass().getResource(carroya2));

  x1=0;
  x2=50;
  xi1=400;
  xi2=350;
  y1=0;
  y2=-50;
  ya1=250;
  ya2=300;

} 
 
public void paint(Graphics g) {
 super.paint(g);
  Graphics2D g2d = (Graphics2D)g;

A=1;

   if(A==1) {
   	try {
     		g2d.drawImage(image_icon1.getImage(), 0, 0, this);    
     		g2d.drawImage(image_icon2.getImage(), x1++,164 , this);   
     		g2d.drawImage(image_icon3.getImage(), x2++,150 , this);  
     		g2d.drawImage(image_icon4.getImage(), xi1--,124 , this);   
     		g2d.drawImage(image_icon5.getImage(), xi2--,137 , this);
     		g2d.drawImage(image_icon6.getImage(), 173,77 , this);
     		g2d.drawImage(image_icon7.getImage(), 187,77 , this);   
     		g2d.drawImage(image_icon8.getImage(), 205,197 , this);   
    		g2d.drawImage(image_icon9.getImage(), 220,197 , this); 
	repaint();		
	Thread.sleep(5);
        } catch(InterruptedException e){ }


   } else {
		
	try {	    
 		g2d.drawImage(image_icon1.getImage(), 0, 0, this); 
 		g2d.drawImage(image_icon2.getImage(), 123,164 , this);   
    		g2d.drawImage(image_icon3.getImage(), 123,150 , this);  
  		g2d.drawImage(image_icon4.getImage(), 255,124 , this);   
    		g2d.drawImage(image_icon5.getImage(), 255,137 , this);
		g2d.drawImage(image_icon6.getImage(), 173,y1++ , this);   
    		g2d.drawImage(image_icon7.getImage(), 187,y2++ , this);   
    		g2d.drawImage(image_icon8.getImage(), 205,ya1-- , this);   
    		g2d.drawImage(image_icon9.getImage(), 220,ya2-- , this);  

	repaint();
	Thread.sleep(5);
	} catch(InterruptedException e){}  

	}//Fin del else
A=0;
repaint();
Toolkit.getDefaultToolkit().sync();
g.dispose();

}//Fin del paint

} //Fin de la clase Interfaz