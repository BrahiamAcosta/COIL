package vista;

import modelos.PersonajePpal;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpriteAnimation extends JPanel implements KeyListener {
    private BufferedImage spriteSheet;
    private BufferedImage fondo;
    private BufferedImage dialogo1;
    private int spriteIndex = 0;
    private int x = 220;
    private int y = 430;
    private int speed = 10;
    private int spriteWidth = 64;
    private int spriteHeight = 64;
    private int screenWidth = 900;
    private int screenHeight = 600;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    PersonajePpal personaje = new PersonajePpal();
    public SpriteAnimation() {
        
        try {
            if(personaje.terminado()){
                fondo = ImageIO.read(new File("src\\main\\java\\img\\mapaFinal.png"));
            }else{
                fondo = ImageIO.read(new File("src\\main\\java\\img\\mapaInicial.png"));
            }
            dialogo1 = ImageIO.read(new File("src\\main\\java\\img\\dialogoInicial.png"));
            spriteSheet = ImageIO.read(new File("src\\main\\java\\img\\soldier_altcolor.png"));
        } catch (IOException e) {
            System.out.println("Error al cargar la hoja de sprites: " + e.getMessage());
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Sprite Animation");
        frame.add(this);
        frame.setSize(screenWidth, screenHeight);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setFocusable(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(fondo,0,0,null);
        switch(personaje.posicionDialogo(x, y)){
            case 1 -> g.drawImage(dialogo1, 400,380,null);
            case 2 -> g.drawImage(dialogo1, 140, 70, null);
            case 3 -> g.drawImage(dialogo1,650,70,null);
            case 4 -> g.drawImage(dialogo1,400,280,null);
        }
        
        if(movingUp){
            Image sprite = spriteSheet.getSubimage(spriteIndex * spriteWidth, 0, spriteWidth, spriteHeight);
            g.drawImage(sprite, x, y, null);
        }
        if(movingDown){
            Image sprite = spriteSheet.getSubimage(spriteIndex * spriteWidth, 2*spriteHeight, spriteWidth, spriteHeight);
            g.drawImage(sprite, x, y, null);
        }
        if(movingLeft){
            Image sprite = spriteSheet.getSubimage(spriteIndex * spriteWidth, 1*spriteHeight, spriteWidth, spriteHeight);
            g.drawImage(sprite, x, y, null);
        }
        if(movingRight){
            Image sprite = spriteSheet.getSubimage(spriteIndex * spriteWidth, 3*spriteHeight, spriteWidth, spriteHeight);
            g.drawImage(sprite, x, y, null);
        }
        if((!movingUp)&(!movingDown)&(!movingLeft)&(!movingRight)){
            Image sprite = spriteSheet.getSubimage(0, 64*2, spriteWidth, spriteHeight);
            g.drawImage(sprite,x,y,null);
        }
    }
    

    public void update() {
        if(x<120){
            x = 120;
        }
        if(x>730){
            x = 730;
        }
        if(y<55){
            y = 55;
        }
        if(y>450){
            y = 450;
        }
        if (movingUp && (personaje.posibleMovArr(x, y))) {
            y -= speed;
            animate();
        }
        if (movingDown && (personaje.posibleMovAba(x, y))) {
            y += speed;
            animate();
        }
        if (movingLeft && (personaje.posibleMovIzq(x, y))) {
            x -= speed;
            animate();
        }
        if (movingRight && (personaje.posibleMovDer(x, y))) {
            x += speed;
            animate();
        }
        repaint();
    }
    
    private void animate() {
        spriteIndex++;
        if (spriteIndex >= 8) {
            spriteIndex = 0;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
        System.out.println("x: " +x+", y: "+y);
        if (e.getKeyCode() == KeyEvent.VK_W) {
            movingUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            movingDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            movingLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            movingRight = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            movingUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            movingDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            movingLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            movingRight = false;
        }
    }
}