package Principal;
import ParteGrafica.vista.SpriteAnimation;

/**
 *
 * @author Brahiam
 */
public class InicioJuego {
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
