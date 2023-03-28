package juego;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;
import control.Teclado;

/**
 *
 * @author Brahiam
 */
class Juego extends Canvas implements Runnable{
    private static final int WIDTH = 900;
    private static final int HEIGHT = 450;

    private static JFrame ventana;
    private static Thread thread;
    private static volatile boolean activo = false;
    
    
    private static Teclado teclado;
    
    
    public Juego(){
        teclado = new Teclado();
        addKeyListener(teclado);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        ventana = new JFrame();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    public void iniciar(){
        activo = true;
        thread = new Thread(this,"graficos");
        thread.start();
    }
    
    public void parar(){
        activo = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public void actualizar(){
        teclado.actualizar();
        if(teclado.arriba){
            System.out.println("arriba");
        }
        if(teclado.abajo){
            System.out.println("abajo");
        }
        if(teclado.izquierda){
            System.out.println("izquierda");
        }
        if(teclado.derecha){
            System.out.println("derecha");
        }
    }
    
    public void mostrar(){
        
    }

    //controla acciones en segundo plano
    public void run() {
        //para controlar el ritmo de las actualizaciones se currenttimemillis
        int milisASeg=1000;
        int actPorSeg = 60; 
        double milisRequeridos = milisASeg/actPorSeg;
        
        long refActualizacion = System.currentTimeMillis();
        double tiempoTranscurrido;
        double delta = 0;//cantidad de tiempo transcurrido para que se realize una act
        
        requestFocus();
        
        while(activo){
            long inicioBucle = System.currentTimeMillis();
            tiempoTranscurrido = inicioBucle-refActualizacion;
            refActualizacion = inicioBucle;
            
            delta+=tiempoTranscurrido/milisRequeridos;
            
            while(delta >=1){
                actualizar();
                delta--;
            }
            mostrar();
        }
    }
    
}
