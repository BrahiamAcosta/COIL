package vista;

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
    private int spriteIndex = 0;
    private int x = 0;
    private int y = 0;
    private int speed = 10;
    private int spriteWidth = 64;
    private int spriteHeight = 64;
    private int screenWidth = 900;
    private int screenHeight = 600;
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public SpriteAnimation() {
        try {
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
        if (movingUp) {
            y -= speed;
            animate();
        }
        if (movingDown) {
            y += speed;
            animate();
        }
        if (movingLeft) {
            x -= speed;
            animate();
        }
        if (movingRight) {
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