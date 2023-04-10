package principal;
import vista.SpriteAnimation;

/**
 *
 * @author Brahiam
 */
public class Principal {
    public static void main(String[] args) {
        SpriteAnimation juego = new SpriteAnimation();
        while (true) {
            juego.update();
            try {
                Thread.sleep(90);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
