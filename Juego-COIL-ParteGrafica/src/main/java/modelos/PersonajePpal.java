package modelos;

/**
 *
 * @author Brahiam
 */
public class PersonajePpal {
    private boolean juego1Terminado = false;
    private boolean juego2Terminado = false;
    private boolean juego3Terminado = false;
    
    public boolean terminado(){
        if(juego1Terminado && juego2Terminado && juego3Terminado){
            return true;
        }else{
            return false;
        }
    }
}
